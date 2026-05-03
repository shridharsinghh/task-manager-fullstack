const BASE_URL = "http://localhost:8080";

// ================= LOGIN =================
function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${BASE_URL}/api/auth/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
    })
    .then(res => res.json())
    .then(data => {
        console.log("LOGIN RESPONSE:", data);

        localStorage.setItem("email", data.email);
        localStorage.setItem("role", data.role);

        window.location.href = "dashboard.html";
    })
    .catch(err => {
        console.error(err);
        alert("Login failed");
    });
}

// ================= SIGNUP =================
function signup() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    fetch(`${BASE_URL}/api/auth/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ name, email, password })
    })
    .then(res => res.json())
    .then(data => {
        console.log("SIGNUP RESPONSE:", data);

        localStorage.setItem("email", data.email);
        localStorage.setItem("role", data.role);

        window.location.href = "dashboard.html";
    })
    .catch(err => {
        console.error(err);
        alert("Signup failed");
    });
}

// ================= NAVIGATION =================
function goToSignup() {
    window.location.href = "signup.html";
}

function goToLogin() {
    window.location.href = "index.html";
}

// ================= LOGOUT =================
function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}

// ================= PAGE LOAD =================
window.onload = function () {
    // run only on dashboard page
    if (!window.location.pathname.includes("dashboard.html")) return;

    const email = localStorage.getItem("email");
    const role = localStorage.getItem("role");

    // ✅ FIX: no popup, only redirect
    if (!email || !role) {
        window.location.href = "index.html";
        return;
    }

    // show role
    const roleText = document.getElementById("roleText");
    if (roleText) roleText.innerText = "Role: " + role;

    // hide project section for MEMBER
    if (role === "MEMBER") {
        const section = document.getElementById("projectSection");
        if (section) section.style.display = "none";
    }

    loadProjects();
};

// ================= LOAD PROJECTS =================
function loadProjects() {
    const email = localStorage.getItem("email");

    fetch(`${BASE_URL}/api/projects`, {
        headers: {
            "X-User-Email": email
        }
    })
    .then(res => {
        if (!res.ok) throw new Error("API failed");
        return res.json();
    })
    .then(projects => {
        console.log("PROJECTS:", projects);

        const dropdown1 = document.getElementById("projectDropdown");
        const dropdown2 = document.getElementById("dashProjectDropdown");

        if (!dropdown1 || !dropdown2) return;

        dropdown1.innerHTML = "";
        dropdown2.innerHTML = "";

        if (!projects || projects.length === 0) {
            dropdown1.innerHTML = "<option>No projects found</option>";
            dropdown2.innerHTML = "<option>No projects found</option>";
            return;
        }

        projects.forEach(p => {
            const opt1 = document.createElement("option");
            opt1.value = p.id;
            opt1.text = p.name;

            const opt2 = opt1.cloneNode(true);

            dropdown1.appendChild(opt1);
            dropdown2.appendChild(opt2);
        });
    })
    .catch(err => {
        console.error(err);
        alert("Error loading projects");
    });
}

// ================= CREATE PROJECT =================
function createProject() {
    const name = document.getElementById("projectName").value;

    fetch(`${BASE_URL}/api/projects?name=${name}`, {
        method: "POST",
        headers: {
            "X-User-Email": localStorage.getItem("email")
        }
    })
    .then(res => res.json())
    .then(() => {
        alert("Project Created!");
        loadProjects();
    })
    .catch(() => alert("Error creating project"));
}

// ================= CREATE TASK =================
function createTask() {
    const title = document.getElementById("title").value;
    const projectId = document.getElementById("projectDropdown").value;
    const email = document.getElementById("assignEmail").value;

    if (!projectId) {
        alert("Select a project first");
        return;
    }

    fetch(`${BASE_URL}/api/tasks?title=${title}&projectId=${projectId}&assignedTo=${email}`, {
        method: "POST"
    })
    .then(res => res.json())
    .then(() => alert("Task Created!"))
    .catch(() => alert("Error creating task"));
}

// ================= LOAD DASHBOARD =================
function loadDashboard() {
    const projectId = document.getElementById("dashProjectDropdown").value;

    if (!projectId) {
        alert("Select a project first");
        return;
    }

    fetch(`${BASE_URL}/api/tasks/dashboard/${projectId}`)
    .then(res => res.json())
    .then(data => {
        document.getElementById("dashboardBox").innerHTML = `
            <p>Total: ${data.totalTasks}</p>
            <p>Todo: ${data.todo}</p>
            <p>In Progress: ${data.inProgress}</p>
            <p>Done: ${data.done}</p>
            <p>Overdue: ${data.overdue}</p>
        `;
    })
    .catch(() => alert("Error loading dashboard"));
}