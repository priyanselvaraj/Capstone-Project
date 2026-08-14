# System Architecture

```text
+-----------------------------+
|        React Frontend       |
| Browser / Bootstrap/Tailwind|
+--------------+--------------+
               |
               | HTTPS REST/JSON
               v
+-----------------------------+
|      Spring Boot API        |
| Controller -> Service       |
|             -> Repository   |
+--------------+--------------+
               |
               | JPA/Hibernate
               v
+-----------------------------+
|        MySQL Database       |
| 8 related business tables  |
+-----------------------------+

External/optional integrations:
- Email notification service
- AI/risk prediction service for Day 42–60 enhancement

Deployment target:
- Frontend: Vercel/Netlify
- Backend: Render/Railway
- Database: Railway/Clever Cloud/Aiven
```
