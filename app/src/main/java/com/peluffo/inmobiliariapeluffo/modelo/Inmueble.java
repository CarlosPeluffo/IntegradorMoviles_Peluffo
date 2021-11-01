package com.peluffo.inmobiliariapeluffo.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String uso;
    private String tipo;
    private int ambientes;
    private double precio;
    private boolean estado;
    private String avatar;
    private String avatarFile;
    private int propietarioId;
    private Propietario propietario;

    public Inmueble() {
    }

    public Inmueble(int id, String direccion, String uso, String tipo, int ambientes, double precio, boolean estado, String avatar, String avatarFile, int propietarioId, Propietario propietario) {
        this.id = id;
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.estado = estado;
        this.avatar = avatar;
        this.avatarFile = avatarFile;
        this.propietarioId = propietarioId;
        this.propietario = propietario;
    }

    public Inmueble(String direccion, String uso, String tipo, int ambientes, double precio, boolean estado, String avatar, String avatarFile, int propietarioId, Propietario propietario) {
        this.direccion = direccion;
        this.uso = uso;
        this.tipo = tipo;
        this.ambientes = ambientes;
        this.precio = precio;
        this.estado = estado;
        this.avatar = avatar;
        this.avatarFile = avatarFile;
        this.propietarioId = propietarioId;
        this.propietario = propietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(String avatarFile) {
        this.avatarFile = avatarFile;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
