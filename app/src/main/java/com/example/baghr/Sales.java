package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Sales extends Fragment {

    private RecyclerView rv;

    private ArrayList<Item> itemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        itemsList = mainActivity.mDatabaseHelper.getItems(0);

        if (itemsList.size() == 0) {
            TextView txtBlank = view.findViewById(R.id.txtBlank);
            txtBlank.setText("No items found");
        }

        rv = view.findViewById(R.id.rvSales);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        // set up recycler view adapter based off of the ccurrent inventory
        RVAdapter adapter = new RVAdapter(itemsList, context);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
        itemsList = null;
        rv.setAdapter(null);
        rv = null;
    }
}
