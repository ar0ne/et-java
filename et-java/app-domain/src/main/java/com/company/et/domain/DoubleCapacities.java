/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.et.domain;

import java.util.ArrayList;

/**
 *
 * @author denis
 */
public enum DoubleCapacities {
    CAPACITY("Объём"), SEPTEMBER("Сентябрь"), OCTOBER("Октябрь"), NOVEMBER("Ноябрь"),
    DECEMBER("Декабрь"), JANUARY("Январь"), FIRST_SEMESTER("1 сем"), FEBRUARY("Февраль"),
    MARCH("Март"), APRIL("Апрель"), MAY("Май"),JUNE("Июнь"), JULY("Июль"), AUGUST("Август"), 
    SECOND_SEMESTER("2 сем"),ALL_YEAR("Весь год");
    
    //private static ArrayList<DoubleCapacities> listOfDoubleCapacities = new ArrayList<>();
    
    private final String name;
    
//    static {
//        for (DoubleCapacities legEnum : DoubleCapacities.values()) {
//            listOfDoubleCapacities.add(legEnum);
//        }
//    }
    
           

    private DoubleCapacities(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    @Override
    public String toString(){
       return name;
    }
    
    public static DoubleCapacities getDoubleCapacitiesByIndex(int index) {
        return DoubleCapacities.values()[index];
    }
}
