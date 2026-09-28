const userId = localStorage.getItem("userId");

if (!userId) {
    window.location.href = "index.html";
}


async function loadDashboard() {

    try {

        const response =
            await fetch(`/api/dashboard/${userId}`);

        if (!response.ok) {
            throw new Error("Failed to load dashboard");
        }

        const data = await response.json();

        console.log("Dashboard data:", data);


        // USER NAME

        document.getElementById("userName").textContent =
            data.name;

        document.getElementById("welcomeName").textContent =
            data.name;


        // BMI

        document.getElementById("bmiValue").textContent =
            Number(data.bmi).toFixed(1);

        document.getElementById("summaryBMI").textContent =
            Number(data.bmi).toFixed(1);


        document.getElementById("bmiStatus").textContent =
            data.bmiCategory;


        // DAILY GOAL

        const dailyGoal =
            Math.round(data.dailyCalorieGoal);

        document.getElementById("dailyGoal").textContent =
            dailyGoal;

        document.getElementById("summaryCalories").textContent =
            dailyGoal + " kcal";


        // CALORIES CONSUMED

        const consumed =
            Math.round(data.caloriesConsumed);

        document.getElementById("caloriesToday").textContent =
            consumed;


        // CALORIE PROGRESS

        let progress =
            (consumed / dailyGoal) * 100;

        progress =
            Math.min(Math.max(progress, 0), 100);


        document.getElementById("calorieProgress")
            .style.width = progress + "%";


        document.getElementById("calorieText")
            .textContent =
            consumed + " / " + dailyGoal + " kcal";


        document.getElementById("caloriePercentage")
            .textContent =
            Math.round(progress) + "%";


        // WORKOUT / CALORIES BURNED

        document.getElementById("workoutMinutes")
            .textContent =
            Math.round(data.caloriesBurned || 0);

    }
    catch (error) {

        console.error("Dashboard error:", error);

    }
}


function logout() {

    localStorage.removeItem("userId");
    localStorage.removeItem("userName");

    window.location.href = "index.html";
}


loadDashboard();