package com.LP.student;

import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;

import java.util.Map;

public class StudentAdder {
    private StudentInputProcessor studentInputProcessor;
    private StudentInputVerifier studentInputVerifier;
    private int studentID;

    public StudentAdder(StudentInputProcessor studentInputProcessor, StudentInputVerifier studentInputVerifier) {
        this.studentInputProcessor = studentInputProcessor;
        this.studentInputVerifier = studentInputVerifier;
        this.studentID = 100000;
    }

    public String addStudent(Map<Integer, Student> studentDictionary, String studentName) {
        String[] processedName = studentInputProcessor.nameSplitter(studentName);
        if (processedName[0].isBlank()) {
            System.out.println("Incorrect credentials.");
            return "Incorrect credentials.";
        } else {
            boolean[] nameCheck = studentInputVerifier.nameVerifier(processedName);
            if (!nameCheck[0]) {
                System.out.println("Incorrect first name.");
                return "Incorrect first name.";
            } else if (!nameCheck[1]) {
                System.out.println("Incorrect last name.");
                return "Incorrect last name.";
            } else if (!nameCheck[2]) {
                System.out.println("Incorrect email.");
                return "Incorrect email.";
            } else {
                Student newStudent = new Student(processedName[0], processedName[1], processedName[2], studentID);
                if (studentDictionary.containsValue(newStudent)) {
                    System.out.println("This email is already taken.");
                    return "This email is already taken.";
                } else {
                    studentDictionary.put(studentID, newStudent);
                    studentID += 1;
                    System.out.println("The student has been added.");
                    return "The student has been added.";
                }
            }
        }
    }
}
