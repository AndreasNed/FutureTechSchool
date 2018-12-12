package futuretechschool;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import DAO.EducationDAO;
import DAO.EducationDAOImpl;
import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import DAO.TeacherDAO;
import DAO.TeacherDAOImpl;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    static EducationDAO educationDAO = new EducationDAOImpl();
    static CourseDAO courseDAO = new CourseDAOImpl();
    static StudentDAO studentDAO = new StudentDAOImpl();
    static TeacherDAO teacherDAO = new TeacherDAOImpl();

    public static void main(String[] args) {

        debugAddEducation();
        debugAddCourse();
        debugAddStudent();
        debugAddTeacher();
        
        debugAddCourseToEducation();
        debugAddStudentToEducation();
        debugAddStudentToCourse();
        debugAddTeacherToCourse();
//        debugRemoveCourse();
//        debugRemoveStudent();
//        debugRemoveTeacher();
//        debugRemoveEducation();
//        debugUpdateCourse();
//        debugUpdateEducation();

    }

    public static void debugAddEducation() {

        Education education1 = new Education();
        education1.setName("Java Utvecklare");
        educationDAO.createEducation(education1);

        Education education2 = new Education();
        education2.setName("Java Testare");
        educationDAO.createEducation(education2);
    }

    public static void debugAddCourse() {

        Course course1 = new Course();
        course1.setName("Java Programmering");

        Course course2 = new Course();
        course2.setName("Java Databaser");

        course1.setPoints(100);
        course2.setPoints(200);

        courseDAO.createCourse(course1);
        courseDAO.createCourse(course2);

    }

    public static void debugAddStudent() {
        Student student1 = new Student();
        student1.setName("Andreas Nedbal");
        student1.setBirthdate(LocalDate.now());

        Student student2 = new Student();
        student2.setName("Pontus P Paulsson");
        student2.setBirthdate(LocalDate.now());

        studentDAO.createStudent(student1);
        studentDAO.createStudent(student2);

    }

    public static void debugAddTeacher() {

        Teacher teacher1 = new Teacher();
        Teacher teacher2 = new Teacher();

        teacher1.setName("Ulf Bilting");
        teacher2.setName("Bita Jibbari");

        teacherDAO.createTeacher(teacher1);
        teacherDAO.createTeacher(teacher2);
    }

    public static void debugAddCourseToEducation() {
        educationDAO.addCourseToEducation(1, 3);
        educationDAO.addCourseToEducation(2, 4);
    }
    
    public static void debugAddStudentToEducation(){
        educationDAO.addStudentToEducation(5, 1);
        educationDAO.addStudentToEducation(6, 2);
    }
    public static void debugAddTeacherToCourse(){
        courseDAO.addTeacherToCourse(7, 3);
        courseDAO.addTeacherToCourse(8, 4);
    }
    public static void debugAddStudentToCourse(){
        studentDAO.addToCourse(5, 3);
        studentDAO.addToCourse(6, 4);
    }
    
    public static void debugRemoveCourse(){
        courseDAO.deleteCourse(3);
    }
    public static void debugRemoveStudent(){
        studentDAO.deleteStudent(5);
    }
    public static void debugRemoveTeacher(){
        teacherDAO.deleteTeacher(7);
    }
    public static void debugRemoveEducation(){
        educationDAO.deleteEducation(1);
    }
    public static void debugUpdateCourse(){
        Course course = new Course();
        course.setName("Java Programmering 2.0");
        course.setPoints(100);
        //courseDAO.updateCourse(course, 3);
    }
    public static void debugUpdateEducation(){
        Education edu = new Education();
        edu.setName("ITHS Äger2");
        edu.setId(2);
        System.out.println(edu.getCourses());
        //educationDAO.updateEducation(edu, 1);
    }
}
