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

public class AddItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_additem, parent, false);

        final Main mainActivity = (Main) getActivity();

        final Context context = getActivity();

        final EditText addItemDesc = view.findViewById(R.id.addItemDesc);

        Button addItemSubmit = view.findViewById(R.id.addItemSubmit);

        addItemSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addItemDesc.getText().toString().equals("")) {
                    mainActivity.currentItem.description = addItemDesc.getText().toString();
                    mainActivity.currentItem.is_stored = 1;
                    int count = mainActivity.mDatabaseHelper.getNumItems();
                    if (count != -1) {
                        mainActivity.currentItem.item_number = count + 1;
                        if(mainActivity.mDatabaseHelper.addItem(mainActivity.currentItem)) {
                            // success
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Account inserted successfully");
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
                        } else {
                            // error has occurred
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Error has occurred");
                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                        }
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                }
                            });
                        }
                    } else {
                        // error has occurred
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("Error has occurred");
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
