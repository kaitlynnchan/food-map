package com.foodmap.app.model;

import java.util.ArrayList;

public class ListsManager {

    private ArrayList<List> lists;

    // Singleton implementation
    private static ListsManager instance;
    private ListsManager() {
        this.lists = new ArrayList<>();

        // Add Default list
        this.lists.add(new List("Favorites", "", 0));
    }
    public static ListsManager getInstance() {
        if(instance == null){
            instance = new ListsManager();
        }
        return instance;
    }

    public void addList(List list){
        lists.add(list);
    }
}
