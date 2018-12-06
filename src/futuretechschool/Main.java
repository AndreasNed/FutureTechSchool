package futuretechschool;

import DAO.DAO;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        DAO dao = new DAO();
        Teacher teacher = new Teacher();
        teacher.setName("Kalle Kula");
        teacher.setBirthdate(LocalDate.now());

        Course course = new Course();
        course.setName("Java one-o-one");
        course.setPoints(100);
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);

        dao.createTeacher(teacher);
        dao.createCourse(course);
        System.out.println(teacher.getId());
        System.out.println(course.getId());

        dao.addTeacherToCourse(1, 2);
        System.out.println(teacher.getCourses());
        dao.removeTeacherFromCourse(1, 2);
        System.out.println(teacher.getCourses());

    }

}
