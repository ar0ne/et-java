package com.company.et.domain;

import java.util.ArrayList;

public class StandartTasks {

    private ArrayList<Task> tasks;

    public StandartTasks() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> newTasks) {
       newTasks = tasks;
    }
}
