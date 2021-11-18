package com.example.intellifridge.repositories;

import com.example.intellifridge.models.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FridgeRepository extends JpaRepository<Fridge, Long> {



}
