package com.LP.course;

import java.util.ArrayList;
import java.util.List;

public class CourseDatabase {
    private Course course1;
    private Course course2;
    private Course course3;
    private Course course4;
    private List<Course> courseList;

    public CourseDatabase(
            Course course1,
            Course course2,
            Course course3,
            Course course4
    ) {
        this.course1 = course1;
        this.course2 = course2;
        this.course3 = course3;
        this.course4 = course4;
        this.courseList = new ArrayList<>();
        addCourses();
    }

    public Course getCourse1() {
        return course1;
    }

    public Course getCourse2() {
        return course2;
    }

    public Course getCourse3() {
        return course3;
    }

    public Course getCourse4() {
        return course4;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    private void addCourses() {
        this.courseList.add(this.course1);
        this.courseList.add(this.course2);
        this.courseList.add(this.course3);
        this.courseList.add(this.course4);

    }
}
