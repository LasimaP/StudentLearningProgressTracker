package com.LP.course.statistics;

import com.LP.course.Course;
import com.LP.course.CourseEnrolling;

import java.util.List;

public class StatisticsViewer {

    public String viewStats(List<Course> courseList) {
        StringBuilder output = new StringBuilder();

        if (CourseEnrolling.noneEnrolled(courseList)) {
            System.out.println("Most popular: n/a");
            System.out.println("Least popular: n/a");
            System.out.println("Highest activity: n/a");
            System.out.println("Lowest activity: n/a");
            System.out.println("Easiest course: n/a");
            System.out.println("Hardest course: n/a");

            output.append("Most popular: n/a\n");
            output.append("Least popular: n/a\n");
            output.append("Highest activity: n/a\n");
            output.append("Lowest activity: n/a\n");
            output.append("Easiest course: n/a\n");
            output.append("Hardest course: n/a\n");
        } else {
            System.out.print("Most popular: ");
            output.append("Most popular: ");
            output.append(PopularityStats.mostPopular(courseList));

            System.out.print("\nLeast popular: ");
            output.append("\nLeast popular: ");
            output.append(PopularityStats.leastPopular(courseList));

            System.out.print("\nHighest activity: ");
            output.append("\nHighest activity: ");
            output.append(ActivityStats.highestActivity(courseList));

            System.out.print("\nLowest activity: ");
            output.append("\nLowest activity: ");
            output.append(ActivityStats.lowestActivity(courseList));

            System.out.print("\nEasiest course: ");
            output.append("\nEasiest course: ");
            output.append(DifficultyStats.easiest(courseList));

            System.out.print("\nHardest course: ");
            output.append("\nHardest course: ");
            output.append(DifficultyStats.hardest(courseList)).append("\n");
            System.out.println();

        }
        return output.toString();
    }

    public String viewTopStudents(Course course) {
        StringBuilder output = new StringBuilder();

        System.out.println(course.getName());
        System.out.println("id\t\tpoints\tcompleted");

        output.append(course.getName()).append("\n");
        output.append("id\t\tpoints\tcompleted\n");
        output.append(TopLearnerStats.topStudents(course));

        return output.toString();
    }
}
