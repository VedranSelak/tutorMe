package com.example.tutorme.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tutorme.roomdatabase.entities.RatingEntity;

import java.util.List;

@Dao
public interface RatingDao {

    @Insert
    void add(RatingEntity ratingEntity);

    @Query("SELECT * FROM ratings JOIN users ON ratings.userId=users.id WHERE users.email=:email")
    List<RatingEntity> getRatingsByUserEmail(String email);

}
