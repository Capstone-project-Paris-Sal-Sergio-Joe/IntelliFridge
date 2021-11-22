package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeRepository extends JpaRepository <Fridge, Long>{
    List<Food> findByNameContaining(String title, Sort sort);
}
