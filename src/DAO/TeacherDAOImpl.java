package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class TeacherDAOImpl implements TeacherDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    //TEACHER
    @Override
    public void createTeacher(Teacher teacher) {
        try {
            em.getTransaction().begin();
            em.persist(teacher);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not insert teacher to database. Exception: " + ex);
        }

    }

    @Override
    public Teacher readTeacher(int id) {
        Teacher teacher = em.find(Teacher.class, id);
        if (teacher != null) {
            return teacher;
        } else {
            System.out.println("No such teacher.");
        }
        return null;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try {
            if (teacher != null) {
                em.getTransaction().begin();
                em.merge(teacher);
                em.getTransaction().commit();
            } else {
                System.out.println("No such teacher.");
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteTeacher(int id) {
        try {
            Teacher teacher = em.find(Teacher.class, id);
            if (teacher != null) {
                em.getTransaction().begin();
                em.remove(teacher);
                em.getTransaction().commit();
            } else {
                System.out.println("No such teacher.");
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Teacher> readAllTeachers() {
        return em.createQuery("Select t from Teacher t").getResultList();
    }

    @Override
    public List<Course> readAllCourses(Teacher teacher) {
        Query query = em.createQuery("SELECT t from Teacher t INNER JOIN t.courses c WHERE t = :teacher");
        query.setParameter("teacher", teacher);
        return query.getResultList();
    }
}
