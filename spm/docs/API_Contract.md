# Supplier Performance Rating System API

Base URL: `http://localhost:8080/api`

All successful responses use:
```json
{"success":true,"data":{},"message":"..."}
```

## Health
- `GET /health`

## Authentication
### Register
`POST /auth/register`
```json
{"username":"admin","password":"admin123","role":"ADMIN"}
```
### Login
`POST /auth/login`
```json
{"username":"admin","password":"admin123"}
```

## Suppliers
- `GET /suppliers`
- `GET /suppliers/{id}`
- `POST /suppliers`
- `PUT /suppliers/{id}`
- `DELETE /suppliers/{id}`

Example create:
```json
{"supplierCode":"SUP001","name":"ABC Components","email":"abc@example.com","phone":"9876543210","address":"Chennai"}
```

## Products
- `GET /products`
- `GET /products/{id}`
- `POST /products`
- `PUT /products/{id}`
- `DELETE /products/{id}`

## Purchase Orders
- `GET /orders`
- `GET /orders/{id}`
- `POST /orders`
- `PUT /orders/{id}`

Example:
```json
{"orderNumber":"PO001","supplier":{"id":1},"totalAmount":12500,"status":"PENDING"}
```

## Deliveries
- `GET /deliveries`
- `POST /deliveries`
- `PUT /deliveries/{id}`

## Ratings
- `POST /ratings`
- `GET /ratings/supplier/{supplierId}`
- `GET /ratings/ranking`

Example:
```json
{
  "supplier":{"id":1},
  "qualityScore":90,
  "deliveryScore":85,
  "costScore":80,
  "quantityAccuracyScore":95,
  "communicationScore":90,
  "complianceScore":100
}
```
The server calculates the overall score automatically.

## Feedback
- `POST /feedback`
- `GET /feedback/supplier/{supplierId}`

Example:
```json
{"supplier":{"id":1},"user":{"id":1},"comments":"Good quality and timely delivery."}
```

## Rating Formula
`quality*0.30 + delivery*0.30 + cost*0.15 + quantityAccuracy*0.10 + communication*0.10 + compliance*0.05`

Categories:
- 90–100 Excellent
- 75–89.99 Good
- 60–74.99 Average
- 40–59.99 Poor
- 0–39.99 Critical
