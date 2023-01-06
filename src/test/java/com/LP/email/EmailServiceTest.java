package com.LP.email;

import com.LP.course.*;
import com.LP.student.Student;
import com.LP.student.StudentAdder;
import com.LP.student.StudentFinder;
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

class EmailServiceTest {
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
    static EmailService emailService;
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
        emailService = new EmailService();

        courses.add(java);
        courses.add(dsa);
        courses.add(databases);
        courses.add(spring);

        studentAdder.addStudent(studentDictionary, "John Doe Jdoe@gmail.com");
        studentAdder.addStudent(studentDictionary, "Jill Scott Jscott@yahoo.com");
        studentAdder.addStudent(studentDictionary, "Robert Lillard rob.lill@gmail.com");

        coursePointsAdder.addPoints(studentDictionary, courses, "100000 600 400 200 100");
        coursePointsAdder.addPoints(studentDictionary, courses, "100001 300 200 100 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100002 200 100 0 0");

        expectedOutcomes = new String[] {
                """
                To: Jdoe@gmail.com
                Re: Your Learning Progress
                Hello, John Doe! You have accomplished our Java course!
                To: Jdoe@gmail.com
                Re: Your Learning Progress
                Hello, John Doe! You have accomplished our DSA course!
                Total 1 students have been notified.""",
                """
                To: Jscott@yahoo.com
                Re: Your Learning Progress
                Hello, Jill Scott! You have accomplished our Java course!
                To: rob.lill@gmail.com
                Re: Your Learning Progress
                Hello, Robert Lillard! You have accomplished our DSA course!
                To: Jscott@yahoo.com
                Re: Your Learning Progress
                Hello, Jill Scott! You have accomplished our Spring course!
                Total 2 students have been notified.""",
        };
    }

    @AfterEach
    void addPoints() {
        coursePointsAdder.addPoints(studentDictionary, courses, "100001 300 0 0 550");
        coursePointsAdder.addPoints(studentDictionary, courses, "100002 0 300 0 0");
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 2)
    void notifyStudent() {
        assertEquals(expectedOutcomes[index], emailService.notify(courses));
    }
}