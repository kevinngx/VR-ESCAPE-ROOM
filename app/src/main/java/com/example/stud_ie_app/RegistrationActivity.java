package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    public final static String NEW_USERNAME ="com.example.stud_ie_app.new_username";
    public final static String NEW_PASSWORD ="com.example.stud_ie_app.new_password";

    private TextView newUsername;
    private TextView newPasswordOne;
    private TextView newPasswordTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        newUsername = findViewById(R.id.newUsername);
        newPasswordOne = findViewById(R.id.newPasswordOne);
        newPasswordTwo = findViewById(R.id.newPasswordTwo);

    }

    public void onSubmitButtonPress(View view) {
        //TODO: Add Login Checks
        System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s \nPasswrod: %s",
                newUsername.getText(), newPasswordOne.getText(), newPasswordTwo.getText()));

        Intent intent = new Intent(this, RegistrationTwoActivity.class);
        intent.putExtra(NEW_USERNAME, newUsername.getText().toString());
        intent.putExtra(NEW_PASSWORD, newPasswordOne.getText().toString());
        startActivity(intent);
    }

    public void onBackToLoginButtonPress(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
