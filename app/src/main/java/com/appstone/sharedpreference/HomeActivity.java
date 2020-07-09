package com.appstone.sharedpreference;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUserName = findViewById(R.id.tv_user_name);
        Button btnLogout = findViewById(R.id.btn_logout);

        prefManager = getApplicationContext().getSharedPreferences("LOGIN", MODE_PRIVATE);
        editor = prefManager.edit();

        String userName = prefManager.getString("USERNAME", "");

        tvUserName.setText("Hello! ".concat(userName));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(HomeActivity.this).setTitle("Alert")
                        .setMessage("Are you sure you want to Logout")
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                editor.putBoolean("ISLOGGEDIN", false);
                                editor.putString("USERNAME", "");
                                editor.putString("EMAIL", "");
                                editor.putString("PASSWORD", "");
                                editor.apply();

                                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();


            }
        });
    }

    public void onShowToastClicked(View view) {
        Toast.makeText(HomeActivity.this, "Toast Button Clicked", Toast.LENGTH_LONG).show();
    }

    public void onShowSnackBarClicked(View view) {
        Snackbar.make(view, "SnackBar is Clicked", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    public void onShowAlertDialogClicked(View view) {
        new AlertDialog.Builder(HomeActivity.this).setTitle("Alert")
                .setMessage("Alert is being shown")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).show();

    }
}