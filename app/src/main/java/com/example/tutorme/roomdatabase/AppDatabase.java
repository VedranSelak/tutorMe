package com.example.tutorme.roomdatabase;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {UserEntity.class, PostEntity.class, FavouritesEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName = "tutorMe_db";
    public abstract UserDao userDao();
    public abstract PostDao postDao();
    public abstract FavouritesDao favouritesDao();
    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getAppDatabase(Context context){
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName).allowMainThreadQueries().build();
        }
        return appDatabase;
    }


}
