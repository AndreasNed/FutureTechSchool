package Menu;

import Utilities.Util;
import futuretechschool.domain.Course;
import futuretechschool.domain.Education;
import futuretechschool.domain.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMenu {

    static Scanner sc = new Scanner(System.in);
    static List<MenuOption> menuStudent = new ArrayList<>();

    public StudentMenu() {
        studentMenu();
    }

    private static void studentMenu() {
        menuStudent.clear();
        boolean run = true;
        menuStudent.add(new MenuOption("0) Back", () -> System.out.println("")));
        menuStudent.add(new MenuOption("1) Create New Student", () -> Util.studentDAO.createStudent(createStudent())));
        menuStudent.add(new MenuOption("2) Read Student", () -> readStudent()));
        menuStudent.add(new MenuOption("3) Update Student", () -> Util.studentDAO.updateStudent(updateStudent())));
        menuStudent.add(new MenuOption("4) Delete Student", () -> Util.studentDAO.deleteStudent(Util.readId())));
        menuStudent.add(new MenuOption("5) Add Student to Education", () -> addStudentToEducation()));
        menuStudent.add(new MenuOption("6) Add Student to Course", () -> addStudentToCourse()));
        menuStudent.add(new MenuOption("7) List all Students", () -> Util.printList(Util.studentDAO.readAllStudents())));
        menuStudent.add(new MenuOption("8) List all Courses by StudentID", () -> Util.printList(Util.studentDAO.readAllCourses(Util.studentDAO.readStudent(Util.readId())))));

        while (run) {
            System.out.println("--STUDENT MENU--");
            menuStudent.forEach((menuOption) -> {
                System.out.println(menuOption.getString());
            });
            System.out.print("Input: ");
            int input = Util.readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuStudent.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuStudent.size() - 1));
                }
            }
        }
    }

    private static Student createStudent() {
        Student student = new Student();
        System.out.print("Name: ");
        String name = sc.nextLine();
        student.setName(name);
        System.out.print("Birthdate(yyyyMMdd): ");
        String bday = sc.nextLine();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate parsedDate = LocalDate.parse(bday, formatter);
            student.setBirthdate(parsedDate);
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid dateformat. Try again.");
        }
        return student;
    }

    private static Student updateStudent() {
        System.out.print("ID of student to Update(0 to cancel): ");
        int id = Util.readNumber();
        if (id == 0) {
            return null;
        }
        Student student = Util.studentDAO.readStudent(id);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return null;
        } else {
            System.out.println("Current name: '" + student.getName() + "'. Leave 'New Name' empty to skip.");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + student.getBirthdate().toString() + "'. Leave 'New Birthday' empty to skip.");
            System.out.println("New Birthday(yyyyMMdd): ");
            String newBday = sc.nextLine();
            if (!newName.equals("")) {
                student.setName(newName);
            }
            if (!newBday.equals("")) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                    LocalDate parsedDate = LocalDate.parse(newBday, formatter);
                    student.setBirthdate(parsedDate);
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid dateformat. Try again.");
                }
            }
        }
        return student;
    }

    private static void addStudentToEducation() {
        System.out.println("List of Students: ");
        Util.studentDAO.readAllStudents().forEach((student) -> {
            System.out.println(student);
        });
        System.out.print("Select Student ID: ");
        int studentID = Util.readNumber();
        Student student = Util.studentDAO.readStudent(studentID);
        if (student.getEducation() == null) {
            if (student != null) {
                System.out.println("Available Educations: ");
                Util.educationDAO.readAllEducations().forEach((education) -> {
                    System.out.println(education);
                });
                System.out.print("Select Education ID: ");
                int educationID = Util.readNumber();
                Education education = Util.educationDAO.readEducation(educationID);
                if (education != null) {
                    student.setEducation(education);
                    Util.studentDAO.updateStudent(student);
                    System.out.println(student.getName() + " added to Education " + education.getName());
                }
            }
        } else {
            System.out.println("Student " + student.getName() + " is already in an Education.");
        }
    }

    private static void addStudentToCourse() {

        System.out.println("List of Students: ");
        Util.studentDAO.readAllStudents().forEach((student) -> {
            System.out.println(student);
        });
        System.out.print("Select Student ID: ");
        int studentID = Util.readNumber();
        Student student = Util.studentDAO.readStudent(studentID);
        if (student != null) {
            System.out.println("Available Courses: ");
            Util.courseDAO.readAllCourses().forEach((course) -> {
                System.out.println(course);
            });
            int courseID = Util.readNumber();
            Course course = Util.courseDAO.readCourse(courseID);
            List<Course> courseList = Util.studentDAO.readAllCourses(student);
            if (!courseList.contains(course)) {
                if (course != null) {
                    course.addStudent(student);
                    Util.courseDAO.updateCourse(course);
                    System.out.println(student.getName() + " added to Course " + course.getName());
                } else {
                    System.out.println("Invalid Course ID");
                }
            } else {
                System.out.println("Student " + student.getName() + " is already in Course " + course.getName());
            }
        } else {
            System.out.println("Invalid Student ID");
        }
    }

    private static void readStudent() {
        Student student = Util.studentDAO.readStudent(Util.readId());
        if (student != null) {
            System.out.println(student);
        }
    }
}
