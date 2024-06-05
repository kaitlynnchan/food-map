package com.foodmap.app.ui.listview;

import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.foodmap.app.R;

/**
 * Recycler View List View Holder
 * Setup view of list item
 */
public class ListViewHolder extends RecyclerView.ViewHolder {

    private TextView listName;
    private TextView listDescription;
    private View view;

    public ListViewHolder(@NonNull View itemView) {
        super(itemView);

        listName = itemView.findViewById(R.id.listNameText);
        listDescription = itemView.findViewById(R.id.listDescriptionText);
        view = itemView;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,25);
        view.setLayoutParams(params);
    }

    public void setListName(String name) {
        listName.setText(name);
    }

    public void setListDescription(String desc){
        listDescription.setText(desc);
    }

    public void setViewOnClickListener(View.OnClickListener listener){
        view.setOnClickListener(listener);
    }

    public void setBackgroundColor(int color){
        view.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_OVER);
    }
}
