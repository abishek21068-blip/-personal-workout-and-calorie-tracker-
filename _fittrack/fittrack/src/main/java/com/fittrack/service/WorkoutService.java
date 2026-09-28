package com.fittrack.service;

import com.fittrack.entity.User;
import com.fittrack.entity.Workout;
import com.fittrack.repository.UserRepository;
import com.fittrack.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    public WorkoutService(WorkoutRepository workoutRepository,
                          UserRepository userRepository) {

        this.workoutRepository = workoutRepository;
        this.userRepository = userRepository;
    }

    public Workout addWorkout(Workout workout) {

        User user = userRepository.findById(workout.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        double calories = calculateCalories(
                user,
                workout.getWorkoutType(),
                workout.getDuration()
        );

        workout.setCaloriesBurned(calories);

        if (workout.getWorkoutDate() == null) {
            workout.setWorkoutDate(LocalDate.now());
        }

        return workoutRepository.save(workout);
    }

    public List<Workout> getUserWorkouts(Long userId) {

        return workoutRepository.findByUserId(userId);
    }

    public List<Workout> getTodayWorkouts(Long userId) {

        return workoutRepository.findByUserIdAndWorkoutDate(
                userId,
                LocalDate.now()
        );
    }

    public void deleteWorkout(Long id) {

        workoutRepository.deleteById(id);
    }

    private double calculateCalories(
            User user,
            String workoutType,
            int duration) {

        double weight = user.getWeight();

        double met;

        switch (workoutType.toLowerCase()) {

            case "walking":
                met = 3.5;
                break;

            case "running":
                met = 9.8;
                break;

            case "cycling":
                met = 7.5;
                break;

            case "swimming":
                met = 8.0;
                break;

            case "weight training":
                met = 6.0;
                break;

            case "yoga":
                met = 2.5;
                break;

            default:
                met = 5.0;
        }

        return (met * 3.5 * weight / 200) * duration;
    }
}