package com.example.project_2_paw.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.dao.CareTaskDAO;
import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.typeConverters.LocalDateTypeConverter;


/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Main Room Database class for the P.A.W. application.
 * Provides access to DAO objects for User and Pet data.
 */

@Database(entities = { User.class, Pet.class, CareTask.class},
        version = 3, exportSchema = false)
@TypeConverters({LocalDateTypeConverter.class})
public abstract class PawDatabase extends RoomDatabase{
    // DAO accessors
    public abstract UserDAO userDAO();
    public abstract PetDAO petDAO();
    public abstract CareTaskDAO careTaskDAO();

    // Singleton instance of the database
    private static volatile PawDatabase INSTANCE;

    public static PawDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PawDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PawDatabase.class,
                            "paw_database"
                    ) .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
