package com.example.intellifridge.repositories;

import com.example.intellifridge.models.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository <FileImage, Long>{

}
