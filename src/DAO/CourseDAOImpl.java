package DAO;

import futuretechschool.domain.Course;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class CourseDAOImpl implements CourseDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    //COURSE
    @Override
    public void createCourse(Course course) {
        try {
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not insert course to database. Exception: " + ex);
        }

    }

    @Override
    public Course readCourse(int id) {
        Course c = em.find(Course.class, id);
        if (c != null) {
            return c;
        } else {
            System.out.println("No such course.");
            return null;
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            if (course != null) {
                em.getTransaction().begin();
                em.merge(course);
                em.getTransaction().commit();
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteCourse(int id) {
        try {
            Course course = em.find(Course.class, id);
            if (course != null) {
                em.getTransaction().begin();
                em.remove(course);
                em.getTransaction().commit();
            } else{
                System.out.println("No such course.");
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Course> readAllCourses() {
        return em.createQuery("Select c from Course c").getResultList();
    }
}
