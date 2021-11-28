package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Food;
import com.example.intellifridge.models.Fridge;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FridgeRepository extends JpaRepository <Fridge, Long>{

    List<Food> findByNameContaining(String title, Sort sort);

    @Query(nativeQuery = true,value = "SELECT * FROM foods where id = ? Order By name asc ")
    List<Food> findAllByNameAsc(String id);

    @Query(nativeQuery = true,value = "SELECT * FROM foods where id = ? Order By name desc ")
    List<Food> findAllByNameDesc(String name);

    String getById(String id);

//    @Query(nativeQuery = true,value = "SELECT * FROM foods Order By is_in_Freezer asc ")
//    List<Food> findAllByNameAsc(String name);






}
