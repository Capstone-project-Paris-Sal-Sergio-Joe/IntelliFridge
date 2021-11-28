package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository <Food, Long>{

    List<Food> findAllByFridgeId(long fridgeId);

    List<Food> findByNameContaining(String title, Sort sort);

    @Query(nativeQuery = true,value = "SELECT * FROM foods where id = ? Order By name asc ")
    List<Food> findAllByNameAsc(String id);

    @Query(nativeQuery = true,value = "SELECT * FROM foods where id = ? Order By name desc ")
    List<Food> findAllByNameDesc(String name);

    Fridge getById(String id);

}
