package com.example.stud_ie_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter mSliderAdapter;

    // Navigation Buttons
    private Button startButton;
    private Button nextButton;
    private Button previousButton;
    private int currentPage;
    Animation btnAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mSliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(mSliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        // Setting up navigation buttons

        nextButton = (Button) findViewById(R.id.nextButton);
        previousButton = (Button) findViewById(R.id.previousButton);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setEnabled(false);
        startButton.setVisibility(View.INVISIBLE);
        btnAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(currentPage + 1);

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(currentPage - 1);

            }
        });
    }

    public void onStartButtonPress(View view) {

        if (currentPage == (mDots.length - 1)) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }

    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[5];
        mDotLayout.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(Color.GRAY);

            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(Color.WHITE);
        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {


        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if (i == 0) {

                // Next Button
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);

                // Previous Button
                previousButton.setEnabled(false);
                previousButton.setVisibility(View.INVISIBLE);

                // Setup start button
                startButton.setEnabled(false);
                startButton.setVisibility(View.INVISIBLE);

            } else if (i == (mDots.length - 1)) {

                // Next Button
                nextButton.setEnabled(false);
                nextButton.setVisibility(View.INVISIBLE);

                // Preivous Button
                previousButton.setEnabled(true);
                previousButton.setVisibility(View.VISIBLE);

                // Setup start button
                startButton.setEnabled(true);
                startButton.setVisibility(View.VISIBLE);
                startButton.setAnimation(btnAnimation);

            } else {

                // Next Button
                nextButton.setEnabled(true);
                nextButton.setVisibility(View.VISIBLE);

                previousButton.setEnabled(true);
                previousButton.setVisibility(View.VISIBLE);

                // Setup start button
                startButton.setEnabled(false);
                startButton.setVisibility(View.INVISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
