package com.example.project_2_paw.navigation;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.example.project_2_paw.AdminPetsActivity;
import com.example.project_2_paw.MainActivity;
import com.example.project_2_paw.PetCreation;
import com.example.project_2_paw.SignupActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class IntentFactoryTest extends TestCase {
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testCreateMain() {
        String username = "testUser";
        boolean isAdmin = true;
        int userId = 1;

        Intent intent = IntentFactory.createMain(context, username, isAdmin, userId);

        assertEquals(MainActivity.class.getName(), intent.getComponent().getClassName());

        assertEquals(username, intent.getStringExtra(IntentFactory.EXTRA_USERNAME));
        assertEquals(isAdmin, intent.getBooleanExtra(IntentFactory.EXTRA_IS_ADMIN, false));
        assertEquals(userId, intent.getIntExtra(IntentFactory.EXTRA_USER_ID, -1));
    }

    @Test
    public void testCreateSignup() {
        Intent intent = IntentFactory.createSignup(context);
        assertEquals(SignupActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void testCreatePet() {
        int ownerId = 10;
        Intent intent = IntentFactory.createPet(context, ownerId);
        assertEquals(PetCreation.class.getName(), intent.getComponent().getClassName());
        assertEquals(ownerId, intent.getIntExtra(IntentFactory.EXTRA_OWNER_ID, -1));
    }

    @Test
    public void testCreateAdminPets() {
        Intent intent = IntentFactory.createAdminPets(context);
        assertEquals(AdminPetsActivity.class.getName(), intent.getComponent().getClassName());
    }
}