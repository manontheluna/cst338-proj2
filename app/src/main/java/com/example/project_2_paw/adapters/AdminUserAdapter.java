package com.example.project_2_paw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project_2_paw.R;
import com.example.project_2_paw.data.entity.User;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.UserViewHolder> {
    private final List<User> users;

    public AdminUserAdapter(List<User> users) {
        this.users = users;
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

        if (u.isAdmin()) {
            holder.txtAdminFlag.setVisibility(View.VISIBLE);
        } else {
            holder.txtAdminFlag.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsername, txtAdminFlag;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtAdminFlag = itemView.findViewById(R.id.txtAdminFlag);
        }
    }
}
