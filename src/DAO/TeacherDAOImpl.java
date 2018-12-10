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

}
