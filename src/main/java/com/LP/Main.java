package com.LP;

import com.LP.course.*;
import com.LP.course.statistics.StatisticsViewer;
import com.LP.email.EmailService;
import com.LP.learningprogresstracker.LearningProgressTracker;
import com.LP.student.*;
import com.LP.student.verificationandprocessing.StudentInputProcessor;
import com.LP.student.verificationandprocessing.StudentInputVerifier;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        LearningProgressTracker learningProgressTracker = lptInitializer();
        lptStarter(learningProgressTracker);

    }

    public static void lptStarter(LearningProgressTracker learningProgressTracker) {
        System.out.println("Learning Progress Tracker\n");
        commandList();

        String command = "";
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            switch (command) {
                case "commands":
                    commandList();
                    break;
                case "add students":
                    learningProgressTracker.addStudent();
                    break;
                case "list":
                    learningProgressTracker.listStudents();
                    break;
                case "add points":
                    learningProgressTracker.addPoints();
                    break;
                case "find":
                    learningProgressTracker.findStudent();
                    break;
                case "statistics":
                    learningProgressTracker.statistics();
                    break;
                case "notify":
                    learningProgressTracker.emailStudents();
                    break;
                case "exit":
                    System.out.println("Bye!");
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                default:
                    if (command.isBlank()) {
                        System.out.println("No input.");
                    } else {
                        System.out.println("Unknown command!");
                    }
                    break;
            }
        }
    }

    public static LearningProgressTracker lptInitializer() {
        StudentInputProcessor studentInputProcessor = new StudentInputProcessor();
        StudentInputVerifier studentInputVerifier = new StudentInputVerifier();
        StudentDatabase studentDatabase = new StudentDatabase();

        StudentAdder studentAdder = new StudentAdder(
                studentInputProcessor,
                studentInputVerifier
        );

        StudentFinder studentFinder = new StudentFinder();

        StudentPrinter studentPrinter = new StudentPrinter();

        Course java = new Java();
        Course databases = new Databases();
        Course dsa = new DSA();
        Course spring = new Spring();

        CourseDatabase courseDatabase = new CourseDatabase(
                java,
                dsa,
                databases,
                spring
        );

        CourseEnrolling courseEnrolling = new CourseEnrolling();

        CoursePointsAdder coursePointsAdder = new CoursePointsAdder(
                studentInputVerifier,
                studentInputProcessor,
                studentFinder,
                courseEnrolling
        );

        StatisticsViewer statisticsViewer = new StatisticsViewer();
        EmailService emailService = new EmailService();

        return new LearningProgressTracker(
                studentDatabase,
                courseDatabase,
                studentAdder,
                studentFinder,
                studentPrinter,
                coursePointsAdder,
                statisticsViewer,
                emailService
        );
    }

    public static void commandList() {
        System.out.println("'commands': to see list of commands");
        System.out.println("'add students': to add students");
        System.out.println("'list': to list students");
        System.out.println("'add points': to add points");
        System.out.println("'find': to find a student");
        System.out.println("'statistics': to get course stats");
        System.out.println("'notify': to notify students of course completion");
        System.out.println("'exit': to exit program");
    }
}