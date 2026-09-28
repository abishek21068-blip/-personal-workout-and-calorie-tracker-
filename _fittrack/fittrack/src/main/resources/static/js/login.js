console.log("LOGIN JS LOADED");

const loginForm = document.getElementById("loginForm");

loginForm.addEventListener("submit", async function(event) {

    event.preventDefault();

    console.log("LOGIN BUTTON CLICKED");

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    console.log("Email:", email);

    try {

        const response = await fetch(
            "/api/users/email/" + encodeURIComponent(email)
        );

        console.log("Response status:", response.status);

        if (!response.ok) {
            alert("User not found");
            return;
        }

        const user = await response.json();

        console.log("User received:", user);

        if (user.password === password) {

            localStorage.setItem("userId", user.id);
            localStorage.setItem("userName", user.name);

            alert("Login successful!");

            window.location.href = "dashboard.html";

        } else {

            alert("Incorrect password");

        }

    } catch (error) {

        console.error("Login error:", error);

        alert("Cannot connect to server");

    }

});