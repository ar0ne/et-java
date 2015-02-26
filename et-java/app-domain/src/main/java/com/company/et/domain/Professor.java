package com.company.et.domain;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 */
public class Professor {
    String fio;
    ObservableList<Task> tasks;
    private Double rate; // ставка
    
    public Professor() {
        tasks=FXCollections.observableArrayList();
        fio="Example";
        rate = 1.0;
    }

    public Professor(String fio, ObservableList<Task> tasks,  Double rate) {
        this.fio = fio;
        this.tasks = tasks;
        this.rate = rate;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ObservableList<Task> tasks) {
        this.tasks = tasks;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
    
}
