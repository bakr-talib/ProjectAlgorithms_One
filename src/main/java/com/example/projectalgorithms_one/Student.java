package com.example.projectalgorithms_one;

public class Student {
    private int id;
    private String name;
    private double average;

    public Student(int id, String name, double average) {
        this.id = id;
        this.name = name;
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

}
