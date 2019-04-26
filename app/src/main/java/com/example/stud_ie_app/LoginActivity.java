package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.CreateData;
import com.example.stud_ie_app.DatabaseClasses.SessionData;

public class LoginActivity extends AppCompatActivity {
    public boolean usrExist = false;
//    public boolean loginCorrect = false;
    private TextView username;
    private TextView password;
    private TextView errorMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SessionData.createDB(this.getApplicationContext());
        username = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
        errorMsg = findViewById(R.id.errorMessage);

//        CreateData.populateBadgesDatabase();
//        CreateData.populateUserBadges();

    }

    public void onLoginButtonPress(View view) {
        Users testUser = SessionData.mUserDatabase.mUserDao().fetchOneUserByUserName(username.getText().toString());

        //TODO: Add Login Checks
        System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s",
                username.getText(), password.getText()));
        if(username.getText().toString().equals("")) {
            errorMsg.setText("Please fill out the username field");
        } else if (password.getText().toString().equals("")) {
            errorMsg.setText("Please fill out the password field");
        } else if (testUser == null){
            errorMsg.setText("User doesn't exist");
        } else if (testUser.getPassword().toString().equals(password.getText().toString())){

            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
            SessionData.currentUser = testUser;
        } else{
            errorMsg.setText("Your login details are incorrect");
        }
    }

    public void onRegisterButtonPress(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


}
