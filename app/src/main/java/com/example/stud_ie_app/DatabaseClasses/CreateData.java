package com.example.stud_ie_app.DatabaseClasses;

import com.example.stud_ie_app.Badges;

import java.util.ArrayList;
import java.util.List;

public class CreateData {

    public static void populateUser() {
        //TODO: Create array list of database objects
        //TODO: Add data to array list
        //TODO: Add arraylist into insertAll Statement
    }

    public static void populateUserBadges() {
        //TODO: Create array list of database objects
        //TODO: Add data to array list
        //TODO: Add arraylist into insertAll Statement
    }

    public static void populateBadgesDatabase() {
        if (SessionData.mBadgeDatabase.mBadgeDao().getAll() == null){
            List<Badges> badgesList = new ArrayList();
            badgesList.add(new Badges(1, "Intern", "Welcome to the company!", 1));
            badgesList.add(new Badges(2, "Graduate", "Reach 1,000 points!", 2));
            badgesList.add(new Badges(3, "Senior", "Reach 5,000 points!", 3));
            badgesList.add(new Badges(4, "Manager", "Reach 10,000 points!", 4));
            badgesList.add(new Badges(5, "Exec", "Reach 25,000,000 points!", 5));
            badgesList.add(new Badges(6, "Beach Guru", "5 correct in one session: Beach", 5));
            badgesList.add(new Badges(7, "Transport Guru", "5 correct in one session: Transport", 7));
            badgesList.add(new Badges(8, "Animal Guru", "5 correct in one session: Animals", 8));
            badgesList.add(new Badges(9, "Sports Guru", "5 correct in one session: Sports", 9));
            badgesList.add(new Badges(10, "Job Guru", "5 correct in one session: Jobs", 10));
            badgesList.add(new Badges(11, "Weather Guru", "5 correct in one session: Weather", 11));
            badgesList.add(new Badges(12, "Nature Guru", "5 correct in one session: Nature", 12));
            badgesList.add(new Badges(13, "Music Guru", "5 correct in one session: Music", 13));
            badgesList.add(new Badges(14, "Exercise Guru", "5 correct in one session: Exercise", 14));
            badgesList.add(new Badges(15, "Politics Guru", "5 correct in one session: Politics", 15));
            badgesList.add(new Badges(16, "Astronomy Guru", "5 correct in one session: Astronomy", 16));
            badgesList.add(new Badges(17, "Hustler", "10 questions in one sitting", 17));
            badgesList.add(new Badges(18, "Genius", "Get 10 correct questions in a row sitting", 18));
            badgesList.add(new Badges(19, "LinkedIn Stalker", "Check out the leaderboard", 19));
            badgesList.add(new Badges(20, "Curious Learner", "Learned about other usages for a word", 20));
            badgesList.add(new Badges(21, "Fired", "Get 5 questions incorrect in a row", 21));
            badgesList.add(new Badges(22, "Two Faced", "Updated user avatar", 22));
            badgesList.add(new Badges(23, "Cyber Security Specialist", "Updated password", 23));
            badgesList.add(new Badges(24, "The Prodigy", "5 Correct in a row for every category", 24));
            SessionData.mBadgeDatabase.mBadgeDao().insertMultipleBadges(badgesList);
        }
    }

    public static void  populateWord() {
        //TODO: Create array list of database objects
        //TODO: Add data to array list
        //TODO: Add arraylist into insertAll Statement
    }

}
