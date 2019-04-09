package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Payroll extends Fragment {

    private RecyclerView rv;

    private ArrayList<User> usersList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payroll, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        usersList = mainActivity.mDatabaseHelper.getUsers();

        if (usersList.size() == 0) {
            TextView txtBlank = view.findViewById(R.id.txtBlank);
            txtBlank.setText("No users found");
        }

        rv = view.findViewById(R.id.rvPayroll);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        // set up recycler view adapter based off of the current user list
        RVAdapterPayroll adapter = new RVAdapterPayroll(usersList, context);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
        usersList = null;
        rv.setAdapter(null);
        rv = null;
    }
}
