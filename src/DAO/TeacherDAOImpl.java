package DAO;

import futuretechschool.domain.Education;
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
        }

    }

    @Override
    public Teacher readTeacher(int id) {
        Teacher teacher = em.find(Teacher.class, id);
        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        try {
            em.getTransaction().begin();
            em.merge(teacher);
            em.getTransaction().commit();
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
