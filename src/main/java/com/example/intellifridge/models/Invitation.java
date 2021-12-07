package com.example.intellifridge.models;

import javax.persistence.*;

@Entity
@Table(name = "invitations")
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
