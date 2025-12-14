package com.example.project_2_paw.data.entity;

import junit.framework.TestCase;

public class PetTest extends TestCase {

    private Pet pet;
    private int ownerId = 1;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        pet = new Pet(1, "pet1", "dog", 2);
    }

    @Override
    protected void tearDown() throws Exception {
        pet = null;
        super.tearDown();
    }

    public void testGetPetId() {
        pet.setPetId(5);
        assertEquals(5, pet.getPetId());
    }

    public void testSetPetId() {
        pet.setPetId(2);
        assertEquals(2, pet.getPetId());
    }

    public void testGetOwnerId() {
        assertEquals(1, pet.getOwnerId());
    }

    public void testSetOwnerId() {
        pet.setOwnerId(2);
        assertEquals(2, pet.getOwnerId());
    }

    public void testTestGetName() {
        assertEquals("pet1", pet.getName());
    }

    public void testTestSetName() {
        pet.setName("Max");
        assertEquals("Max", pet.getName());
    }

    public void testGetSpecies() {
        assertEquals("dog", pet.getSpecies());
    }

    public void testSetSpecies() {
        pet.setSpecies("Cat");
        assertEquals("Cat", pet.getSpecies());
    }

    public void testGetAge() {
        assertEquals(2, pet.getAge());
    }

    public void testSetAge() {
        pet.setAge(7);
        assertEquals(7, pet.getAge());
    }
}