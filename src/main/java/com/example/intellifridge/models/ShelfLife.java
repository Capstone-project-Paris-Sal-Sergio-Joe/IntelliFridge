package com.example.intellifridge.models;

import javax.persistence.*;

@Entity
@Table(name = "food_shelf_life")
public class ShelfLife {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(nullable = false, columnDefinition = "int not null")
    private int shelfLifeFridgeDays;

    @Column(nullable = false, columnDefinition = "int not null")
    private int shelfLifeFreezerDays;

    @Column(columnDefinition = "VARCHAR(200)")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodGroup foodGroup;

    @OneToOne
    private Food foodId;

    public ShelfLife(long id, String name, int shelfLifeFridgeDays, int shelfLifeFreezerDays, String imgUrl, FoodGroup foodGroup, Food foodId) {
        this.id = id;
        this.name = name;
        this.shelfLifeFridgeDays = shelfLifeFridgeDays;
        this.shelfLifeFreezerDays = shelfLifeFreezerDays;
        this.imgUrl = imgUrl;
        this.foodGroup = foodGroup;
        this.foodId = foodId;
    }

    public ShelfLife() {
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

    public int getShelfLifeFridgeDays() {
        return shelfLifeFridgeDays;
    }

    public void setShelfLifeFridgeDays(int shelfLifeFridgeDays) {
        this.shelfLifeFridgeDays = shelfLifeFridgeDays;
    }

    public int getShelfLifeFreezerDays() {
        return shelfLifeFreezerDays;
    }

    public void setShelfLifeFreezerDays(int shelfLifeFreezerDays) {
        this.shelfLifeFreezerDays = shelfLifeFreezerDays;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public Food getFoodId() {
        return foodId;
    }

    public void setFoodId(Food foodId) {
        this.foodId = foodId;
    }
}
