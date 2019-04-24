package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DatabaseClasses.UserDatabase;

public class RegistrationTwoActivity extends AppCompatActivity {

    ImageView id_image;
    TextView id_username;
    String newUsername;
    String newPassword;
    int newAvatar = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_two);

        id_image = (ImageView) findViewById(R.id.user_avatar);
        id_username = (TextView) findViewById(R.id.user_username);

        Bundle bundle = getIntent().getExtras();

        newUsername = bundle.getString(RegistrationActivity.NEW_USERNAME);
        newPassword = bundle.getString(RegistrationActivity.NEW_PASSWORD);

        System.out.println("username is: " + newUsername);
        id_username.setText(newUsername);

    }

    public void onButtonPress(View view) {
        System.out.println("Button Pressed");
        System.out.println("id is: " + view.getId());

        // Find avatar
        int[][] avatars = {
                {R.id.avatar_zero, R.drawable.avatar0},
                {R.id.avatar_one, R.drawable.avatar1},
                {R.id.avatar_two, R.drawable.avatar2},
                {R.id.avatar_three, R.drawable.avatar3},
                {R.id.avatar_four, R.drawable.avatar4},
                {R.id.avatar_five, R.drawable.avatar5},
                {R.id.avatar_six, R.drawable.avatar6},
                {R.id.avatar_seven, R.drawable.avatar7},
                {R.id.avatar_eight, R.drawable.avatar8}
        };

        newAvatar = 0;
        while (newAvatar < avatars.length) {
            if (view.getId() == avatars[newAvatar][0])
                break;
            newAvatar++;
        }
        System.out.println("Selected avatar: " + newAvatar);

        // Update Employee id_card
        id_image.setImageResource(avatars[newAvatar][1]);


    }

    public void onSubmitButtonPress(View view) {
        //TODO: Add Login Checks
//        System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s \nPasswrod: %s",
//                newUsername.getText(), newPasswordOne.getText(), newPasswordTwo.getText()));

        //create user
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = getIntent().getExtras();
                Users user = new Users(bundle.getString(RegistrationActivity.NEW_USERNAME), bundle.getString(RegistrationActivity.NEW_PASSWORD), newAvatar );
                SessionData.mUserDatabase.mUserDao () . insertOnlySingleUser (user);
                SessionData.currentUser = user;
            }
        }) .start();
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    public void onBackToLoginButtonPress(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
