const userId = localStorage.getItem("userId");

if (!userId) {
    window.location.href = "index.html";
}


const workoutForm =
    document.getElementById("workoutForm");


workoutForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        const workout = {

            userId: parseInt(userId),

            workoutType:
            document.getElementById(
                "workoutType"
            ).value,

            duration:
                parseInt(
                    document.getElementById(
                        "duration"
                    ).value
                ),

            workoutDate:
                document.getElementById(
                    "workoutDate"
                ).value || null
        };


        try {

            const response =
                await fetch("/api/workouts", {

                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(workout)
                });


            if (!response.ok) {

                throw new Error(
                    "Workout could not be added"
                );
            }


            const savedWorkout =
                await response.json();


            alert(
                "Workout added! Calories burned: "
                +
                Math.round(
                    savedWorkout.caloriesBurned
                )
                +
                " kcal"
            );


            workoutForm.reset();

            loadWorkouts();


        } catch (error) {

            console.error(error);

            alert(
                "Unable to add workout."
            );
        }

    }
);


async function loadWorkouts() {

    try {

        const response =
            await fetch(
                `/api/workouts/user/${userId}`
            );

        const workouts =
            await response.json();

        const workoutList =
            document.getElementById(
                "workoutList"
            );


        if (workouts.length === 0) {

            workoutList.innerHTML =
                `<p class="empty-message">
                    No workouts yet.
                </p>`;

            return;
        }


        workoutList.innerHTML =
            workouts.map(workout => `

                <div class="list-item">

                    <div>

                        <strong>
                            ${workout.workoutType}
                        </strong>

                        <p>
                            ${workout.duration} minutes
                        </p>

                    </div>

                    <div>

                        <strong>
                            ${Math.round(
                workout.caloriesBurned
            )} kcal
                        </strong>

                        <p>
                            ${workout.workoutDate}
                        </p>

                    </div>

                </div>

            `).join("");


    } catch (error) {

        console.error(error);

    }
}


function logout() {

    localStorage.removeItem("userId");

    localStorage.removeItem("userName");

    window.location.href =
        "index.html";
}


loadWorkouts();