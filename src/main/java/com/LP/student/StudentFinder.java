package com.LP.student;

import com.LP.course.Course;

import java.util.List;
import java.util.Map;

public class StudentFinder {

    public String findStudent(
            Map<Integer, Student> studentDictionary,
            List<Course> courseList,
            String id
    ) {

        try {
            int studentID = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            System.out.println("No student is found for id=" + id + ".");
            return "No student is found for id=" + id + ".";
        }
        int studentID = Integer.parseInt(id);
        if(!studentDictionary.containsKey(studentID)) {
            System.out.println("No student is found for id=" + studentID + ".");
            return "No student is found for id=" + studentID + ".";
        } else {
            StringBuilder output = new StringBuilder();
            System.out.print(studentID + " points: ");
            output.append(studentID).append(" points: ");

            String space = "";
            for (Course course : courseList) {
                var enrolledStudents = course.getEnrolledStudents();
                int points = enrolledStudents.containsKey(studentID) ? enrolledStudents.get(studentID).getCurrentPoints() : 0;
                System.out.print(space + course + "=" + points);
                output.append(space).append(course).append("=").append(points);
                space = "; ";
            }
            System.out.println();
            return output.toString();
        }
    }

    public boolean findStudent(Map<Integer, Student> studentList, int studentID) {
        return studentList.containsKey(studentID);
    }
}
