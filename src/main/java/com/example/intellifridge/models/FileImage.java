package com.example.intellifridge.models;


import javax.persistence.*;

@Entity

@Table(name="file_imgs")
public class FileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    @Column(length=255)
    private String img_url;

    public FileImage(){}


}
