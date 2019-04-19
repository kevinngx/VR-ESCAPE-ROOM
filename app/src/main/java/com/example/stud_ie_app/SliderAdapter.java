package com.example.stud_ie_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;
    }

    public int[] slideImages = {
            //TODO: Replace these with actual photos
            R.drawable.placeholder1,
            R.drawable.placeholder2,
            R.drawable.placeholder3,
            R.drawable.placeholder1
    };

    public String[] slideHeadings = {
            "Welcome",
            "Learn",
            "Grow",
            "Compete",
    };

    public String[] slideDescriptions = {
            "Stud.ie is a quiz app that will test your mastery over the english language.",
            "Here you will be tested on your knowledge of english grammar. Each question will " +
                    "present you with a sentence with a missing word. It is your job to find the " +
                    "missing word. Match it correctly to build your compendium of words.",
            "As your knowledge base increases, you will gain experience points and level up, " +
                    "climbing the corporate ladder, and unlocking new levels that will reap even" +
                    "more experience points.",
            "It doesn't stop there, track your performance against other wordsmiths around the " +
                    "world.",
    };


    @Override
    public int getCount() {
        return slideHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImage = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slideDescription);

        slideImage.setImageResource(slideImages[position]);
        slideHeading.setText(slideHeadings[position]);
        slideDescription.setText(slideDescriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
