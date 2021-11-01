package com.peluffo.inmobiliariapeluffo.modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {

    private int id;
    private int nroPago;
    private String fechaPago;
    private double monto;
    private Contrato contrato;

    public Pago() {}

    public Pago(int id, int nroPago, String fechaPago, double monto, Contrato contrato) {
        this.id = id;
        this.nroPago = nroPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
        this.contrato = contrato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
