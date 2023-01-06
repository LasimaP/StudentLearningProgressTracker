package com.LP.email;

import com.LP.course.Course;
import com.LP.student.EnrolledStudent;
import com.LP.student.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmailService {
    public String notify(List<Course> courseList) {
        StringBuilder output = new StringBuilder();

        Set<Student> studentSet = new HashSet<>();

        for (Course course : courseList) {
            List<EnrolledStudent> enrolledStudents = new ArrayList<>();
            enrolledStudents.addAll(course.getEnrolledStudents().values());
            for (EnrolledStudent enrolledStudent : enrolledStudents) {
                if (enrolledStudent.getCurrentPoints() == course.getTotalPoints()) {
                    Student student = enrolledStudent.getStudent();
                    congratsEmail(student, course);
                    studentSet.add(student);
                    course.getEnrolledStudents().remove(student.getId(), enrolledStudent);
                    output.append(congratsEmail(student, course)).append("\n");
                }
            }
        }
        System.out.println("Total " + studentSet.size() + " students have been notified.");

        return output + "Total " + studentSet.size() + " students have been notified.";
    }

    private String congratsEmail (Student student, Course course) {
        StringBuilder output = new StringBuilder();

        System.out.println("To: " + student.getEmail());
        System.out.println("Re: Your Learning Progress");
        System.out.println("Hello, " + student.getFirstName() + " " + student.getLastName()
                + "! You have accomplished our " + course.getName() + " course!");

        output.append("To: ");
        output.append(student.getEmail());
        output.append("\nRe: Your Learning Progress");
        output.append("\nHello, ");
        output.append(student.getFirstName());
        output.append(" ");
        output.append(student.getLastName());
        output.append("! You have accomplished our ");
        output.append(course.getName());
        output.append(" course!");

        return output.toString();

    }
}
