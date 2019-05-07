package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Home extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        Button homeInv = view.findViewById(R.id.homeInv);
        Button homeSales = view.findViewById(R.id.homeSales);
        Button homeTime = view.findViewById(R.id.homeTime);
        Button homePay = view.findViewById(R.id.homePay);

        if (mainActivity.currentUser.type.equals("admin")) {
            // do nothing
        } else if (mainActivity.currentUser.type.equals("hr")) {
            homeInv.setVisibility(View.GONE);
            homeSales.setVisibility(View.GONE);
        } else if (mainActivity.currentUser.type.equals("manager")) {
            homePay.setVisibility(View.GONE);
        } else {
            homeSales.setVisibility(View.GONE);
            homePay.setVisibility(View.GONE);
        }

        // navigates to add inventory page
        homeInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(0, false);
            }
        });

        // navigates to remove inventory page
        homeSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(1, false);
            }
        });

        // navigates to view inventory page
        homeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(2, false);
            }
        });

        // navigates to view inventory page
        homePay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.launchActivityMenu(4, false);
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
