package com.LP.course.statistics;

import com.LP.course.Course;

import java.util.List;

public class ActivityStats {

    public static String highestActivity(List<Course> courseList) {
        return PopularityStats.mostPopular(courseList);
    }

    public static String lowestActivity(List<Course> courseList) {
        return PopularityStats.leastPopular(courseList);
    }
}
