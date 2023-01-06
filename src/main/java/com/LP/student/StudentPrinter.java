package com.LP.student;

import java.util.Map;

public class StudentPrinter {
    public String listStudents(Map<Integer, Student> studentDictionary) {
        if (studentDictionary.isEmpty()) {
            System.out.println("No students found.");
            return "No students found.";
        }
        StringBuilder output = new StringBuilder();
        System.out.println("Students:");
        output.append("Student\n");
        for (int studentID : studentDictionary.keySet()) {
            System.out.println(studentID);
            output.append(studentID + "\n");
        }
        return output.toString();
    }
}
