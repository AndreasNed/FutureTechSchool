package Menu;

import Utilities.Utilities;
import futuretechschool.GradeEnum;
import futuretechschool.domain.Course;
import futuretechschool.domain.Grade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeMenu {

    static List<MenuOption> menuGrade = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public GradeMenu() {
        gradeMenu();
    }

    private static void gradeMenu() {
        menuGrade.clear();
        boolean run = true;
        menuGrade.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuGrade.add(new MenuOption("1) Create Grade", () -> Utilities.gradeDAO.createGrade(createGrade())));
        menuGrade.add(new MenuOption("2) Read Grades by Student", () -> readGrades()));
        menuGrade.add(new MenuOption("3) Update Grade", () -> Utilities.gradeDAO.updateGrade(updateGrade())));
        menuGrade.add(new MenuOption("4) Delete Grade", () -> Utilities.gradeDAO.deleteGrade(Utilities.readId())));
        menuGrade.add(new MenuOption("5) List all Grades", () -> Utilities.printList(Utilities.gradeDAO.readAllGrade())));
        while (run) {
            System.out.println("--GRADE MENU--");
            for (MenuOption menuOption : menuGrade) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = Utilities.readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuGrade.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuGrade.size() - 1));
                }
            }
        }
    }

    private static void readGrades() {
        Utilities.printList(Utilities.studentDAO.readAllStudents());
        Utilities.printList(Utilities.gradeDAO.readGradesByStudent(Utilities.studentDAO.readStudent(Utilities.readId())));
    }

    private static Grade createGrade() {
        Utilities.printList(Utilities.studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = Utilities.readNumber();
        Utilities.printList(Utilities.studentDAO.readAllCourses(Utilities.studentDAO.readStudent(studentId)));
        System.out.print("Course ID: ");
        int courseId = Utilities.readNumber();
        System.out.print("Grade (IG / G / VG): ");
        String gradeString = sc.nextLine();
        Course course = Utilities.courseDAO.readCourse(courseId);
        course.removeStudent(Utilities.studentDAO.readStudent(studentId));
        Grade grade = new Grade();
        grade.setCourse(Utilities.courseDAO.readCourse(courseId));
        grade.setStudent(Utilities.studentDAO.readStudent(studentId));
        try {
            grade.setGrade(GradeEnum.valueOf(gradeString));
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid grade. Enter a valid Grade(IG / G / VG)");
        }
        return grade;

    }

    private static Grade updateGrade() {
        Utilities.printList(Utilities.studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = Utilities.readNumber();
        Utilities.printList(Utilities.gradeDAO.readGradesByStudent(Utilities.studentDAO.readStudent(studentId)));
        System.out.print("ID of Grade to Update: ");
        int gradeId = Utilities.readNumber();
        System.out.print("New Grade (IG / G / VG): ");
        String gradeString = sc.nextLine();
        Grade grade = Utilities.gradeDAO.readGrade(gradeId);
        try {
            grade.setGrade(GradeEnum.valueOf(gradeString));
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid grade. Enter a valid Grade(IG / G / VG)");
        }

        return grade;

    }
}
