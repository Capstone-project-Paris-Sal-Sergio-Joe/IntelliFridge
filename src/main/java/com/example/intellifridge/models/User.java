package com.example.intellifridge.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(200)")
    private String email;

    @Column(nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @Column(columnDefinition = "VARCHAR(200)")
    private String profilePicture;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name ="fridge_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "fridge_id")}
    )

    private List<Fridge> fridges;


    public User(long id, String username, String email, String password, String phoneNumber, String profilePicture, List<Fridge> fridges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.fridges = fridges;
    }


    public User() { }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public List<Fridge> getFridges() {
        return fridges;
    }

    public void setFridges(List<Fridge> fridges) {
        this.fridges = fridges;
    }
}
