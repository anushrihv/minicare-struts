package com.minicare.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Seeker extends Member implements Serializable {
    private int numberOfChildren;
    private String spouseName;
    Set job = new HashSet();

    public Seeker(){

    }

    public Set getJob() {
        return job;
    }

    public void setJob(Set job) {
        this.job = job;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }
}
