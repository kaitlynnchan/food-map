package com.foodmap.app.model;

public class PinnedLocation {

    private String id;
    private String name;
    private double rating;
    private String notes;

    public PinnedLocation(String id, String name, double rating, String notes) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if(rating <= 5 && rating >= 0){
            this.rating = rating;
        } else {
            this.rating = 0;
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
