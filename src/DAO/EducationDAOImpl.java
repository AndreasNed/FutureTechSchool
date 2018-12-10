/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Education;
import futuretechschool.domain.Student;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author GasCan
 */
public class EducationDAOImpl implements EducationDAO {
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();
    
    @Override
    public void createEducation(Education education) {
        em.getTransaction().begin();
        em.persist(education);
        em.getTransaction().commit();
    }

    @Override
    public Education readEducation(int id) {
        em.getTransaction().begin();
        Education education = em.find(Education.class, id);
        em.getTransaction().commit();
        return education;
    }

    @Override
    public void updateEducation(Education education, int id) {
        em.getTransaction().begin();
        Education foundEducation = em.find(Education.class, id);
        foundEducation.setName(education.getName());
        foundEducation.setStudents(education.getStudents());
        foundEducation.setCourses(education.getCourses());
        em.getTransaction().commit();
        
    }

    @Override
    public void deleteEducation(int id) {
        em.getTransaction().commit();
        em.remove(em.find(Education.class, id));
        em.getTransaction().begin();
    }

    @Override
    public void addCourseToEducation(int educationId, int courseId) {
        em.getTransaction().begin();
        Education education = em.find(Education.class, educationId);
        Course course = em.find(Course.class, courseId);
        education.addCourse(course);
        em.getTransaction().commit();
        
    }

    @Override
    public void removeCourseFromEducation(int educationId, int courseId) {
        em.getTransaction().begin();
        Education education = readEducation(educationId);
        Course course = em.find(Course.class, courseId);
        education.removeCourse(course);
        em.getTransaction().commit();
        
    }

    @Override
    public void addStudentToEducation(int studentId, int educationId) {
        em.getTransaction().begin();
        Education education = em.find(Education.class, educationId);
        Student student = em.find(Student.class, studentId);
        education.addStudent(student);
        em.getTransaction().commit();
    }

    @Override
    public void removeStudentFromEducation(int studentId, int educationId) {
        em.getTransaction().begin();
        Education education = readEducation(educationId);
        education.removeStudent(em.find(Student.class, studentId));
        em.getTransaction().commit();
        
    }
        
}
