package org.example;

import org.example.controllers.ClientesController;
import org.example.entities.Clientes;
import org.example.persistence.ConfigJPA;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        ClientesController controller = new ClientesController();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);

        while (!exit) {
            System.out.println("\n--------------------------------------");
            System.out.println("¿Qué desea hacer?");
            System.out.println("1. Agregar un nuevo cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Actualizar información de un cliente");
            System.out.println("4. Eliminar un cliente");
            System.out.println("5. Buscar cliente por ciudad");
            System.out.println("6. SALIR");
            System.out.print("Opción: ");

            if (sc.hasNextInt()) {
                int eleccion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (eleccion) {
                    case 1:
                        // AGREGAR CLIENTE
                        System.out.print("Introduzca un nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Introduzca apellidos: ");
                        String apellidos = sc.nextLine();
                        System.out.print("Introduzca sexo: ");
                        String sexo = sc.nextLine();
                        System.out.print("Introduzca ciudad: ");
                        String ciudad = sc.nextLine();

                        // Fecha
                        Date fecha = null;
                        boolean valido = false;
                        while (!valido) {
                            System.out.print("Introduzca fecha (dd/MM/yyyy): ");
                            String entrada = sc.nextLine();
                            try {
                                fecha = formato.parse(entrada);
                                valido = true;
                            } catch (ParseException e) {
                                System.out.println("Formato incorrecto. Usa dd/MM/yyyy.");
                            }
                        }

                        // Teléfono
                        int telefono = 0;
                        valido = false;
                        while (!valido) {
                            System.out.print("Introduzca teléfono de 9 dígitos: ");
                            String entrada = sc.nextLine().trim();
                            if (entrada.matches("\\d{9}")) {
                                telefono = Integer.parseInt(entrada);
                                valido = true;
                            } else {
                                System.out.println("El número debe tener exactamente 9 dígitos.");
                            }
                        }

                        // Correo
                        String correo = "";
                        valido = false;
                        String patronCorreo = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|es)$";
                        while (!valido) {
                            System.out.print("Introduzca un correo electrónico: ");
                            String correoTemporal = sc.nextLine().trim();
                            if (correoTemporal.matches(patronCorreo)) {
                                correo = correoTemporal;
                                valido = true;
                            } else {
                                System.out.println("Formato inválido. Debe terminar en .com o .es");
                            }
                        }

                        controller.registrarClientes(nombre, apellidos, sexo, ciudad, fecha, telefono, correo);
                        System.out.println("Cliente registrado correctamente!");
                        break;

                    case 2:
                        // LISTAR CLIENTES
                        List<Clientes> clientes = controller.listarClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                        } else {
                            System.out.println("Clientes registrados:");
                            for (Clientes c : clientes) {
                                System.out.println("ID: " + c.getId() +
                                        " | " + c.getNombre() + " " + c.getApellidos() +
                                        " | Ciudad: " + c.getCiudad() +
                                        " | Fecha nacimiento: " + formato.format(c.getFechaNacimiento()) +
                                        " | Teléfono: " + c.getTelefono() +
                                        " | Correo: " + c.getCorreoElectronico());
                            }
                        }
                        break;

                    case 3:
                        // ACTUALIZAR CLIENTE
                        System.out.print("Introduce el ID del cliente a modificar: ");
                        Long id = sc.nextLong();
                        sc.nextLine(); // limpiar buffer
                        Clientes client = controller.consultarClientes(id);

                        if (client != null) {
                            System.out.print("Nuevo nombre (" + client.getNombre() + "): ");
                            String n = sc.nextLine();
                            if (!n.isEmpty()) client.setNombre(n);

                            System.out.print("Nuevos apellidos (" + client.getApellidos() + "): ");
                            String a = sc.nextLine();
                            if (!a.isEmpty()) client.setApellidos(a);

                            System.out.print("Nueva ciudad (" + client.getCiudad() + "): ");
                            String c = sc.nextLine();
                            if (!c.isEmpty()) client.setCiudad(c);

                            // Fecha
                            valido = false;
                            while (!valido) {
                                System.out.print("Nueva fecha de nacimiento (" + formato.format(client.getFechaNacimiento()) + "): ");
                                String fechaStr = sc.nextLine();
                                if (fechaStr.isEmpty()) {
                                    valido = true; // no se cambia
                                } else {
                                    try {
                                        Date f = formato.parse(fechaStr);
                                        client.setFechaNacimiento(f);
                                        valido = true;
                                    } catch (ParseException e) {
                                        System.out.println("Formato incorrecto. Usa dd/MM/yyyy.");
                                    }
                                }
                            }

                            // Teléfono
                            valido = false;
                            while (!valido) {
                                System.out.print("Nuevo teléfono (" + client.getTelefono() + "): ");
                                String telStr = sc.nextLine();
                                if (telStr.isEmpty()) {
                                    valido = true;
                                } else if (telStr.matches("\\d{9}")) {
                                    client.setTelefono(Integer.parseInt(telStr));
                                    valido = true;
                                } else {
                                    System.out.println("Debe ser un número de 9 dígitos.");
                                }
                            }

                            // Correo
                            valido = false;
                            while (!valido) {
                                System.out.print("Nuevo correo (" + client.getCorreoElectronico() + "): ");
                                String cor = sc.nextLine();
                                if (cor.isEmpty()) {
                                    valido = true;
                                } else if (cor.matches(".+@.+\\.(com|es)")) {
                                    client.setCorreoElectronico(cor);
                                    valido = true;
                                } else {
                                    System.out.println("Formato inválido. Debe terminar en .com o .es");
                                }
                            }

                            controller.actualizarCliente(client);
                            System.out.println("Cliente actualizado correctamente.");
                        } else {
                            System.out.println("No se encontró ningún cliente con ese ID.");
                        }
                        break;

                    case 4:
                        // ELIMINAR CLIENTE
                        clientes = controller.listarClientes();
                        if (clientes.isEmpty()) {
                            System.out.println("No hay clientes registrados.");
                            break;
                        }

                        System.out.println("Clientes registrados:");
                        for (Clientes c : clientes) {
                            System.out.println("ID: " + c.getId() + " | " + c.getNombre() + " " + c.getApellidos());
                        }

                        System.out.println("¿Cómo deseas eliminar el cliente?");
                        System.out.println("1. Por ID");
                        System.out.println("2. Por nombre");
                        System.out.print("Elige una opción: ");
                        int opcion = sc.nextInt();
                        sc.nextLine(); // limpiar buffer

                        switch (opcion) {
                            case 1:
                                System.out.print("Introduce el ID del cliente: ");
                                Long idCliente = sc.nextLong();
                                sc.nextLine();
                                controller.eliminarClientePorId(idCliente);
                                System.out.println("Cliente eliminado.");
                                break;
                            case 2:
                                System.out.print("Introduce el nombre del cliente: ");
                                String nombreCliente = sc.nextLine();
                                controller.eliminarClientePorNombre(nombreCliente);
                                System.out.println("Cliente(s) eliminado(s).");
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                        break;

                    case 5:
                        // BUSCAR CLIENTE POR CIUDAD
                        System.out.print("Introduce la ciudad para buscar clientes: ");
                        String ciudadCliente = sc.nextLine();

                        clientes = controller.consultarPorCiudad(ciudadCliente);

                        if (clientes.isEmpty()) {
                            System.out.println("No se encontraron clientes en la ciudad: " + ciudadCliente);
                        } else {
                            System.out.println("Clientes en " + ciudadCliente + ":");
                            for (Clientes c : clientes) {
                                System.out.println("ID: " + c.getId() +
                                        " | " + c.getNombre() + " " + c.getApellidos() +
                                        " | Correo: " + c.getCorreoElectronico() +
                                        " | Teléfono: " + c.getTelefono());
                            }
                        }
                        break;

                    case 6:
                        // SALIR
                        System.out.println("Saliendo del programa. ¡Adiós!");
                        exit = true;
                        ConfigJPA.close();
                        sc.close();
                        break;

                    default:
                        System.out.println("Opción no válida.");
                }

            } else {
                System.out.println("Debe elegir un número válido.");
                sc.nextLine(); // limpiar buffer
            }
        }
    }
}
