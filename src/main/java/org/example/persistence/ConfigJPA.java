package org.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    // Metodos de insercion a bases de datos
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("miUnidad");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public static void close(){
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}