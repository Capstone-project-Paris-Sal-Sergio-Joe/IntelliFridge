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



    @Column(nullable = false, columnDefinition = "Boolean")
    private boolean isInFreezer;



    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;


    @Column(nullable = false, columnDefinition = "TimeStamp(20)")
    private Timestamp date_Added;

    @Column(nullable = false, columnDefinition = "TimeStamp(20)")
    private Timestamp expirationDate;

    @OneToMany(mappedBy = "foods")
    private List<User> users;
    private List<Fridge> fridges;

    public Food() {
    }

    public Food(long id, boolean isInFreezer, String name, Timestamp date_Added, Timestamp expirationDate) {
        this.id = id;
        this.isInFreezer = isInFreezer;
        this.name = name;
        this.date_Added = date_Added;
        this.expirationDate = expirationDate;
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

    public Timestamp getDate_Added() {
        return date_Added;
    }

    public void setDate_Added(Timestamp date_Added) {
        this.date_Added = date_Added;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }
}
