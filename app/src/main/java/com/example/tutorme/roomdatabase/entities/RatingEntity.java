package com.example.tutorme.roomdatabase.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ratings", foreignKeys = @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId"))
public class RatingEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long userId;
    private float rating;

    public RatingEntity(long userId, float rating) {
        this.userId = userId;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
