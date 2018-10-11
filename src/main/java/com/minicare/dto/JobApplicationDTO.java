package com.minicare.dto;

import com.minicare.model.Status;

public class JobApplicationDTO {
    int jobApplicationId;
    int jobId;
    int memberId;
    String jobTitle;
    double expectedPay;
    double payPerHour;
    Status status;
    String sitterFirstName;
    String sitterLastName;

    public String getSitterFirstName() {
        return sitterFirstName;
    }

    public void setSitterFirstName(String sitterFirstName) {
        this.sitterFirstName = sitterFirstName;
    }

    public String getSitterLastName() {
        return sitterLastName;
    }

    public void setSitterLastName(String sitterLastName) {
        this.sitterLastName = sitterLastName;
    }

    public int getJobApplicationId() {
        return jobApplicationId;
    }

    public void setJobApplicationId(int jobApplicationId) {
        this.jobApplicationId = jobApplicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
