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
public class CalificacionAsignacionAlumno {
    private int asignacionID, alumnoID;
    private float calificacion;

    public CalificacionAsignacionAlumno(int asignacionID, int alumnoID, float calificacion) {
        this.asignacionID = asignacionID;
        this.alumnoID = alumnoID;
        this.calificacion = calificacion;
    }

    
    
    public int getAsignacionID() {
        return asignacionID;
    }

    public void setAsignacionID(int asignacionID) {
        this.asignacionID = asignacionID;
    }

    public int getAlumnoID() {
        return alumnoID;
    }

    public void setAlumnoID(int alumnoID) {
        this.alumnoID = alumnoID;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
    
    
    
}
