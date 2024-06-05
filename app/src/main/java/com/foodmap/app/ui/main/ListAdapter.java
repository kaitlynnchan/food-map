package com.foodmap.app.ui.main;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodmap.app.R;
import com.foodmap.app.model.List;
import com.foodmap.app.model.ListsManager;

public class ListAdapter extends RecyclerView.Adapter<ListViewHolder>{

    private ListsManager listsManager;
    private Context context;

    public ListAdapter(Context context, ListsManager listsManager) {
        this.context = context;
        this.listsManager = listsManager;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        TypedArray colorOptions = context.getResources().obtainTypedArray(R.array.color_option_array);
        List listItem = listsManager.getList(position);
        holder.setListName(listItem.getName());
        holder.setListDescription(listItem.getDescription());
        holder.setBackgroundColor(colorOptions.getColor(listItem.getColorIndex(), 0));
    }

    @Override
    public int getItemCount() {
        return listsManager.getSize();
    }
}