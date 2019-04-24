package com.example.stud_ie_app;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertOnlySingleUser (Users users);
    @Insert
    void insertMultipleUsers (List<Users> usersList);
    @Query ("SELECT * FROM Users WHERE userName = :userName")
    Users fetchOneUserByUserName (String userName);
    @Update
    void updateUser (Users users);
    @Delete
    void deleteUser (Users users);

}