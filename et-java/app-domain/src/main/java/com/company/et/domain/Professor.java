package com.company.et.domain;

import java.util.ArrayList;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Professor {

    private String fio;
    private ArrayList<ObservableList<Task>> tasks;
    private Double rate; // ставка

    public Professor(String fio, ArrayList<ObservableList<Task>> tasks, Double rate) {
        initTasks();
        this.fio = fio;
        this.tasks = tasks;
        this.rate = rate;
    }
    public Professor(Professor professor) {
        initTasks();
        this.fio = professor.fio;
        this.rate = professor.rate;
        Collections.copy(professor.tasks, this.tasks);
    }
    public Professor() {
        initTasks();
        tasks.stream().forEach((task) -> {
            Collections.copy(task, FXCollections.observableArrayList());
        });
        fio="Example";
        rate = 1.0;
    }
    private void initTasks() {
        tasks = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            tasks.add(FXCollections.observableArrayList());
        }
    }
    public ArrayList<ObservableList<Task>> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<ObservableList<Task>> tasks) {
        this.tasks = tasks;
    }
    
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    @Override
    public String toString() {
        return "Professor{" + "fio=" + fio + ", tasks=" + tasks + ", rate=" + rate + '}';
    }
}
