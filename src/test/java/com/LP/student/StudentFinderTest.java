package com.LP.student;

import com.LP.course.*;
import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentFinderTest {
    static Course java;
    static Course dsa;
    static Course databases;
    static Course spring;
    static List<Course> courses;
    static Map<Integer, Student> studentDictionary;
    static StudentInputVerifier studentInputVerifier;
    static StudentInputProcessor studentInputProcessor;
    static CourseEnrolling courseEnrolling;
    static StudentFinder studentFinder;
    static StudentAdder studentAdder;
    static CoursePointsAdder coursePointsAdder;

    static String[] simulatedInputs;
    static String[] expectedOutcomes;

    static int index = 0;

    @BeforeAll
    static void setUp() {
        java = new Java();
        dsa = new DSA();
        databases = new Databases();
        spring = new Spring();
        courses = new ArrayList<>();
        studentDictionary = new TreeMap<>();
        studentInputVerifier = new StudentInputVerifier();
        studentInputProcessor = new StudentInputProcessor();
        courseEnrolling = new CourseEnrolling();
        studentFinder = new StudentFinder();
        studentAdder = new StudentAdder(studentInputProcessor, studentInputVerifier);
        coursePointsAdder = new CoursePointsAdder(studentInputVerifier,studentInputProcessor, studentFinder, courseEnrolling);

        courses.add(java);
        courses.add(dsa);
        courses.add(databases);
        courses.add(spring);

        studentAdder.addStudent(studentDictionary, "John Doe Jdoe@gmail.com");
        studentAdder.addStudent(studentDictionary, "Jill Scott Jscott@yahoo.com");
        studentAdder.addStudent(studentDictionary, "Robert Lillard rob.lill@gmail.com");
        studentAdder.addStudent(studentDictionary, "Paul Hills phills@uncc.edu");
        studentAdder.addStudent(studentDictionary, "Ana Smith smith.a@gmail.com");
        studentAdder.addStudent(studentDictionary, "Bob Peters Bpeters@yahoo.com");
        studentAdder.addStudent(studentDictionary, "Simone Brown simone@yahoo.com");

        coursePointsAdder.addPoints(studentDictionary, courses, "100000 400 300 200 100");
        coursePointsAdder.addPoints(studentDictionary, courses, "100001 300 200 100 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100002 200 100 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100003 100 0 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100004 0 0 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100005 350 250 150 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100006 250 150 0 0");

        simulatedInputs = new String[] {
                "100000", "100001", "100002", "100", "100003", "Kyle", "ab3ma"
        };

        expectedOutcomes = new String[] {
                "100000 points: Java=400; DSA=300; Databases=200; Spring=100",
                "100001 points: Java=300; DSA=200; Databases=100; Spring=0",
                "100002 points: Java=200; DSA=100; Databases=0; Spring=0",
                "No student is found for id=100.",
                "100003 points: Java=100; DSA=0; Databases=0; Spring=0",
                "No student is found for id=Kyle.",
                "No student is found for id=ab3ma"
        };
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 6)
    void findStudent() {
        assertEquals(expectedOutcomes[index], studentFinder.findStudent(studentDictionary, courses, simulatedInputs[index]));
    }
}