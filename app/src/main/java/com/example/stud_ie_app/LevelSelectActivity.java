package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutionException;

public class LevelSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);
    }

    public void onCategoryOne(View view) {
        System.out.println("Category One");
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void onCategoryTwo(View view) {
        System.out.println("Category Two");
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }

    public void onCategoryThree(View view) {
        System.out.println("Category Three");
    }

    public void onCategoryFour(View view) {
        System.out.println("Category Four");
    }

    public void onCategoryFive(View view) {
        System.out.println("Category Five");
    }

    public void onCategorySix(View view) {
        System.out.println("Category Six");
    }

    public void onCategorySeven(View view) {
        System.out.println("Category Seven");
    }

    public void onCategoryEight(View view) {
        System.out.println("Category Eight");
    }

    public void onCategoryNine(View view) {
        System.out.println("Category Nine");
    }

    public void onCategoryTen(View view) {
        System.out.println("Category Ten");
    }
}
