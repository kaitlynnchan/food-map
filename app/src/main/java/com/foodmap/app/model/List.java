package com.foodmap.app.model;

import java.util.ArrayList;
import java.util.UUID;

public class List {

    enum COLOR {
        RED, ORANGE, YELLOW, GREEN, BLUE
    }

    private final String listId = UUID.randomUUID().toString();
    private String name;
    private String description;
    private COLOR color;
    private ArrayList<PinnedLocation> pins;

    public List(String name, String description, COLOR color) {
        this.name = name;
        this.description = description;
        this.color = color;

        pins = new ArrayList<>();
    }

    public String getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }

    public ArrayList<PinnedLocation> getPins() {
        return pins;
    }

}
