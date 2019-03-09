package com.example.baghr;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateAccount extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);

        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);

        Button btnCreateAcc = findViewById(R.id.createAcc);

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fName = ((EditText) findViewById(R.id.firstName)).getText().toString();
                String lName = ((EditText) findViewById(R.id.lastName)).getText().toString();
                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String pwd = ((EditText) findViewById(R.id.password)).getText().toString();

                AddAccount(fName, lName, email, pwd);
            }
        });

    }

    public void AddAccount(String fName, String lName, String email, String pwd) {
        boolean insertData = mDatabaseHelper.addAccount(fName, lName, email, pwd);

        if (insertData)
            toastMessage("Data successfully inserted");
        else
            toastMessage("Error. Data insertion failed.");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    }
