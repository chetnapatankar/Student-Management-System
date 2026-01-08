# Student Information System (Java & MySQL)

A robust backend application designed to manage student records efficiently. This system has been upgraded from a file-based storage approach to a **Relational Database Management System (RDBMS)** using MySQL and JDBC for enterprise-level data integrity.

## 🚀 Key Features
* **Database Integration:** Replaces flat-file storage with **MySQL** for persistent and scalable data management.
* **JDBC Connectivity:** Utilizes `java.sql` to establish secure connections between the Java application and the database.
* **CRUD Operations:** Implements full Create, Read, Update, and Delete functionalities using optimized SQL queries.
* **Search & Sort:** Features SQL-based sorting (ORDER BY) and searching (WHERE clauses) for faster data retrieval.
* **Error Handling:** Robust exception handling for database connection failures and invalid inputs.

## 🛠️ Tech Stack
* **Language:** Java (Core & Advanced)
* **Database:** MySQL 8.0
* **API/Driver:** JDBC (MySQL Connector/J)
* **Concepts:** OOP, Exception Handling, Relational Database Design.

## ⚙️ How to Setup & Run

### Prerequisites
* Java Development Kit (JDK) installed.
* MySQL Server and MySQL Workbench installed.
* VS Code (or any Java IDE).

### Step 1: Database Setup
Before running the Java code, you must create the database.
1. Open MySQL Workbench.
2. Copy the code from `database_setup.sql` (or use the commands below) and run them:
   ```sql
   CREATE DATABASE student_db;
   USE student_db;
   CREATE TABLE students (
       roll_number INT PRIMARY KEY,
       name VARCHAR(100),
       marks DOUBLE
   );
