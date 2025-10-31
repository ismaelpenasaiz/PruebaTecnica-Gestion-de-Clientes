package org.example.persistence;

import jakarta.persistence.EntityManager;
import org.example.entities.Clientes;

import java.util.List;

public class ClientesJPA {
    private EntityManager em;
    public ClientesJPA() {
    }
    public ClientesJPA(EntityManager em){
        em = ConfigJPA.getEntityManager();
        this.em=em;
    }
    public static void registrarClientes(Clientes clientes) {
        try (EntityManager em = ConfigJPA.getEntityManager();){
            em.getTransaction().begin();
            em.persist(clientes);
            em.getTransaction().commit();
        }
    }

    public List<Clientes> listarClientes() {
        return em.createQuery("SELECT c FROM Clientes c", Clientes.class).getResultList();
    }

    public Clientes consultarClientes(Long idClientes) {
            return em.find(Clientes.class, idClientes);
    }

    public void eliminarClientesPorID(Long idClientes) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            Clientes cliente = em.find(Clientes.class, idClientes);
            if (cliente != null) {
                em.remove(cliente);
            } else {
                System.out.println("No se encontró ningún cliente con ID " + idClientes);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
    public void eliminarClientePorNombre(String nombre) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            List<Clientes> clientes = em.createQuery(
                            "SELECT c FROM Clientes c WHERE LOWER(c.nombre) = LOWER(:nombre)", Clientes.class)
                    .setParameter("nombre", nombre)
                    .getResultList();

            if (clientes.isEmpty()) {
                System.out.println("No se encontró ningún cliente con el nombre: " + nombre);
            } else {
                for (Clientes c : clientes) {
                    em.remove(c);
                }
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
    public List<Clientes> consultarPorCiudad(String ciudad) {
        return em.createQuery(
                        "SELECT c FROM Clientes c WHERE LOWER(c.ciudad) = LOWER(:ciudad)", Clientes.class)
                .setParameter("ciudad", ciudad)
                .getResultList();
    }

    public void actualizarCliente(Clientes cliente) {
        var tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(cliente);  // merge actualiza el objeto existente
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
    }
}
