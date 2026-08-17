package com.supplier.rating.repository;

import com.supplier.rating.model.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    Optional<PurchaseOrder> findByOrderNumberIgnoreCase(String orderNumber);
    boolean existsByOrderNumberIgnoreCase(String orderNumber);
}
