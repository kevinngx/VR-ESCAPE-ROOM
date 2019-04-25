package com.example.stud_ie_app;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DashboardFragments.FragmentLeaderboard;
import com.example.stud_ie_app.DashboardFragments.FragmentQuiz;
import com.example.stud_ie_app.DashboardFragments.FragmentUser;

public class DashboardActivity extends AppCompatActivity {

    public static final String CATEGORY = "category";
    private TabLayout dashboardTabLayout;
    private AppBarLayout dashboardAppBarLayout;
    private ViewPager dashboardViewPager;

    ImageView userAvatar;
    TextView userUsername;
    TextView userRole;
    TextView userScore;

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
        adapter.addFragments(new FragmentLeaderboard(), "Leaderboard");

        // Setting up Adapter
        dashboardViewPager.setAdapter(adapter);
        dashboardTabLayout.setupWithViewPager(dashboardViewPager);

        setupUserId();
    }

    private void setupUserId() {
        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userUsername = (TextView) findViewById(R.id.user_username);
        userRole = (TextView) findViewById(R.id.user_role);
        userScore = (TextView) findViewById(R.id.user_score);

        int[] avatars = {
                R.drawable.avatar0,
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3,
                R.drawable.avatar4,
                R.drawable.avatar5,
                R.drawable.avatar6,
                R.drawable.avatar7,
                R.drawable.avatar8,
        };

        userAvatar.setImageResource(avatars[SessionData.currentUser.getAvatar()]);
        userUsername.setText(SessionData.currentUser.getUserName());
        userRole.setText(SessionData.currentUser.getRole());
        userScore.setText(Integer.toString(SessionData.currentUser.getScore()));

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
                "Transport",
                "Animals",
                "Sports",
                "Jobs",
                "Weather",
                "Nature",
                "Music",
                "Exercise",
                "Politics",
                "Astronomy"
        };

        int levelId = 0;
        while (levelId < levels.length) {
            if (view.getId() == levels[levelId])
                break;
            levelId++;
        }
        System.out.println("Level Selected: " + levelName[levelId]);

        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(CATEGORY, levelName[levelId]);
        startActivity(intent);
    }
}
