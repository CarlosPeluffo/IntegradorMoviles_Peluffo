package com.peluffo.inmobiliariapeluffo.modelo;

import java.io.Serializable;

public class Inquilino implements Serializable {

    private int id;
    private String apellido;
    private String nombre;
    private Long dni;
    private String mail;
    private String telefono;
    private String lugarDeTrabajo;
    private String nombreGarante;
    private String telefonoGarante;
    private String mailGarante;

    public Inquilino() {}

    public Inquilino(int id, String apellido, String nombre, Long dni, String mail, String telefono, String lugarDeTrabajo, String nombreGarante, String telefonoGarante, String mailGarante) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.telefono = telefono;
        this.lugarDeTrabajo = lugarDeTrabajo;
        this.nombreGarante = nombreGarante;
        this.telefonoGarante = telefonoGarante;
        this.mailGarante = mailGarante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLugarDeTrabajo() {
        return lugarDeTrabajo;
    }

    public void setLugarDeTrabajo(String lugarDeTrabajo) {
        this.lugarDeTrabajo = lugarDeTrabajo;
    }

    public String getNombreGarante() {
        return nombreGarante;
    }

    public void setNombreGarante(String nombreGarante) {
        this.nombreGarante = nombreGarante;
    }

    public String getTelefonoGarante() {
        return telefonoGarante;
    }

    public void setTelefonoGarante(String telefonoGarante) {
        this.telefonoGarante = telefonoGarante;
    }

    public String getMailGarante() {
        return mailGarante;
    }

    public void setMailGarante(String mailGarante) {
        this.mailGarante = mailGarante;
    }
}
