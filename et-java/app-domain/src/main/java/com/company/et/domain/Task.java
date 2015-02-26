package com.company.et.domain;

/**
 * Created by denis on 20.2.15.
 */
public class Task {
    String professorsWork;
    String period;
    Double capacity;
    Double septemberCapacity;
    Double octoberCapacity;
    Double novemberCapacity;
    Double decemberCapacity;
    Double januaryCapacity;
    Double firstSemester;
    Double februaryCapacity;
    Double marchCapacity;
    Double aprilCapacity;
    Double mayCapacity;
    Double juneCapacity;
    Double julyCapacity;
    Double augustCapacity;
    Double secondSemester;
    Double allYear;
    Boolean completeWork;
    Double mark;

    public Task() {
        professorsWork = "someWork";
        period = "";
        capacity=0.0;
        septemberCapacity=0.0;
        octoberCapacity=0.0;
        novemberCapacity=0.0;
        decemberCapacity=0.0;
        januaryCapacity=0.0;
        firstSemester=0.0;
        februaryCapacity=0.0;
        marchCapacity=0.0;
        aprilCapacity=0.0;
        mayCapacity=0.0;
        juneCapacity=0.0;
        julyCapacity=0.0;
        augustCapacity=0.0;
        secondSemester=0.0;
        allYear=0.0;
        completeWork=false;
        mark=0.0;
    }

    public Task(String professorsWork, String period, Double capacity, Double septemberCapacity, Double octoberCapacity, Double novemberCapacity, Double decemberCapacity, Double januaryCapacity, Double firstSemester, Double februaryCapacity, Double marchCapacity, Double aprilCapacity, Double mayCapacity, Double juneCapacity, Double julyCapacity, Double augustCapacity, Double secondSemester, Double allYear, Boolean completeWork, Double mark) {
        this.professorsWork = professorsWork;
        this.period = period;
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

    public String getProfessorsWork() {
        return professorsWork;
    }

    public void setProfessorsWork(String professorsWork) {
        this.professorsWork = professorsWork;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getSeptemberCapacity() {
        return septemberCapacity;
    }

    public void setSeptemberCapacity(Double septemberCapacity) {
        this.septemberCapacity = septemberCapacity;
    }

    public Double getOctoberCapacity() {
        return octoberCapacity;
    }

    public void setOctoberCapacity(Double octoberCapacity) {
        this.octoberCapacity = octoberCapacity;
    }

    @Override
    public String toString() {
        return "Task{" + "professorsWork=" + professorsWork + ", period=" + period + ", capacity=" + capacity + ", septemberCapacity=" + septemberCapacity + ", octoberCapacity=" + octoberCapacity + ", novemberCapacity=" + novemberCapacity + ", decemberCapacity=" + decemberCapacity + ", januaryCapacity=" + januaryCapacity + ", firstSemester=" + firstSemester + ", februaryCapacity=" + februaryCapacity + ", marchCapacity=" + marchCapacity + ", aprilCapacity=" + aprilCapacity + ", mayCapacity=" + mayCapacity + ", juneCapacity=" + juneCapacity + ", julyCapacity=" + julyCapacity + ", augustCapacity=" + augustCapacity + ", secondSemester=" + secondSemester + ", allYear=" + allYear + ", completeWork=" + completeWork + ", mark=" + mark + '}';
    }

    public Double getNovemberCapacity() {
        return novemberCapacity;
    }

    public void setNovemberCapacity(Double novemberCapacity) {
        this.novemberCapacity = novemberCapacity;
    }

    public Double getDecemberCapacity() {
        return decemberCapacity;
    }

    public void setDecemberCapacity(Double decemberCapacity) {
        this.decemberCapacity = decemberCapacity;
    }

    public Double getJanuaryCapacity() {
        return januaryCapacity;
    }

    public void setJanuaryCapacity(Double januaryCapacity) {
        this.januaryCapacity = januaryCapacity;
    }

    public Double getFirstSemester() {
        return firstSemester;
    }

    public void setFirstSemester(Double firstSemester) {
        this.firstSemester = firstSemester;
    }

    public Double getFebruaryCapacity() {
        return februaryCapacity;
    }

    public void setFebruaryCapacity(Double februaryCapacity) {
        this.februaryCapacity = februaryCapacity;
    }

    public Double getMarchCapacity() {
        return marchCapacity;
    }

    public void setMarchCapacity(Double marchCapacity) {
        this.marchCapacity = marchCapacity;
    }

    public Double getAprilCapacity() {
        return aprilCapacity;
    }

    public void setAprilCapacity(Double aprilCapacity) {
        this.aprilCapacity = aprilCapacity;
    }

    public Double getMayCapacity() {
        return mayCapacity;
    }

    public void setMayCapacity(Double mayCapacity) {
        this.mayCapacity = mayCapacity;
    }

    public Double getJuneCapacity() {
        return juneCapacity;
    }

    public void setJuneCapacity(Double juneCapacity) {
        this.juneCapacity = juneCapacity;
    }

    public Double getJulyCapacity() {
        return julyCapacity;
    }

    public void setJulyCapacity(Double julyCapacity) {
        this.julyCapacity = julyCapacity;
    }

    public Double getAugustCapacity() {
        return augustCapacity;
    }

    public void setAugustCapacity(Double augustCapacity) {
        this.augustCapacity = augustCapacity;
    }

    public Double getSecondSemester() {
        return secondSemester;
    }

    public void setSecondSemester(Double secondSemester) {
        this.secondSemester = secondSemester;
    }

    public Double getAllYear() {
        return allYear;
    }

    public void setAllYear(Double allYear) {
        this.allYear = allYear;
    }

    public Boolean getCompleteWork() {
        return completeWork;
    }

    public void setCompleteWork(Boolean completeWork) {
        this.completeWork = completeWork;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
    
    
}
