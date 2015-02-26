/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.domain;

import java.util.List;

/**
 *
 * @author Пальчук
 */
public class StdWorks {
    List<Work> works;

    public StdWorks() {
        
    }

    public StdWorks(List<Work> works) {
        this.works = works;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
