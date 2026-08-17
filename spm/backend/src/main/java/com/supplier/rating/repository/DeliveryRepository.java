package com.supplier.rating.repository;

import com.supplier.rating.model.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByPurchaseOrderSupplierId(Long supplierId);
}
