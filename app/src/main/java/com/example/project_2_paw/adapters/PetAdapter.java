package com.example.project_2_paw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.data.entity.Pet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Luna
 * RecyclerView Adapter that displays list of pets associated
 * with currently logged in user shows pet name and details (species/age)
 * create instance of pet adapter
 * set it on a recycler view
 * call set pets to populate list
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    private List<Pet> pets = new ArrayList<>();

    public interface OnPetClickListener {
        void onPetClick(Pet pet);
    }

    private OnPetClickListener listener;

    // Constructor that takes a listener
    public PetAdapter(OnPetClickListener listener) {
        this.listener = listener;
    }

    // Default constructor
    public PetAdapter() { }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PetViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.name.setText(pet.getName());
        holder.details.setText(pet.getSpecies() + "- Age: " + pet.getAge());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPetClick(pet);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    class PetViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView details;

        public PetViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(android.R.id.text1);
            details = itemView.findViewById(android.R.id.text2);
        }
    }
}
