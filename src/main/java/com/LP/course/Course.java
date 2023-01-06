package com.LP.course;

import com.LP.student.EnrolledStudent;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String name;
    private int totalPoints;
    private Map<Integer, EnrolledStudent> enrolledStudents;

    public Course(String name, int totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
        this.enrolledStudents = new HashMap<>();

    }

    public double calculateCompleted(int studentID) {
        EnrolledStudent enrolledStudent = this.enrolledStudents.get(studentID);

        double decimalCompleted = (double) enrolledStudent.getCurrentPoints() / this.totalPoints;
        BigDecimal percentageCompleted = new BigDecimal(decimalCompleted * 100);
        enrolledStudent.setPercentCompleted(
                percentageCompleted.setScale(1, RoundingMode.HALF_UP).doubleValue()
        );
        return enrolledStudent.getPercentCompleted();
    }

    public void addPoints(int studentID, int points) {
        EnrolledStudent enrolledStudent = this.enrolledStudents.get(studentID);

        int currentPoints = enrolledStudent.getCurrentPoints();
        if ((currentPoints + points) > this.totalPoints) {
            currentPoints = this.totalPoints;
        } else {
            currentPoints += points;
        }
        enrolledStudent.setCurrentPoints(currentPoints);
    }

    public String getName() {
        return name;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public Map<Integer, EnrolledStudent> getEnrolledStudents() {
        return enrolledStudents;
    }

    @Override
    public String toString() {
        return name;
    }
}
