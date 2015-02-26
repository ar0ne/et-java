package com.company.et.domain;

import java.util.ArrayList;

/**
 */
public class Professor {
    String fio;
    ArrayList<Task> tasks;
    
    public Professor() {
        tasks=new ArrayList<Task>();
        fio="Example";
    }

    public Professor(String fio, ArrayList<Task> tasks) {
        this.fio = fio;
        this.tasks = tasks;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
}
