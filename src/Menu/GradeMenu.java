package Menu;

import DAO.GradeDAO;
import DAO.GradeDAOImpl;
import static Menu.MainMenu.courseDAO;
import static Menu.MainMenu.menuGrade;
import static Menu.MainMenu.studentDAO;
import Utilities.Utilities;
import futuretechschool.GradeEnum;
import futuretechschool.domain.Course;
import futuretechschool.domain.Grade;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GradeMenu {

    static GradeDAO gradeDAO = new GradeDAOImpl();
    static List<MenuOption> menuGrade = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public GradeMenu() {
        gradeMenu();
    }

    private static void gradeMenu() {
        menuGrade.clear();
        boolean run = true;
        menuGrade.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuGrade.add(new MenuOption("1) Create Grade", () -> gradeDAO.createGrade(createGrade())));
        menuGrade.add(new MenuOption("2) Read Grades by Student", () -> readGrades()));
        menuGrade.add(new MenuOption("3) Update Grade", () -> gradeDAO.updateGrade(updateGrade())));
        menuGrade.add(new MenuOption("4) Delete Grade", () -> gradeDAO.deleteGrade(MainMenu.readId())));
        menuGrade.add(new MenuOption("5) List all Grades", () -> Utilities.printList(gradeDAO.readAllGrade())));
        while (run) {
            System.out.println("--GRADE MENU--");
            for (MenuOption menuOption : menuGrade) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = MainMenu.readNumber();
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
        Utilities.printList(studentDAO.readAllStudents());
        Utilities.printList(gradeDAO.readGradesByStudent(studentDAO.readStudent(Utilities.readId())));
    }

    private static Grade createGrade() {
        MainMenu.printList(studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = MainMenu.readNumber();
        MainMenu.printList(studentDAO.readAllCourses(studentDAO.readStudent(studentId)));
        System.out.print("Course ID: ");
        int courseId = MainMenu.readNumber();
        System.out.print("Grade (IG / G / VG): ");
        String gradeString = sc.nextLine();

        Course course = courseDAO.readCourse(courseId);
        course.removeStudent(studentDAO.readStudent(studentId));

        Grade grade = new Grade();
        grade.setCourse(courseDAO.readCourse(courseId));
        grade.setStudent(studentDAO.readStudent(studentId));
        grade.setGrade(GradeEnum.valueOf(gradeString));
        return grade;

    }

    private static Grade updateGrade() {
        Utilities.printList(studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = Utilities.readNumber();
        Utilities.printList(gradeDAO.readGradesByStudent(studentDAO.readStudent(studentId)));
        System.out.print("ID of Grade to Update: ");
        int gradeId = Utilities.readNumber();
        System.out.println("New Grade (IG / G / VG): ");
        String gradeString = sc.next();
        Grade grade = gradeDAO.readGrade(gradeId);
        grade.setGrade(GradeEnum.valueOf(gradeString));

        return grade;

    }
}
