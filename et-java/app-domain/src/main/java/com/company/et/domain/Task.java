package com.company.et.domain;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Task {
    Work professorsWork;
    IntegerProperty countOfHours;
    IntegerProperty capacity;
    IntegerProperty septemberCapacity;
    IntegerProperty octoberCapacity;
    IntegerProperty novemberCapacity;
    IntegerProperty decemberCapacity;
    IntegerProperty januaryCapacity;
    IntegerProperty firstSemester;
    IntegerProperty februaryCapacity;
    IntegerProperty marchCapacity;
    IntegerProperty aprilCapacity;
    IntegerProperty mayCapacity;
    IntegerProperty juneCapacity;
    IntegerProperty julyCapacity;
    IntegerProperty augustCapacity;
    IntegerProperty secondSemester;
    IntegerProperty allYear;
    BooleanProperty completeWork;
    IntegerProperty mark;

    public Task() {
       
    }

    public Task(Work professorsWork, Integer countOfHours, Integer capacity, Integer septemberCapacity, 
            Integer octoberCapacity, Integer novemberCapacity, Integer decemberCapacity, Integer januaryCapacity, 
            Integer firstSemester, Integer februaryCapacity, Integer marchCapacity, Integer aprilCapacity, 
            Integer mayCapacity, Integer juneCapacity, Integer julyCapacity, Integer augustCapacity, Integer secondSemester,
            Integer allYear, Boolean completeWork, Integer mark) {
        this.professorsWork = professorsWork;
        this.countOfHours = new SimpleIntegerProperty(countOfHours);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.septemberCapacity = new SimpleIntegerProperty(septemberCapacity);
        this.octoberCapacity = new SimpleIntegerProperty(octoberCapacity);
        this.novemberCapacity = new SimpleIntegerProperty(novemberCapacity);
        this.decemberCapacity = new SimpleIntegerProperty(decemberCapacity);
        this.januaryCapacity = new SimpleIntegerProperty(januaryCapacity);
        this.firstSemester = new SimpleIntegerProperty(firstSemester);
        this.februaryCapacity = new SimpleIntegerProperty(februaryCapacity);
        this.marchCapacity = new SimpleIntegerProperty(marchCapacity);
        this.aprilCapacity = new SimpleIntegerProperty(aprilCapacity);
        this.mayCapacity = new SimpleIntegerProperty(mayCapacity);
        this.juneCapacity = new SimpleIntegerProperty(juneCapacity);
        this.julyCapacity = new SimpleIntegerProperty(julyCapacity);
        this.augustCapacity = new SimpleIntegerProperty(augustCapacity);
        this.secondSemester = new SimpleIntegerProperty(secondSemester);
        this.allYear = new SimpleIntegerProperty(allYear);
        this.completeWork = new SimpleBooleanProperty(completeWork);
        this.mark = new SimpleIntegerProperty(mark);
    }

    public Work getProfessorsWork() {
        return professorsWork;
    }

    public void setProfessorsWork(Work professorsWork) {
        this.professorsWork = professorsWork;
    }
    public StringProperty professorsWorkProperty() {
        return new SimpleStringProperty(professorsWork.getTitle());
    }
    
    public Integer getCountOfHours() {
        return countOfHours.get();
    }

    public void setCountOfHours(Integer countOfHours) {
        this.countOfHours.set(countOfHours);
    }
    public IntegerProperty countOfHoursProperty() {
        return countOfHours;
    }
    public Integer getCapacity() {
        return capacity.get();
    }

    public void setCapacity(Integer capacity) {
        this.capacity.set(capacity);
    }
    public IntegerProperty capacityProperty() {
        return capacity;
    }
    
    public Integer getSeptemberCapacity() {
        return septemberCapacity.get();
    }

    public void setSeptemberCapacity(Integer septemberCapacity) {
        this.septemberCapacity.set(septemberCapacity);
    }
    public IntegerProperty septemberCapacityProperty() {
        return septemberCapacity;
    }
    public Integer getOctoberCapacity() {
        return octoberCapacity.get();
    }

    public void setOctoberCapacity(Integer octoberCapacity) {
        this.octoberCapacity.set(octoberCapacity);
    }
    public IntegerProperty octoberCapacityProperty() {
        return octoberCapacity;
    }

    public Integer getNovemberCapacity() {
        return novemberCapacity.get();
    }

    public void setNovemberCapacity(Integer novemberCapacity) {
        this.novemberCapacity.set(novemberCapacity);
    }
    public IntegerProperty novemberCapacityProperty() {
        return novemberCapacity;
    }

    public Integer getDecemberCapacity() {
        return decemberCapacity.get();
    }

    public void setDecemberCapacity(Integer decemberCapacity) {
        this.decemberCapacity.set(decemberCapacity);
    }
    public IntegerProperty decemberCapacityProperty() {
        return decemberCapacity;
    }

    public Integer getJanuaryCapacity() {
        return januaryCapacity.get();
    }

    public void setJanuaryCapacity(Integer januaryCapacity) {
        this.januaryCapacity.set(januaryCapacity);
    }
    public IntegerProperty januaryCapacityProperty() {
        return januaryCapacity;
    }

    public Integer getFirstSemester() {
        return firstSemester.get();
    }

    public void setFirstSemester(Integer firstSemester) {
        this.firstSemester.set(firstSemester);
    }
    
    public IntegerProperty firstSemesterProperty() {
        return firstSemester;
    }

    public Integer getFebruaryCapacity() {
        return februaryCapacity.get();
    }

    public void setFebruaryCapacity(Integer februaryCapacity) {
        this.februaryCapacity.set(februaryCapacity);
    }
    public IntegerProperty februaryCapacityProperty() {
        return februaryCapacity;
    }

    public Integer getMarchCapacity() {
        return marchCapacity.get();
    }

    public void setMarchCapacity(Integer marchCapacity) {
        this.marchCapacity.set(marchCapacity);
    }
    public IntegerProperty marchCapacityProperty() {
        return marchCapacity;
    }

    public Integer getAprilCapacity() {
        return aprilCapacity.get();
    }

    public void setAprilCapacity(Integer aprilCapacity) {
        this.aprilCapacity.set(aprilCapacity);
    }
    public IntegerProperty aprilCapacityProperty() {
        return aprilCapacity;
    }

    public Integer getMayCapacity() {
        return aprilCapacity.get();
    }

    public void setMayCapacity(Integer mayCapacity) {
        this.mayCapacity.set(mayCapacity);
    }
    public IntegerProperty mayCapacityProperty() {
        return mayCapacity;
    }

    public Integer getJuneCapacity() {
        return juneCapacity.get();
    }

    public void setJuneCapacity(Integer juneCapacity) {
        this.juneCapacity.set(juneCapacity);
    }
    public IntegerProperty juneCapacityProperty() {
        return juneCapacity;
    }

    public Integer getJulyCapacity() {
        return julyCapacity.get();
    }

    public void setJulyCapacity(Integer julyCapacity) {
        this.julyCapacity.set(julyCapacity);
    }
    public IntegerProperty julyCapacityProperty() {
        return julyCapacity;
    }

    public Integer getAugustCapacity() {
        return augustCapacity.get();
    }

    public void setAugustCapacity(Integer augustCapacity) {
        this.augustCapacity.set(augustCapacity);
    }
    public IntegerProperty augustCapacityProperty() {
        return augustCapacity;
    }

    public Integer getSecondSemester() {
        return secondSemester.get();
    }
    
    public void setSecondSemester(Integer secondSemester) {
        this.secondSemester.set(secondSemester);
    }
    
    public IntegerProperty secondSemesterProperty() {
        return secondSemester;
    }
    
    public Integer getAllYear() {
        return allYear.get();
    }

    public void setAllYear(Integer allYear) {
        this.allYear.set(allYear);
    }
    
    public IntegerProperty allYearProperty() {
        return allYear;
    }
    
    public Boolean getCompleteWork() {
        return completeWork.get();
    }

    public void setCompleteWork(Boolean completeWork) {
        this.completeWork.set(completeWork);
    }

    public BooleanProperty completeWorkProperty() {
        return completeWork;
    }
    public Integer getMark() {
        return mark.get();
    }

    public void setMark(Integer mark) {
        this.mark.set(mark);
    }
    
    public IntegerProperty markProperty() {
        return mark;
    }
    @Override
    public String toString() {
        return "Task{" + "professorsWork=" + professorsWork + ", countOfHours=" + countOfHours + ", capacity=" + capacity + ", septemberCapacity=" + septemberCapacity + ", octoberCapacity=" + octoberCapacity + ", novemberCapacity=" + novemberCapacity + ", decemberCapacity=" + decemberCapacity + ", januaryCapacity=" + januaryCapacity + ", firstSemester=" + firstSemester + ", februaryCapacity=" + februaryCapacity + ", marchCapacity=" + marchCapacity + ", aprilCapacity=" + aprilCapacity + ", mayCapacity=" + mayCapacity + ", juneCapacity=" + juneCapacity + ", julyCapacity=" + julyCapacity + ", augustCapacity=" + augustCapacity + ", secondSemester=" + secondSemester + ", allYear=" + allYear + ", completeWork=" + completeWork + ", mark=" + mark + '}';
    }
    
}
