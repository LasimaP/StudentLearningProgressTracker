package com.LP.learningprogresstracker;

import com.LP.course.CourseDatabase;
import com.LP.course.CoursePointsAdder;
import com.LP.course.statistics.StatisticsViewer;
import com.LP.email.EmailService;
import com.LP.student.StudentAdder;
import com.LP.student.StudentDatabase;
import com.LP.student.StudentFinder;
import com.LP.student.StudentPrinter;

import java.util.Scanner;

public class LearningProgressTracker {
    private final Scanner scanner = new Scanner(System.in);
    private StudentDatabase studentDatabase;
    private CourseDatabase courseDatabase;
    private StudentAdder studentAdder;
    private final StudentFinder studentFinder;
    private StudentPrinter studentPrinter;
    private CoursePointsAdder coursePointsAdder;
    private StatisticsViewer statisticsViewer;
    private EmailService emailService;

    public LearningProgressTracker(
            StudentDatabase studentDatabase,
            CourseDatabase courseDatabase,
            StudentAdder studentAdder,
            StudentFinder studentFinder,
            StudentPrinter studentPrinter,
            CoursePointsAdder coursePointsAdder,
            StatisticsViewer statisticsViewer,
            EmailService emailService
    ) {
        this.studentDatabase = studentDatabase;
        this.courseDatabase = courseDatabase;
        this.studentAdder = studentAdder;
        this.studentFinder = studentFinder;
        this.studentPrinter = studentPrinter;
        this.coursePointsAdder = coursePointsAdder;
        this.statisticsViewer = statisticsViewer;
        this.emailService = emailService;
    }

    public void addStudent() {

        System.out.println("Enter student credentials or 'back' to return:");

        int studentCounter = 0;

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }
            String output = studentAdder.addStudent(studentDatabase.getStudents(), input);
            if (output.equals("The student has been added.")) {
                studentCounter += 1;
            }
        }
        System.out.println("Total " + studentCounter + " students have been added.");
    }

    public void findStudent() {
        System.out.println("Enter an id or 'back' to return:");

        while (true) {
            String input = scanner.next();
            if (input.equals("back")) {
                break;
            }

            studentFinder.findStudent(studentDatabase.getStudents(), courseDatabase.getCourseList(), input);
        }
        scanner.nextLine();
    }

    public void listStudents() {
        studentPrinter.listStudents(studentDatabase.getStudents());
    }

    public void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }

            coursePointsAdder.addPoints(studentDatabase.getStudents(), courseDatabase.getCourseList(), input);
        }
    }

    public void statistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        statisticsViewer.viewStats(courseDatabase.getCourseList());

        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("back")) {
                break;
            }

            switch (input) {
                case "java" -> {
                    statisticsViewer.viewTopStudents(courseDatabase.getCourse1());
                }
                case "dsa" -> {
                    statisticsViewer.viewTopStudents(courseDatabase.getCourse2());
                }
                case "databases" -> {
                    statisticsViewer.viewTopStudents(courseDatabase.getCourse3());
                }
                case "spring" -> {
                    statisticsViewer.viewTopStudents(courseDatabase.getCourse4());
                }
                default -> System.out.println("Unknown course.");
            }
        }
    }
    public void emailStudents() {
        emailService.notify(courseDatabase.getCourseList());
    }
}
