package com.example.intellifridge.repositories;

import com.example.intellifridge.models.FoodGroup;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;

public interface FoodGroupRepository extends JpaAttributeConverter <FoodGroup, Long> {
}
