package com.example.intellifridge.models;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false)
    private boolean isInFreezer;


    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private Timestamp dateAdded;

    @Column(nullable = false)
    private Timestamp expirationDate;

    @OneToOne
    private ShelfLife shelfLife;

    @ManyToOne
    @JoinColumn(name ="fridge_id")
    private Fridge fridge;

    public Food(long id, boolean isInFreezer, String name, Timestamp dateAdded, Timestamp expirationDate, ShelfLife shelfLife, Fridge fridge) {
        this.id = id;
        this.isInFreezer = isInFreezer;
        this.name = name;
        this.dateAdded = dateAdded;
        this.expirationDate = expirationDate;
        this.shelfLife = shelfLife;
        this.fridge = fridge;
    }

    public Food() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isInFreezer() {
        return isInFreezer;
    }

    public void setInFreezer(boolean inFreezer) {
        isInFreezer = inFreezer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public ShelfLife getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(ShelfLife shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
    }
}
