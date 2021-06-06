package com.example.tutorme.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavouritesDao {
    @Insert
    void add(FavouritesEntity favouritesEntity);

    @Query("SELECT * FROM favourites JOIN users ON favourites.userId=users.id WHERE users.email=:email")
    List<FavouritesEntity> getAllByUserEmail(String email);

    @Query("DELETE FROM favourites WHERE postId=:postId AND userId=:userId")
    void removeFromFavourites(long postId, long userId);
}
