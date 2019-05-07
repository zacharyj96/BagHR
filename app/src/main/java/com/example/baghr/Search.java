package com.example.baghr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Search extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, parent, false);

        final Main mainActivity = (Main) getActivity();

        final Context context = getActivity();

        final EditText searchText = view.findViewById(R.id.searchParams);

        Button searchSubmit = view.findViewById(R.id.searchSubmit);

        searchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchText.getText().toString().equals("")) {
                    mainActivity.searchParams = searchText.getText().toString();
                    mainActivity.launchActivityMenu(14, false);
                }
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
