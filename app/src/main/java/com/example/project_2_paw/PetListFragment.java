package com.example.project_2_paw;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.adapters.PetAdapter;
import com.example.project_2_paw.data.entity.Pet;
import com.example.project_2_paw.data.repository.PawRepository;

import java.util.List;
import java.util.concurrent.Executors;

public class PetListFragment extends Fragment {

    private RecyclerView petRecyclerView;
    private PetAdapter petAdapter;
    private PawRepository repository;
    private int currentUserId;

    public static PetListFragment newInstance(int userId) {
        PetListFragment fragment = new PetListFragment();
        Bundle args = new Bundle();
        args.putInt("userId", userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentUserId = getArguments().getInt("userId");
        }
        repository = new PawRepository(requireContext());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pet_list, container, false);

        petRecyclerView = view.findViewById(R.id.petRecycler);
        petRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        petAdapter = new PetAdapter(pet -> {
            Intent intent = new Intent(requireContext(), PetTasksActivity.class);
            intent.putExtra(PetTasksActivity.EXTRA_PET_ID, pet.getPetId());
            intent.putExtra(PetTasksActivity.EXTRA_PET_NAME, pet.getName());
            startActivity(intent);
        });

        petRecyclerView.setAdapter(petAdapter);

        repository = new PawRepository(requireContext());

        loadPets();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPets();
    }

    private void loadPets() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Pet> pets = repository.getPetsByOwnerId(currentUserId);

            if (isAdded()) { // Fragment is attached to Activity
                requireActivity().runOnUiThread(() -> petAdapter.setPets(pets));
            }
        });
    }
}


