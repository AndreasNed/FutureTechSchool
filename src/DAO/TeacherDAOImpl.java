package DAO;

import futuretechschool.domain.Teacher;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TeacherDAOImpl implements TeacherDAO {
    
    
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
    public void updateTeacher(Teacher teacher) {
        em.getTransaction().begin();
        em.merge(teacher);
        em.getTransaction().commit();
    }

    @Override
    public void deleteTeacher(int id) {
        em.getTransaction().begin();
        Teacher teacher = em.find(Teacher.class, id);
        em.remove(teacher);
        em.getTransaction().commit();
    }

}
