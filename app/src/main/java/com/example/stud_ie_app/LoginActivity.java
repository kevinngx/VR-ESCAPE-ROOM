package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

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


        if (SessionData.mBadgeDatabase.mBadgeDao().getAll() == null){
            List<Badges> badgesList = new ArrayList();
            badgesList.add(new Badges(1, "Intern", "100 points!", 1));
            badgesList.add(new Badges(2, "Graduate", "300 points!", 2));
            badgesList.add(new Badges(3, "Senior", "1000 points!", 3));
            badgesList.add(new Badges(4, "Manager", "10,000 points!", 4));
            badgesList.add(new Badges(5, "Exec", "50,000 points!", 5));
            badgesList.add(new Badges(6, "Graduate", "300 points!", 6));
            badgesList.add(new Badges(7, "Transport Guru", "10 correct in one session: Transport", 7));
            badgesList.add(new Badges(8, "Animal Guru", "10 correct in one session: Animals", 8));
            badgesList.add(new Badges(9, "Sports Guru", "10 correct in one session: Sports", 9));
            badgesList.add(new Badges(10, "Job Guru", "10 correct in one session: Jobs", 10));
            badgesList.add(new Badges(11, "Weather Guru", "10 correct in one session: Weather", 11));
            badgesList.add(new Badges(12, "Nature Guru", "10 correct in one session: Nature", 12));
            badgesList.add(new Badges(13, "Music Guru", "10 correct in one session: Music", 13));
            badgesList.add(new Badges(14, "Exercise Guru", "10 correct in one session: Exercise", 14));
            badgesList.add(new Badges(15, "Politics Guru", "10 correct in one session: Politics", 15));
            badgesList.add(new Badges(16, "Astronomy Guru", "10 correct in one session: Astronomy", 16));
            SessionData.mBadgeDatabase.mBadgeDao().insertMultipleBadges (badgesList);
        }




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
        //test = SessionData.mUserDatabase.mUserDao () . fetchOneUserByUserName(username.getText().toString());

    }

    public void onRegisterButtonPress(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


}
