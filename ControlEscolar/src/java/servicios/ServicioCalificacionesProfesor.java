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
public class ServicioCalificacionesProfesor {
    private static ServicioCalificacionesProfesor s;
    
    private ServicioCalificacionesProfesor() {}
    
    public static ServicioCalificacionesProfesor Instance() {
        if (s == null) {
            s = new ServicioCalificacionesProfesor();
        }
        
        return s;
    }
    
    //busca calificaciones de una asignacion
    public Respuesta buscarCalificacionDeAsignacion(int asignacionId) {
        MoodleConexion conn = MoodleConexion.Instance();
        
        ArrayList<Calificacion> calis = new ArrayList<>();
        String mensaje = "";
        
        ArrayList<Calificacion> califs = conn.obtenerListaCalificaciones();
        for (Calificacion calif : califs) {
            if(calif.getAssignment() == asignacionId) {
                calis.add(calif);
                break;
            }
        }
        
        return new Respuesta(calis, mensaje);
    }
}
