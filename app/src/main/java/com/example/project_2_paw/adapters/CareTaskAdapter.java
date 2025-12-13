package com.example.project_2_paw.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_paw.R;
import com.example.project_2_paw.data.entity.CareTask;

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class CareTaskAdapter extends RecyclerView.Adapter<CareTaskAdapter.CareTaskViewHolder> {
    public interface OnTaskCheckedChangeListener {
        void onTaskCheckedChanged(CareTask task, boolean isChecked);
    }

    private final OnTaskCheckedChangeListener listener;
    private List<CareTask> tasks = new ArrayList<>();

    public CareTaskAdapter(OnTaskCheckedChangeListener listener) {
        this.listener = listener;
    }

    public void setTasks(List<CareTask> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    @Override
    public CareTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_care_task, parent, false);
        return new CareTaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CareTaskViewHolder holder, int position) {
        CareTask task = tasks.get(position);
        holder.taskName.setText(task.getTaskName());
        holder.taskDate.setText("Due: " + task.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if (task.getDueDate() != null) {
            holder.taskDate.setText("Due: " + task.getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        } else {
            holder.taskDate.setText("Due: -");
        }

        holder.completedCheckBox.setOnCheckedChangeListener(null);
        holder.completedCheckBox.setChecked(task.isCompleted());

        holder.completedCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onTaskCheckedChanged(task, isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class CareTaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName;
        TextView taskDate;
        CheckBox completedCheckBox;

        CareTaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.textTaskName);
            taskDate = itemView.findViewById(R.id.textTaskDate);
            completedCheckBox = itemView.findViewById(R.id.checkTaskCompleted);
        }
    }
}
