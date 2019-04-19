package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutionException;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void onLevelSelectButtonClick(View view){
        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);
    }

    public void onLogoutButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onOnbardingButtonClick(View view) {
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }
}
