package com.appstone.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUserName;
    private EditText mEtEmailAddress;
    private EditText mEtPassword;

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUserName = findViewById(R.id.et_user_name);
        mEtEmailAddress = findViewById(R.id.et_email_address);
        mEtPassword = findViewById(R.id.et_password);

        prefManager = getApplicationContext().getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = prefManager.edit();


        boolean isUserLoggedIn = prefManager.getBoolean("ISLOGGEDIN", false);

        if (isUserLoggedIn) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveToHomeScreen();
                }
            }, 1000);


        }

    }

    //ui thread
    //background thread

    public void onLoginClicked(View view) {
        String username = mEtUserName.getText().toString();
        String email = mEtEmailAddress.getText().toString();
        String password = mEtPassword.getText().toString();

        editor.putString("USERNAME", username);
        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.putBoolean("ISLOGGEDIN", true);

        editor.apply(); // for ui thread


        moveToHomeScreen();

//        editor.commit(); // for background thread.
    }


    private void moveToHomeScreen() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
//        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
//        startActivity(homeIntent);
    }
}