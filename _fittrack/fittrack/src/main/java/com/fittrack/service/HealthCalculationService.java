package com.fittrack.service;

import com.fittrack.entity.User;
import org.springframework.stereotype.Service;

@Service
public class HealthCalculationService {

    public double calculateBMI(User user) {

        double heightInMeters = user.getHeight() / 100;

        return user.getWeight() /
                (heightInMeters * heightInMeters);
    }

    public double calculateBMR(User user) {

        double weight = user.getWeight();
        double height = user.getHeight();
        int age = user.getAge();

        if (user.getGender().equalsIgnoreCase("male")) {

            return (10 * weight)
                    + (6.25 * height)
                    - (5 * age)
                    + 5;

        } else {

            return (10 * weight)
                    + (6.25 * height)
                    - (5 * age)
                    - 161;
        }
    }

    public double calculateDailyCalories(User user) {

        double bmr = calculateBMR(user);

        double activityMultiplier;

        switch (user.getActivityLevel().toLowerCase()) {

            case "sedentary":
                activityMultiplier = 1.2;
                break;

            case "light":
                activityMultiplier = 1.375;
                break;

            case "moderate":
                activityMultiplier = 1.55;
                break;

            case "very active":
                activityMultiplier = 1.725;
                break;

            default:
                activityMultiplier = 1.2;
        }

        return bmr * activityMultiplier;
    }

    public String getBMICategory(double bmi) {

        if (bmi < 18.5) {
            return "Underweight";

        } else if (bmi < 25) {
            return "Normal";

        } else if (bmi < 30) {
            return "Overweight";

        } else {
            return "Obese";
        }
    }
}