/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectosNegocio;

/**
 *
 * @author crisb
 */
public class DetalleAsignacion {
    private int idAlumno;
    private int idAsignacion;
    private String name, descripcion, fechaDeEntrega;
    private double calificacion = 200.0;
    private boolean status;

    public DetalleAsignacion() { }

    public DetalleAsignacion(int idAlumno, int idAsignacion, String name, String descripcion, String fechaDeEntrega, double calificacion, boolean status) {
        this.idAlumno = idAlumno;
        this.idAsignacion = idAsignacion;
        this.name = name;
        this.descripcion = descripcion;
        this.fechaDeEntrega = fechaDeEntrega;
        this.calificacion = calificacion;
        this.status = status;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaDeEntrega() {
        return fechaDeEntrega;
    }

    public void setFechaDeEntrega(String fechaDeEntrega) {
        this.fechaDeEntrega = fechaDeEntrega;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    


    

}
