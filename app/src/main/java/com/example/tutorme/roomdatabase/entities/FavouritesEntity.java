package com.example.tutorme.roomdatabase.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.tutorme.roomdatabase.UserEntity;

@Entity(tableName = "favourites", foreignKeys = {@ForeignKey(entity= PostEntity.class, parentColumns="id", childColumns="postId"), @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId")})
public class FavouritesEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;

    private long postId;
    private long userId;

    public FavouritesEntity(long postId, long userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
