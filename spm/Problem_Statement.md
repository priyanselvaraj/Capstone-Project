# Problem Statement

## 1. Title
Supplier Performance Rating and Management System

## 2. Domain
Procurement / Supply Chain Management

## 3. Who is the user?
1. Admin/Procurement Manager – manages suppliers, products, purchase orders, deliveries, ratings and reports.
2. Supplier – views assigned purchase orders, delivery status, performance scores and feedback.

## 4. What problem are we solving?
Organizations often evaluate suppliers using scattered records and manual calculations. This makes it difficult to compare supplier quality, delivery performance, cost and compliance consistently. The proposed system centralizes supplier information and automatically calculates performance scores using configurable weighted criteria.

## 5. Proposed Solution
The application will provide:
- Secure login with role-based access.
- Supplier and product management.
- Purchase order and order-item management.
- Delivery tracking.
- Supplier performance rating.
- Weighted overall score calculation.
- Supplier ranking and performance categories.
- Feedback and performance history.

## 6. Core Entities / Database Tables
1. users
2. suppliers
3. products
4. purchase_orders
5. order_items
6. deliveries
7. supplier_ratings
8. feedback

## 7. User Roles & Permissions
### Admin / Procurement Manager
- Manage suppliers and products.
- Create and manage purchase orders.
- Record deliveries.
- Create supplier ratings.
- View rankings and reports.
- Manage feedback.

### Supplier
- View own profile.
- View purchase orders and delivery information.
- View own ratings and feedback.

## 8. Success Criteria
- Authorized users can log in securely.
- Admin can manage all core procurement records.
- Supplier performance can be calculated automatically.
- A supplier can receive a score from 0–100 and a rating category.
- Supplier rankings can be viewed using performance scores.

## 9. Out of Scope
- Real payment processing.
- Real-time logistics/GPS tracking.
- Automatic purchase-order creation using external ERP systems.
- Production-grade predictive AI in the Day 2–10 MVP.

## 10. Chosen Track
Java (Spring Boot)
