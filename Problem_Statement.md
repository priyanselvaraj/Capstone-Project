# Problem Statement

## 1. Title
Supplier Performance Rating System

## 2. Domain
Supply Chain / Procurement Management

## 3. Who is the user? (2-3 user types, with roles)
- **Procurement Manager** — onboards suppliers, creates purchase orders, logs
  deliveries and quality issues, reviews supplier scorecards, triggers score
  recalculation.
- **Supplier** — views their own performance dashboard, sees flagged issues
  and the reasons behind their current rating.

## 4. What problem are we solving? (3-5 sentences, real-life example)
Procurement teams at small and mid-sized manufacturing or trading businesses
track supplier reliability using scattered spreadsheets or plain gut feeling.
There is no simple, standalone tool to score suppliers objectively on
on-time delivery, quality, and pricing consistency. For example, a
manufacturer working with 40 active suppliers has no early warning when a
supplier's on-time delivery rate quietly drops from 95% to 60% over three
months — the problem only surfaces after a production delay has already
happened. This system gives procurement teams a live, weighted scorecard per
supplier and flags underperformers before they cause real damage.

## 5. Proposed Solution (what the application will do, feature-wise)
- Supplier onboarding and profile management
- Purchase order creation and tracking
- Delivery logging (on-time vs. late, quantity received vs. ordered)
- Quality issue logging with severity levels (Low / Medium / High)
- Automated weighted scoring engine (on-time %, quality, price variance)
- Tier classification (A / B / C) per supplier, recalculated periodically
- Supplier-facing dashboard to view their own scorecard and issue history
- Email/SMS alert to a supplier when their tier drops
- Manager dashboard with sortable/filterable supplier rankings

## 6. Core Entities / Database Tables (list all, minimum 5)
1. **User** — login credentials, role (MANAGER / SUPPLIER), linked supplier (if applicable)
2. **Supplier** — company profile, category, contact info, onboarding date
3. **PurchaseOrder** — order raised against a supplier, expected delivery date, value
4. **Delivery** — actual delivery logged against a purchase order
5. **QualityIssue** — defect/issue logged against a purchase order
6. **RatingScore** — computed periodic score and tier per supplier

## 7. User Roles & Permissions (minimum 2 distinct roles, e.g. Admin & User)
- **Procurement Manager**: full CRUD on Supplier, PurchaseOrder, Delivery,
  and QualityIssue; can trigger score recalculation; can view every
  supplier's scorecard.
- **Supplier**: read-only access limited to their own PurchaseOrder,
  Delivery, QualityIssue, and RatingScore records — cannot view or modify
  other suppliers' data.

## 8. Success Criteria (e.g. 'a user should be able to book an appointment in under 1 minute')
- A manager should be able to log a new delivery and see the supplier's
  updated score reflect that change within seconds.
- A supplier should be able to view their current tier and understand the
  reason for it (e.g., "2 late deliveries this quarter") in under 1 minute
  of logging in.

## 9. Out of Scope (clearly list what you will NOT build, to avoid over-commitment)
- Payment processing / invoicing
- Multi-currency support
- Contract management or e-signatures
- Native mobile apps (web-responsive only)
- Multi-tenant support for more than one buying organization

## 10. Chosen Track: Java (Spring Boot) / Python (Django or FastAPI)
*[Select one before Day 2]*
