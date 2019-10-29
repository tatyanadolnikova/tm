package com.dolnikova.tm;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String projectName;
    private List<String> tasks;

    public Project(String projectName) {
        this.projectName = projectName;
        tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        tasks.add(task);
    }

    public String readTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public void updateTask(int taskNumber, String newTask) {
        tasks.add(taskNumber - 1, newTask);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List getTasks() {
        return tasks;
    }

    public void setTasks(List tasks) {
        this.tasks = tasks;
    }

}
