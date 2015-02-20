package com.company.et.domain;

import java.util.ArrayList;

/**
 */
public class Professor {
    private String FIO;
    private ArrayList<Task> professorTasks;
    private ArrayList<ArrayList<Integer>> tableOfHoursAndTasksFirstSem;
    private ArrayList<ArrayList<Integer>> tableOfHoursAndTasksSecondSem;
    public final int COUNT_OF_MONTH_IN_FIRST_SEM=5;
    public final int COUNT_OF_MONTH_IN_SECOND_SEM=7;

    public Professor(String FIO, ArrayList<Task> professorTasks, ArrayList<ArrayList<Integer>> tableOfHoursAndTasksFirstSem, ArrayList<ArrayList<Integer>> tableOfHoursAndTasksSecondSem) {
        this.FIO = FIO;
        this.professorTasks = professorTasks;
        this.tableOfHoursAndTasksFirstSem = tableOfHoursAndTasksFirstSem;
        this.tableOfHoursAndTasksSecondSem = tableOfHoursAndTasksSecondSem;
    }

    public Professor() {
        FIO="";
        professorTasks = new ArrayList<Task>();
        tableOfHoursAndTasksFirstSem = new ArrayList<ArrayList<Integer>>(professorTasks.size());
        tableOfHoursAndTasksSecondSem = new ArrayList<ArrayList<Integer>>(professorTasks.size());
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public ArrayList<Task> getProfessorTasks() {
        return professorTasks;
    }

    public void setProfessorTasks(ArrayList<Task> professorTasks) {
        this.professorTasks = professorTasks;
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
