# email-management-system-java
A console-based Email Management System built using Core Java with authentication, search, starred, trash, draft handling, and file-based data persistence.


# 📧 Email Management System (Core Java)

A **console-based Email Management System** developed using **Core Java** that simulates real-world email functionalities similar to Gmail.  
This project focuses on **backend logic, object-oriented programming, and file-based data persistence**.

---

## 🧩 Project Overview

The Email Management System allows users to create accounts, log in securely, compose emails, manage inboxes, save drafts, star important emails, delete and restore messages, and search emails efficiently.  
All user and email data is persisted using **Java Serialization**, ensuring data is retained even after restarting the application.

---


## 🚀 Features

- User registration and secure login with limited authentication attempts  
- Compose emails and manage Inbox, Sent, and Draft folders  
- Draft editing and sending functionality  
- Star and unstar important emails  
- Trash management with restore and permanent delete options  
- Read and unread email tracking with unread count  
- Keyword-based email search (by subject and sender)  
- Email labeling (Work, Personal, Important)  
- File-based data persistence using Java Serialization  
- Menu-driven and exception-safe console interface  

---


## 🛠️ Technologies Used

- Core Java  
- Object-Oriented Programming (OOP)  
- Java Collections Framework  
- File Handling and Serialization  
- Exception Handling  

---


## ▶️ How to Run the Project

1. Clone the repository
2. Navigate to the project directory
3. Compile all Java files
4. Run the application

   
> Ensure all `.java` files are present in the same directory before running the program.


---

## 📂 Project Structure

email-management-system-java/
│
├── EmailDriver.java # Application entry point
├── Gmail.java # Core application logic
├── User.java # User model class
├── Mail.java # Mail model class
├── gmail_data.db # Auto-generated data file
└── README.md



---

## 🔄 Application Flow

Start Application
↓
Create Account / Login
↓
Home Menu
├── Compose Mail
├── Inbox (Read / Unread)
├── Sent Mails
├── Drafts (Edit & Send)
├── Starred Mails
├── Trash (Restore / Delete)
├── Search Mail
└── Logout

