package com.uala.demo.model;

import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("id")
    private int id;

    @SerializedName("nombre")
    private String name;

    @SerializedName("autor")
    private String author;

    @SerializedName("disponibilidad")
    private boolean availability;

    @SerializedName("popularidad")
    private int popularity;

    @SerializedName("imagen")
    private String imageUrl;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailability() {
        return availability;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}