package com.example.project_wrielses.room.entities.appdatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project_wrielses.room.entities.entities.Bus;
import com.example.project_wrielses.room.entities.entities.Driver;
import com.example.project_wrielses.room.entities.entities.User;
import com.example.project_wrielses.room.entities.dao.MyDao;

@Database(entities = {User.class, Driver.class, Bus.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public static synchronized AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder
                    (context, AppDatabase.class, "notes_dp").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
    public abstract MyDao dao();
}

