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

public class TimeSheet extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_time, parent, false);

        final Main mainActivity = (Main) getActivity();

        final Context context = getActivity();

        final EditText addHoursDesc = view.findViewById(R.id.addHoursDesc);

        Button addHoursSubmit = view.findViewById(R.id.addHoursSubmit);

        addHoursSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!addHoursDesc.getText().toString().equals("") && isNumeric(addHoursDesc.getText().toString())) {
                    mainActivity.currentUser.hours_worked += Double.parseDouble(addHoursDesc.getText().toString());
                    mainActivity.mDatabaseHelper.updateHoursWorked(mainActivity.currentUser.email, mainActivity.currentUser.hours_worked);
                    // success
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage("Hours worked updated successfully");
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

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
    }
}
