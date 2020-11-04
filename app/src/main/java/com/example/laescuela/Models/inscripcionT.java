package com.example.laescuela.Models;

public class inscripcionT {

    private Integer id_inscripcion;
    private String nombreAlumno;
    private String apellidoAlumno;
    private String direccionAlumno;
    private String telefonoAlumno;
    private String gradoGrad;
    private String fechaInscrita;
    private byte[] imagen;

    ConexionSQLHelper conn;

    public inscripcionT() {
        this.id_inscripcion = id_inscripcion;
        this.nombreAlumno = nombreAlumno;
        this.apellidoAlumno = apellidoAlumno;
        this.direccionAlumno = direccionAlumno;
        this.telefonoAlumno = telefonoAlumno;
        this.gradoGrad = gradoGrad;
        this.fechaInscrita = fechaInscrita;
        this.imagen = imagen;
    }

    public Integer getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(Integer id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public String getDireccionAlumno() {
        return direccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.direccionAlumno = direccionAlumno;
    }

    public String getTelefonoAlumno() {
        return telefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.telefonoAlumno = telefonoAlumno;
    }

    public String getGradoGrad() {
        return gradoGrad;
    }

    public void setGradoGrad(String gradoGrad) {
        this.gradoGrad = gradoGrad;
    }

    public String getFechaInscrita() {
        return fechaInscrita;
    }

    public void setFechaInscrita(String fechaInscrita) {
        this.fechaInscrita = fechaInscrita;
    }

    public byte[] getImagen() {

        return imagen;
    }

    public byte[] setImagen(byte[] imagen) {
        this.imagen = imagen;
        return imagen;
    }
}