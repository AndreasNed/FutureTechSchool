package DAO;

import futuretechschool.domain.Teacher;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

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
        }
        return null;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try {
            if (teacher == null) {
                em.getTransaction().rollback();
            } else {
                em.getTransaction().begin();
                em.merge(teacher);
                em.getTransaction().commit();
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }

    }

    @Override
    public void deleteTeacher(int id) {
        try {
            em.getTransaction().begin();
            Teacher teacher = em.find(Teacher.class, id);
            em.remove(teacher);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }

    }

    @Override
    public List<Teacher> readAllTeachers() {
        return em.createQuery("Select t from Teacher t").getResultList();
    }
}
