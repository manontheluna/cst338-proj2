package com.example.project_2_paw.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project_2_paw.data.entity.CareTask;

import java.util.List;
@Dao
public interface CareTaskDAO {

    @Insert
    void insertTask(CareTask task);

    @Update
    void updateTask(CareTask task);

    @Delete
    void deleteTask(CareTask task);

    @Query("SELECT * FROM caretask WHERE petId = :petId")
    LiveData<List<CareTask>> getTasksForPet(int petId);

    @Query("SELECT * FROM CareTask")
    List<CareTask> getAllTasks();
    @Query("SELECT * FROM caretask WHERE petId = :petId")
    List<CareTask> getTasksForPetSync(int petId);
    @Query("SELECT * FROM caretask WHERE petId = :petId AND isCompleted = :completed")
    List<CareTask> getTasksForPetByCompletionSync(int petId, boolean completed);

}
