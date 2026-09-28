package com.fittrack.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "food_entries")
public class FoodEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String foodName;

    private Double quantity;

    private Double calories;

    private String mealType;

    private LocalDate entryDate;

    public FoodEntry() {
    }

    public FoodEntry(Long userId, String foodName, Double quantity,
                     Double calories, String mealType,
                     LocalDate entryDate) {

        this.userId = userId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.calories = calories;
        this.mealType = mealType;
        this.entryDate = entryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }
}