/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexionapimoodle.ConexionMoodle;
import conexioncontrolescolar.ConexionControlEscolar;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectosNegocio.Alumno;
import objectosNegocio.Respuesta;
import org.json.JSONException;
import org.json.JSONObject;
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
    
    public Respuesta registrarPadre(String nombre, String apellido, String email, String password, String codigoAlumno) {
        ConexionControlEscolar con = ConexionControlEscolar.Instance();
        
        String token = null;
        String mensaje = "";
        try {
            token = con.registrarUsuario(nombre, apellido, email, password, codigoAlumno);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            mensaje = ex.getMessage();
        }
        if(token == null)
            return new Respuesta(null, mensaje);
        
        return new Respuesta(token, "");
    }
    
    public Respuesta loguearPadre(String email, String password) {
        ConexionControlEscolar con = ConexionControlEscolar.Instance();
        
        String token = null;
        String mensaje = "";
        try {
            token = con.loguearUsuario(email, password);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            mensaje = ex.getMessage();
        }
        if(token == null)
            return new Respuesta(null, mensaje);
        
        return new Respuesta(token, "");
    }
    
    /*
    public Respuesta autenticarUsuario(String username, String password) {
        ConexionMoodle con = new ConexionMoodle(0);
        
        String respuesta;
        try {
           respuesta = con.AutenticarUsuario(username, password);
           return new Respuesta(respuesta, "");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al autenticar usuario.");
        }
    }
    
    public Respuesta registrarUsuario(String username, String password, String firstname, String lastname, String email) {
        ConexionMoodle con = new ConexionMoodle(1);
        
        String respuesta;
        try {
            respuesta = con.Llamar("core_user_create_users", 
                    "users[0][username]=" + username
                    + "&users[0][password]=" + password
                    + "&users[0][firstname]=" + firstname
                    + "&users[0][lastname]=" + lastname
                    + "&users[0][email]=" + email
           );
           //new Alumno(json.getInt("id"), username, firstname, lastname, email);
           return new Respuesta(respuesta, "");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al crear usuario.");
        }
    }
    */
} 
