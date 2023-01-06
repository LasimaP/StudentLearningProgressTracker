package com.LP.course.statistics;

import com.LP.course.Course;

import java.util.*;

public class DifficultyStats {

    public static String easiest(List<Course> courseList) {
        return difficultyStat(courseList, "max");

    }

    public static String hardest(List<Course> courseList) {
        return difficultyStat(courseList, "min");
    }

    private static String difficultyStat(List<Course> courseList, String choice) {
        Map<Course, Double> coursePointAverages = new LinkedHashMap<>();

        StringBuilder output = new StringBuilder();
        for (Course course : courseList) {
            coursePointAverages.put(course, getAverage(course));
        }

        double num = maxOrMin(coursePointAverages, choice);
        int emptyNum = 0;
        String space = "";
        for (Course course : coursePointAverages.keySet()) {
            if (coursePointAverages.get(course) == num) {
                System.out.print(space + course);
                output.append(space).append(course);
                space = ", ";
            } else {
                emptyNum++;
            }
        }
        if (emptyNum == 4) {
            System.out.print("n/a");
            return "n/a";
        }
        return output.toString();
    }

    private static double getAverage(Course course) {
        var students = course.getEnrolledStudents();
        int studentCount = students.size();
        int pointsTotal = 0;

        for(int id : students.keySet()) {
            pointsTotal = pointsTotal + students.get(id).getCurrentPoints();
        }

        return (double) pointsTotal / studentCount;
    }

    private static double maxOrMin(Map<Course, Double> map, String choice) {
        List<Double> numArray = new ArrayList<>();
        numArray.addAll(map.values());
        numArray.sort(Comparator.naturalOrder());

        double min = numArray.get(0);
        double max = numArray.get(numArray.size() - 1);

        if (choice.equals("min") && (min != max)) {
            return min;
        } else if (choice.equals("max")) {
            return max;
        } else {
            return -1;
        }
    }
}
