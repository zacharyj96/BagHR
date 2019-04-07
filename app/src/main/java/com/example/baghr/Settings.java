package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Settings extends Fragment {

    User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, parent, false);

        Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        currentUser = mainActivity.currentUser;

        TextView firstName = view.findViewById(R.id.settingsFirst);
        TextView lastName = view.findViewById(R.id.settingsLast);
        TextView email = view.findViewById(R.id.settingsEmail);
        TextView type = view.findViewById(R.id.settingsType);

        firstName.setText(currentUser.first_name);
        lastName.setText(currentUser.last_name);
        email.setText(currentUser.email);
        type.setText(currentUser.type);

        return view;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
        currentUser = null;
    }
}
