package com.foodmap.app.model;

import java.util.ArrayList;

/**
 * List Manager Class
 * Stores a collection of lists. Class is singleton
 */
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

    public void configureList(List newList){
        int index = getIndexOfList(newList);
        if(index < 0){
            addList(newList);
        } else {
            setList(index, newList);
        }
    }

    public int getIndexOfList(List list){
        return lists.indexOf(list);
    }

    public void setList(int index, List list){
        lists.set(index, list);
    }

    public int getSize(){
        return lists.size();
    }

}
