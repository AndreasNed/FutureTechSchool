package futuretechschool;

import DAO.DAO;
import futuretechschool.domain.Education;
import futuretechschool.domain.Teacher;
import futuretechschool.domain.Student;
import futuretechschool.domain.Course;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    static DAO dao = new DAO();

    public static void main(String[] args) {

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
        dao.deleteCourse(2);
        System.out.println(teacher.getCourses());

    }

}
