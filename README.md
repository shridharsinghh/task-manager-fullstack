# 🚀 Team Task Manager (Full-Stack)

A full-stack web application that allows users to manage projects, assign tasks, and track progress with role-based access control (RBAC).

---

## 🎯 Project Overview

This project simulates a real-world team collaboration tool where:

* Admin users can create projects and assign tasks
* Members can view and work on assigned tasks
* Dashboard provides real-time task tracking

The goal was to build a **complete end-to-end system** including backend APIs, database relationships, authentication, and frontend UI.

---

## 🔥 Features

### 🔐 Authentication

* User Signup & Login
* Secure password handling (BCrypt)
* JWT-based authentication (token generated)

### 👥 Role-Based Access Control (RBAC)

* ADMIN → Create projects, assign tasks
* MEMBER → View tasks and dashboard

### 📁 Project Management

* Create new projects
* Assign owner to project

### ✅ Task Management

* Create tasks under projects
* Assign tasks to users
* Update task status (TODO, IN_PROGRESS, DONE)

### 📊 Dashboard

* Total tasks count
* Tasks by status
* Overdue tasks tracking

---

## ⚙️ Tech Stack

### 🖥 Backend

* Java, Spring Boot
* Spring Data JPA (Hibernate)
* MySQL Database
* REST APIs

### 🌐 Frontend

* HTML, CSS, JavaScript (Vanilla JS)
* Fetch API for backend communication

### 🔐 Security

* JWT (JSON Web Token)
* BCrypt Password Encoding

---

## 📁 Project Structure

```plaintext id="s1n3rd"
task-manager/
├── backend/        # Spring Boot application
├── frontend/       # HTML, CSS, JS
└── README.md
```

---

## ▶️ How to Run Locally

### 1️⃣ Backend Setup

* Open backend folder in IntelliJ
* Configure database in `application.properties`
* Run Spring Boot application

Runs at:

```id="r2a1m0"
http://localhost:8080
```

---

### 2️⃣ Frontend Setup

* Open frontend folder in VS Code
* Use Live Server

Runs at:

```id="r4k1m9"
http://127.0.0.1:5500
```

---

## 🌐 API Endpoints

| Method | Endpoint                         |
| ------ | -------------------------------- |
| POST   | /api/auth/register               |
| POST   | /api/auth/login                  |
| GET    | /api/projects                    |
| POST   | /api/projects                    |
| POST   | /api/tasks                       |
| GET    | /api/tasks/dashboard/{projectId} |

---

## 🧠 Key Learnings

* Implemented clean architecture (Controller → Service → Repository)
* Understood role-based access control in real scenarios
* Worked with REST APIs and frontend integration
* Learned debugging full-stack issues (frontend + backend)
* Built a real-world project under constraints

---

## 🚧 Future Improvements

* Add project members (team collaboration)
* Implement full JWT-based route protection
* Improve UI using React / Tailwind
* Add notifications and comments on tasks




---

## 👨‍💻 Author

Shridhar

---

## ⭐ Conclusion

This project demonstrates the ability to build a **complete full-stack system**, handle real-world problems, and integrate backend with frontend effectively.

It reflects strong understanding of:

* Backend development (Spring Boot)
* API design
* Database relationships
* Frontend integration
