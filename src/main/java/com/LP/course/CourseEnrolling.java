package com.LP.course;

import com.LP.student.EnrolledStudent;
import com.LP.student.Student;

import java.util.List;
import java.util.Map;

public class CourseEnrolling {
    public void enrollStudent(Course course, Map<Integer, Student> studentDictionary, int studentID) {
        if (!course.getEnrolledStudents().containsKey(studentID)) {
            Student student = studentDictionary.get(studentID);
            EnrolledStudent enrolledStudent = new EnrolledStudent(student, 0);
            course.getEnrolledStudents().put(studentID, enrolledStudent);
        }
    }

    public static boolean noneEnrolled(List<Course> courseList) {
        int amountEnrolled = 0;

        for (Course course : courseList) {
            amountEnrolled += course.getEnrolledStudents().size();
        }
        return amountEnrolled == 0;
    }
}

