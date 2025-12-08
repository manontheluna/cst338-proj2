package com.example.project_2_paw.data.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.data.entity.Pet;

import java.util.concurrent.Executors;


/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Main Room Database class for the P.A.W. application.
 * Provides access to DAO objects for User and Pet data.
 */

@Database(entities = { User.class, Pet.class }, version = 1, exportSchema = false)
public abstract class PawDatabase extends RoomDatabase{
    // DAO accessors
    public abstract UserDAO userDAO();
    public abstract PetDAO petDAO();

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
                    ).addCallback(cb).build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback cb = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Executors.newSingleThreadExecutor().execute(() -> {
                PawDatabase instance = INSTANCE;
                if (instance != null) {
                    instance.userDAO().insert(
                            new User("admin", "admin123", true)
                    );
                }
            });
        }
    };
}
