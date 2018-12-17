package Menu;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import DAO.EducationDAO;
import DAO.EducationDAOImpl;
import DAO.GradeDAO;
import DAO.GradeDAOImpl;
import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import DAO.TeacherDAO;
import DAO.TeacherDAOImpl;
import futuretechschool.GradeEnum;
import futuretechschool.domain.Course;
import futuretechschool.domain.Education;
import futuretechschool.domain.Grade;
import futuretechschool.domain.Student;
import futuretechschool.domain.Teacher;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    static EducationDAO educationDAO = new EducationDAOImpl();
    static CourseDAO courseDAO = new CourseDAOImpl();
    static StudentDAO studentDAO = new StudentDAOImpl();
    static TeacherDAO teacherDAO = new TeacherDAOImpl();
    static GradeDAO gradeDAO = new GradeDAOImpl();

    static List<MenuOption> menuMain = new ArrayList<>();
    static List<MenuOption> menuStudent = new ArrayList<>();
    static List<MenuOption> menuTeacher = new ArrayList<>();
    static List<MenuOption> menuCourse = new ArrayList<>();
    static List<MenuOption> menuEducation = new ArrayList<>();
    static List<MenuOption> menuGrade = new ArrayList<>();

    public static void menu() {
        menuMain.clear();

        menuMain.add(new MenuOption("0) Exit", () -> System.exit(1)));
        menuMain.add(new MenuOption("1) Student Menu", () -> studentMenu()));
        menuMain.add(new MenuOption("2) Teacher Menu", () -> teacherMenu()));
        menuMain.add(new MenuOption("3) Course Menu", () -> courseMenu()));
        menuMain.add(new MenuOption("4) Education Menu", () -> educationMenu()));
        menuMain.add(new MenuOption("5) Grade Menu", () -> gradeMenu()));

        while (true) {
            System.out.println("--MAIN MENU--");
            for (MenuOption menuOption : menuMain) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
            try {
                menuMain.get(input).getMenu().menuMethod();
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Invalid input - Type a number between 0-" + (menuMain.size() - 1));
            }
        }
    }

    private static void studentMenu() {
        menuStudent.clear();
        boolean run = true;

        menuStudent.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuStudent.add(new MenuOption("1) Create New Student", () -> studentDAO.createStudent(createStudent())));
        menuStudent.add(new MenuOption("2) Read Student", () -> System.out.println(studentDAO.readStudent(readId()))));
        menuStudent.add(new MenuOption("3) Update Student", () -> studentDAO.updateStudent(updateStudent())));
        menuStudent.add(new MenuOption("4) Delete Student", () -> studentDAO.deleteStudent(readId())));
        menuStudent.add(new MenuOption("5) Add Student to Education", () -> addStudentToEducation()));
        menuStudent.add(new MenuOption("6) Add Student to Course", () -> addStudentToCourse()));
        menuStudent.add(new MenuOption("7) List all Students", () -> printList(studentDAO.readAllStudents())));
        menuStudent.add(new MenuOption("8) List all Courses by StudentID", () -> printList(studentDAO.readAllCourses(studentDAO.readStudent(readId())))));

        while (run) {
            System.out.println("--STUDENT MENU--");
            for (MenuOption menuOption : menuStudent) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
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

    private static void teacherMenu() {
        menuTeacher.clear();
        boolean run = true;

        menuTeacher.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuTeacher.add(new MenuOption("1) Create New Teacher", () -> teacherDAO.createTeacher(createTeacher())));
        menuTeacher.add(new MenuOption("2) Read Teacher", () -> System.out.println(teacherDAO.readTeacher(readId()))));
        menuTeacher.add(new MenuOption("3) Update Teacher", () -> teacherDAO.updateTeacher(updateTeacher())));
        menuTeacher.add(new MenuOption("4) Delete Teacher", () -> teacherDAO.deleteTeacher(readId())));
        menuTeacher.add(new MenuOption("5) Add Teacher To Course", () -> addTeacherToCourse()));
        menuTeacher.add(new MenuOption("6) List all Teachers", () -> printList(teacherDAO.readAllTeachers())));
        while (run) {
            System.out.println("--TEACHER MENU--");
            for (MenuOption menuOption : menuTeacher) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
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

    private static void courseMenu() {
        menuCourse.clear();
        boolean run = true;

        menuCourse.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuCourse.add(new MenuOption("1) Create New Course", () -> courseDAO.createCourse(createCourse())));
        menuCourse.add(new MenuOption("2) Read Course", () -> System.out.println(courseDAO.readCourse(readId()))));
        menuCourse.add(new MenuOption("3) Update Course", () -> courseDAO.updateCourse(updateCourse())));
        menuCourse.add(new MenuOption("4) Delete Course", () -> courseDAO.deleteCourse(readId())));
        menuCourse.add(new MenuOption("5) List all Courses", () -> printList(courseDAO.readAllCourses())));
        while (run) {
            System.out.println("--COURSE MENU--");
            for (MenuOption menuOption : menuCourse) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuCourse.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuCourse.size() - 1));
                }
            }
        }
    }

    private static void educationMenu() {
        menuEducation.clear();
        boolean run = true;

        menuEducation.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuEducation.add(new MenuOption("1) Create Education", () -> educationDAO.createEducation(createEducation())));
        menuEducation.add(new MenuOption("2) Read Education", () -> System.out.println(educationDAO.readEducation(readId()))));
        menuEducation.add(new MenuOption("3) Update Education", () -> educationDAO.updateEducation(updateEducation())));
        menuEducation.add(new MenuOption("4) Delete Education", () -> educationDAO.deleteEducation(readId())));
        menuEducation.add(new MenuOption("5) Add Course To Education", () -> addCourseToEducation()));
        menuEducation.add(new MenuOption("6) List all Educations", () -> printList(educationDAO.readAllEducations())));
        while (run) {
            System.out.println("--EDUCATION MENU--");
            for (MenuOption menuOption : menuEducation) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
            if (input == 0) {
                run = false;
            } else {
                try {
                    menuEducation.get(input).getMenu().menuMethod();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("Invalid input - Type a number between 0-" + (menuEducation.size() - 1));
                }

            }
        }
    }

    private static void gradeMenu() {
        menuGrade.clear();
        boolean run = true;

        menuGrade.add(new MenuOption("0) Back", () -> System.out.println("back")));
        menuGrade.add(new MenuOption("1) Create Grade", () -> gradeDAO.createGrade(createGrade())));
        menuGrade.add(new MenuOption("2) Read Grades by Student", () -> readGrades()));
        menuGrade.add(new MenuOption("3) Update Grade", () -> gradeDAO.updateGrade(updateGrade())));
        menuGrade.add(new MenuOption("4) Delete Grade", () -> gradeDAO.deleteGrade(readId())));
        menuGrade.add(new MenuOption("5) List all Grades", () -> printList(gradeDAO.readAllGrade())));
        while (run) {
            System.out.println("--GRADE MENU--");
            for (MenuOption menuOption : menuGrade) {
                System.out.println(menuOption.getString());
            }
            System.out.print("Input: ");
            int input = readNumber();
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
        printList(studentDAO.readAllStudents());
        printList(gradeDAO.readGradesByStudent(studentDAO.readStudent(readId())));
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
        int id = readNumber();
        if (id == 0) {
            return null;
        }
        Student student = studentDAO.readStudent(id);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return null;
        } else {
            System.out.println("Current name: '" + student.getName() + "' Leave 'New Name' empty to skip");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + student.getBirthdate().toString() + "' Leave 'New Birthday' empty to skip");
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

    private static int readId() {
        System.out.print("Enter ID: ");
        int i = readNumber();
        return i;
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
        int id = readNumber();
        if (id == 0) {
            return null;
        }
        Teacher teacher = teacherDAO.readTeacher(id);
        if (teacher == null) {
            System.out.println("Invalid teacher ID");
            return null;
        } else {
            System.out.println("Current name: '" + teacher.getName() + "', leave 'New Name' empty to skip");
            System.out.print("New Name: ");
            String newName = sc.nextLine();
            System.out.println("Current Birthday: '" + teacher.getBirthdate().toString() + "', leave 'New Birthday' empty to skip");
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

    private static Education createEducation() {
        Education education = new Education();
        System.out.print("Name: ");
        String name = sc.nextLine();
        education.setName(name);
        return education;
    }

    private static Education updateEducation() {

        System.out.print("ID of education to Update(0 to cancel): "); //TODOD        
        int id = readNumber();
        if (id == 0) {
            return null;
        }
        Education education = educationDAO.readEducation(id);
        System.out.println("Current name: " + education.getName() + "Leave 'New Name' empty to skip");
        System.out.print("New Name: ");
        String newName = sc.nextLine();
        if (!newName.equals("")) {
            education.setName(newName);
        }
        return education;

    }

    public static int readNumber() {
        String strNum;
        int num;
        try {
            strNum = sc.nextLine();
            num = Integer.parseInt(strNum);
        } catch (Exception e) {
            num = -1;
        }
        return num;
    }

    private static Course createCourse() {
        Course course = new Course();
        System.out.print("Name: ");
        String name = sc.nextLine();
        course.setName(name);
        return course;
    }

    private static Course updateCourse() {
        System.out.print("ID of course to Update(0 to cancel): "); //TODOD        
        int id = readNumber();
        if (id == 0) {
            return null;
        }
        Course course = courseDAO.readCourse(id);
        System.out.println("Current name: '" + course.getName() + "'. Leave 'New Name' empty to skip");
        System.out.print("New Name: ");
        String newName = sc.nextLine();
        if (!newName.equals("")) {
            course.setName(newName);
        }
        return course;
    }

    private static void addStudentToEducation() {
        List<Student> students = new ArrayList<>();
        List<Education> educations = new ArrayList<>();
        students = studentDAO.readAllStudents();
        educations = educationDAO.readAllEducations();
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.print("Select Student ID: ");
        int studentID = readNumber();
        Student student = studentDAO.readStudent(studentID);
        if (student != null) {

            System.out.println("Available Educations: ");
            for (Education education : educations) {
                System.out.println(education);
            }

            System.out.print("Add Student " + student.getName() + " to Education: ");
            int educationID = readNumber();
            Education education = educationDAO.readEducation(educationID);
            if (education != null) {

                student.setEducation(education);
                studentDAO.updateStudent(student);

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
        students = studentDAO.readAllStudents();
        courses = courseDAO.readAllCourses();
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.print("Select Student ID: ");
        int studentID = readNumber();
        Student student = studentDAO.readStudent(studentID);
        if (student != null) {
            System.out.println("Available Courses: ");
            for (Course course : courses) {
                System.out.println(course);
            }

            System.out.print("Add Student " + student.getName() + " to Course: ");
            int courseID = readNumber();
            Course course = courseDAO.readCourse(courseID);
            if (course != null) {
                course.addStudent(student);
                courseDAO.updateCourse(course);
                System.out.println(student.getName() + " added to course " + course.getName());

            } else {
                System.out.println("Invalid Course ID");
            }

        } else {
            System.out.println("Invalid Student ID");
        }

    }

    private static void addTeacherToCourse() {
        List<Teacher> teachers = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        teachers = teacherDAO.readAllTeachers();
        courses = courseDAO.readAllCourses();
        System.out.println("List of teachers: ");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.toString());
        }

        System.out.print("Select teacher ID: ");
        int teacherID = readNumber();
        Teacher teacher = teacherDAO.readTeacher(teacherID);

        System.out.println("Available Courses: ");
        for (Course course : courses) {
            System.out.println(course);
        }

        System.out.print("Add Teacher '" + teacher.getName() + "' to course: ");
        int courseID = readNumber();
        Course course = courseDAO.readCourse(courseID);

        course.addTeacher(teacher);
        courseDAO.updateCourse(course);

        System.out.println(teacher.getName() + " added to course " + course.getName());
    }

    private static void addCourseToEducation() {

        printList(courseDAO.readAllCourses());

        System.out.print("Course ID: ");
        int courseID = readNumber();

        System.out.println(educationDAO.readAllEducations());
        System.out.print("Add course #" + courseID + " to Education ID: ");
        int educationID = readNumber();
        Education education = educationDAO.readEducation(educationID);

        education.addCourse(courseDAO.readCourse(courseID));
        educationDAO.updateEducation(education);
    }

    private static <T> void printList(List<T> list) {
        System.out.println("_________________________________");
        list.stream().forEach(System.out::println);
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    private static Grade createGrade() {
        printList(studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = readNumber();
        printList(studentDAO.readAllCourses(studentDAO.readStudent(studentId)));
        System.out.print("Course ID: ");
        int courseId = readNumber();
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
        printList(studentDAO.readAllStudents());
        System.out.print("Student ID: ");
        int studentId = readNumber();
        printList(gradeDAO.readGradesByStudent(studentDAO.readStudent(studentId)));
        System.out.print("ID of Grade to Update: ");
        int gradeId = readNumber();
        System.out.println("New Grade (IG / G / VG): ");
        String gradeString = sc.next();
        Grade grade = gradeDAO.readGrade(gradeId);
        grade.setGrade(GradeEnum.valueOf(gradeString));

        return grade;

    }

}
