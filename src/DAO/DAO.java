package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO implements DAOInterface {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    //TEACHER
    @Override
    public void createTeacher(Teacher teacher) {
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
    }

    @Override
    public Teacher readTeacher(int id) {
        Teacher teacher = em.find(Teacher.class, id);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher, int id) {
        em.getTransaction().begin();
        Teacher t1 = em.find(Teacher.class, id);
        t1 = teacher;
        em.persist(t1);
        em.getTransaction().commit();
    }

    @Override
    public void deleteTeacher(int id) {
        em.getTransaction().begin();
        Teacher teacher = em.find(Teacher.class, id);
        em.remove(teacher);
        em.getTransaction().commit();
    }

    //COURSE
    @Override
    public void createCourse(Course course) {
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public Course readCourse(int id) {
        Course c = em.find(Course.class, id);
        return c;
    }

    @Override
    public void updateCourse(Course course, int id) {
        em.getTransaction().begin();
        Course c = em.find(Course.class, id);
        c = course;
        em.persist(c);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCourse(int id) {
        em.getTransaction().begin();
        Course course = em.find(Course.class, id);
        em.remove(course);
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override //TESTAD FUNKAR
    public void addTeacherToCourse(int teacherId, int courseId) {
        Course c = em.find(Course.class, courseId);
        Teacher t = em.find(Teacher.class, teacherId);
        List<Course> courses = new ArrayList<>();
        courses.add(c);
        t.setCourses(courses);
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    @Override //TESTAD FUNKAR
    public void removeTeacherFromCourse(int teacherId, int courseId) {
        Course c = em.find(Course.class, courseId);
        Teacher t = em.find(Teacher.class, teacherId);
        List<Course> courses = new ArrayList<>();
        courses = t.getCourses();
        courses.remove(c);
        t.setCourses(courses);
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();

    }

}
