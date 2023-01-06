package com.LP.course.statistics;

import com.LP.course.*;
import com.LP.student.*;
import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatisticsViewerTest {
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
    static StatisticsViewer statisticsViewer;
    static Course[] simulatedInputs;
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
        studentAdder.addStudent(studentDictionary, "Kyle Johnson kjohn@uncc.edu");
        studentAdder.addStudent(studentDictionary, "Mike Calls Mcalls@gmail.com");
        studentAdder.addStudent(studentDictionary, "Cathy Griffin Cgriffin@uncc.edu");

        coursePointsAdder.addPoints(studentDictionary, courses, "100000 400 300 200 100");
        coursePointsAdder.addPoints(studentDictionary, courses, "100001 300 200 100 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100002 200 100 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100003 100 0 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100004 0 0 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100005 350 250 150 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100006 250 150 0 0");
        coursePointsAdder.addPoints(studentDictionary, courses, "100007 0 0 300 200");
        coursePointsAdder.addPoints(studentDictionary, courses, "100008 0 300 200 100");
        coursePointsAdder.addPoints(studentDictionary, courses, "100009 100 200 300 400");

        simulatedInputs = new Course[] {java, dsa, databases, spring};

        expectedOutcomes = new String[] {
                """
                Java
                id		points	completed
                100000	400		66.7%
                100005	350		58.3%
                100001	300		50.0%
                100006	250		41.7%
                100002	200		33.3%
                100003	100		16.7%
                100009	100		16.7%
                """,
                """
                DSA
                id		points	completed
                100000	300		75.0%
                100008	300		75.0%
                100005	250		62.5%
                100001	200		50.0%
                100009	200		50.0%
                100006	150		37.5%
                100002	100		25.0%
                """,
                """
                Databases
                id		points	completed
                100007	300		62.5%
                100009	300		62.5%
                100000	200		41.7%
                100008	200		41.7%
                100005	150		31.3%
                100001	100		20.8%
                """,
                """
                Spring
                id		points	completed
                100009	400		72.7%
                100007	200		36.4%
                100000	100		18.2%
                100008	100		18.2%
                """,
                """
                Most popular: Java, DSA
                Least popular: Spring
                Highest activity: Java, DSA
                Lowest activity: Spring
                Easiest course: Java
                Hardest course: Spring
                """
        };

        statisticsViewer = new StatisticsViewer();
        statisticsViewer.viewStats(courses);
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 4)
    void viewTopStudents() {
        assertEquals(
                expectedOutcomes[index],
                statisticsViewer.viewTopStudents(simulatedInputs[index])
        );
    }

    @AfterAll
    @Test
    static void viewStats() {
        assertEquals(expectedOutcomes[index],statisticsViewer.viewStats(courses));
    }
}