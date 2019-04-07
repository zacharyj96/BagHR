package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Inventory extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_inventory, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        Button addInv = view.findViewById(R.id.addInv);
        Button remInv = view.findViewById(R.id.remInv);
        Button viewInv = view.findViewById(R.id.viewInv);

        // navigates to add inventory page
        addInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.addingItem = true;
                mainActivity.launchActivityMenu(7, false);
            }
        });

        // navigates to remove inventory page
        remInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.addingItem = false;
                mainActivity.launchActivityMenu(7, false);
            }
        });

        // navigates to view inventory page
        viewInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(12, false);
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
