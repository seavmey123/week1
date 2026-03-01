package com.example.reviews1.model;

public class Task {
    private String id;
    private String title;
    private String category;
    private int progress;
    private String categoryColor;

    public Task(String id, String title, String category, int progress, String categoryColor) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.progress = progress;
        this.categoryColor = categoryColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getCategoryColor() {
        return categoryColor;
    }

    public void setCategoryColor(String categoryColor) {
        this.categoryColor = categoryColor;
    }
}