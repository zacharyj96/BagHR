package com.example.baghr;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.sqlite.*;

import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Login extends Activity {

    private boolean authenticated;
    private ProgressDialog dialog;
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupWindowAnimations();

        //SQLiteDatabase mydatabase = openOrCreateDatabase("BagHR", MODE_PRIVATE, null);

        //mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Account (email TEXT PRIMARY KEY, first_name TEXT NOT NULL, last_name TEXT NOT NULL, password TEXT NOT NULL);");


        mDatabaseHelper = DatabaseHelper.getInstance(this);


        // hides keyboard when activity is pressed
        findViewById(R.id.mainLayoutLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        EditText user = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);

        final Context context = Login.this;

        TextView versionNumber = findViewById(R.id.versionNumber);

        // sets version number based on app bundle version
        try {
            versionNumber.setText("Version " + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Button btnLogin = findViewById(R.id.login);

        Button btnSignUp = findViewById(R.id.signUp);

        // navigates to create account page
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreateAccount.class);

                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // if fields are valid, try logging in
                if (fieldValidate()) {
                    // sets up progress dialog while page is loading
                    dialog = new ProgressDialog(context);
                    dialog.setMessage("Loading");
                    dialog.setCancelable(false);
                    dialog.setInverseBackgroundForced(false);
                    dialog.show();

                    //creates new thread used to login
                    Thread backgroundThread = new Thread(new Runnable() {
                        public void run() {
                            authenticated = false;
                            String username = ((EditText) findViewById(R.id.username)).getText().toString();
                            String password = ((EditText) findViewById(R.id.password)).getText().toString();

                            // authentication code

                            List<User> users = mDatabaseHelper.getUserByEmail(username);
                            if (users.size() > 0) {
                                try {
                                    User u = users.get(0);
                                    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

                                    // converts base64 string stored in database into byte array
                                    byte[] bytePass = org.apache.commons.codec.binary.Base64.decodeBase64(u.password.getBytes());

                                    // first 16 bytes is the salt
                                    byte[] salt = Arrays.copyOfRange(bytePass, 0, 16);

                                    // last 20 bytes is the password
                                    byte[] compare = Arrays.copyOfRange(bytePass, 16, 36);

                                    // takes user entered password and hashes it
                                    KeySpec keyspec = new PBEKeySpec(password.toCharArray(), salt, 10000, 160);
                                    Key key = factory.generateSecret(keyspec);

                                    // if arrays equal, then user is authenticated

                                    if (Arrays.equals(compare, key.getEncoded())) {
                                        authenticated = true;
                                    }
                                } catch (Exception e) {
                                    // error authenticating hash, display message
                                    e.printStackTrace();
                                    dialog.dismiss();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                            builder.setMessage("Error authenticating password");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    });
                                }

                                // if user is authenticated, load main activity
                                if (authenticated) {
                                    launchActivity();
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                            builder.setMessage("Error authenticating password");
                                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                }
                                            });
                                            AlertDialog dialog = builder.create();
                                            dialog.show();
                                        }
                                    });
                                }

                                launchActivity();
                                dialog.dismiss();
                            } else {
                                // user doesn't exist, display error message
                                dialog.dismiss();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setMessage("Account doesn't exist");
                                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                });
                            }

                            // dismisses progress dialog
                            dialog.dismiss();
                        }
                    });
                    // start login thread in background
                    backgroundThread.start();
                }
            }
        });


    }

    // handles activity transitions
    private void setupWindowAnimations() {
        Slide slide = new Slide(Gravity.LEFT);
        slide.setDuration(1200);
        getWindow().setEnterTransition(slide);
        getWindow().setAllowEnterTransitionOverlap(false);
    }

    // launches main activity
    private void launchActivity() {
        Intent intent = new Intent(this, Main.class);

        startActivity(intent);
    }

    private boolean fieldValidate() {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        return !username.getText().toString().equals("") && !password.getText().toString().equals("");
    }

}
