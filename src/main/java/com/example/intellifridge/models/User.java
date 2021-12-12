package com.example.intellifridge.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(unique = true, columnDefinition = "VARCHAR(200)")
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "VARCHAR(15)")
    private String phoneNumber;

    @Column(name="profile_picture" ,columnDefinition = "VARCHAR(200)")
    private String profilePicture;

    @Column(nullable = false)
    private boolean notifications;

    @Column(nullable = false)
    private boolean isPrivate;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name ="fridge_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "fridge_id")}
    )

    private List<Fridge> fridges = new ArrayList<>();

    public User() { }

    public User(long id, String username, String email, String password, String phoneNumber, String profilePicture, boolean notifications, List<Fridge> fridges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.notifications = notifications;
        this.fridges = fridges;
    }

    public User(long id, String username, String email, String password, String phoneNumber, String profilePicture, List<Fridge> fridges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.fridges = fridges;
    }


    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
        profilePicture = copy.profilePicture;

    }

    public User(long id, String username, String email, String password, String phoneNumber, String profilePicture, boolean notifications, boolean isPrivate, List<Fridge> fridges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profilePicture = profilePicture;
        this.notifications = notifications;
        this.isPrivate = isPrivate;
        this.fridges = fridges;
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

    public boolean isNotifications() {
        return notifications;
    }

    public void setNotifications(boolean notifications) {
        this.notifications = notifications;
    }

    public boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", notifications=" + notifications +
                ", isPrivate=" + isPrivate +
                ", fridges=" + fridges +
                '}';
    }
}
