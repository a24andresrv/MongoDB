package com.example.model;

import java.time.LocalDate;

public class Empleado {
    private int empNo;
    private String nombre;
    private int dep;
    private double salario;
    private LocalDate fechaAlta;
    private String oficio;
    private Double comision;

    public Empleado(int empNo, String nombre, int dep, double salario, LocalDate fechaAlta, String oficio,
            Double comision) {
        this.empNo = empNo;
        this.nombre = nombre;
        this.dep = dep;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.oficio = oficio;
        this.comision = comision;
    }

    public Empleado(int empNo, String nombre, int dep, double salario, LocalDate fechaAlta, String oficio) {
        this.empNo = empNo;
        this.nombre = nombre;
        this.dep = dep;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.oficio = oficio;
        this.comision = null;
    }

    public Empleado(int empNo, String nombre, int dep, double salario, LocalDate fechaAlta) {
        this.empNo = empNo;
        this.nombre = nombre;
        this.dep = dep;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.oficio = null;
        this.comision = null;
    }

    // Getters and Setters
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "empNo=" + empNo +
                ", nombre='" + nombre + '\'' +
                ", dep=" + dep +
                ", salario=" + salario +
                ", fechaAlta=" + fechaAlta +
                ", oficio='" + oficio + '\'' +
                ", comision=" + comision +
                '}';
    }
}
