package com.foodmap.app.ui.main;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.foodmap.app.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListDialog extends DialogFragment {


    public ListDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static ListDialog newInstance(String param1, String param2) {
        ListDialog fragment = new ListDialog();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dialog_list, container, false);
        TableLayout colorOptionsTable = view.findViewById(R.id.colorOptionsTable);
//        colorOptionsTable.removeAllViews();
//
        TableRow colorTableRow = new TableRow(view.getContext());
        colorTableRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f ));
        colorTableRow.setPadding(16, 16, 16, 16);
        colorOptionsTable.addView(colorTableRow);

        TypedArray colorOptions = view.getResources().obtainTypedArray(R.array.color_option_array);
        for(int i = 0; i < colorOptions.length(); i++){
            view.getResources().getDrawable(R.drawable.round_button_background);

            Button button = new Button(view.getContext());
            TableRow.LayoutParams params = new TableRow.LayoutParams(100,100,1.0f);
            params.setMarginEnd(20);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.round_button_background);
            button.getBackground().setColorFilter(colorOptions.getColor(i, 0), PorterDuff.Mode.SRC_IN);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            colorTableRow.addView(button);
        }

        return view;
    }
}