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
    
    public Respuesta getCalificaciones() {
        MoodleConexion conn = MoodleConexion.Instance();
        
        return new Respuesta(conn.obtenerListaCalificaciones(), "");
    }
    
    //busca calificaciones de un alumno
    public Respuesta getCalificaciones(int alumnoId) {
        MoodleConexion conn = MoodleConexion.Instance();
        
        ArrayList<Calificacion> califsAlumno = new ArrayList<>();
        String mensaje = "";
        
        ArrayList<Calificacion> califs = conn.obtenerListaCalificaciones();
        for (Calificacion calif : califs) {
            if(calif.getIduser() == alumnoId) {
                califsAlumno.add(calif);
            }
        }
        
        return new Respuesta(califsAlumno, mensaje);
    }
}
