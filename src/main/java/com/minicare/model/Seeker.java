package com.minicare.model;

import java.io.Serializable;

public class Seeker extends Member implements Serializable {
    private int numberOfChildren;
    private String spouseName;

    public Seeker(){

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
