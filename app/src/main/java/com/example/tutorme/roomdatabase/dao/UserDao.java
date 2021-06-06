package com.example.tutorme.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tutorme.roomdatabase.entities.UserEntity;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    UserEntity loginUser(String email);

    @Query("DELETE FROM users")
    void deleteAll();
}
