package org.example.controllers;
import org.example.entities.Clientes;
import org.example.persistence.ClientesJPA;

import java.util.*;

public class ClientesController {
    private ClientesJPA clienteJPA;
    public ClientesController(ClientesJPA clienteJPA) {
        this.clienteJPA = clienteJPA;
    }

    public ClientesController() {

    }

    public void registrarClientes(String nombre, String apellidos, String sexo, String ciudad, Date fechaNacimiento,
                                         int telefono, String correoElectronico) {
        ClientesJPA.registrarClientes(new Clientes(nombre, apellidos, sexo, ciudad, fechaNacimiento, telefono, correoElectronico));
    }

    public List<Clientes> listarClientes() {
        ClientesJPA clientesJPA = new ClientesJPA();
        return clientesJPA.listarClientes();
    }

    public Clientes consultarClientes(Long idCliente) {
        return clienteJPA.consultarClientes(idCliente);
    }

    public void actualizarCliente(Clientes cliente) {
        clienteJPA.actualizarCliente(cliente);
    }

    public void eliminarClientePorId(Long id) {
        clienteJPA.eliminarClientesPorID(id);
    }
    public void eliminarClientePorNombre(String nombre) {
        clienteJPA.eliminarClientePorNombre(nombre);
    }
    public List<Clientes> consultarPorCiudad(String ciudad) {
        return clienteJPA.consultarPorCiudad(ciudad);
    }

}

