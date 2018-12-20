package Menu;

import Utilities.Util;
import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherMenu {

    static List<MenuOption> menuTeacher = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public TeacherMenu() {
        teacherMenu();
    }

    private static void teacherMenu() {
        menuTeacher.clear();
        boolean run = true;
        menuTeacher.add(new MenuOption("0) Back", () -> System.out.println("")));
        menuTeacher.add(new MenuOption("1) Create New Teacher", () -> Util.teacherDAO.createTeacher(createTeacher())));
        menuTeacher.add(new MenuOption("2) Read Teacher", () -> readTeacher()));
        menuTeacher.add(new MenuOption("3) Update Teacher", () -> Util.teacherDAO.updateTeacher(updateTeacher())));
        menuTeacher.add(new MenuOption("4) Delete Teacher", () -> Util.teacherDAO.deleteTeacher(Util.readId())));
        menuTeacher.add(new MenuOption("5) Add Teacher To Course", () -> addTeacherToCourse()));
        menuTeacher.add(new MenuOption("6) List all Teachers", () -> Util.printList(Util.teacherDAO.readAllTeachers())));
        while (run) {
            System.out.println("--TEACHER MENU--");
            menuTeacher.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuTeacher.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuTeacher.size() - 1));
                }
            }
        }
    }

    private static Teacher createTeacher() {
        Teacher teacher = new Teacher();
        System.out.print("Name: ");
        String name = sc.nextLine();
        teacher.setName(name);
        System.out.print("Birthdate(yyyyMMdd): ");
        String bday = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate parsedDate = LocalDate.parse(bday, formatter);
            teacher.setBirthdate(parsedDate);
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid dateformat. Please try again.");
            return null;
        }
        return teacher;
    }

    private static Teacher updateTeacher() {
        System.out.print("ID of teacher to Update(0 to cancel): ");
        int id = Util.readNumber();
        if (id == 0) {
            return null;
        }
        Teacher teacher = Util.teacherDAO.readTeacher(id);
        if (teacher == null) {
            return null;
        } else {
            System.out.println("Current name: '" + teacher.getName() + "'. Leave 'New Name' empty to skip.");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + teacher.getBirthdate().toString() + "'. Leave 'New Birthday' empty to skip.");
            System.out.println("New Birthday(yyyyMMdd): ");
            String newBday = sc.nextLine();
            if (!newName.equals("")) {
                teacher.setName(newName);
            }
            if (!newBday.equals("")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate parsedDate = LocalDate.parse(newBday, formatter);
                    teacher.setBirthdate(parsedDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid dateformat. Try again.");
                }
            }
        }
        return teacher;
    }

    private static void addTeacherToCourse() {
        System.out.println("List of teachers: ");
        Util.printList(Util.teacherDAO.readAllTeachers());

        System.out.print("Select teacher ID: ");
        int teacherID = Util.readNumber();
        Teacher teacher = Util.teacherDAO.readTeacher(teacherID);

        if (teacher != null) {
            System.out.println("Available Courses: ");
            Util.printList(Util.courseDAO.readAllCourses());
            System.out.print("Select Course ID: ");
            int courseID = Util.readNumber();
            Course course = Util.courseDAO.readCourse(courseID);
            List<Course> teacherCourses = Util.teacherDAO.readAllCourses(teacher);
            if (!teacherCourses.contains(course)) {
                if (course != null) {
                    course.addTeacher(teacher);
                    Util.courseDAO.updateCourse(course);
                    System.out.println(teacher.getName() + " added to course " + course.getName());
                }
            } else {
                System.out.println("Teacher " + teacher.getName() + " is already teaching course " + course.getName());
            }
        }
    }

    private static void readTeacher() {
        Teacher teacher = Util.teacherDAO.readTeacher(Util.readId());
        if (teacher != null) {
            System.out.println(teacher);
        }
    }
}
