package com.company.et.domain;

import java.util.ArrayList;
import java.util.Collections;

public class Task {

    private String professorsWork;

    private String period;

    private ArrayList<Double> capacities;

    private Boolean completeWork;

    public Task() {
        professorsWork = "SomeWork";
        period = " ";
        capacities = new ArrayList<>(Collections.nCopies(16, 0.0));
        completeWork = false;
    }

    public Task(String professorsWork, String period, Boolean completeWork) {
        capacities = new ArrayList<>(Collections.nCopies(16, 0.0));
        this.professorsWork = professorsWork;
        this.period = period;
        this.completeWork = completeWork;
    }

    public Task(String professorsWork, String period, ArrayList<Double> capacities, Boolean completeWork) {
        this.professorsWork = professorsWork;
        this.period = period;
        this.capacities = capacities;
        this.completeWork = completeWork;
    }

    public Task(Task task) {
        this.capacities = task.capacities;
        this.completeWork = task.completeWork;
        this.period = task.period;
        this.professorsWork = task.professorsWork;
    }

    public String getProfessorsWork() {
        return professorsWork;
    }

    public Boolean getCompleteWork() {
        return completeWork;
    }

    public void setCompleteWork(Boolean completeWork) {
        this.completeWork = completeWork;
    }

    public ArrayList<Double> getCapacities() {
        return capacities;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setCapacities(ArrayList<Double> capacities) {
        this.capacities = capacities;
    }

    public void setProfessorsWork(String professorsWork) {
        this.professorsWork = professorsWork;
    }
}
