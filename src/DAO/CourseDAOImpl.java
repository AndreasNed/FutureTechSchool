package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CourseDAOImpl implements CourseDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

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
    public void updateCourse(Course course) {
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCourse(int id) {
        em.getTransaction().begin();
        em.remove(em.find(Course.class, id));
        em.getTransaction().commit();
    }

    @Override //TESTAD FUNKAR
    public void addTeacherToCourse(int teacherId, int courseId) {
        em.getTransaction().begin();
        Course c = em.find(Course.class, courseId);
        Teacher t = em.find(Teacher.class, teacherId);
        t.addCourse(c);
        em.getTransaction().commit();
    }

    @Override //TESTAD FUNKAR
    public void removeTeacherFromCourse(int teacherId, int courseId) {
        em.getTransaction().begin();
        Course c = em.find(Course.class, courseId);
        Teacher t = em.find(Teacher.class, teacherId);
        t.removeCourse(c);
        em.getTransaction().commit();

    }

}
