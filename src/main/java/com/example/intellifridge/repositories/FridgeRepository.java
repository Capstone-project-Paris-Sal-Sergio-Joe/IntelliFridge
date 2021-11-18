package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FridgeRepository extends JpaRepository <Fridge, Long>{

}
