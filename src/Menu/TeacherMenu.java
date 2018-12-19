/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;


import Utilities.Utilities;
import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author GasCan
 */
public class TeacherMenu {

    static List<MenuOption> menuTeacher = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public TeacherMenu() {
        teacherMenu();
    }

    private static void teacherMenu() {
        menuTeacher.clear();
        boolean run = true;
        menuTeacher.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuTeacher.add(new MenuOption("1) Create New Teacher", () -> Utilities.teacherDAO.createTeacher(createTeacher())));
        menuTeacher.add(new MenuOption("2) Read Teacher", () -> readTeacher()));
        menuTeacher.add(new MenuOption("3) Update Teacher", () -> Utilities.teacherDAO.updateTeacher(updateTeacher())));
        menuTeacher.add(new MenuOption("4) Delete Teacher", () -> Utilities.teacherDAO.deleteTeacher(Utilities.readId())));
        menuTeacher.add(new MenuOption("5) Add Teacher To Course", () -> addTeacherToCourse()));
        menuTeacher.add(new MenuOption("6) List all Teachers", () -> Utilities.printList(Utilities.teacherDAO.readAllTeachers())));
        while (run) {
            System.out.println("--TEACHER MENU--");
            for (MenuOption menuOption : menuTeacher) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = Utilities.readNumber();
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
        System.out.print("ID of teacher to Update(0 to cancel): "); //TODOD        
        int id = Utilities.readNumber();
        if (id == 0) {
            return null;
        }
        Teacher teacher = Utilities.teacherDAO.readTeacher(id);
        if (teacher == null) {
            return null;
        } else {
            System.out.println("Current name: '" + teacher.getName() + "'. Leave 'New Name' empty to skip");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + teacher.getBirthdate().toString() + "'. Leave 'New Birthday' empty to skip");
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
        Utilities.printList(Utilities.teacherDAO.readAllTeachers());

        System.out.print("Select teacher ID: ");
        int teacherID = Utilities.readNumber();
        Teacher teacher = Utilities.teacherDAO.readTeacher(teacherID);

        if (teacher != null) {
            System.out.println("Available Courses: ");
            Utilities.printList(Utilities.courseDAO.readAllCourses());
            System.out.print("Select Course ID: ");
            int courseID = Utilities.readNumber();
            Course course = Utilities.courseDAO.readCourse(courseID);
            List<Course> teacherCourses = Utilities.teacherDAO.readAllCourses(teacher);
            if (!teacherCourses.contains(course)) {
                if (course != null) {
                    course.addTeacher(teacher);
                    Utilities.courseDAO.updateCourse(course);
                    System.out.println(teacher.getName() + " added to course " + course.getName());
                }
            }else{
                System.out.println("Teacher " + teacher.getName() + " is already teaching course " + course.getName());
            }
        }
    }

    private static void readTeacher() {
        Teacher teacher = Utilities.teacherDAO.readTeacher(Utilities.readId());
        if (teacher != null) {
            System.out.println(teacher);
        }
    }
}
