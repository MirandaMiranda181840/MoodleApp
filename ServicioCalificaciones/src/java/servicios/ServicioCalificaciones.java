/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexionapimoodle.ConexionMoodle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectosNegocio.Alumno;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;
import org.json.JSONException;
import org.json.JSONObject;

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
    /*
    //&moodlewsrestformat=json
    //mod_assign_get_grades
    public Respuesta getCursosAlumno(int alumnoId){
        //http://localhost/moodle/webservice/rest/server.php?wstoken=6a184382969c536d063d0f909581ec36&wsfunction=core_enrol_get_users_courses&userid=55
        MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerListaCursosAlumno(alumnoId),"");
    }
    
    public Respuesta getAsignacionesCurso(int cursoID){
           MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerListaAsignacionesCurso(cursoID),"");
    }
    
    public Respuesta getDetalleAsignacion(String infoId){
        MoodleConexion conn= MoodleConexion.Instance();
        return new Respuesta(conn.obtenerDetalleAsignacion(infoId),"");
    }
    */
    
    public Respuesta getCursos(String token) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
            respuesta = con.Llamar("core_course_get_courses", "");
            
            return new Respuesta(respuesta, "");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return new Respuesta(null, "Error al obtener asignaciones.");
        }
    }
    
    public Respuesta getAsignaciones(String token) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
            respuesta = con.Llamar("mod_assign_get_assignments", "");
            return new Respuesta(respuesta, "");
        } catch (IOException ex) {
            return new Respuesta(null, "Error al obtener asignaciones.");
        }
    }
    
    public Respuesta getCalificacionAsignacion(String token, String asignacionId) {
        ConexionMoodle con = new ConexionMoodle(token);
        
        String respuesta;
        try {
            respuesta = con.Llamar("mod_assign_get_grades", "assignmentids[0]" + asignacionId);
            return new Respuesta(respuesta, "");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return new Respuesta(null, "Error al obtener calificaciones.");
        }
    }
}
