package com.example.tutorme.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tutorme.roomdatabase.entities.PostEntity;

import java.util.List;
@Dao
public interface PostDao {
    @Insert
    void add(PostEntity post);

    @Query("SELECT * FROM posts")
    List<PostEntity> getAll();

    @Query("SELECT * FROM posts WHERE emailOfTutor=:email")
    List<PostEntity> getAllByUserEmail(String email);

    @Query("SELECT * FROM posts WHERE id = :id LIMIT 1")
    PostEntity getPostById(long id);

    @Query("UPDATE posts SET fieldName=:fieldName, description=:description WHERE id=:id")
    void updatePost(String fieldName, String description, long id);

    @Delete
    void delete(PostEntity post);
}
