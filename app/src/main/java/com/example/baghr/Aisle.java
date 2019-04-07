package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Aisle extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_aisle, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);

        // sets toolbar to be back arrow
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);

        // navigate back when toolbar arrow is clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(0, true);
            }
        });

        Button aisleA = view.findViewById(R.id.aisleA);
        Button aisleB = view.findViewById(R.id.aisleB);
        Button aisleC = view.findViewById(R.id.aisleC);
        Button aisleD = view.findViewById(R.id.aisleD);
        Button aisleE = view.findViewById(R.id.aisleE);
        Button aisleF = view.findViewById(R.id.aisleF);
        Button aisleG = view.findViewById(R.id.aisleG);
        Button aisleH = view.findViewById(R.id.aisleH);
        Button aisleI = view.findViewById(R.id.aisleI);
        Button aisleJ = view.findViewById(R.id.aisleJ);

        // navigates to row page
        aisleA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "A";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "B";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "C";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "D";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "E";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "F";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "G";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "H";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "I";
                mainActivity.launchActivityMenu(8, false);
            }
        });

        // navigates to row page
        aisleJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = new Item();
                mainActivity.currentItem.aisle = "J";
                mainActivity.launchActivityMenu(8, false);
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
