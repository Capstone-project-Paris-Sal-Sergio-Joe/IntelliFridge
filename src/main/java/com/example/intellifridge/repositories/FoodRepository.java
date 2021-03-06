package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findAllByFridgeId(long fridgeId);

    List<Food> findAllByFridgeIdOrderByExpirationDate(long fridgeId);

    List<Food> findAllByFridgeIdOrderByDateAddedDesc(long fridgeId);

    List<Food> findAllByFridgeIdOrderByName(long fridgeId);
}
