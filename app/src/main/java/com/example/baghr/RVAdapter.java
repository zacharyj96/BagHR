package com.example.baghr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> implements View.OnClickListener {

    // card view for each individual item
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView desc;
        TextView aisle;
        TextView row;
        TextView shelf;
        TextView itemNum;
        TextView itemPrice;
        Button removeItem;

        // generates card variables
        ItemViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            desc = itemView.findViewById(R.id.desc);
            aisle = itemView.findViewById(R.id.aisle);
            row = itemView.findViewById(R.id.row);
            shelf = itemView.findViewById(R.id.shelf);
            itemNum = itemView.findViewById(R.id.itemNum);
            removeItem = itemView.findViewById(R.id.removeItem);
            itemPrice = itemView.findViewById(R.id.itemPrice);
        }
    }

    ArrayList<Item> items;

    private Context context;

    // creates new adapter
    RVAdapter(ArrayList<Item> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);

        // creates view holder to generate card and sets its onclicklistener to be the RV adapter
        ItemViewHolder itemViewHolder = new ItemViewHolder(v);
        itemViewHolder.itemView.setOnClickListener(RVAdapter.this);
        itemViewHolder.itemView.setTag(itemViewHolder);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        // fills card with data from items object (rv adapter tells itemviewholder which card number it is, and that's
        // the index of the array list it takes data from)
        final int j = i;
        itemViewHolder.desc.setText(items.get(i).description);
        itemViewHolder.aisle.setText(items.get(i).aisle);
        NumberFormat nf = DecimalFormat.getInstance();
        nf.setMaximumFractionDigits(0);
        NumberFormat nf2 = NumberFormat.getCurrencyInstance();
        itemViewHolder.row.setText(nf.format(items.get(i).row_number));
        itemViewHolder.shelf.setText(items.get(i).shelf);
        itemViewHolder.itemNum.setText(nf.format(items.get(i).item_number));
        itemViewHolder.itemPrice.setText(nf2.format(items.get(i).price));
        if (items.get(i).is_stored == 0) {
            itemViewHolder.removeItem.setVisibility(View.INVISIBLE);
        } else {
            itemViewHolder.removeItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Main mainActivity = (Main) context;
                    mainActivity.mDatabaseHelper.updateIsStored(items.get(j).aisle, items.get(j).row_number, items.get(j).shelf);
                    mainActivity.launchActivityMenu(0, true);
                }
            });
        }

    }

    @Override
    public void onClick(View view) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) view.getTag();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
