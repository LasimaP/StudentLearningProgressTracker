package com.LP.student;

public class EnrolledStudent {
    private Student student;
    private int currentPoints;
    private double percentCompleted;
    private int id;


    public EnrolledStudent(Student student, int points) {
        this.student = student;
        this.currentPoints = points;
        this.id = student.getId();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(int points) {
        this.currentPoints = points;
    }

    public double getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(double percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
