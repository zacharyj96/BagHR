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

        mDatabaseHelper = DatabaseHelper.getInstance(this);


        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText confirmPassword = findViewById(R.id.confirmPassword);

        Button btnCreateAcc = findViewById(R.id.createAcc);

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if user exists first, give error message if they don't


                if (!firstName.getText().toString().equals("") && !lastName.getText().toString().equals("") && !email.getText().toString().equals("") && !password.getText().toString().equals("") && password.getText().toString().equals(confirmPassword.getText().toString())) {
                    String fName = firstName.getText().toString();
                    String lName = lastName.getText().toString();
                    String em = email.getText().toString();
                    String pwd = password.getText().toString();

                    User u = new User(fName, lName, em, pwd);

                    AddAccount(u);
                }
            }
        });

    }

    public void AddAccount(User u) {
        boolean insertData = mDatabaseHelper.addAccount(u);

        if (insertData)
            toastMessage("Data successfully inserted");
        else
            toastMessage("Error. Data insertion failed.");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
