package com.foodmap.app.ui.dialog;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.foodmap.app.R;
import com.foodmap.app.model.List;
import com.foodmap.app.model.ListsManager;
import com.foodmap.app.model.database.FirestoreHandler;
import com.foodmap.app.model.database.SharedPreferencesManager;

import java.util.ArrayList;

/**
 * List Dialog
 * Users can create new lists and specify name, color,
 * and description.
 */
public class ListDialog extends DialogFragment {

    private int selectedColor = 0;
    private ArrayList<Button> buttons;
    private ListsManager listsManager;

    public ListDialog() {
        buttons = new ArrayList<>();
        listsManager = ListsManager.getInstance();
    }

    public static ListDialog newInstance() {
        ListDialog fragment = new ListDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_list, container, false);

        loadColorOptions(view);

        Button saveBtn = view.findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText listNameEditText = view.findViewById(R.id.listNameEditText);
                String listName = listNameEditText.getText().toString();
                if(listName.isEmpty()){
                    listName = "New List";
                }

                EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
                String description = descriptionEditText.getText().toString();
                if(description.isEmpty()){
                    description = "";
                }

                // create List object and send to main
                FirestoreHandler.addList(
                        SharedPreferencesManager.getUserId(view.getContext()),
                        new List(listName, description, selectedColor)
                );
                getDialog().dismiss();
            }
        });
        return view;
    }

    private void loadColorOptions(View view) {
        // Color Options Table
        TableLayout colorOptionsTable = view.findViewById(R.id.colorOptionsTable);
        // Color Table Row
        TableRow colorTableRow = new TableRow(view.getContext());
        colorTableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f ));
        colorTableRow.setPadding(16, 16, 16, 16);
        colorOptionsTable.addView(colorTableRow);

        // Get color options from resource
        TypedArray colorOptions = view.getResources().obtainTypedArray(R.array.color_option_array);
        for(int i = 0; i < colorOptions.length(); i++){
            // Create button for each color and set params
            Button button = new Button(view.getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(100,100,1.0f);
            params.setMarginEnd(20);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.round_button_background);
            button.getBackground().setColorFilter(colorOptions.getColor(i, 0), PorterDuff.Mode.SRC_IN);
            button.setBackgroundTintMode(null);

            final int index = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedColor = index;
                    updateButtons();
                }
            });
            if(selectedColor == i){
                setButtonSelected(button);
            }

            colorTableRow.addView(button);
            buttons.add(button);
        }
    }

    private void updateButtons(){
        for(int i = 0; i < buttons.size(); i++){
            if(selectedColor == i){
                setButtonSelected(buttons.get(i));
            } else{
                setButtonUnselected(buttons.get(i));
            }
        }
    }

    private void setButtonSelected(Button button) {
        button.setForeground(getResources().getDrawable(R.drawable.round_button_border));
        button.setSelected(true);
    }

    private void setButtonUnselected(Button button) {
        button.setForeground(null);
        button.setSelected(false);
    }
}