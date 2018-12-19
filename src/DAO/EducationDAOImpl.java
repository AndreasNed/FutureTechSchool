
package DAO;

import futuretechschool.domain.Education;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class EducationDAOImpl implements EducationDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    EntityManager em = emf.createEntityManager();

    @Override
    public void createEducation(Education education) {
        try {
            em.getTransaction().begin();
            em.persist(education);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not insert education to database. Exception: " + ex);
        }
    }

    @Override
    public Education readEducation(int id) {
        Education education = em.find(Education.class, id);
        if (education != null) {
            return education;
        } else {
            System.out.println("No such education.");
            return null;
        }
    }

    @Override
    public void updateEducation(Education education) {
        try {
            em.getTransaction().begin();
            if (education == null) {
                em.getTransaction().rollback();
            } else {
                em.merge(education);
                em.getTransaction().commit();
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public void deleteEducation(int id) {
        Education e = em.find(Education.class, id);
        try {
            if (e != null) {
                em.getTransaction().begin();
                em.remove(em.find(Education.class, id));
                em.getTransaction().commit();
            } else {
                System.out.println("No such education.");
            }
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Education> readAllEducations() {
        return em.createQuery("Select e from Education e").getResultList();
    }
}
