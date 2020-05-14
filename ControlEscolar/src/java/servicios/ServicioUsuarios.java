/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexion.MoodleConexion;
import objectosNegocio.Respuesta;

/**
 *
 * @author Email
 */
public class ServicioUsuarios {
    private static ServicioUsuarios s;
    
    private ServicioUsuarios() {}
    
    public static ServicioUsuarios Instance() {
        if (s == null) {
            s = new ServicioUsuarios();
        }
        
        return s;
    }
    
    public Respuesta getUsuarios() {
        MoodleConexion conn = MoodleConexion.Instance();
        
        return new Respuesta(conn.obtenerListaAlumnos(), "");
    }
    
    public Respuesta getAlumnoId(int idAlumno){
        MoodleConexion conn = MoodleConexion.Instance();
        
        return new Respuesta(conn.obtenerAlumnoId(idAlumno), "");
    }
    
} 
