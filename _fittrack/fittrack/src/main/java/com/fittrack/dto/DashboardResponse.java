package com.fittrack.dto;

public class DashboardResponse {

    private Long userId;

    private String name;

    private double bmi;

    private String bmiCategory;

    private double dailyCalorieGoal;

    private double caloriesConsumed;

    private double caloriesBurned;

    private double remainingCalories;

    public DashboardResponse() {
    }

    public DashboardResponse(
            Long userId,
            String name,
            double bmi,
            String bmiCategory,
            double dailyCalorieGoal,
            double caloriesConsumed,
            double caloriesBurned,
            double remainingCalories) {

        this.userId = userId;
        this.name = name;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
        this.dailyCalorieGoal = dailyCalorieGoal;
        this.caloriesConsumed = caloriesConsumed;
        this.caloriesBurned = caloriesBurned;
        this.remainingCalories = remainingCalories;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public String getBmiCategory() {
        return bmiCategory;
    }

    public void setBmiCategory(String bmiCategory) {
        this.bmiCategory = bmiCategory;
    }

    public double getDailyCalorieGoal() {
        return dailyCalorieGoal;
    }

    public void setDailyCalorieGoal(double dailyCalorieGoal) {
        this.dailyCalorieGoal = dailyCalorieGoal;
    }

    public double getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(double caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public double getRemainingCalories() {
        return remainingCalories;
    }

    public void setRemainingCalories(double remainingCalories) {
        this.remainingCalories = remainingCalories;
    }
}