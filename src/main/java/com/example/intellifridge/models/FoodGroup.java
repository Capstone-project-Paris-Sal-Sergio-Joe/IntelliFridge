package com.example.intellifridge.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "food_group")
public class FoodGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodGroup")
    private List<ShelfLife> foodShelfLife;

    public FoodGroup(long id, String name, List<ShelfLife> foodShelfLife) {
        this.id = id;
        this.name = name;
        this.foodShelfLife = foodShelfLife;
    }

    public FoodGroup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ShelfLife> getFoodShelfLife() {
        return foodShelfLife;
    }

    public void setFoodShelfLife(List<ShelfLife> foodShelfLife) {
        this.foodShelfLife = foodShelfLife;
    }
}
