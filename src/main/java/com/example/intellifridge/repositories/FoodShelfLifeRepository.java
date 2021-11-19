package com.example.intellifridge.repositories;

import com.example.intellifridge.models.ShelfLife;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodShelfLifeRepository extends JpaRepository <ShelfLife, Long > {
}
