# 🚀 Team Task Manager (Full-Stack)

A full-stack web application that allows users to manage projects, assign tasks, and track progress using role-based access control (RBAC).

---

## 🎯 Project Overview

This project simulates a real-world team collaboration tool where:

- Admin users can create projects and assign tasks  
- Members can view and work on assigned tasks  
- A dashboard provides real-time task insights  

The goal was to build a complete end-to-end system including backend APIs, authentication, database relationships, and frontend UI.

---

## 🔥 Features

### 🔐 Authentication
- User Signup & Login  
- Secure password handling using BCrypt  
- JWT-based authentication  

---

### 👥 Role-Based Access Control (RBAC)

- **ADMIN**
  - Create projects  
  - Assign tasks  

- **MEMBER**
  - View tasks  
  - Access dashboard  

---

### 📁 Project Management
- Create new projects  
- Assign project owner  

---

### ✅ Task Management
- Create tasks under projects  
- Assign tasks to users  
- Update task status (TODO, IN_PROGRESS, DONE)  

---

### 📊 Dashboard
- Total tasks count  
- Tasks by status  
- Overdue task tracking  

---

## ⚙️ Tech Stack

### 🖥 Backend
- Java  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- MySQL  

---

### 🌐 Frontend
- HTML, CSS, JavaScript (Vanilla JS)  
- Fetch API  

---

### 🔐 Security
- JWT (JSON Web Token)  
- BCrypt Password Encoding  

---

## 📁 Project Structure

```
task-manager/
├── backend/        # Spring Boot application
├── frontend/       # HTML, CSS, JS
└── README.md
```

---

## ▶️ How to Run Locally

### 🔹 Backend

1. Open backend in IntelliJ  
2. Configure database in application.properties  
3. Run Spring Boot  

```
http://localhost:8080
```

---

### 🔹 Frontend

1. Open frontend in VS Code  
2. Run using Live Server  

```
http://127.0.0.1:5500
```

---

## 🌐 API Endpoints

| Method | Endpoint |
|------|--------|
| POST | /api/auth/register |
| POST | /api/auth/login |
| GET  | /api/projects |
| POST | /api/projects |
| POST | /api/tasks |
| GET  | /api/tasks/dashboard/{projectId} |

---

## 🎥 Demo

Add your demo video link here

---

## 🌐 Live Deployment

Not deployed due to cloud limitations.  
Full functionality is demonstrated in the demo video.

---

## 🧠 Key Learnings

- Built layered architecture (Controller → Service → Repository)  
- Implemented RBAC using Spring Security  
- Integrated frontend with REST APIs  
- Debugged full-stack issues  
- Designed real-world database relationships  

---

## 🚧 Future Improvements

- Add team collaboration (multiple members per project)  
- Full JWT route protection  
- Improve UI (React / Tailwind)  
- Notifications & comments  

---

## 👨‍💻 Author

Shridhar

---

## ⭐ Conclusion

This project demonstrates the ability to build a complete full-stack system, including:

- Backend development (Spring Boot)  
- API design  
- Authentication & security  
- Database modeling  
- Frontend integration  
