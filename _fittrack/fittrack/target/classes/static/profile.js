const userId = localStorage.getItem("userId");

if (!userId) {
    window.location.href = "index.html";
}


async function loadProfile() {

    try {

        const response =
            await fetch(
                `/api/users/${userId}`
            );

        if (!response.ok) {

            throw new Error(
                "Profile not found"
            );
        }

        const user =
            await response.json();


        document.getElementById(
            "profileName"
        ).textContent = user.name;


        document.getElementById(
            "profileEmail"
        ).textContent = user.email;


        document.getElementById(
            "profileAge"
        ).textContent =
            user.age + " years";


        document.getElementById(
            "profileGender"
        ).textContent =
            user.gender;


        document.getElementById(
            "profileHeight"
        ).textContent =
            user.height + " cm";


        document.getElementById(
            "profileWeight"
        ).textContent =
            user.weight + " kg";


        document.getElementById(
            "profileActivity"
        ).textContent =
            user.activityLevel;


    } catch (error) {

        console.error(error);

        alert(
            "Unable to load profile."
        );
    }
}


function logout() {

    localStorage.removeItem("userId");

    localStorage.removeItem("userName");

    window.location.href =
        "index.html";
}


loadProfile();