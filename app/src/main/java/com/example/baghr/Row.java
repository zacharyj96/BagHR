package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Row extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_row, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);

        // sets toolbar to be back arrow
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);

        // navigate back when toolbar arrow is clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(7, true);
            }
        });

        Button row1 = view.findViewById(R.id.row1);
        Button row2 = view.findViewById(R.id.row2);
        Button row3 = view.findViewById(R.id.row3);
        Button row4 = view.findViewById(R.id.row4);
        Button row5 = view.findViewById(R.id.row5);
        Button row6 = view.findViewById(R.id.row6);
        Button row7 = view.findViewById(R.id.row7);
        Button row8 = view.findViewById(R.id.row8);
        Button row9 = view.findViewById(R.id.row9);
        Button row10 = view.findViewById(R.id.row10);
        Button row11 = view.findViewById(R.id.row11);
        Button row12 = view.findViewById(R.id.row12);

        // navigates to shelf page
        row1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 1;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 2;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 3;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 4;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 5;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 6;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 7;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 8;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 9;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 10;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 11;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        // navigates to shelf page
        row12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.row_number = 12;
                mainActivity.launchActivityMenu(9, false);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
    }
}
