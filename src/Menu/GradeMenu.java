package Menu;

import Utilities.Util;
import Utilities.GradeEnum;
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
        menuGrade.add(new MenuOption("0) Back", () -> System.out.println("")));
        menuGrade.add(new MenuOption("1) Create Grade", () -> Util.gradeDAO.createGrade(createGrade())));
        menuGrade.add(new MenuOption("2) Read Grades by Student", () -> readGrades()));
        menuGrade.add(new MenuOption("3) Update Grade", () -> Util.gradeDAO.updateGrade(updateGrade())));
        menuGrade.add(new MenuOption("4) Delete Grade", () -> Util.gradeDAO.deleteGrade(Util.readId())));
        menuGrade.add(new MenuOption("5) List all Grades", () -> Util.printList(Util.gradeDAO.readAllGrade())));
        while (run) {
            System.out.println("--GRADE MENU--");
            menuGrade.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
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
        Util.printList(Util.studentDAO.readAllStudents());
        Util.printList(Util.gradeDAO.readGradesByStudent(Util.studentDAO.readStudent(Util.readId())));
    }

    private static Grade createGrade() {
        Util.printList(Util.studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = Util.readNumber();
        Util.printList(Util.studentDAO.readAllCourses(Util.studentDAO.readStudent(studentId)));
        System.out.print("Course ID: ");
        int courseId = Util.readNumber();
        System.out.print("Grade (IG / G / VG): ");
        String gradeString = sc.nextLine();
        Course course = Util.courseDAO.readCourse(courseId);
        course.removeStudent(Util.studentDAO.readStudent(studentId));
        Grade grade = new Grade();
        grade.setCourse(Util.courseDAO.readCourse(courseId));
        grade.setStudent(Util.studentDAO.readStudent(studentId));
        try {
            grade.setGrade(GradeEnum.valueOf(gradeString));
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid grade. Enter a valid Grade(IG / G / VG)");
        }
        return grade;
    }

    private static Grade updateGrade() {
        Util.printList(Util.studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = Util.readNumber();
        Util.printList(Util.gradeDAO.readGradesByStudent(Util.studentDAO.readStudent(studentId)));
        System.out.print("ID of Grade to Update: ");
        int gradeId = Util.readNumber();
        System.out.print("New Grade (IG / G / VG): ");
        String gradeString = sc.nextLine();
        Grade grade = Util.gradeDAO.readGrade(gradeId);
        try {
            grade.setGrade(GradeEnum.valueOf(gradeString));
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid grade. Enter a valid Grade(IG / G / VG)");
        }
        return grade;
    }
}
