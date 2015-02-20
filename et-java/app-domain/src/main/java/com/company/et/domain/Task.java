package com.company.et.domain;

/**
 * Created by denis on 20.2.15.
 */
public class Task {
    String title;

    public Task() {
        title = "";
    }

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
