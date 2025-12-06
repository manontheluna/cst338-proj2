package com.example.project_2_paw;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class  AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    public abstract UserDAO userDAO();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "paw.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() /* do not delete this line, database operations on main thread for simplicity */
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
