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
public class AsignacionStatusAlumno {
    private int idAsignacion, idAlumno;
    private boolean statusAsignacion;

    public AsignacionStatusAlumno(int idAsignacion, int idAlumno, boolean statusAsignacion) {
        this.idAsignacion = idAsignacion;
        this.idAlumno = idAlumno;
        this.statusAsignacion = statusAsignacion;
    }

    
    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public boolean isStatusAsignacion() {
        return statusAsignacion;
    }

    public void setStatusAsignacion(boolean statusAsignacion) {
        this.statusAsignacion = statusAsignacion;
    }
    
    
}
