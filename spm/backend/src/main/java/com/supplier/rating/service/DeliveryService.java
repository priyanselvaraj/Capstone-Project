package com.supplier.rating.service;

import com.supplier.rating.model.entity.Delivery;
import com.supplier.rating.model.entity.PurchaseOrder;
import com.supplier.rating.repository.DeliveryRepository;
import com.supplier.rating.repository.PurchaseOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepository deliveries;
    private final PurchaseOrderRepository orders;
    public DeliveryService(DeliveryRepository deliveries, PurchaseOrderRepository orders) { this.deliveries = deliveries; this.orders = orders; }

    public List<Delivery> findAll() { return deliveries.findAll(); }

    @Transactional
    public Delivery create(Delivery d) {
        if (d.getPurchaseOrder() == null || d.getPurchaseOrder().getId() == null)
            throw new IllegalArgumentException("Order id is required");
        PurchaseOrder order = orders.findById(d.getPurchaseOrder().getId())
                .orElseThrow(() -> new IllegalArgumentException("Purchase order not found"));
        if (d.getQuantityReceived() < 0) throw new IllegalArgumentException("Quantity cannot be negative");
        d.setPurchaseOrder(order);
        if (d.getActualDate() == null) d.setActualDate(LocalDate.now());
        if (d.getStatus() == null || d.getStatus().isBlank())
            d.setStatus(d.getExpectedDate() != null && d.getActualDate().isAfter(d.getExpectedDate()) ? "LATE" : "ON_TIME");
        return deliveries.save(d);
    }

    @Transactional
    public Delivery update(Long id, Delivery input) {
        Delivery d = deliveries.findById(id).orElseThrow(() -> new IllegalArgumentException("Delivery not found: " + id));
        if (input.getPurchaseOrder() != null && input.getPurchaseOrder().getId() != null)
            d.setPurchaseOrder(orders.findById(input.getPurchaseOrder().getId()).orElseThrow(() -> new IllegalArgumentException("Purchase order not found")));
        if (input.getExpectedDate() != null) d.setExpectedDate(input.getExpectedDate());
        if (input.getActualDate() != null) d.setActualDate(input.getActualDate());
        if (input.getQuantityReceived() >= 0) d.setQuantityReceived(input.getQuantityReceived());
        if (input.getStatus() != null && !input.getStatus().isBlank()) d.setStatus(input.getStatus());
        return deliveries.save(d);
    }
}
