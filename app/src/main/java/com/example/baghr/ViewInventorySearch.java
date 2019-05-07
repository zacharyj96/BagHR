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

public class ViewInventorySearch extends Fragment {

    private RecyclerView rv;

    private ArrayList<Item> itemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewinventorysearch, parent, false);

        final Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        itemsList = mainActivity.mDatabaseHelper.getItems(mainActivity.searchParams);

        if (itemsList.size() == 0) {
            TextView txtBlank = view.findViewById(R.id.txtBlank);
            txtBlank.setText("No items found");
        }

        rv = view.findViewById(R.id.rvSearch);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);

        // set up recycler view adapter based off of the current inventory
        RVAdapter adapter = new RVAdapter(itemsList, context);
        rv.setAdapter(adapter);

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
