package com.example.stud_ie_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.DashboardFragments.FragmentLeaderboard;
import com.example.stud_ie_app.DashboardFragments.FragmentQuiz;
import com.example.stud_ie_app.DashboardFragments.FragmentUser;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "DashboardActivity";

    public static final String CATEGORY = "category";
    private TabLayout dashboardTabLayout;
    private AppBarLayout dashboardAppBarLayout;
    private ViewPager dashboardViewPager;

    Dialog mDialog;

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

        // Set up dialog popup
        mDialog = new Dialog(this);

        setupUserId();
    }

    private void setupUserId() {

        userAvatar = (ImageView) findViewById(R.id.user_avatar);
        userUsername = (TextView) findViewById(R.id.leaderboard_username);
        userRole = (TextView) findViewById(R.id.user_role);
        userScore = (TextView) findViewById(R.id.user_score);

        userAvatar.setImageResource(ImageBank.avatars[SessionData.currentUser.getAvatar()]);
        userUsername.setText(SessionData.currentUser.getUserName());
        userRole.setText(SessionData.currentUser.getRole());
        userScore.setText(Integer.toString(SessionData.currentUser.getScore()));

    }

    public void onLevelSelect(View view) {
        // Identify Level
        int[] levels = {
                R.id.level_transport, // 0
                R.id.level_beach, // 1
                R.id.level_sports, // 2
                R.id.level_jobs, // 3
                R.id.level_weather, // 4
                R.id.level_nature, // 5
                R.id.level_music, // 6
                R.id.level_exercise, // 7
                R.id.level_politics, // 8
                R.id.level_astronomy // 9
        };

        int levelId = 0;
        while (levelId < levels.length) {
            if (view.getId() == levels[levelId])
                break;
            levelId++;
        }
        System.out.println("Level Selected: " + QuestionBank.categories[levelId]);

        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra(CATEGORY, QuestionBank.categories[levelId]);
        startActivity(intent);
    }

    public void onUpdateRoleButtonClick(View view) {
        Log.d(TAG, "onUpdateRole: pressed");
        mDialog.setContentView(R.layout.popup_update_role);

        // Set onClickListeners
        Button popupBack = (Button) mDialog.findViewById(R.id.popup_back);
        Button popupSubmit = (Button) mDialog.findViewById(R.id.popup_submit);

        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        popupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Submit Changes");
            }
        });

        // Launch dialog window
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();

    }

    public void onUpdateAvatarButtonClick(View view) {
        Log.d(TAG, "onUpdateAvatar: pressed");
    }

    public void onUpdatePasswordButtonClick(View view) {
        Log.d(TAG, "onUpdatePassword: pressed");
    }

    public void onLogoutButtonClick(View view) {
        Log.d(TAG, "onLogoutButtonClick: pressed");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void onOnbardingButtonClick(View view) {
        Log.d(TAG, "onOnbardingButtonClick: pressed");
        Intent intent = new Intent(this, OnboardingActivity.class);
        startActivity(intent);
    }
}
