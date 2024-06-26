package com.foodmap.app.ui.mainfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodmap.app.databinding.FragmentListBinding;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.model.database.FirestoreHandler;
import com.foodmap.app.model.database.SharedPreferencesManager;
import com.foodmap.app.ui.listview.ListAdapter;

/**
 * List View Screen
 * Displays user lists and search bar
 */
public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListsManager listsManager;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);

        updateUI();

        return binding.getRoot();
    }

    private void updateUI() {
        listsManager = FirestoreHandler.getListCollection(
                SharedPreferencesManager.getUserId(getContext()),
                new FirestoreHandler.FirestoreListCallback() {
            @Override
            public ListsManager getLists(ListsManager lists) {
                loadLists();
                return lists;
            }
        });
    }

    private void loadLists(){
        // add lists to recyclerview
        ListAdapter listAdapter = new ListAdapter(getContext(), listsManager);
        RecyclerView recyclerView = binding.listRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}