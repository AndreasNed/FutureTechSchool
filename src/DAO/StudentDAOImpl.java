package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Education;
import futuretechschool.domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class StudentDAOImpl implements StudentDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    
    @Override
    public void createStudent(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public Student readStudent(int id) {
            Student s = em.find(Student.class, id);
            return s;
    }

    @Override
    public void updateStudent(Student student) {//INTE TESTAT
        try {
            em.getTransaction().begin();
            em.merge(student);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteStudent(int id) {
        em.getTransaction().begin();
        Student s = em.find(Student.class, id);
        em.remove(s);
        em.getTransaction().commit();
    }
    
    @Override
    public List<Student> readAllStudents(){
        return em.createNamedQuery("Select s from Student s").getResultList();
    }
}