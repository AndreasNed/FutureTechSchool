package futuretechschool;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import DAO.EducationDAO;
import DAO.EducationDAOImpl;
import DAO.StudentDAO;
import DAO.StudentDAOImpl;
import DAO.TeacherDAO;
import DAO.TeacherDAOImpl;
import Menu.Menu;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PontusMain {

    static EducationDAO educationDAO = new EducationDAOImpl();
    static CourseDAO courseDAO = new CourseDAOImpl();
    static StudentDAO studentDAO = new StudentDAOImpl();
    static TeacherDAO teacherDAO = new TeacherDAOImpl();

    public static void main(String[] args) {
        
        Menu.menu();

//        debugAddEducation(); // funkar
//        debugAddCourse(); // funkar
//        debugAddStudent(); // funkar
//        debugAddTeacher(); // funkar
//
//        debugAddCourseToEducation(); // funkar
//        debugAddStudentToEducation(); // funkar
//        debugAddStudentToCourse(); // funkar
//        debugAddTeacherToCourse(); // funkar
//        debugRemoveCourse(); // funkar
//        debugRemoveStudent(); // funkar
//        debugRemoveTeacher(); // funkar
//        debugRemoveEducation(); // funkar inte. Går inte ta bort education då den är FK i Student-tabellen.
//        debugUpdateCourse();
//        debugUpdateEducation();
//        debugRemoveStudentCourse();
//        debugRemoveTeacherCourse(); //funkar
          

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
//        educationDAO.addCourseToEducation(1, 3);
//        educationDAO.addCourseToEducation(2, 4);
        Course course = courseDAO.readCourse(3);
        Course course2 = courseDAO.readCourse(4);
        System.out.println(course.getId());
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(course);
        courseList.add(course2);
        Education education = educationDAO.readEducation(1);
        System.out.println(education.getId());
        education.setCourses(courseList);
        System.out.println(education.getCourses());
        education.setName("Pontus PaulzZON");
        educationDAO.updateEducation(education);

    }

    public static void debugAddStudentToEducation() {
//        educationDAO.addStudentToEducation(5, 1);
//        educationDAO.addStudentToEducation(6, 2);
//        Education education = educationDAO.readEducation(1);
//        Education education1 = educationDAO.readEducation(2);
//        education.addStudent(studentDAO.readStudent(5));
//        education.addStudent(studentDAO.readStudent(6));
//        educationDAO.updateEducation(education);
//        educationDAO.updateEducation(education1);
          Student s = studentDAO.readStudent(5);
          s.setEducation(educationDAO.readEducation(1));
          studentDAO.updateStudent(s);
        

    }
    public static void debugAddTeacherToCourse(){
        Teacher t1 = teacherDAO.readTeacher(7);
        Teacher t2 = teacherDAO.readTeacher(8);
        
        Course c1 = courseDAO.readCourse(3);
        Course c2 = courseDAO.readCourse(4);
        
        //c1.addTeacher(t1);
        //c2.addTeacher(t2);
        
        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(t1);
        c1.setTeachers(teacherList);
        
        courseDAO.updateCourse(c1);
//        courseDAO.addTeacherToCourse(7, 3);
//        courseDAO.addTeacherToCourse(8, 4);
    }

    public static void debugAddStudentToCourse() {
        Student s1 = studentDAO.readStudent(5);
        Student s2 = studentDAO.readStudent(6);
        Course course = courseDAO.readCourse(3);
        //Course course2 = courseDAO.readCourse(4);
        
//        course.addStudent(s1);
//        course.addStudent(s2);
        List<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        course.setStudents(studentList);

        courseDAO.updateCourse(course);
        //courseDAO.updateCourse(course2);
    }

    public static void debugRemoveCourse() {
        courseDAO.deleteCourse(3);
    }

    public static void debugRemoveStudent() {
        studentDAO.deleteStudent(5);
    }

    public static void debugRemoveTeacher() {
        teacherDAO.deleteTeacher(7);
    }

    public static void debugRemoveEducation() {
        educationDAO.deleteEducation(1);
    }

    public static void debugUpdateCourse() {
        Course course = new Course();
        course.setName("Java Programmering 2.0");
        course.setPoints(100);
        //courseDAO.updateCourse(course, 3);
    }

    public static void debugUpdateEducation() {
        Education edu = new Education();
        edu.setName("ITHS Äger2");
        edu.setId(2);
        System.out.println(edu.getCourses());
        //educationDAO.updateEducation(edu, 1);
    }
    public static void debugRemoveStudentCourse(){
        Course course = courseDAO.readCourse(3);
        Student student = studentDAO.readStudent(5);
        
        System.out.println(course.getStudents());
        System.out.println(student);
        course.removeStudent(student);
        System.out.println(course.getStudents());
        courseDAO.updateCourse(course);
        
        
    }
    public static void debugRemoveTeacherCourse(){
        Course course = courseDAO.readCourse(3);
        Teacher teacher = teacherDAO.readTeacher(7);
        
        course.removeTeacher(teacher);
        courseDAO.updateCourse(course);
        
        
    }
}
