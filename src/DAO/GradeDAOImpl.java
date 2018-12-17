/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import futuretechschool.domain.Course;
import futuretechschool.domain.Grade;
import futuretechschool.domain.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author GasCan
 */
public class GradeDAOImpl implements GradeDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void createGrade(Grade grade) {
        try {
            em.getTransaction().begin();
            em.persist(grade);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }
    
    @Override
    public Grade readGrade(int id){
        return em.find(Grade.class, id);
    }

    @Override
    public List<Grade> readGradesByStudent(Student student) {
        Query query = em.createQuery("SELECT g from Grade g INNER JOIN g.student s WHERE s = :student");
        query.setParameter("student", student);
        return query.getResultList();
    }

    @Override
    public void updateGrade(Grade grade) {
        try {
            em.getTransaction().begin();
            em.merge(grade);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteGrade(int id) {
        try {
            em.getTransaction().begin();
            Grade grade = em.find(Grade.class, id);
            em.remove(grade);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Grade> readAllGrade() {
       return em.createQuery("Select g from Grade g").getResultList();
    }

}
