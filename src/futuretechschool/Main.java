package futuretechschool;

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

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();

        Teacher t1 = new Teacher();
        t1.setBirthdate(LocalDate.now());
        t1.setName("Ulf Bror");

        Education e1 = new Education();
        e1.setName("Construction Education");

        Course c1 = new Course();
        c1.setName("Welding 1-0-1");
        c1.setPoints(100);

        Course c2 = new Course();
        c2.setName("Hammering 1-0-1");
        c2.setPoints(50);

        List<Course> courseList = new ArrayList<>();
        courseList.add(c1);
        courseList.add(c2);

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(t1);

        e1.setCourse(courseList);

        c1.setTeachers(teacherList);
        c2.setTeachers(teacherList);
        t1.setCourses(courseList);

        em.getTransaction().begin();
        em.persist(t1);
        em.persist(e1);
        em.persist(c1);
        em.persist(c2);
        em.getTransaction().commit();

    }

}
