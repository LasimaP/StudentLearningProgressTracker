package com.LP.course.statistics;

import com.LP.course.Course;

import java.util.Comparator;
import java.util.List;

public class PopularityStats {

    private static final Comparator<Course> popularityComparator = Comparator.comparingInt(course -> course.getEnrolledStudents().size());

    public static String mostPopular(List<Course> courseList) {
        courseList.sort(popularityComparator.reversed());

        StringBuilder output = new StringBuilder();

        int max = courseList.get(0).getEnrolledStudents().size();
        if (max == 0) {
            System.out.println("n/a");
            return "n/a";
        }

        String space = "";
        for (Course course : courseList) {
            if (course.getEnrolledStudents().size() == max) {
                System.out.print(space + course);
                output.append(space).append(course);
                space = ", ";
            }
        }
        return output.toString();
    }

    public static String leastPopular(List<Course> courseList) {
        courseList.sort(popularityComparator);

        StringBuilder output = new StringBuilder();

        int min = courseList.get(0).getEnrolledStudents().size();
        int max = courseList.get(courseList.size() - 1).getEnrolledStudents().size();
        if (min == max) {
            System.out.print("n/a");
            return "n/a";
        }

        String space = "";
        for (Course course : courseList) {
            if (course.getEnrolledStudents().size() == min) {
                System.out.print(space + course);
                output.append(space).append(course);
                space = ", ";
            }
        }
        return output.toString();
    }
}
