package com.example.intellifridge.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fridges")
public class Fridge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @ManyToMany(mappedBy = "fridges")
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fridge")
    private List<Food> foods;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public Fridge(long id, String name, List<User> users, List<Food> foods) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.foods = foods;
    }

    public Fridge(){
    }

    public Fridge(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
