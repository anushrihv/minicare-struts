package com.minicare.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Sitter extends Member implements Serializable {
    private int yearsOfExperience;
    private double expectedPay;
    Set jobapplication = new HashSet();

    public Sitter(){

    }

    public Set getJobapplication() {
        return jobapplication;
    }

    public void setJobapplication(Set jobapplication) {
        this.jobapplication = jobapplication;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }
}
