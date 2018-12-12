package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Student;
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
    public void updateStudent(Student newStudentInfo, int id) {//INTE TESTAT
        try {
            em.getTransaction().begin();
            em.merge(newStudentInfo);
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
    public void addToCourse(int studentId, int courseId) {
        try {
            em.getTransaction().begin();
            Course c = em.find(Course.class, courseId);
            Student s = em.find(Student.class, studentId);
            s.addCourse(c);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void removeFromCourse(int studentId, int courseId) {
        try {
            em.getTransaction().begin();
            Course c = em.find(Course.class, courseId);
            Student s = em.find(Student.class, studentId);
            s.removeCourse(c);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }
    
    
//    private void executeInsideTransaction(Consumer<EntityManager> action) {
//        final EntityTransaction tx = entityManager.getTransaction();
//        try {
//            tx.begin();
//            action.accept(entityManager);
//            tx.commit(); 
//        }
//        catch (RuntimeException e) {
//            tx.rollback();
//            throw e;
//        }
//    }
    
//    executeInsideTransaction(entityManager -> entityManager.remove(user));

}