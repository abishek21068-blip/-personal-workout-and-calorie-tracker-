package com.fittrack.service;

import com.fittrack.entity.FoodEntry;
import com.fittrack.repository.FoodEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodService {

    private final FoodEntryRepository foodEntryRepository;

    public FoodService(FoodEntryRepository foodEntryRepository) {
        this.foodEntryRepository = foodEntryRepository;
    }

    public FoodEntry addFood(FoodEntry foodEntry) {

        if (foodEntry.getEntryDate() == null) {
            foodEntry.setEntryDate(LocalDate.now());
        }

        return foodEntryRepository.save(foodEntry);
    }

    public List<FoodEntry> getUserFoods(Long userId) {

        return foodEntryRepository.findByUserId(userId);
    }

    public List<FoodEntry> getTodayFoods(Long userId) {

        return foodEntryRepository.findByUserIdAndEntryDate(
                userId,
                LocalDate.now()
        );
    }

    public void deleteFood(Long id) {

        foodEntryRepository.deleteById(id);
    }

    public double getTodayCalories(Long userId) {

        List<FoodEntry> foods = getTodayFoods(userId);

        return foods.stream()
                .mapToDouble(FoodEntry::getCalories)
                .sum();
    }
}