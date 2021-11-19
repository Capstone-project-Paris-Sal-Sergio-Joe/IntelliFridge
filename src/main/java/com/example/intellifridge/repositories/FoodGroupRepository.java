package com.example.intellifridge.repositories;

import com.example.intellifridge.models.FoodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {
}
