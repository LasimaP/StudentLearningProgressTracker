package com.LP.student;

import java.util.Map;
import java.util.TreeMap;

public class StudentDatabase {
    private Map<Integer, Student> students;

    public StudentDatabase() {
        this.students = new TreeMap<>();
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<Integer, Student> students) {
        this.students = students;
    }
}
