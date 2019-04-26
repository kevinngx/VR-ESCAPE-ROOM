package com.example.stud_ie_app;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity (primaryKeys = {"username", "badgeID"})
public class UsrBadges {
//    @NonNull
//    @PrimaryKey
//    private String UsrBadgeID;
    @NonNull
    private String username;
    @NonNull
    private int badgeID;

//    public UsrBadges(String usrBadgeID, String username, int badgeID) {
//        this.UsrBadgeID = username + badgeID;
//        this.username = username;
//        this.BadgeID = badgeID;
//    }


    public UsrBadges(@NonNull String username, int badgeID) {
        this.username = username;
        this.badgeID = badgeID;
    }

    @Ignore
    public UsrBadges() {
    }

//    @NonNull
//    public String getUsrBadgeID() {
//        return UsrBadgeID;
//    }
//
//    public void setUsrBadgeID(@NonNull String usrBadgeID) {
//        UsrBadgeID = usrBadgeID;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }
}