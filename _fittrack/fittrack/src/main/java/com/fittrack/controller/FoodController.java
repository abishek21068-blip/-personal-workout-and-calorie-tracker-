package com.fittrack.controller;

import com.fittrack.entity.FoodEntry;
import com.fittrack.service.FoodService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public FoodEntry addFood(@RequestBody FoodEntry foodEntry) {

        return foodService.addFood(foodEntry);
    }

    @GetMapping("/user/{userId}")
    public List<FoodEntry> getUserFoods(
            @PathVariable Long userId) {

        return foodService.getUserFoods(userId);
    }

    @GetMapping("/user/{userId}/today")
    public List<FoodEntry> getTodayFoods(
            @PathVariable Long userId) {

        return foodService.getTodayFoods(userId);
    }

    @GetMapping("/user/{userId}/today/calories")
    public double getTodayCalories(
            @PathVariable Long userId) {

        return foodService.getTodayCalories(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {

        foodService.deleteFood(id);

        return "Food entry deleted successfully";
    }
}