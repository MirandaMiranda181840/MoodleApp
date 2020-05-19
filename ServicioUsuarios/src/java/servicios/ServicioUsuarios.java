/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexionapimoodle.ConexionMoodle;
import conexioncontrolescolar.ConexionControlEscolar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectosNegocio.Alumno;
import objectosNegocio.Asignacion;
import objectosNegocio.DatosHijo;
import objectosNegocio.DatosPadre;
import objectosNegocio.DatosProfesor;
import objectosNegocio.Respuesta;
import org.json.JSONArray;
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
    /*
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
    */
    
    public Respuesta autenticarUsuario(String username, String password) {
        ConexionMoodle con = new ConexionMoodle(0);
        
        String respuesta;
        try {
            respuesta = con.AutenticarUsuario(username, password);
            
            JSONObject js;
            try {
                System.out.println("RESPUESTA: " + respuesta);
                js = new JSONObject(respuesta);
                String token = js.getString("token");
                
                return new Respuesta(token, "");
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

            return new Respuesta(null, "Error al autenticar usuario. Usuario y/o contraseña invalidos.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al autenticar usuario.");
        }
    }
    
    public Respuesta registrarUsuario(String email, String password, String firstname, String lastname) {
        ConexionMoodle con = new ConexionMoodle(1);
        
        String respuesta;
        try {
            respuesta = con.Llamar("core_user_create_users", 
                    "users[0][username]=" + email
                    + "&users[0][password]=" + password
                    + "&users[0][firstname]=" + firstname
                    + "&users[0][lastname]=" + lastname
                    + "&users[0][email]=" + email
           );
            try {
                if(respuesta!=null){
                    JSONObject js = new JSONObject(respuesta);
                    if(js.getString("errorcode") != null){
                        String amigable = "Desconocido.";
                        if(js.getString("errorcode").contains("lphanumeric"))
                            amigable = "La contraseña debe de tener al menos 8 caracteres, 3 numeros y 1 símbolo.";
                        return new Respuesta(null, amigable);
                    }
                }
            } catch (JSONException ex) {
                //Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("OTRA: " + respuesta);
           
           //new Alumno(json.getInt("id"), username, firstname, lastname, email);
           String token = (String) this.autenticarUsuario(email, password).getRespuesta();
           return new Respuesta(token, "");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al crear usuario.");
        }
    }

    public Respuesta getNombreUsuario(String token, String email) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
           respuesta = con.Llamar("core_user_get_users_by_field", 
                    "field=username&values[0]="+email
           );
           //respuesta.replace("[", ""); 
           respuesta = respuesta.substring(1, respuesta.length()); //quitar llaves que causan un error raro
           respuesta = respuesta.substring(0, respuesta.length() - 2);
           System.out.println("RESPUESTA NOMBRE: " + respuesta);
           JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(respuesta);
                return new Respuesta(jsonObj.getString("fullname"), ""); 
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
           return new Respuesta(null, "Error al obtener nombre.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al obtener nombre.");
        }
    }
    
    public Respuesta getIdUsuario(String token) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
           respuesta = con.Llamar("core_webservice_get_site_info", "");
           
           System.out.println("RESPUESTA ID: " + respuesta);
           JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(respuesta);
                return new Respuesta(jsonObj.getInt("userid"), ""); 
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
           return new Respuesta(null, "Error al obtener id.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al obtener id.");
        }
    }
    
    public Respuesta getHijo(String token) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
           respuesta = con.Llamar("gradereport_user_get_grade_items", "courseid=1"); //obtiene lista de todos los usuarios del sistema
           System.out.println("HIJO: " + respuesta);
           JSONObject jsonObj;
            try {
                jsonObj = new JSONObject(respuesta);
                JSONArray array = jsonObj.getJSONArray("usergrades");
                /*
                String txtJson = array.toString();
                txtJson = txtJson.substring(1, txtJson.length()); //quitar llaves que causan un error raro
                txtJson = txtJson.substring(0, txtJson.length() - 1);
                
                System.out.println(txtJson);
                JSONObject nuevo = new JSONObject(txtJson);*/
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    System.out.println("USUARIO: " + obj.getInt("userid"));
                    System.out.println("MI TOKEN: " + token);
                    String respuesta2 = con.Llamar("core_user_view_user_profile", "userid="+obj.getInt("userid")); //verificacion de que es su hijo
                    System.out.println("ES O NO ES: " + respuesta2);
                    if(respuesta2.contains("\"status\":true")){
                        int id = obj.getInt("userid");
                        String fullname = obj.getString("userfullname");
                        DatosHijo da = new DatosHijo(id, fullname);
                        return new Respuesta(da, ""); 
                    }
                        
                }
                
                return new Respuesta(null, "Error al obtener hijo."); 
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
           return new Respuesta(null, "Error al obtener hijo.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al obtener hijo.");
        }
    }
    
    
    public Respuesta getProfesor(String token, int cursoId) {
        ConexionMoodle con = new ConexionMoodle(0);
        
        String respuesta;
        try {
           respuesta = con.Llamar("core_enrol_get_enrolled_users", "courseid="+cursoId);
            System.out.println(respuesta);
           JSONArray array;
            try {
                array = new JSONArray(respuesta);
                
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    JSONArray roles = obj.getJSONArray("roles");
                    if(roles.toString().contains("\"shortname\":\"editingteacher\""))
                        return new Respuesta(new DatosProfesor(obj.getInt("id"), obj.getString("fullname")), "");
                }
                
                return new Respuesta(null, "Error al obtener profesor."); 
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
           return new Respuesta(null, "Error al obtener profesor.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al obtener profesor.");
        }
    }
    
    public Respuesta getPadres(String token) {
        ConexionMoodle con = new ConexionMoodle(0);
        
        String respuesta;
        try {
           respuesta = con.Llamar("core_enrol_get_enrolled_users", "courseid=1");
            System.out.println(respuesta);
           JSONArray array;
            try {
                array = new JSONArray(respuesta);
                ArrayList<DatosPadre> list = new ArrayList<>();
                
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    JSONArray roles = obj.getJSONArray("roles");
                    if(roles.toString().contains("\"shortname\":\"parent\"")){
                        System.out.println("si es padre");
                        list.add(new DatosPadre(obj.getInt("id"), obj.getString("fullname")));
                    }
                }
                
                DatosPadre[] _arr = new DatosPadre[list.size()]; 
                _arr = list.toArray(_arr); 
                
                return new Respuesta(_arr, ""); 
            } catch (JSONException ex) {
                Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
           return new Respuesta(null, "Error al obtener padres.");
        } catch (IOException ex) {
            Logger.getLogger(ServicioUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return new Respuesta(null, "Error al obtener padres.");
        }
    }
} 
