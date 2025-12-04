package com.example.project_2_paw.data.repository;

import android.content.Context;

import com.example.project_2_paw.data.dao.PetDAO;
import com.example.project_2_paw.data.dao.UserDAO;
import com.example.project_2_paw.data.db.PawDatabase;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.entity.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PawRepository {
    private final UserDAO userDAO;
    private final PetDAO petDAO;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PawRepository(Context context) {
        PawDatabase db = PawDatabase.getInstance(context);
        userDAO = db.userDAO();
        petDAO = db.petDAO();
    }

    public void insertUser(User user) {
        executor.execute(() -> userDAO.insert(user));
    }

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public void insertPet(Pet pet) {
        executor.execute(() -> petDAO.insert(pet));
    }

    public List<Pet> getPetsByOwnerId(int ownerId) {
        return petDAO.getPetsByOwnerId(ownerId);
    }
}
