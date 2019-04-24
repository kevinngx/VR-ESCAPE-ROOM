package com.example.stud_ie_app;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.View;

import com.example.stud_ie_app.DatabaseClasses.UserDatabase;

public class SessionData {
    public static Users currentUser = new Users("Kevin", "password", 1);
    public static UserDatabase mUserDatabase;
    public static void createDB(Context context){

         mUserDatabase = Room.databaseBuilder(context.getApplicationContext(),
                UserDatabase.class, "user_db")
                .fallbackToDestructiveMigration()
                .build();

    };


}

