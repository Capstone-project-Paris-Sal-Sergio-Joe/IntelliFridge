package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository <Food, Long>{


}
