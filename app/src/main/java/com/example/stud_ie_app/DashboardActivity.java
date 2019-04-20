package com.example.stud_ie_app;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    private TabLayout dashboardTabLayout;
    private AppBarLayout dashboardAppBarLayout;
    private ViewPager dashboardViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        dashboardTabLayout = (TabLayout) findViewById(R.id.dashboard_tab_layout);
        dashboardAppBarLayout = (AppBarLayout) findViewById(R.id.dashboard_app_bar);
        dashboardViewPager = (ViewPager) findViewById(R.id.dashboard_viewpager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Adding fragments to the ViewPager
        adapter.addFragments(new FragmentUser(), "User");
        adapter.addFragments(new FragmentQuiz(), "Quiz");
        adapter.addFragments(new FragmentStats(), "Stats");

        // Setting up Adapter
        dashboardViewPager.setAdapter(adapter);
        dashboardTabLayout.setupWithViewPager(dashboardViewPager);
    }

    public void onLogoutButtonClick(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onOnbardingButtonClick(View view) {
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }

    public void onLevelSelect(View view) {
        // Identify Level
        int[] levels = {
                R.id.level_transport, // 0
                R.id.level_animals, // 1
                R.id.level_sports, // 2
                R.id.level_jobs, // 3
                R.id.level_weather, // 4
                R.id.level_nature, // 5
                R.id.level_music, // 6
                R.id.level_exercise, // 7
                R.id.level_politics, // 8
                R.id.level_astronomy // 9
        };

        String[] levelName = {
                "transport",
                "animals",
                "sports",
                "jobs",
                "weather",
                "nature",
                "music",
                "exercise",
                "politics",
                "astronomy"
        };

        int levelId = 0;
        while (levelId < levels.length) {
            if (view.getId() == levels[levelId])
                break;
            levelId++;
        }
        System.out.println("Level Selected: " + levelName[levelId]);

        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
