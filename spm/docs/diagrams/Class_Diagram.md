# Class / Module Diagram

```text
User
Supplier
Product
PurchaseOrder
OrderItem
Delivery
SupplierRating
Feedback

SupplierController
    |
SupplierService
    |
SupplierRepository
    |
Supplier

RatingController
    |
RatingService
    |
RatingCalculationService
    |
SupplierRatingRepository

PurchaseOrderController
    |
PurchaseOrderService
    |
PurchaseOrderRepository

DeliveryController
    |
DeliveryService
    |
DeliveryRepository
```

Core relationships:
- Supplier 1..* PurchaseOrder
- PurchaseOrder 1..* OrderItem
- Product 1..* OrderItem
- PurchaseOrder 1..* Delivery
- Supplier 1..* SupplierRating
- Supplier 1..* Feedback
- User 1..* Feedback
