console.log("REGISTER JS LOADED");

const registerForm = document.getElementById("registerForm");

const message = document.getElementById("message");

registerForm.addEventListener("submit", async function (event) {

    event.preventDefault();

    console.log("Register button clicked");

    const user = {

        name: document.getElementById("name").value.trim(),

        email: document.getElementById("email").value.trim(),

        password: document.getElementById("password").value,

        age: parseInt(
            document.getElementById("age").value
        ),

        gender: document.getElementById("gender").value,

        height: parseFloat(
            document.getElementById("height").value
        ),

        weight: parseFloat(
            document.getElementById("weight").value
        ),

        activityLevel:
        document.getElementById("activityLevel").value
    };

    console.log("User data:", user);

    try {

        message.textContent = "Creating account...";
        message.className = "message";

        const response = await fetch(
            "/api/users/register",
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(user)
            }
        );

        console.log(
            "Response status:",
            response.status
        );

        if (!response.ok) {

            const errorText =
                await response.text();

            console.error(
                "Registration error:",
                errorText
            );

            message.textContent =
                errorText || "Registration failed.";

            message.className =
                "message error";

            return;
        }

        const savedUser =
            await response.json();

        console.log(
            "Saved user:",
            savedUser
        );

        localStorage.setItem(
            "userId",
            savedUser.id
        );

        localStorage.setItem(
            "userName",
            savedUser.name
        );

        message.textContent =
            "Account created successfully!";

        message.className =
            "message success";

        alert(
            "Account created successfully!"
        );

        window.location.href =
            "dashboard.html";

    } catch (error) {

        console.error(
            "Connection error:",
            error
        );

        message.textContent =
            "Cannot connect to the server.";

        message.className =
            "message error";
    }

});