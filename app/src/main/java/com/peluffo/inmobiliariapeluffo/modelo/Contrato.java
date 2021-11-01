package com.peluffo.inmobiliariapeluffo.modelo;

import android.util.Log;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;


public class Contrato implements Serializable {

    private int id;
    private String fechaInicio;
    private String fechaFin;
    private double monto;
    private boolean cancelado;
    private String fechaCancelado;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato(int id, String fechaInicio, String fechaFin, double monto, boolean cancelado, String fechaCancelado, Inquilino inquilino, Inmueble inmueble) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.monto = monto;
        this.cancelado = cancelado;
        this.fechaCancelado = fechaCancelado;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaCancelado() {
        return fechaCancelado;
    }

    public void setFechaCancelado(String fechaCancelado) {
        this.fechaCancelado = fechaCancelado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
