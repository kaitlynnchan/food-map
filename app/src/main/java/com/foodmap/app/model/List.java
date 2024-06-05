package com.foodmap.app.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * List Class
 * Defines a class model including:
 *  id, name, description, color index, pins
 * Defines a default list for new users
 */
public class List {

    public static List defaultList = new List("Favorites", "", 0);

    private String id;
    private String name;
    private String description;
    private int colorIndex;
    private ArrayList<PinnedLocation> pins;

    public List(String name, String description, int colorIndex) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.colorIndex = colorIndex;

        pins = new ArrayList<>();
    }

    public List(String id, String name, String description, int colorIndex) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.colorIndex = colorIndex;

        pins = new ArrayList<>();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        List listObj = (List) obj;
        if(listObj == null) return false;
        return (Objects.equals(listObj.id, this.id));
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
