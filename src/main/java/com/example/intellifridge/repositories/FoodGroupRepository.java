package com.example.intellifridge.repositories;

import com.example.intellifridge.models.FoodGroup;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodGroupRepository extends JpaRepository<FoodGroup, Long> {
}
