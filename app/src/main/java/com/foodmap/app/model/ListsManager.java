package com.foodmap.app.model;

import java.util.ArrayList;

public class ListsManager {

    private ArrayList<List> lists;

    public ListsManager() {
        this.lists = new ArrayList<>();

        // Add Default list
        this.lists.add(new List("Favorites", "", List.COLOR.RED));
    }
}
