package com.example.reviews1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.reviews1.adapter.TaskAdapter;
import com.example.reviews1.adapter.TaskGroupAdapter;
import com.example.reviews1.api.ApiClient;
import com.example.reviews1.api.ApiService;
import com.example.reviews1.model.Task;
import com.example.reviews1.model.TaskGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvInProgressTasks;
    private RecyclerView rvTaskGroups;
    private TaskAdapter taskAdapter;
    private TaskGroupAdapter taskGroupAdapter;
    private List<Task> taskList;
    private List<TaskGroup> taskGroupList;
    private FloatingActionButton fabAdd;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize API service
        apiService = ApiClient.getApiService();

        // Initialize views
        initViews();

        // Setup RecyclerViews
        setupRecyclerViews();

        // Load data (using dummy data for now, replace with API calls)
        loadDummyData();

        // Setup FAB click listener
        fabAdd.setOnClickListener(v -> {
            Toast.makeText(MainActivity.this, "Add new task", Toast.LENGTH_SHORT).show();
            // TODO: Open add task dialog or activity
        });
    }

    private void initViews() {
        rvInProgressTasks = findViewById(R.id.rvInProgressTasks);
        rvTaskGroups = findViewById(R.id.rvTaskGroups);
        fabAdd = findViewById(R.id.fabAdd);
    }

    private void setupRecyclerViews() {
        // Setup In Progress Tasks RecyclerView (Horizontal)
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList, task -> {
            Toast.makeText(MainActivity.this, "Clicked: " + task.getTitle(), Toast.LENGTH_SHORT).show();
            // TODO: Open task details
        });

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);
        rvInProgressTasks.setLayoutManager(horizontalLayoutManager);
        rvInProgressTasks.setAdapter(taskAdapter);

        // Setup Task Groups RecyclerView (Vertical)
        taskGroupList = new ArrayList<>();
        taskGroupAdapter = new TaskGroupAdapter(taskGroupList, taskGroup -> {
            Toast.makeText(MainActivity.this, "Clicked: " + taskGroup.getName(), Toast.LENGTH_SHORT).show();
            // TODO: Open task group details
        });

        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(this);
        rvTaskGroups.setLayoutManager(verticalLayoutManager);
        rvTaskGroups.setAdapter(taskGroupAdapter);
    }

    private void loadDummyData() {
        // Dummy tasks
        taskList.add(new Task("1", "Grocery shopping app design", "Office Project", 60, "#FFB6C1"));
        taskList.add(new Task("2", "Uber Eats redesign challenge", "Personal Project", 45, "#FF8A65"));
        taskAdapter.notifyDataSetChanged();

        // Dummy task groups
        taskGroupList.add(new TaskGroup("1", "Office Project", 25, 70, "#FFE8F4", "office"));
        taskGroupList.add(new TaskGroup("2", "Personal Project", 30, 52, "#E8E4FF", "personal"));
        taskGroupList.add(new TaskGroup("3", "Daily Study", 10, 67, "#FFE8D6", "study"));
        taskGroupAdapter.notifyDataSetChanged();
    }

    // Method to fetch tasks from API
    private void fetchTasksFromApi() {
        apiService.getTasks().enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    taskList.clear();
                    taskList.addAll(response.body());
                    taskAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load tasks: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to fetch task groups from API
    private void fetchTaskGroupsFromApi() {
        apiService.getTaskGroups().enqueue(new Callback<List<TaskGroup>>() {
            @Override
            public void onResponse(Call<List<TaskGroup>> call, Response<List<TaskGroup>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    taskGroupList.clear();
                    taskGroupList.addAll(response.body());
                    taskGroupAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<TaskGroup>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load task groups: " + t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}