/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexioncontrolescolar.ConexionControlEscolar;
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
        ConexionControlEscolar conn = ConexionControlEscolar.Instance();
        
        return new Respuesta(conn.obtenerMensajesRelevantes(userId), "");
    }
    
    
    public void enviarMensaje(int remitenteId, int recipienteId, String mensaje) {
        ConexionControlEscolar conn = ConexionControlEscolar.Instance();
        
        conn.enviarMensaje(remitenteId, recipienteId, mensaje);
    }
}
