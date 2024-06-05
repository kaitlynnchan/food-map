package com.foodmap.app.model;

import java.util.ArrayList;

public class ListsManager {

    private ArrayList<List> lists = new ArrayList<>();

    // Singleton implementation
    private static ListsManager instance;
    private ListsManager() {}
    public static ListsManager getInstance() {
        if(instance == null){
            instance = new ListsManager();
        }
        return instance;
    }

    public void addList(List list){
        lists.add(list);
    }

    public List getList(int index){
        return lists.get(index);
    }

    public int getSize(){
        return lists.size();
    }

}
