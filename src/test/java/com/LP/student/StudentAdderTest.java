package com.LP.student;

import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentAdderTest {
    static String[] names;
    static String[] expectedOutcomes;
    static int index = 0;

    static Map<Integer, Student> studentDictionary;
    static StudentAdder studentAdder;

    String name;
    String expected;
    @BeforeAll
    static void setUp() {
        StudentInputProcessor studentInputProcessor = new StudentInputProcessor();
        StudentInputVerifier studentInputVerifier = new StudentInputVerifier();
        studentAdder = new StudentAdder(studentInputProcessor, studentInputVerifier);
        studentDictionary = new HashMap<>();

        names = new String[] {
                "Jean-Clause van Helsing jc@google.it", "Mary Luise Johnson maryj@google.com", "陳 港 生",
                "John Doe email", "J. Doe name@domain.com", "John D. name@domain.com", "        "
        };

        expectedOutcomes = new String[] {
                "The student has been added.", "The student has been added.", "Incorrect first name.",
                "Incorrect email.", "Incorrect first name.", "Incorrect last name.", "Incorrect first name."
        };
    }

    @BeforeEach
    void createStudent() {
        name = names[index];
        expected = expectedOutcomes[index];
    }

    @AfterEach
    void incrementIndex() {
        index++;
    }

    @RepeatedTest(value = 6)
    void addStudent() {
        assertEquals(expected, studentAdder.addStudent(studentDictionary, name));
    }
}