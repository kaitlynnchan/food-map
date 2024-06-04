package com.foodmap.app.model;

import java.util.ArrayList;

public class ListsManager {

    private ArrayList<List> lists;

    public ListsManager() {
        this.lists = new ArrayList<>();

        // Add Default list
        this.lists.add(new List("Favorites", "", 0));
    }

    public void addList(List list){
        lists.add(list);
    }
}
