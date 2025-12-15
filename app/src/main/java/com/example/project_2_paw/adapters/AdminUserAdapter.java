package com.example.project_2_paw.adapters;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_2_paw.R;
import com.example.project_2_paw.data.entity.User;
import com.example.project_2_paw.data.repository.PawRepository;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.UserViewHolder> {
    private final List<User> users;
    private final PawRepository repo;
    private final int currentAdminId;

    public AdminUserAdapter(List<User> users, PawRepository repo, int currentAdminId) {
        this.users = users;
        this.repo = repo;
        this.currentAdminId = currentAdminId;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_user, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User u = users.get(position);
        holder.txtUsername.setText(u.getUsername());
        holder.txtAdminFlag.setVisibility(u.isAdmin() ? View.VISIBLE : View.GONE);

        // Reset state before setting click listeners.
        holder.btnDeactivateUser.setVisibility(View.VISIBLE);
        holder.btnDeactivateUser.setOnClickListener(null);
        holder.btnDeactivateUser.setEnabled(true);
        holder.btnDeactivateUser.setAlpha(1f);

        // Block self-deactivation
        if (u.getUserId() == currentAdminId) {
            holder.btnDeactivateUser.setVisibility(View.INVISIBLE);
            return;
        }

        holder.btnDeactivateUser.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Deactivate user?")
                    .setMessage("This user will no longer be able to log in.")
                    .setPositiveButton("Deactivate", (dialog, which) -> {
                        int pos = holder.getBindingAdapterPosition();
                        if (pos == RecyclerView.NO_POSITION) return;

                        User target = users.get(pos);

                        // Safety: if something changed, still block self
                        if (target.getUserId() == currentAdminId) return;

                        repo.deactivateUser(target.getUserId());

                        // Remove from the admin list for cleanliness
                        users.remove(pos);
                        notifyItemRemoved(pos);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsername, txtAdminFlag;
        ImageButton btnDeactivateUser;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtAdminFlag = itemView.findViewById(R.id.txtAdminFlag);
            btnDeactivateUser = itemView.findViewById(R.id.btnDeactivateUser);
        }
    }
}
