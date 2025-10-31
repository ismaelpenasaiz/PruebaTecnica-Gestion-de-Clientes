package org.example.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="clientes")
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 40)
    private String nombre;
    @Column(nullable = false, length = 40)
    private String apellidos;
    @Column(nullable = false, length = 10)
    private String sexo;
    @Column(nullable = false, length = 40)
    private String ciudad;
    @Column(nullable = false)
    private Date fechaNacimiento;
    @Column(nullable = false, length = 9)
    private int telefono;
    @Column(nullable = false, length = 40)
    private String correoElectronico;

    public Clientes(String nombre, String apellidos, String sexo, String ciudad, Date fechaNacimiento, int telefono, String correoElectronico) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Long getId() {
        return id;
    }

}