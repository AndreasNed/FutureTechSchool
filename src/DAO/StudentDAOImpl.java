package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Student;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

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
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not insert student to database. Exception: " + ex);
        }
    }

    @Override
    public Student readStudent(int id) {
        Student s = em.find(Student.class, id);
        if (s != null) {
            return s;
        } else {
            System.out.println("No such student.");
            return null;
        }
    }

    @Override
    public void updateStudent(Student student) {
        try {
            if (student != null) {
                em.getTransaction().begin();
                em.merge(student);
                em.getTransaction().commit();
            }
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            Student s = em.find(Student.class, id);
            if (s != null) {
                em.getTransaction().begin();
                em.remove(s);
                em.getTransaction().commit();
            } else {
                System.out.println("No such student.");
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Student> readAllStudents() {
        return em.createQuery("Select s from Student s").getResultList();
    }

    @Override
    public List<Course> readAllCourses(Student student) {
        Query query = em.createQuery("SELECT c from Student s INNER JOIN s.courses c WHERE s = :student");
        query.setParameter("student", student);

        Query query1 = em.createQuery("SELECT e FROM Student s INNER JOIN s.education c INNER JOIN c.courses e WHERE s = :student");
        query1.setParameter("student", student);

        List<Course> courseList = new ArrayList<>();
        courseList.addAll(query.getResultList());
        courseList.addAll(query1.getResultList());
        return courseList;

    }

    @Override
    public int getTotalPoints(Student student) {
        Query query = em.createNativeQuery("select sum(points) from COURSE as c inner join GRADE as g on g.course_id = c.id where g.student_id = " + student.getId());

        BigDecimal points = (BigDecimal) query.getSingleResult();
        if (points != null) {
            return points.intValue();
        } else {
            return 0;
        }
    }

}
