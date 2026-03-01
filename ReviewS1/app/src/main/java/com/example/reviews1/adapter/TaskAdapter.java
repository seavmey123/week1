package com.example.reviews1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews1.R;
import com.example.reviews1.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    public TaskAdapter(List<Task> taskList, OnTaskClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskCategory;
        TextView tvTaskTitle;
        ProgressBar progressBarTask;
        TextView tvTaskProgress;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskCategory = itemView.findViewById(R.id.tvTaskCategory);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            progressBarTask = itemView.findViewById(R.id.progressBarTask);
            tvTaskProgress = itemView.findViewById(R.id.tvTaskProgress);
        }

        public void bind(Task task, OnTaskClickListener listener) {
            tvTaskCategory.setText(task.getCategory());
            tvTaskTitle.setText(task.getTitle());
            progressBarTask.setProgress(task.getProgress());
            tvTaskProgress.setText(task.getProgress() + "%");

            // Set category background color
            if (task.getCategoryColor() != null) {
                try {
                    tvTaskCategory.setBackgroundColor(Color.parseColor(task.getCategoryColor()));
                } catch (IllegalArgumentException e) {
                    // Use default color if parsing fails
                    tvTaskCategory.setBackgroundResource(R.drawable.category_badge_pink);
                }
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTaskClick(task);
                }
            });
        }
    }
}