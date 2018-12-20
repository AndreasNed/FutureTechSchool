package futuretechschool;


import Utilities.Util;
import Menu.MainMenu;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        //debug();
        MainMenu.menu();
    }

    public static void debugAddEducation() {

        Education education1 = new Education();
        education1.setName("Java Utvecklare");
        Util.educationDAO.createEducation(education1);

        Education education2 = new Education();
        education2.setName("Java Testare");
        Util.educationDAO.createEducation(education2);
    }

    public static void debugAddCourse() {

        Course course1 = new Course();
        course1.setName("Java Programmering");

        Course course2 = new Course();
        course2.setName("Java Databaser");

        course1.setPoints(100);
        course2.setPoints(200);

        Util.courseDAO.createCourse(course1);
        Util.courseDAO.createCourse(course2);

    }

    public static void debugAddStudent() {
        Student student1 = new Student();
        student1.setName("Andreas Nedbal");
        student1.setBirthdate(LocalDate.now());

        Student student2 = new Student();
        student2.setName("Pontus P Paulsson");
        student2.setBirthdate(LocalDate.now());

        Util.studentDAO.createStudent(student1);
        Util.studentDAO.createStudent(student2);

    }

    public static void debugAddTeacher() {

        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

        teacher1.setName("Ulf Bilting");
        teacher2.setName("Bita Jibbari");

        Util.teacherDAO.createTeacher(teacher1);
        Util.teacherDAO.createTeacher(teacher2);
    }

    public static void debugAddCourseToEducation() {
        Course course = Util.courseDAO.readCourse(3);
        Course course2 = Util.courseDAO.readCourse(4);
        System.out.println(course.getId());
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(course);
        courseList.add(course2);
        Education education = Util.educationDAO.readEducation(1);
        System.out.println(education.getId());
        education.setCourses(courseList);
        System.out.println(education.getCourses());
        education.setName("Pontus Paulsson");
        Util.educationDAO.updateEducation(education);

    }

    public static void debugAddStudentToEducation() {
        Student s = Util.studentDAO.readStudent(5);
        s.setEducation(Util.educationDAO.readEducation(1));
        Util.studentDAO.updateStudent(s);

    }

    public static void debugAddTeacherToCourse() {
        Teacher t1 = Util.teacherDAO.readTeacher(7);
        Teacher t2 = Util.teacherDAO.readTeacher(8);

        Course c1 = Util.courseDAO.readCourse(3);
        Course c2 = Util.courseDAO.readCourse(4);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(t1);
        c1.setTeachers(teacherList);
        
        Util.courseDAO.updateCourse(c1);

    }

    public static void debugAddStudentToCourse() {
        Student s1 = Util.studentDAO.readStudent(5);
        Student s2 = Util.studentDAO.readStudent(6);
        Course course = Util.courseDAO.readCourse(3);
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        course.setStudents(studentList);
        Util.courseDAO.updateCourse(course);
    }

    public static void debugRemoveCourse() {
        Util.courseDAO.deleteCourse(3);
    }

    public static void debugRemoveStudent() {
        Util.studentDAO.deleteStudent(5);
    }

    public static void debugRemoveTeacher() {
        Util.teacherDAO.deleteTeacher(7);
    }

    public static void debugRemoveEducation() {
        Util.educationDAO.deleteEducation(1);
    }

    public static void debugUpdateCourse() {
        Course course = new Course();
        course.setName("Java Programmering 2.0");
        course.setPoints(100);
    }

    public static void debugUpdateEducation() {
        Education edu = new Education();
        edu.setName("ITHS Äger2");
        edu.setId(2);
        System.out.println(edu.getCourses());
    }

    public static void debugRemoveStudentCourse() {
        Course course = Util.courseDAO.readCourse(3);
        Student student = Util.studentDAO.readStudent(5);
        System.out.println(course.getStudents());
        System.out.println(student);
        course.removeStudent(student);
        System.out.println(course.getStudents());
        Util.courseDAO.updateCourse(course);
    }

    public static void debugRemoveTeacherCourse() {
        Course course = Util.courseDAO.readCourse(3);
        Teacher teacher = Util.teacherDAO.readTeacher(7);
        course.removeTeacher(teacher);
        Util.courseDAO.updateCourse(course);

    }

    private static void debug() {
        Student student1 = new Student();
        student1.setName("Andreas Nedbal");
        student1.setBirthdate(LocalDate.now());

        Student student2 = new Student();
        student2.setName("Pontus Paulsson");
        student2.setBirthdate(LocalDate.now());

        Student student3 = new Student();
        student3.setName("Patrik Freij");
        student3.setBirthdate(LocalDate.now());

        Teacher teacher1 = new Teacher();
        teacher1.setName("Morgan Malm");
        teacher1.setBirthdate(LocalDate.now());

        Teacher teacher2 = new Teacher();
        teacher2.setName("Valentina Mikhailov");
        teacher2.setBirthdate(LocalDate.now());

        Teacher teacher3 = new Teacher();
        teacher3.setName("Anastasiya Smirnov");
        teacher3.setBirthdate(LocalDate.now());

        Education education1 = new Education();
        education1.setName("Java Education");

        Education education2 = new Education();
        education2.setName("Anatomy Education");

        Education education3 = new Education();
        education3.setName("Spelling Education");

        Course course1 = new Course();
        course1.setName("Java Course");
        course1.setPoints(10);

        Course course2 = new Course();
        course2.setName("Anatomy Course");
        course2.setPoints(100);

        Course course3 = new Course();
        course3.setName("Spelling 1-0-1 Course");
        course3.setPoints(200);

        Util.educationDAO.createEducation(education1);
        Util.educationDAO.createEducation(education2);
        Util.educationDAO.createEducation(education3);

        Util.courseDAO.createCourse(course1);
        Util.courseDAO.createCourse(course2);
        Util.courseDAO.createCourse(course3);

        Util.teacherDAO.createTeacher(teacher1);
        Util.teacherDAO.createTeacher(teacher2);
        Util.teacherDAO.createTeacher(teacher3);

        Util.studentDAO.createStudent(student1);
        Util.studentDAO.createStudent(student2);
        Util.studentDAO.createStudent(student3);

        education1.addCourse(course1);
        Util.educationDAO.updateEducation(education1);

        education2.addCourse(course2);
        Util.educationDAO.updateEducation(education2);

        education3.addCourse(course3);
        Util.educationDAO.updateEducation(education3);

        course1.addTeacher(teacher1);
        Util.courseDAO.updateCourse(course1);

        course2.addTeacher(teacher2);
        Util.courseDAO.updateCourse(course1);

        course3.addTeacher(teacher3);
        Util.courseDAO.updateCourse(course3);

        student1.setEducation(education1);
        Util.studentDAO.updateStudent(student1);

        student2.setEducation(education2);
        student2.addCourse(course3);
        Util.studentDAO.updateStudent(student2);

        student3.addCourse(course2);
        Util.studentDAO.updateStudent(student3);

    }
}
