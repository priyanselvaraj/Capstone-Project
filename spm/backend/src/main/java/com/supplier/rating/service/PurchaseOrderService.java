package com.supplier.rating.service;

import com.supplier.rating.model.entity.PurchaseOrder;
import com.supplier.rating.model.entity.Supplier;
import com.supplier.rating.repository.PurchaseOrderRepository;
import com.supplier.rating.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class PurchaseOrderService {
    private final PurchaseOrderRepository orders;
    private final SupplierRepository suppliers;
    public PurchaseOrderService(PurchaseOrderRepository orders, SupplierRepository suppliers) { this.orders = orders; this.suppliers = suppliers; }

    public List<PurchaseOrder> findAll() { return orders.findAll(); }
    public PurchaseOrder findById(Long id) { return orders.findById(id).orElseThrow(() -> new IllegalArgumentException("Purchase order not found: " + id)); }

    @Transactional
    public PurchaseOrder create(PurchaseOrder order) {
        if (order.getOrderNumber() == null || order.getOrderNumber().isBlank()) throw new IllegalArgumentException("Order number is required");
        if (orders.existsByOrderNumberIgnoreCase(order.getOrderNumber())) throw new IllegalArgumentException("Order number already exists");
        if (order.getSupplier() == null || order.getSupplier().getId() == null) throw new IllegalArgumentException("Supplier id is required");
        Supplier supplier = suppliers.findById(order.getSupplier().getId()).orElseThrow(() -> new IllegalArgumentException("Supplier not found"));
        order.setSupplier(supplier);
        if (order.getOrderDate() == null) order.setOrderDate(LocalDate.now());
        if (order.getTotalAmount() < 0) throw new IllegalArgumentException("Total amount cannot be negative");
        if (order.getStatus() == null || order.getStatus().isBlank()) order.setStatus("PENDING");
        return orders.save(order);
    }

    @Transactional
    public PurchaseOrder update(Long id, PurchaseOrder input) {
        PurchaseOrder o = findById(id);
        if (input.getSupplier() != null && input.getSupplier().getId() != null)
            o.setSupplier(suppliers.findById(input.getSupplier().getId()).orElseThrow(() -> new IllegalArgumentException("Supplier not found")));
        if (input.getOrderDate() != null) o.setOrderDate(input.getOrderDate());
        if (input.getTotalAmount() >= 0) o.setTotalAmount(input.getTotalAmount());
        if (input.getStatus() != null && !input.getStatus().isBlank()) o.setStatus(input.getStatus());
        return orders.save(o);
    }
}
