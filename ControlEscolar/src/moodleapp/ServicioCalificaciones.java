/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleapp;

import conexion.JDBConexion;
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
    
    public Respuesta<ArrayList<Calificacion>> getCalificaciones() {
        JDBConexion conn = JDBConexion.Instance();
        
        return new Respuesta(conn.obtenerListaCalificaciones(), "");
    }
    
    public Respuesta<ArrayList<Calificacion>> getCalificaciones(int alumnoId) {
        JDBConexion conn = JDBConexion.Instance();
        
        ArrayList<Calificacion> califsAlumno = new ArrayList<>();
        String mensaje = "";
        
        ArrayList<Calificacion> califs = conn.obtenerListaCalificaciones();
        for (Calificacion calif : califs) {
            if(calif.getIduser() == alumnoId) {
                califsAlumno.add(calif);
                if (calif.getCalificacion() < 7)
                    mensaje = "Esto es una alarma, el estudiante con el id " + calif.getIduser() +", tiene bajas calificaciones.";
            }
        }
        
        return new Respuesta(califsAlumno, mensaje);
    }
}
