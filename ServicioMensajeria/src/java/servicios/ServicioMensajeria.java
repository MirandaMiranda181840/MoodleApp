/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexioncontrolescolar.ConexionControlEscolar;
import encriptado.AES;
import java.util.ArrayList;
import objectosNegocio.Mensaje;
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
            ConexionControlEscolar.Instance(); //establecer conexion
        }
        
        return s;
    }
    
    public Respuesta getMensajesRelevantes(int userId, int receptorId) {
        ConexionControlEscolar conn = ConexionControlEscolar.Instance();
        
        System.out.println(userId + ", " + receptorId);
        ArrayList<Mensaje> mensajes = conn.obtenerMensajesRelevantes(userId, receptorId);
        for (Mensaje mensaje : mensajes) {
            mensaje.setContenido(AES.decrypt(mensaje.getContenido(), "SECRETO"));
        }
        
        return new Respuesta(mensajes, "");
    }
    
    
    public void enviarMensaje(int remitenteId, int recipienteId, String mensaje) {
        ConexionControlEscolar conn = ConexionControlEscolar.Instance();
        System.out.println(remitenteId + ", " + recipienteId + ", " + mensaje);
        conn.enviarMensaje(remitenteId, recipienteId, AES.encrypt(mensaje, "SECRETO"));
    }
}
