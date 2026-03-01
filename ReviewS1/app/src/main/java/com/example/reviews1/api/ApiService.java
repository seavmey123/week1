package com.example.reviews1.api;

import com.example.reviews1.model.Task;
import com.example.reviews1.model.TaskGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    // Base URL - Replace with your actual API endpoint
    String BASE_URL = "https://your-api-endpoint.com/api/";

    // Get all tasks
    @GET("tasks")
    Call<List<Task>> getTasks();

    // Get task by ID
    @GET("tasks/{id}")
    Call<Task> getTaskById(@Path("id") String id);

    // Create new task
    @POST("tasks")
    Call<Task> createTask(@Body Task task);

    // Update task
    @PUT("tasks/{id}")
    Call<Task> updateTask(@Path("id") String id, @Body Task task);

    // Delete task
    @DELETE("tasks/{id}")
    Call<Void> deleteTask(@Path("id") String id);

    // Get all task groups
    @GET("taskgroups")
    Call<List<TaskGroup>> getTaskGroups();

    // Get task group by ID
    @GET("taskgroups/{id}")
    Call<TaskGroup> getTaskGroupById(@Path("id") String id);

    // Create new task group
    @POST("taskgroups")
    Call<TaskGroup> createTaskGroup(@Body TaskGroup taskGroup);

    // Update task group
    @PUT("taskgroups/{id}")
    Call<TaskGroup> updateTaskGroup(@Path("id") String id, @Body TaskGroup taskGroup);

    // Delete task group
    @DELETE("taskgroups/{id}")
    Call<Void> deleteTaskGroup(@Path("id") String id);
}