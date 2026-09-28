package com.fittrack.repository;

import com.fittrack.entity.FoodEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodEntryRepository
        extends JpaRepository<FoodEntry, Long> {

    List<FoodEntry> findByUserId(Long userId);

    List<FoodEntry> findByUserIdAndEntryDate(
            Long userId,
            LocalDate entryDate
    );
}