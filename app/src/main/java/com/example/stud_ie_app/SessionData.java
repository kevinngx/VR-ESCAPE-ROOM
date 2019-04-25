package com.example.stud_ie_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;

import com.example.stud_ie_app.DatabaseClasses.BadgeDatabase;
import com.example.stud_ie_app.DatabaseClasses.UserDatabase;
import com.example.stud_ie_app.DatabaseClasses.UsrBadgesDatabase;

public class SessionData {
    public static Users currentUser = new Users("Kevin", "password", 1);
    public static UserDatabase mUserDatabase;
    public static BadgeDatabase mBadgeDatabase;
    public static UsrBadgesDatabase mUsrBadgesDatabase;
    public static void createDB(Context context){

         mUserDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "user_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

         mBadgeDatabase = Room.databaseBuilder(context.getApplicationContext(),
                BadgeDatabase.class, "badge_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
         mUsrBadgesDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UsrBadgesDatabase.class, "usrbadge_db").allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    };


}

