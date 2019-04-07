package com.example.baghr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class Shelf extends Fragment {

    final Main mainActivity = (Main) getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_shelf, parent, false);

        final Context context = getActivity();

        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);

        // sets toolbar to be back arrow
        //toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white);

        // navigate back when toolbar arrow is clicked
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(8, true);
            }
        });

        Button shelfA = view.findViewById(R.id.shelfA);
        Button shelfB = view.findViewById(R.id.shelfB);
        Button shelfC = view.findViewById(R.id.shelfC);
        Button shelfD = view.findViewById(R.id.shelfD);
        Button shelfE = view.findViewById(R.id.shelfE);
        Button shelfF = view.findViewById(R.id.shelfF);
        Button shelfG = view.findViewById(R.id.shelfG);
        Button shelfH = view.findViewById(R.id.shelfH);
        Button shelfI = view.findViewById(R.id.shelfI);
        Button shelfJ = view.findViewById(R.id.shelfJ);
        Button shelfK = view.findViewById(R.id.shelfK);
        Button shelfL = view.findViewById(R.id.shelfL);
        Button shelfM = view.findViewById(R.id.shelfM);
        Button shelfN = view.findViewById(R.id.shelfN);
        Button shelfO = view.findViewById(R.id.shelfO);

        // navigates to add/remove page
        shelfA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem.shelf = "A";
                if (mainActivity.addingItem && !validateItem()) {
                    mainActivity.launchActivityMenu(10, false);
                } else if (!mainActivity.addingItem && validateItem()) {
                    mainActivity.launchActivityMenu(11, false);
                } else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Invalid location selected");
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }
        });

        return view;
    }

    private boolean validateItem() {
        List<Item> items = mainActivity.mDatabaseHelper.getItemByLocation(mainActivity.currentItem.aisle, mainActivity.currentItem.row_number, mainActivity.currentItem.shelf);
        return items.size() > 0;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
    }
}
