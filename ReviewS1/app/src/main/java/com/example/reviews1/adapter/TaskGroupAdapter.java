package com.example.reviews1.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews1.R;
import com.example.reviews1.model.TaskGroup;

import java.util.List;

public class TaskGroupAdapter extends RecyclerView.Adapter<TaskGroupAdapter.TaskGroupViewHolder> {

    private List<TaskGroup> taskGroupList;
    private OnTaskGroupClickListener listener;

    public interface OnTaskGroupClickListener {
        void onTaskGroupClick(TaskGroup taskGroup);
    }

    public TaskGroupAdapter(List<TaskGroup> taskGroupList, OnTaskGroupClickListener listener) {
        this.taskGroupList = taskGroupList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task_group, parent, false);
        return new TaskGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskGroupViewHolder holder, int position) {
        TaskGroup taskGroup = taskGroupList.get(position);
        holder.bind(taskGroup, listener);
    }

    @Override
    public int getItemCount() {
        return taskGroupList.size();
    }

    static class TaskGroupViewHolder extends RecyclerView.ViewHolder {
        CardView cvIconContainer;
        ImageView ivGroupIcon;
        TextView tvGroupName;
        TextView tvTaskCount;
        ProgressBar progressCircularGroup;
        TextView tvGroupProgress;

        public TaskGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            cvIconContainer = itemView.findViewById(R.id.cvIconContainer);
            ivGroupIcon = itemView.findViewById(R.id.ivGroupIcon);
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            tvTaskCount = itemView.findViewById(R.id.tvTaskCount);
            progressCircularGroup = itemView.findViewById(R.id.progressCircularGroup);
            tvGroupProgress = itemView.findViewById(R.id.tvGroupProgress);
        }

        public void bind(TaskGroup taskGroup, OnTaskGroupClickListener listener) {
            tvGroupName.setText(taskGroup.getName());
            tvTaskCount.setText(taskGroup.getTaskCount() + " Tasks");
            progressCircularGroup.setProgress(taskGroup.getCompletionPercentage());
            tvGroupProgress.setText(taskGroup.getCompletionPercentage() + "%");

            // Set icon container background color
            if (taskGroup.getIconColor() != null) {
                try {
                    cvIconContainer.setCardBackgroundColor(Color.parseColor(taskGroup.getIconColor()));
                } catch (IllegalArgumentException e) {
                    // Use default color if parsing fails
                    cvIconContainer.setCardBackgroundColor(Color.parseColor("#FFE8F4"));
                }
            }

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTaskGroupClick(taskGroup);
                }
            });
        }
    }
}