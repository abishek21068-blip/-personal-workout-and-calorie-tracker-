package com.fittrack.service;

import com.fittrack.dto.DashboardResponse;
import com.fittrack.entity.User;
import com.fittrack.entity.Workout;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DashboardService {

    private final UserRepository userRepository;
    private final WorkoutRepository workoutRepository;
    private final FoodService foodService;
    private final HealthCalculationService healthService;

    public DashboardService(
            UserRepository userRepository,
            WorkoutRepository workoutRepository,
            FoodService foodService,
            HealthCalculationService healthService) {

        this.userRepository = userRepository;
        this.workoutRepository = workoutRepository;
        this.foodService = foodService;
        this.healthService = healthService;
    }

    public DashboardResponse getDashboard(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        double bmi = healthService.calculateBMI(user);

        double dailyGoal =
                healthService.calculateDailyCalories(user);

        double caloriesConsumed =
                foodService.getTodayCalories(userId);

        List<Workout> workouts =
                workoutRepository.findByUserIdAndWorkoutDate(
                        userId,
                        LocalDate.now()
                );

        double caloriesBurned = workouts.stream()
                .mapToDouble(Workout::getCaloriesBurned)
                .sum();

        double remainingCalories =
                dailyGoal - caloriesConsumed + caloriesBurned;

        return new DashboardResponse(
                user.getId(),
                user.getName(),
                Math.round(bmi * 100.0) / 100.0,
                healthService.getBMICategory(bmi),
                Math.round(dailyGoal * 100.0) / 100.0,
                Math.round(caloriesConsumed * 100.0) / 100.0,
                Math.round(caloriesBurned * 100.0) / 100.0,
                Math.round(remainingCalories * 100.0) / 100.0
        );
    }
}