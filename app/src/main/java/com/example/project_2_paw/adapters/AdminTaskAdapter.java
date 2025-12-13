package com.example.project_2_paw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.R;
import com.example.project_2_paw.data.entity.CareTask;

import java.util.List;

public class AdminTaskAdapter extends RecyclerView.Adapter<AdminTaskAdapter.TaskViewHolder> {
    private final List<CareTask> tasks;

    public AdminTaskAdapter(List<CareTask> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        CareTask task = tasks.get(position);

        holder.title.setText(task.getTaskName());
        holder.dueDate.setText("Due: " + task.getDueDate().toString());
        holder.status.setText(task.isCompleted() ? "Completed" : "Pending");
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate, status;
        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTaskTitle);
            dueDate = itemView.findViewById(R.id.txtTaskDueDate);
            status = itemView.findViewById(R.id.txtTaskStatus);
        }
    }
}
