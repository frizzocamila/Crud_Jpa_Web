package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author frizzocamila
 */
public class ConnFactory {

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("LojaPU");
        EntityManager entityManager = factory.createEntityManager();
        return entityManager;
    }
}
