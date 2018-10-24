package com.minicare.model;

import java.io.Serializable;

public class Sitter extends Member implements Serializable {
    private int yearsOfExperience;
    private double expectedPay;

    public Sitter(){

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
