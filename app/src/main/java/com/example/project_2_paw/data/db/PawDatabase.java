package com.example.project_2_paw.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.entity.User;

@Database(entities = { User.class, Pet.class }, version = 1, exportSchema = false)
public abstract class PawDatabase extends RoomDatabase{
    private static volatile PawDatabase INSTANCE;

    public abstract UserDAO userDAO();
    public abstract PetDAO petDAO();

    public static PawDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PawDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PawDatabase.class,
                            "paw_database"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
