/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexion.ControlEscolarConexion;
import conexion.MoodleConexion;
import objectosNegocio.Respuesta;

/**
 *
 * @author Email
 */
public class ServicioMensajeria {
    private static ServicioMensajeria s;
    
    private ServicioMensajeria() {}
    
    public static ServicioMensajeria Instance() {
        if (s == null) {
            s = new ServicioMensajeria();
        }
        
        return s;
    }
    
    public Respuesta getMensajesRelevantes(int userId) {
        ControlEscolarConexion conn = ControlEscolarConexion.Instance();
        
        return new Respuesta(conn.obtenerMensajesRelevantes(userId), "");
    }
}
