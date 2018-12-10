package futuretechschool;

import DAO.CourseDAOImpl;
import DAO.TeacherDAOImpl;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        CourseDAOImpl courseDAO = new CourseDAOImpl();
        TeacherDAOImpl teacherDAO = new TeacherDAOImpl();

        Teacher teacher = new Teacher();
        teacher.setName("Kalle Kula");
        teacher.setBirthdate(LocalDate.now());

        Course course = new Course();
        course.setName("Java one-o-one");
        course.setPoints(100);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);

        teacherDAO.createTeacher(teacher);
        courseDAO.createCourse(course);
//
//        System.out.println(teacherDAO.readTeacher(1));
//        System.out.println(courseDAO.readCourse(2));
//        System.out.println(teacher.getId());
//        System.out.println(course.getId());
//        
//        System.out.println(teacher.getCourses());
//
        courseDAO.addTeacherToCourse(1, 2);
        
        teacher = teacherDAO.readTeacher(1);
        
        System.out.println(teacher.getCourses());
//        courseDAO.deleteCourse(2);
//        System.out.println(teacher.getCourses());

    }

}
