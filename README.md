# BE-BBQRestaurantManagement
BBQRestaurantManagement is a backend management system for a BBQ restaurant, developed using Spring Boot 3 and MySQL. The system is designed to handle core restaurant operations with a robust architecture, following RESTful API best practices.

API Testing with Postman.

Key Features
Entity Management (14–15 tables): Supports operations for customers, employees, orders, bills, bookings, menu items, ingredients, inventory, and more.
Authentication & Authorization: Implements JWT-based Authentication and Role-based Authorization to ensure secure access control for different user roles (e.g., Admin, Staff).
Order & Billing: Enables order creation, bill generation, and payment tracking.
Inventory Control: Tracks ingredient stock, supplier relationships, and real-time inventory updates.
Booking System: Allows table reservations with customer and time-slot management.

Technical Stack
Backend Framework: Spring Boot 3
Database: MySQL (with JPA/Hibernate for ORM)
Security: Spring Security with JWT
Architecture: Layered design (Controller – Service – Repository) ensuring maintainability and scalability.
Tools: Maven for build management, Lombok for reducing boilerplate code.

As this is my first project, developed while learning, there are still several areas that could be improved:
Some early tables contain poorly written SQL statements, which have been fixed in later tables. (I intentionally kept the original mistakes to remind myself of the errors.)
Authentication and Authorization are still quite basic.
Relationships between some database tables are not strongly linked.
Certain naming conventions are inconsistent or not fully appropriate.
Several parts could benefit from AI-assisted optimization.
The code contains many comments for learning purposes.
