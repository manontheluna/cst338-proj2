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
import com.example.project_2_paw.data.entity.CareTask;
import com.example.project_2_paw.data.repository.PawRepository;

import java.util.List;

public class AdminTaskAdapter extends RecyclerView.Adapter<AdminTaskAdapter.TaskViewHolder> {
    private final List<CareTask> tasks;
    private final PawRepository repo;

    public AdminTaskAdapter(List<CareTask> tasks,PawRepository repo) {
        this.tasks = tasks;
        this.repo = repo;
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

        holder.btnDeleteTask.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Delete task?")
                    .setMessage("This will permanently delete the task.")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        int pos = holder.getBindingAdapterPosition();
                        if (pos == RecyclerView.NO_POSITION) return;

                        repo.deleteTask(tasks.get(pos));   // delete from DB
                        tasks.remove(pos);                 // remove from list
                        notifyItemRemoved(pos);            // update UI
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title, dueDate, status;
        ImageButton btnDeleteTask;
        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTaskTitle);
            dueDate = itemView.findViewById(R.id.txtTaskDueDate);
            status = itemView.findViewById(R.id.txtTaskStatus);
            btnDeleteTask = itemView.findViewById(R.id.btnDeleteTask);
        }
    }
}
