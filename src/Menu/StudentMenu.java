package Menu;

import static Menu.MainMenu.menuStudent;
import Utilities.Utilities;
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

    public StudentMenu() {
        studentMenu();
    }

    private static void studentMenu() {
        menuStudent.clear();
        boolean run = true;
        menuStudent.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuStudent.add(new MenuOption("1) Create New Student", () -> Utilities.studentDAO.createStudent(createStudent())));
        menuStudent.add(new MenuOption("2) Read Student", () -> readStudent()));
        menuStudent.add(new MenuOption("3) Update Student", () -> Utilities.studentDAO.updateStudent(updateStudent())));
        menuStudent.add(new MenuOption("4) Delete Student", () -> Utilities.studentDAO.deleteStudent(Utilities.readId())));
        menuStudent.add(new MenuOption("5) Add Student to Education", () -> addStudentToEducation()));
        menuStudent.add(new MenuOption("6) Add Student to Course", () -> addStudentToCourse()));
        menuStudent.add(new MenuOption("7) List all Students", () -> Utilities.printList(Utilities.studentDAO.readAllStudents())));
        menuStudent.add(new MenuOption("8) List all Courses by StudentID", () -> Utilities.printList(Utilities.studentDAO.readAllCourses(Utilities.studentDAO.readStudent(Utilities.readId())))));

        while (run) {
            System.out.println("--STUDENT MENU--");
            for (MenuOption menuOption : menuStudent) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = Utilities.readNumber();
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
        int id = Utilities.readNumber();
        if (id == 0) {
            return null;
        }
        Student student = Utilities.studentDAO.readStudent(id);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return null;
        } else {
            System.out.println("Current name: '" + student.getName() + "'. Leave 'New Name' empty to skip");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + student.getBirthdate().toString() + "'. Leave 'New Birthday' empty to skip");
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
        List<Student> students = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        students = Utilities.studentDAO.readAllStudents();
        educations = Utilities.educationDAO.readAllEducations();
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.print("Select Student ID: ");
        int studentID = Utilities.readNumber();
        Student student = Utilities.studentDAO.readStudent(studentID);
        if (student != null) {
            System.out.println("Available Educations: ");
            for (Education education : educations) {
                System.out.println(education);
            }
            int educationID = Utilities.readNumber();
            Education education = Utilities.educationDAO.readEducation(educationID);
            if (education != null) {
                student.setEducation(education);
                Utilities.studentDAO.updateStudent(student);
                System.out.println(student.getName() + " added to education " + education.getName());
            } else {
                System.out.println("Invalid Education ID");
            }
        } else {
            System.out.println("Invalid Student ID");
        }
    }

    private static void addStudentToCourse() {
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        students = Utilities.studentDAO.readAllStudents();
        courses = Utilities.courseDAO.readAllCourses();
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.print("Select Student ID: ");
        int studentID = Utilities.readNumber();
        Student student = Utilities.studentDAO.readStudent(studentID);
        if (student != null) {
            System.out.println("Available Courses: ");
            for (Course course : courses) {
                System.out.println(course);
            }
            int courseID = Utilities.readNumber();
            Course course = Utilities.courseDAO.readCourse(courseID);
            List<Course> courseList = Utilities.studentDAO.readAllCourses(student);
            if (!courseList.contains(course)) {
                if (course != null) {
                    course.addStudent(student);
                    Utilities.courseDAO.updateCourse(course);
                    System.out.println(student.getName() + " added to course " + course.getName());
                } else {
                    System.out.println("Invalid Course ID");
                }
            }else{
                System.out.println("Student " + student.getName() + " is already in course " + course.getName());
            }
        } else {
            System.out.println("Invalid Student ID");
        }
    }

    private static void readStudent() {
        Student student = Utilities.studentDAO.readStudent(Utilities.readId());
        if (student != null) {
            System.out.println(student);
        }
    }
}
