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

public class RemoveItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_removeitem, parent, false);

        final Main mainActivity = (Main) getActivity();

        final Context context = getActivity();

        Button remItemYes = view.findViewById(R.id.remItemYes);

        Button remItemNo = view.findViewById(R.id.remItemNo);

        remItemNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.currentItem = null;
                mainActivity.launchActivityMenu(0, true);
            }
        });

        remItemYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.mDatabaseHelper.updateIsStored(mainActivity.currentItem.aisle, mainActivity.currentItem.row_number, mainActivity.currentItem.shelf);
                // success
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Item removed successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                mainActivity.currentItem = null;
                mainActivity.launchActivityMenu(0, true);
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
