package com.example.baghr;

import android.app.Activity;
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

public class Login extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupWindowAnimations();

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

    private boolean FieldValidate() {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        return !username.getText().equals("") && !password.getText().equals("");
    }
}
