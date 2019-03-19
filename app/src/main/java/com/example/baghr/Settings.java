package com.example.baghr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Settings extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, parent, false);

        Main mainActivity = (Main) getActivity();

        Context context = getActivity();

        return view;
    }

    @Override
    public void onDestroyView() {
        //fragment cleanup
        super.onDestroyView();
    }
}
