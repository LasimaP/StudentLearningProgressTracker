package com.LP.course;

import com.LP.student.*;
import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;

import java.util.List;
import java.util.Map;

public class CoursePointsAdder {
    private StudentInputVerifier studentInputVerifier;
    private StudentInputProcessor studentInputProcessor;
    private StudentFinder studentFinder;

    private CourseEnrolling courseEnrolling;

    public CoursePointsAdder(
            StudentInputVerifier studentInputVerifier,
            StudentInputProcessor studentInputProcessor,
            StudentFinder studentFinder,
            CourseEnrolling courseEnrolling
    ) {
        this.studentInputVerifier = studentInputVerifier;
        this.studentInputProcessor = studentInputProcessor;
        this.studentFinder = studentFinder;
        this.courseEnrolling = courseEnrolling;
    }

    public String addPoints(
            Map<Integer, Student> studentDictionary,
            List<Course> courseList,
            String idAndPoints
    ) {
        String[] idAndPointsArray = idAndPoints.split(" ");
        if (idAndPointsArray.length != 5 || !studentInputVerifier.pointsVerifier(idAndPointsArray)) {
            System.out.println("Incorrect points format.");
            return "Incorrect points format.";
        } else {
            try {
                int studentID = Integer.parseInt(idAndPointsArray[0]);
                if (!studentFinder.findStudent(studentDictionary, studentID)) {
                    System.out.println("No student is found for id=" + studentID + ".");
                    return "No student is found for id=" + studentID + ".";
                } else {
                    int[] pointsArray = studentInputProcessor.pointSplitter(idAndPointsArray);
                    int idx = 0;
                    for (Course course : courseList) {
                        if (pointsArray[idx] != 0) {
                            courseEnrolling.enrollStudent(course, studentDictionary, studentID);
                            course.addPoints(studentID,pointsArray[idx]);
                        }
                        idx++;
                    }
                    System.out.println("Points updated.");
                    return "Points updated.";
                }

            } catch (NumberFormatException e) {
                String studentID = idAndPointsArray[0];
                System.out.println("No student is found for id=" + studentID + ".");
                return "No student is found for id=" + studentID + ".";
            }
        }
    }
}
