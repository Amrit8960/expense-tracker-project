

* 📚 Deeper **theory explanations**
* 🧠 Stronger **OOP, JDBC, and Transaction Management insights**
* 💡 **Use-case explanations** and diagrams in Markdown
* 🧩 **Code snippets** and logical flow
* 🧮 Detailed setup instructions
* 🎨 Polished formatting and markdown visuals

---

# 💰 Expense Tracker (Java + JDBC + OOP + Swing GUI + MySQL)

> A full-featured Expense Management System built in **Core Java** using **Object-Oriented Programming**, **JDBC**, **MySQL**, and **Swing GUI**, with robust **Transaction Management** and **Collections Framework** integration.

---

## 🧠 Overview

**Expense Tracker** is a desktop-based Java application that allows users to record, organize, and analyze daily expenses efficiently.
It’s built with **scalable architecture** using Java’s **OOP principles** and **JDBC** for database communication, ensuring data consistency with **Transaction Management**.

This project demonstrates:

* ✅ Real-world **OOP implementation**
* ✅ Use of **Collections Framework**
* ✅ Safe and efficient **database operations** with `PreparedStatement`
* ✅ **Transaction control** using `commit()` and `rollback()`
* ✅ Interactive **GUI** using `Swing`

---

## 🏗️ System Architecture

```
         +------------------------+
         |     Expense Tracker     |
         +------------------------+
                    |
           +----------------+
           |   GUI Layer    |
           | (Swing JFrame) |
           +----------------+
                    |
           +----------------+
           |  Service Layer  |
           | (Business Logic)|
           +----------------+
                    |
           +----------------+
           |     DAO Layer   |
           |  (Database Ops) |
           +----------------+
                    |
           +----------------+
           |   JDBC Layer    |
           | (Connection Mgmt)|
           +----------------+
                    |
           +----------------+
           |   MySQL DB      |
           +----------------+
```

---

## 🔑 Key Features

| Feature                       | Description                                                  |
| ----------------------------- | ------------------------------------------------------------ |
| 💾 **Add Expenses**           | Input title, amount, category, and date to add new entries   |
| 📜 **View All Expenses**      | Retrieve all saved records in tabular format                 |
| 🗂️ **Group by Category**     | Uses `Map<String, List<Expense>>` to group expenses          |
| 📂 **Unique Categories**      | Uses `Set<String>` to avoid duplicate categories             |
| ✏️ **Update & Delete**        | Modify or remove existing expenses using `PreparedStatement` |
| 🔒 **Transaction Management** | Ensures data integrity using commit/rollback                 |
| 🧮 **Collections + Generics** | Simplifies handling of data using Java collections           |
| 🪟 **GUI Interface**          | Intuitive Swing-based interface for real-time interaction    |

---

## 🧱 Technologies Used

| Layer               | Technology                                        |
| ------------------- | ------------------------------------------------- |
| **Frontend (UI)**   | Java Swing (JFrame, JPanel, JTextField, JButton)  |
| **Backend Logic**   | Core Java (OOP, Collections, Generics)            |
| **Database**        | MySQL                                             |
| **Database Access** | JDBC (PreparedStatement + Transaction Management) |
| **IDE**             | IntelliJ IDEA / Eclipse                           |
| **Connector**       | MySQL Connector J (JAR file)                      |

---

## ⚙️ Database Design

### 📘 Schema

```sql
CREATE DATABASE expense_tracker;
USE expense_tracker;

CREATE TABLE expenses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    category VARCHAR(50),
    date DATE NOT NULL
);

CREATE TABLE transaction_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    message VARCHAR(200),
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 💡 ER Diagram (Text View)

```
[Expense]
  id (PK)
  title
  amount
  category
  date

[Transaction_Log]
  log_id (PK)
  message
  log_date
```

---

## 🧩 Core Java & OOP Concepts Used

| OOP Concept       | Implementation                                                |
| ----------------- | ------------------------------------------------------------- |
| **Encapsulation** | Private attributes in `Expense` class with getters & setters  |
| **Abstraction**   | Service layer hides complex DAO logic                         |
| **Inheritance**   | `Expense` extends base `DatabaseEntity`                       |
| **Polymorphism**  | `ExpenseServiceInterface` implemented by `ExpenseServiceImpl` |
| **Composition**   | DAO objects used inside service classes                       |

### Example:

```java
public class ExpenseServiceImpl implements ExpenseServiceInterface {
    private ExpenseDAO dao = new ExpenseDAO();
    ...
}
```

---

## 💾 CRUD Operations Using JDBC PreparedStatement

CRUD stands for **Create, Read, Update, Delete**.
We implemented all using **`PreparedStatement`** (safe, efficient, and reusable).

| Operation  | Method             | SQL Used                                           | Example                  |
| ---------- | ------------------ | -------------------------------------------------- | ------------------------ |
| **Create** | `addExpense()`     | `INSERT INTO expenses VALUES (?, ?, ?, ?)`         | Adds a new expense       |
| **Read**   | `getAllExpenses()` | `SELECT * FROM expenses`                           | Fetches all expense data |
| **Update** | `updateExpense()`  | `UPDATE expenses SET title=?, amount=? WHERE id=?` | Modifies a record        |
| **Delete** | `deleteExpense()`  | `DELETE FROM expenses WHERE id=?`                  | Removes a record         |

**Why PreparedStatement?**

* Prevents SQL Injection
* Uses placeholders (`?`) for safe dynamic data
* Increases query performance (compiled once)

### Example Snippet:

```java
String query = "INSERT INTO expenses (title, amount, category, date) VALUES (?, ?, ?, ?)";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setString(1, expense.getTitle());
stmt.setDouble(2, expense.getAmount());
stmt.setString(3, expense.getCategory());
stmt.setString(4, expense.getDate());
stmt.executeUpdate();
```

---

## 🔒 Transaction Management

Transactions ensure **data consistency**.
If one operation fails, all related operations are **rolled back**.

### Example Flow:

1. Insert Expense into `expenses`
2. Insert Log into `transaction_log`
3. If log insertion fails → rollback expense entry

### Transaction Code:

```java
conn.setAutoCommit(false);
expenseDAO.insertExpense(conn, expense);
transactionDAO.insertLog(conn, "Added new expense");
conn.commit(); // confirm all operations
```

### Rollback on Error:

```java
catch (SQLException e) {
    conn.rollback(); // undo all operations
}
```

**Result:**
✅ Both succeed → data saved
❌ One fails → nothing saved (safe state)

---

## 🧮 Collections & Generics

| Collection                     | Description                 | Example                                                 |
| ------------------------------ | --------------------------- | ------------------------------------------------------- |
| **List<Expense>**              | Stores all expenses         | `List<Expense> expenses = new ArrayList<>();`           |
| **Set<String>**                | Stores unique categories    | `Set<String> categories = new HashSet<>();`             |
| **Map<String, List<Expense>>** | Groups expenses by category | `Map<String, List<Expense>> grouped = new HashMap<>();` |
| **Generics**                   | Used in `GenericDAO<T>`     | Provides reusable DAO structure                         |

**Example:**

```java
Map<String, List<Expense>> grouped = dao.getExpensesByCategory();
for (String cat : grouped.keySet()) {
    System.out.println("Category: " + cat);
    for (Expense e : grouped.get(cat)) System.out.println(e);
}
```

---

## 🪟 GUI Overview (Swing)

### Interface Features

* Simple text fields for input
* Buttons for **Add**, **View All**, **View by Category**
* Output shown in scrollable `JTextArea`

### Components Used

* `JFrame` → Main Window
* `JPanel` → Group inputs
* `JTextField`, `JLabel`, `JButton`, `JTextArea`
* `JScrollPane` → For displaying data

### Layout Example:

```java
JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
panel.add(new JLabel("Title:"));
panel.add(titleField);
...
```

---

## 💻 How to Run Locally

### Prerequisites:

* ✅ JDK 17 or later
* ✅ MySQL installed and running
* ✅ MySQL Connector JAR
* ✅ IntelliJ IDEA / Eclipse IDE

### Steps:

1️⃣ **Clone the repository**

```bash
git clone https://github.com/Amit07973/ExpenseTracker.git
```

2️⃣ **Open in IntelliJ IDEA or Eclipse**

3️⃣ **Add JDBC Driver**

```
File → Project Structure → Modules → Dependencies → + → mysql-connector-j.jar
```

4️⃣ **Set up MySQL Database**
Run the SQL commands from the “Database Design” section.

5️⃣ **Configure DB credentials**
Edit:

```java
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

6️⃣ **Run the Project**
Run `Main.java` (console version)
or `ExpenseTrackerGUI.java` (GUI version)

---

## 🧩 Example Output

### ✅ Console Output:

```
✅ Connected to Database!
✅ Expense Added Successfully!
✅ Transaction committed successfully!

📋 All Expenses:
1 | Lunch        | ₹180.00  | Food       | 2025-11-24
2 | Travel       | ₹500.00  | Transport  | 2025-11-24

📂 Unique Categories: [Food, Transport]
```

### 🪟 GUI Screenshot (Suggestion)

*(You can add a screenshot here in your GitHub README)*

```markdown
![Expense Tracker GUI Screenshot](assets/gui-screenshot.png)
```

---

## 🔍 Project Flow Summary

```
User Action → GUI Input
         ↓
Expense Object Created
         ↓
Service Layer Validates Data
         ↓
DAO Executes SQL via JDBC
         ↓
Transaction Commit / Rollback
         ↓
Data Stored in MySQL Database
```

---

## 🚀 Future Enhancements

✅ Add **user login system (authentication)**
✅ Generate **expense reports (PDF/Excel)**
✅ Integrate **Pie Chart** for visual analytics
✅ Add **search and filter** options
✅ Deploy on cloud using **AWS RDS / JavaFX Web**

---

## 👨‍💻 Team Members

| Role            | Name          |
| --------------- | ------------- |
| **Team Leader** | Amit Kumar    |
| **Team Member** | Amrit Shekhar |
| **Team Member** | Ankit Yadav   |

---

## 📚 References

* Oracle JDBC Documentation
* MySQL Developer Guide
* Java Swing UI Components
* Effective Java (Joshua Bloch) — for OOP design patterns

---

## 🏁 Conclusion

This project demonstrates:

* Core Java + OOP in a real-world use case
* JDBC for database integration
* PreparedStatement for secure queries
* Transaction Management for reliability
* Collections + Generics for flexible data handling
* Swing GUI for user interaction

It’s a complete example of how **Java, MySQL, and OOP** combine to create a clean, scalable application.

---

## 🪙 License

This project is open-source under the **MIT License**.
You are free to use, modify, and distribute it.

---

## ⭐ Support

If you found this project helpful, please give it a ⭐ on GitHub!
👉 [Amrit8960 on GitHub](https://github.com/Amrit8960)

---

