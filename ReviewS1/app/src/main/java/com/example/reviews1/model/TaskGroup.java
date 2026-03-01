package com.example.reviews1.model;

public class TaskGroup {
    private String id;
    private String name;
    private int taskCount;
    private int completionPercentage;
    private String iconColor;
    private String icon;

    public TaskGroup(String id, String name, int taskCount, int completionPercentage, String iconColor, String icon) {
        this.id = id;
        this.name = name;
        this.taskCount = taskCount;
        this.completionPercentage = completionPercentage;
        this.iconColor = iconColor;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getCompletionPercentage() {
        return completionPercentage;
    }

    public void setCompletionPercentage(int completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public String getIconColor() {
        return iconColor;
    }

    public void setIconColor(String iconColor) {
        this.iconColor = iconColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
