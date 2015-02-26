package com.company.et.domain;

/**
 * Created by denis on 20.2.15.
 */
public class Task {
    Work professorsWork;
    Integer countOfHours;
    Integer capacity;
    Integer septemberCapacity;
    Integer octoberCapacity;
    Integer novemberCapacity;
    Integer decemberCapacity;
    Integer januaryCapacity;
    Integer firstSemester;
    Integer februaryCapacity;
    Integer marchCapacity;
    Integer aprilCapacity;
    Integer mayCapacity;
    Integer juneCapacity;
    Integer julyCapacity;
    Integer augustCapacity;
    Integer secondSemester;
    Integer allYear;
    Boolean completeWork;
    Integer mark;

    public Task() {
        professorsWork = new Work("Example");
        countOfHours = 0;
        capacity=0;
        septemberCapacity=0;
        octoberCapacity=0;
        novemberCapacity=0;
        decemberCapacity=0;
        januaryCapacity=0;
        firstSemester=0;
        februaryCapacity=0;
        marchCapacity=0;
        aprilCapacity=0;
        mayCapacity=0;
        juneCapacity=0;
        julyCapacity=0;
        augustCapacity=0;
        secondSemester=0;
        allYear=0;
        completeWork=false;
        mark=0;
    }

    public Task(Work professorsWork, Integer countOfHours, Integer capacity, Integer septemberCapacity, Integer octoberCapacity, Integer novemberCapacity, Integer decemberCapacity, Integer januaryCapacity, Integer firstSemester, Integer februaryCapacity, Integer marchCapacity, Integer aprilCapacity, Integer mayCapacity, Integer juneCapacity, Integer julyCapacity, Integer augustCapacity, Integer secondSemester, Integer allYear, Boolean completeWork, Integer mark) {
        this.professorsWork = professorsWork;
        this.countOfHours = countOfHours;
        this.capacity = capacity;
        this.septemberCapacity = septemberCapacity;
        this.octoberCapacity = octoberCapacity;
        this.novemberCapacity = novemberCapacity;
        this.decemberCapacity = decemberCapacity;
        this.januaryCapacity = januaryCapacity;
        this.firstSemester = firstSemester;
        this.februaryCapacity = februaryCapacity;
        this.marchCapacity = marchCapacity;
        this.aprilCapacity = aprilCapacity;
        this.mayCapacity = mayCapacity;
        this.juneCapacity = juneCapacity;
        this.julyCapacity = julyCapacity;
        this.augustCapacity = augustCapacity;
        this.secondSemester = secondSemester;
        this.allYear = allYear;
        this.completeWork = completeWork;
        this.mark = mark;
    }

    public Work getProfessorsWork() {
        return professorsWork;
    }

    public void setProfessorsWork(Work professorsWork) {
        this.professorsWork = professorsWork;
    }

    public Integer getCountOfHours() {
        return countOfHours;
    }

    public void setCountOfHours(Integer countOfHours) {
        this.countOfHours = countOfHours;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getSeptemberCapacity() {
        return septemberCapacity;
    }

    public void setSeptemberCapacity(Integer septemberCapacity) {
        this.septemberCapacity = septemberCapacity;
    }

    public Integer getOctoberCapacity() {
        return octoberCapacity;
    }

    public void setOctoberCapacity(Integer octoberCapacity) {
        this.octoberCapacity = octoberCapacity;
    }

    @Override
    public String toString() {
        return "Task{" + "professorsWork=" + professorsWork + ", countOfHours=" + countOfHours + ", capacity=" + capacity + ", septemberCapacity=" + septemberCapacity + ", octoberCapacity=" + octoberCapacity + ", novemberCapacity=" + novemberCapacity + ", decemberCapacity=" + decemberCapacity + ", januaryCapacity=" + januaryCapacity + ", firstSemester=" + firstSemester + ", februaryCapacity=" + februaryCapacity + ", marchCapacity=" + marchCapacity + ", aprilCapacity=" + aprilCapacity + ", mayCapacity=" + mayCapacity + ", juneCapacity=" + juneCapacity + ", julyCapacity=" + julyCapacity + ", augustCapacity=" + augustCapacity + ", secondSemester=" + secondSemester + ", allYear=" + allYear + ", completeWork=" + completeWork + ", mark=" + mark + '}';
    }

    public Integer getNovemberCapacity() {
        return novemberCapacity;
    }

    public void setNovemberCapacity(Integer novemberCapacity) {
        this.novemberCapacity = novemberCapacity;
    }

    public Integer getDecemberCapacity() {
        return decemberCapacity;
    }

    public void setDecemberCapacity(Integer decemberCapacity) {
        this.decemberCapacity = decemberCapacity;
    }

    public Integer getJanuaryCapacity() {
        return januaryCapacity;
    }

    public void setJanuaryCapacity(Integer januaryCapacity) {
        this.januaryCapacity = januaryCapacity;
    }

    public Integer getFirstSemester() {
        return firstSemester;
    }

    public void setFirstSemester(Integer firstSemester) {
        this.firstSemester = firstSemester;
    }

    public Integer getFebruaryCapacity() {
        return februaryCapacity;
    }

    public void setFebruaryCapacity(Integer februaryCapacity) {
        this.februaryCapacity = februaryCapacity;
    }

    public Integer getMarchCapacity() {
        return marchCapacity;
    }

    public void setMarchCapacity(Integer marchCapacity) {
        this.marchCapacity = marchCapacity;
    }

    public Integer getAprilCapacity() {
        return aprilCapacity;
    }

    public void setAprilCapacity(Integer aprilCapacity) {
        this.aprilCapacity = aprilCapacity;
    }

    public Integer getMayCapacity() {
        return mayCapacity;
    }

    public void setMayCapacity(Integer mayCapacity) {
        this.mayCapacity = mayCapacity;
    }

    public Integer getJuneCapacity() {
        return juneCapacity;
    }

    public void setJuneCapacity(Integer juneCapacity) {
        this.juneCapacity = juneCapacity;
    }

    public Integer getJulyCapacity() {
        return julyCapacity;
    }

    public void setJulyCapacity(Integer julyCapacity) {
        this.julyCapacity = julyCapacity;
    }

    public Integer getAugustCapacity() {
        return augustCapacity;
    }

    public void setAugustCapacity(Integer augustCapacity) {
        this.augustCapacity = augustCapacity;
    }

    public Integer getSecondSemester() {
        return secondSemester;
    }

    public void setSecondSemester(Integer secondSemester) {
        this.secondSemester = secondSemester;
    }

    public Integer getAllYear() {
        return allYear;
    }

    public void setAllYear(Integer allYear) {
        this.allYear = allYear;
    }

    public Boolean getCompleteWork() {
        return completeWork;
    }

    public void setCompleteWork(Boolean completeWork) {
        this.completeWork = completeWork;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
    
    
}
