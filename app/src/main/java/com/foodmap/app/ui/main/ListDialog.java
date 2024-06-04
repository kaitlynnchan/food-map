package com.foodmap.app.ui.main;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

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
import com.foodmap.app.ui.MainActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDialog extends DialogFragment {

    private int selectedColor;
    private ArrayList<Button> buttons;
    private ListsManager listsManager;

    public ListDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDialog newInstance() {
        ListDialog fragment = new ListDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectedColor = 0;
        buttons = new ArrayList<>();
        listsManager = ListsManager.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_list, container, false);

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
//            ((GradientDrawable) button.getBackground()).setColor(colorOptions.getColor(i, 0));
            final int index = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedColor = index;
                    updateButtons();
                }
            });
            if(selectedColor == i){
                button.setForeground(getResources().getDrawable(R.drawable.round_button_background2));
                button.setSelected(true);
            }

            colorTableRow.addView(button);
            buttons.add(button);
        }

        Button saveBtn = view.findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText listNameEditText = v.findViewById(R.id.listNameEditText);
                String listName = listNameEditText.getText().toString();

                EditText descriptionEditText = v.findViewById(R.id.descriptionEditText);
                String description = descriptionEditText.getText().toString();

                // create List object and send to main
//                MainActivity.addList(new List(listName, description, selectedColor));
                getDialog().dismiss();

            }
        });
        return view;
    }

    private void updateButtons(){
        for(int i = 0; i < buttons.size(); i++){
            if(selectedColor == i){
                buttons.get(i).setForeground(getResources().getDrawable(R.drawable.round_button_background2));
                buttons.get(i).setSelected(true);
            } else{
                buttons.get(i).setForeground(null);
                buttons.get(i).setSelected(false);
            }
        }
    }
}