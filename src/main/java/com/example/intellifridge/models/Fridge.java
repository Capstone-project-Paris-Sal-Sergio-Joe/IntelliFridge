package com.example.intellifridge.models;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<User> users = new ArrayList<>();

    public Fridge(){

    }

    public Fridge(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Fridge(long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
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


    public void addUser(User user) {
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Fridge{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}































//package com.example.intellifridge.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "fridges")
//public class Fridge {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
//    private String name;
//
//    @ManyToMany(mappedBy = "fridges")
//    private List<User> users;
//
//    public Fridge(){
//
//    }
//
//    public Fridge(long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
