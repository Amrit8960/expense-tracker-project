# 💰 Java Expense Tracker (OOP + JDBC + GUI)

## Overview
This project is a Java-based Expense Tracker demonstrating full Object-Oriented Programming (OOP) and database integration.

### 🧠 OOP Concepts Used
- **Encapsulation**: Private fields with getters/setters.
- **Abstraction**: Service layer hides database logic.
- **Inheritance**: Common abstract class for entities.
- **Polymorphism**: Interface implementation for service layer.

## 🧩 Technologies
- Java (Core + Swing)
- MySQL
- JDBC

## ⚙️ Setup
1. Create database using:
   ```sql
   CREATE DATABASE expense_tracker;
   USE expense_tracker;
   CREATE TABLE expenses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(100), amount DOUBLE, category VARCHAR(50), date DATE);
