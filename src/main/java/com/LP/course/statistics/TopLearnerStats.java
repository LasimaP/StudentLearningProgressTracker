package com.LP.course.statistics;

import com.LP.course.Course;
import com.LP.student.EnrolledStudent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TopLearnerStats {

    private static final Comparator<EnrolledStudent> topLearnerComparator = Comparator.comparingInt(EnrolledStudent::getCurrentPoints);
    private static final Comparator<EnrolledStudent> idComparator = Comparator.comparingInt(EnrolledStudent::getId);

    public static String topStudents(Course course) {
        StringBuilder output = new StringBuilder();

        List<EnrolledStudent> students = new ArrayList<>(course.getEnrolledStudents().values());
        students.sort(topLearnerComparator.reversed().thenComparing(idComparator));

        for (EnrolledStudent student : students) {
            if (student.getCurrentPoints() != 0) {
                System.out.println(
                        student.getId() + "\t"
                        + student.getCurrentPoints() + "\t\t"
                        + course.calculateCompleted(student.getId()) + "%"
                );

                output.append(student.getId());
                output.append("\t");
                output.append(student.getCurrentPoints());
                output.append("\t\t");
                output.append(course.calculateCompleted(student.getId()));
                output.append("%");
                output.append("\n");
            }
        }
        return output.toString();
    }
}
