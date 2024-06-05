package com.foodmap.app.model;

import java.util.ArrayList;
import java.util.UUID;

public class List {

    private String listId;
    private String name;
    private String description;
    private int colorIndex;
    private ArrayList<PinnedLocation> pins;

    public List(String name, String description, int colorIndex) {
        this.listId = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.colorIndex = colorIndex;

        pins = new ArrayList<>();
    }

    public List(String listId, String name, String description, int colorIndex) {
        this.listId = listId;
        this.name = name;
        this.description = description;
        this.colorIndex = colorIndex;

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

    public int getColorIndex() {
        return colorIndex;
    }

    public void setColor(int colorIndex) {
        this.colorIndex = colorIndex;
    }

    public ArrayList<PinnedLocation> getPins() {
        return pins;
    }

}
