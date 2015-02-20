package com.company.domain;

import java.util.ArrayList;

/**
 */
public class Professor {
    private String FIO;
    private ArrayList<Task> tasks;
    private ArrayList<ArrayList<Integer>> tableOfHoursAndTasksFirstSem;
    private ArrayList<ArrayList<Integer>> tableOfHoursAndTasksSecondSem;

    public Professor(String FIO, ArrayList<Task> tasks, ArrayList<ArrayList<Integer>> tableOfHoursAndTasksFirstSem, ArrayList<ArrayList<Integer>> tableOfHoursAndTasksSecondSem) {
        this.FIO = FIO;
        this.tasks = tasks;
        this.tableOfHoursAndTasksFirstSem = tableOfHoursAndTasksFirstSem;
        this.tableOfHoursAndTasksSecondSem = tableOfHoursAndTasksSecondSem;
    }

    public Professor() {
        FIO="";
        tasks = new ArrayList<Task>();
        tableOfHoursAndTasksFirstSem = new ArrayList<ArrayList<Integer>>(tasks.size());
        tableOfHoursAndTasksSecondSem = new ArrayList<ArrayList<Integer>>(tasks.size());
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<ArrayList<Integer>> getTableOfHoursAndTasksFirstSem() {
        return tableOfHoursAndTasksFirstSem;
    }

    public void setTableOfHoursAndTasksFirstSem(ArrayList<ArrayList<Integer>> tableOfHoursAndTasksFirstSem) {
        this.tableOfHoursAndTasksFirstSem = tableOfHoursAndTasksFirstSem;
    }

    public ArrayList<ArrayList<Integer>> getTableOfHoursAndTasksSecondSem() {

        return tableOfHoursAndTasksSecondSem;
    }

    public void setTableOfHoursAndTasksSecondSem(ArrayList<ArrayList<Integer>> tableOfHoursAndTasksSecondSem) {
        this.tableOfHoursAndTasksSecondSem = tableOfHoursAndTasksSecondSem;
    }

}
