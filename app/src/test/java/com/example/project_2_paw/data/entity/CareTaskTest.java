package com.example.project_2_paw.data.entity;

/**
 * @author Manuel Caro
 * @date 12/13/2025
 * @description Unit tests for entity CareTask
 */

import static org.junit.Assert.*;

import org.junit.Test;

import java.time.LocalDate;

/**
 * Unit tests for the CareTask entity.
 * Verifies that getters and setters behave as expected.
 */

public class CareTaskTest {

    @Test
    public void getTaskId() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025, 5, 29), false);
        task.setTaskId(10);
        assertEquals(10, task.getTaskId());
    }

    @Test
    public void setTaskId() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025,5,29),false);
        task.setTaskId(5);
        assertEquals(5, task.getTaskId());
    }

    @Test
    public void getPetId() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025,5,29), false);
        assertEquals(1, task.getPetId());
    }

    @Test
    public void setPetId() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025, 5, 29), false);
        task.setPetId(42);
        assertEquals(42, task.getPetId());
    }

    @Test
    public void getTaskName() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025, 5, 29), false);
        assertEquals("Walk", task.getTaskName());
    }

    @Test
    public void setTaskName() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025, 5, 29), false);
        task.setTaskName("Vet appointment");
        assertEquals("Vet appointment", task.getTaskName());
    }

    @Test
    public void getDueDate() {
        LocalDate date = LocalDate.of(2025, 5, 29);
        CareTask task = new CareTask(1, "Walk", date, false);
        assertEquals(date, task.getDueDate());
    }

    @Test
    public void setDueDate() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025,5,29), false);
    LocalDate newDate = LocalDate.of(2025, 4, 2);
    task.setDueDate(newDate);
    assertEquals(newDate, task.getDueDate());
    }

    @Test
    public void isCompleted() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025,5,29), false);
        assertFalse(task.isCompleted());
    }

    @Test
    public void setCompleted() {
        CareTask task = new CareTask(1, "Walk", LocalDate.of(2025,5,29), false);
        task.setCompleted(true);
        assertTrue(task.isCompleted());
        task.setCompleted(false);
        assertFalse(task.isCompleted());
    }
}