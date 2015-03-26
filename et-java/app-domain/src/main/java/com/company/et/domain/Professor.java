package com.company.et.domain;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Professor {

    
    private String fio;
    private ObservableList<Task> tasksWorkMethod;
    private ObservableList<Task> tasksScince;
    private ObservableList<Task> tasksPublic;
    private Double rate; // ставка

    public Professor(String fio, ObservableList<Task> tasksWorkMethod, ObservableList<Task> tasksScince, ObservableList<Task> tasksPublic, Double rate) {
        this.fio = fio;
        this.tasksWorkMethod = tasksWorkMethod;
        this.tasksScince = tasksScince;
        this.tasksPublic = tasksPublic;
        this.rate = rate;
    }
    
    public ObservableList<Task> getTasksWorkMethod() {
        return tasksWorkMethod;
    }

    public void setTasksWorkMethod(ObservableList<Task> tasksWorkMethod) {
        this.tasksWorkMethod = tasksWorkMethod;
    }

    

    public ObservableList<Task> getTasksScince() {
        return tasksScince;
    }

    public void setTasksScince(ObservableList<Task> tasksScince) {
        this.tasksScince = tasksScince;
    }

    public ObservableList<Task> getTasksPublic() {
        return tasksPublic;
    }

    public void setTasksPublic(ObservableList<Task> tasksPublic) {
        this.tasksPublic = tasksPublic;
    }
    
    public Professor() {
        tasksWorkMethod=FXCollections.observableArrayList();
        tasksScince=FXCollections.observableArrayList();
        tasksPublic=FXCollections.observableArrayList();
        fio="Example";
        rate = 1.0;
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
        return "Professor{" + "fio=" + fio + ", tasks=" + tasksWorkMethod + ", rate=" + rate + '}';
    }
}
