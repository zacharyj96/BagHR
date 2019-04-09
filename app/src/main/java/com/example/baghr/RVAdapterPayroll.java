package com.example.baghr;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RVAdapterPayroll extends RecyclerView.Adapter<RVAdapterPayroll.UserViewHolder> implements View.OnClickListener {

    // card view for each individual item
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView empName;
        TextView empHoursWorked;

        // generates card variables
        UserViewHolder(View userView) {
            super(userView);
            cv = itemView.findViewById(R.id.cv);
            empName = itemView.findViewById(R.id.empName);
            empHoursWorked = itemView.findViewById(R.id.empHoursWorked);
        }
    }

    ArrayList<User> users;

    private Context context;

    // creates new adapter
    RVAdapterPayroll(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.payrollitem, viewGroup, false);

        // creates view holder to generate card and sets its onclicklistener to be the RV adapter
        UserViewHolder userViewHolder = new UserViewHolder(v);
        userViewHolder.itemView.setOnClickListener(RVAdapterPayroll.this);
        userViewHolder.itemView.setTag(userViewHolder);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder userViewHolder, int i) {
        // fills card with data from items object (rv adapter tells itemviewholder which card number it is, and that's
        // the index of the array list it takes data from)
        userViewHolder.empName.setText(users.get(i).first_name + " " + users.get(i).last_name);
        userViewHolder.empHoursWorked.setText(String.valueOf(users.get(i).hours_worked));
    }

    @Override
    public void onClick(View view) {
        UserViewHolder userViewHOlder = (UserViewHolder) view.getTag();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
