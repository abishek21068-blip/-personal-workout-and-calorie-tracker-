const userId = localStorage.getItem("userId");

if (!userId) {
    window.location.href = "index.html";
}


const foodForm =
    document.getElementById("foodForm");


foodForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const food = {

            userId: parseInt(userId),

            foodName:
            document.getElementById(
                "foodName"
            ).value,

            quantity:
                parseFloat(
                    document.getElementById(
                        "quantity"
                    ).value
                ),

            calories:
                parseFloat(
                    document.getElementById(
                        "calories"
                    ).value
                ),

            mealType:
            document.getElementById(
                "mealType"
            ).value,

            entryDate:
                document.getElementById(
                    "entryDate"
                ).value || null
        };


        try {

            const response =
                await fetch("/api/foods", {

                    method: "POST",

                    headers: {
                        "Content-Type":
                            "application/json"
                    },

                    body:
                        JSON.stringify(food)
                });


            if (!response.ok) {

                throw new Error(
                    "Food could not be added"
                );
            }


            await response.json();


            alert(
                "Food added successfully!"
            );


            foodForm.reset();

            loadFoods();


        } catch (error) {

            console.error(error);

            alert(
                "Unable to add food."
            );
        }

    }
);


async function loadFoods() {

    try {

        const response =
            await fetch(
                `/api/foods/user/${userId}/today`
            );

        const foods =
            await response.json();

        const foodList =
            document.getElementById(
                "foodList"
            );


        if (foods.length === 0) {

            foodList.innerHTML =
                `<p class="empty-message">
                    No food entries yet.
                </p>`;

            return;
        }


        foodList.innerHTML =
            foods.map(food => `

                <div class="list-item">

                    <div>

                        <strong>
                            ${food.foodName}
                        </strong>

                        <p>
                            ${food.mealType}
                            -
                            ${food.quantity}
                        </p>

                    </div>

                    <strong>
                        ${Math.round(
                food.calories
            )} kcal
                    </strong>

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


loadFoods();