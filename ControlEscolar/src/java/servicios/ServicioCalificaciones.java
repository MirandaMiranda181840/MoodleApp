/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexion.MoodleConexion;
import java.util.ArrayList;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;

/**
 *
 * @author Email
 */
public class ServicioCalificaciones {
    private static ServicioCalificaciones s;
    
    private ServicioCalificaciones() {}
    
    public static ServicioCalificaciones Instance() {
        if (s == null) {
            s = new ServicioCalificaciones();
        }
        
        return s;
    }
    
    public Respuesta getCursosAlumno(int alumnoId){
        MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerListaCursosAlumno(alumnoId),"");
    }
    
    public Respuesta getAsignacionesCurso(int cursoID){
           MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerListaAsignacionesCurso(cursoID),"");
    }
    
    public Respuesta getDetalleAsignacion(String infoId){
        
        MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerDetalleAsignacion(infoId),"");
    }
    
}
