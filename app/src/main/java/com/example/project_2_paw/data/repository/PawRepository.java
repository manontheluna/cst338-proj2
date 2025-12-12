package com.example.project_2_paw.data.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;

import com.example.project_2_paw.data.dao.CareTaskDAO;
import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.data.entity.CareTask;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author: Quratulain Siddiq
 * Project 2: P.A.W (Pet, Activity & Wellness)
 * Repository layer for accessing User and Pet data through DAOs.
 * Handles threading for database operations.
 */

public class PawRepository {
    private final UserDAO userDAO;
    private final PetDAO petDAO;
    private final CareTaskDAO careTaskDAO;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PawRepository(Context context) {
        PawDatabase db = PawDatabase.getInstance(context);
        userDAO = db.userDAO();
        petDAO = db.petDAO();
        careTaskDAO = db.careTaskDAO();
    }

    // ----- User operations -----
    public void insertUser(User user) {
        executor.execute(() -> userDAO.insert(user));
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    // ----- Pet operations -----
    public void insertPet(Pet pet) {
        executor.execute(() -> petDAO.insert(pet));
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return petDAO.getPetsByOwnerId(ownerId);
    }

    // ----- CareTask operations -----
    public void insertTask(CareTask task) {
        executor.execute(() -> careTaskDAO.insertTask(task));
    }
    public void updateTask(CareTask task) {
        executor.execute(() -> careTaskDAO.updateTask(task));
    }
    public void deleteTask(CareTask task) {
        executor.execute(() -> careTaskDAO.deleteTask(task));
    }
    public LiveData<List<CareTask>> getTasksForPet(int petId) {
        return careTaskDAO.getTasksForPet(petId);
    }
    public List<CareTask> getTasksForPetSync(int petId) {
        return careTaskDAO.getTasksForPetSync(petId);
    }
}
