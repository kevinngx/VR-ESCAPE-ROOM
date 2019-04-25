package com.example.stud_ie_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BadgeDao {
    @Insert
    void insertSingleBadge(Badges badges);

    @Insert
    void insertMultipleBadges (List<Badges> badgesList);

    @Query("SELECT * FROM Badges WHERE badgeID = :id ")
    Badges fetchBadgeByID (int id);

    @Query("SELECT * FROM Badges ORDER BY badgeID")
    List<Badges> getAll();

}

