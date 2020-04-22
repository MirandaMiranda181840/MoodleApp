/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleapp;

import conexion.JDBConexion;
import java.util.ArrayList;
import objectosNegocio.Alumno;
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
    
    public Respuesta<ArrayList<Alumno>> getUsuarios() {
        JDBConexion conn = JDBConexion.Instance();
        
        return new Respuesta(conn.obtenerListaAlumnos(), "");
    }
}
