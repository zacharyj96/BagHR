package com.example.baghr;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CreateAccount extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    EditText firstName, lastName, email, password, confirmPassword;

    Spinner spin;

    String[] userTypes = {"Admin", "Manager", "HR", "Salesperson"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createaccount);

        mDatabaseHelper = DatabaseHelper.getInstance(this);


        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        spin = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        final Context context = CreateAccount.this;

        Button btnCreateAcc = findViewById(R.id.createAcc);

        btnCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    // check if user exists first, give error message if they don't
                    String em = email.getText().toString();
                    List<User> users = mDatabaseHelper.getUserByEmail(em);
                    if (users.size() == 0) {
                        // create user account
                        String fName = firstName.getText().toString();
                        String lName = lastName.getText().toString();
                        String userType = spin.getSelectedItem().toString().toLowerCase();


                        try {
                            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

                            SecureRandom random = new SecureRandom();
                            byte[] salt = new byte[16];
                            random.nextBytes(salt);

                            KeySpec keyspec = new PBEKeySpec(password.getText().toString().toCharArray(), salt, 10000, 160);
                            Key key = factory.generateSecret(keyspec);

                            byte[] hash = key.getEncoded();

                            byte[] hashBytes = new byte[36];

                            System.arraycopy(salt, 0, hashBytes, 0, 16);
                            System.arraycopy(hash, 0, hashBytes, 16, 20);

                            String savedPasswordHash = new String(org.apache.commons.codec.binary.Base64.encodeBase64(hashBytes));

                            User u = new User(fName, lName, em, savedPasswordHash, 0, userType);

                            addAccount(u);
                        } catch (Exception e) {
                            e.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    builder.setMessage("Error creating account");
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
                        mDatabaseHelper.updateUserByEmail(email.getText().toString(), firstName.getText().toString(), lastName.getText().toString());
                        // user already exists
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setMessage("User already exists");
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

    }

    private void addAccount(User u) {
        boolean insertData = mDatabaseHelper.addAccount(u);

        if (insertData)
            toastMessage("Data successfully inserted");
        else
            toastMessage("Error. Data insertion failed.");
    }

    private boolean validateFields() {
        return !firstName.getText().toString().equals("") && !lastName.getText().toString().equals("") && !email.getText().toString().equals("") && !password.getText().toString().equals("") && password.getText().toString().equals(confirmPassword.getText().toString()) && !spin.getSelectedItem().toString().equals("");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
