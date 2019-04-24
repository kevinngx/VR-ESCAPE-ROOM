package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SessionData.createDB(this.getApplicationContext());
        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);

    }

    public void onLoginButtonPress(View view) {
        //TODO: Add Login Checks
        System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s",
                username.getText(), password.getText()));

        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    public void onRegisterButtonPress(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


}
