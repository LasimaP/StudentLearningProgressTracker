package com.LP.course;

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

class CoursePointsAdderTest {
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
        coursePointsAdder = new CoursePointsAdder(studentInputVerifier, studentInputProcessor, studentFinder, courseEnrolling);

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
        studentAdder.addStudent(studentDictionary, "Kyle Johnson kjohn@uncc.edu");
        studentAdder.addStudent(studentDictionary, "Mike Calls Mcalls@gmail.com");
        studentAdder.addStudent(studentDictionary, "Cathy Griffin Cgriffin@uncc.edu");

        simulatedInputs = new String[] {
                "100000 400 300 200 100", "100001 300 200 100 0", "", "100003 100 0 0 0",
                "100004 0 0 0 0", "100005 -350 250 150 0", "1000 250 150 0 0", "100007 0 a 300 200",
                "akdf 0 300 200 100", "             "
        };

        expectedOutcomes = new String[] {
                "Points updated.", "Points updated.", "Incorrect points format.", "Points updated.", "Points updated.",
                "Incorrect points format.", "No student is found for id=1000.", "Incorrect points format.",
                "No student is found for id=akdf.", "Incorrect points format."
        };
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 10)
    void addPoints() {
        assertEquals(expectedOutcomes[index], coursePointsAdder.addPoints(studentDictionary, courses, simulatedInputs[index]));
    }
}