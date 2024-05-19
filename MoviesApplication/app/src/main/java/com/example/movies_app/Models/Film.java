package com.example.movies_app.Models;

import java.time.Year;
import java.util.Date;

public class Film {
    private int id,image;
    private static int counter=0;
    private String nom,type,desc;
    private double rating;
    private int created_at;

    public Film(String nom, String type, String desc, double rating, int created_at,int image) {
        this.id=++counter;
        this.nom = nom;
        this.type = type;
        this.desc = desc;
        this.rating = rating;
        this.created_at = created_at;
        this.image=image;
    }

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
