package com.fittrack.controller;

import com.fittrack.entity.Workout;
import com.fittrack.service.WorkoutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping
    public Workout addWorkout(@RequestBody Workout workout) {

        return workoutService.addWorkout(workout);
    }

    @GetMapping("/user/{userId}")
    public List<Workout> getUserWorkouts(
            @PathVariable Long userId) {

        return workoutService.getUserWorkouts(userId);
    }

    @GetMapping("/user/{userId}/today")
    public List<Workout> getTodayWorkouts(
            @PathVariable Long userId) {

        return workoutService.getTodayWorkouts(userId);
    }

    @DeleteMapping("/{id}")
    public String deleteWorkout(@PathVariable Long id) {

        workoutService.deleteWorkout(id);

        return "Workout deleted successfully";
    }
}