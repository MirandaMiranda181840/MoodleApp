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
import objectosNegocio.Asignacion;
import objectosNegocio.Calificacion;
import objectosNegocio.Curso;
import objectosNegocio.DatosAsignacion;
import objectosNegocio.Respuesta;
import org.json.JSONArray;
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
    
    public Respuesta getCursos(int hijoId) {
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta;
            
            respuesta = con.Llamar("core_enrol_get_users_courses", "userid="+hijoId);
            System.out.println("hijoId"+hijoId);
            System.out.println("getCursos json: " + respuesta);
            ArrayList<Curso> cursos = new ArrayList<>();
            
            JSONArray arr = new JSONArray(respuesta);
            
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                cursos.add(
                        new Curso(obj.getInt("id"), obj.getString("fullname"), obj.getString("summary"))
                );
            }
            System.out.println("getCursos res: " + cursos.toString());

            Curso[] _arr = new Curso[cursos.size()]; 
            _arr = cursos.toArray(_arr); 
            
            return new Respuesta(_arr, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Respuesta(null, "Error al obtener cursos.");
    }
    
    public Respuesta getAsignaciones(int cursoId) {
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta;
            
            ArrayList<Asignacion> asignaciones = new ArrayList<>();
            
            respuesta = con.Llamar("mod_assign_get_assignments", "");
            System.out.println("getAsignaciones json: " + respuesta);
            JSONObject jsonObj = new JSONObject(respuesta);
            JSONArray arr = jsonObj.getJSONArray("courses");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if(obj.getInt("id") == cursoId){
                    JSONArray asigs = obj.getJSONArray("assignments");
                    for (int j = 0; j < asigs.length(); j++) {
                        JSONObject obj2 = asigs.getJSONObject(j);
                        Asignacion asig = new Asignacion();
                        asig.setId(obj2.getInt("id"));
                        asig.setIdcurso(cursoId);
                        asig.setIntroduccion(obj2.getString("intro"));
                        asig.setNombre(obj2.getString("name"));
                        asig.setNumSubidas(obj2.getInt("nosubmissions"));
                        asig.setFechaEntrega(obj2.getInt("duedate"));
                        
                        asignaciones.add(asig);
                    }
                }
            }
            System.out.println("getAsignaciones obj: "+ asignaciones.toString());
            
            Asignacion[] _arr = new Asignacion[asignaciones.size()]; 
            _arr = asignaciones.toArray(_arr); 
            
            return new Respuesta(_arr, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener asignaciones.");
    }
    
    public Respuesta getCalificacionAsignacion(int hijoId, int cursoId, int asignacionId) {
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta = con.Llamar("mod_assign_get_grades", "assignmentids[0]=" + asignacionId);
            System.out.println("getCalificacionAsignacion json: " + respuesta);
            
            JSONObject jsonObj = new JSONObject(respuesta);
            JSONArray arr = jsonObj.getJSONArray("assignments");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if(obj.getInt("assignmentid") == asignacionId){
                    JSONArray arr2 = obj.getJSONArray("grades");
                    for (int j = 0; j < arr2.length(); j++) {
                        JSONObject obj2 = arr2.getJSONObject(j);
                        if(obj2.getInt("userid") == hijoId){
                            System.out.println(obj2.getDouble("grade"));
                            double grade = obj2.getDouble("grade");
                            float fgrade = (float) grade;
                            int fechaCal = (int) this.getFechaCalificada(hijoId, cursoId, asignacionId).getRespuesta();
                            System.out.println(fechaCal);
                            DatosAsignacion asig = new DatosAsignacion(
                                    obj.getInt("assignmentid"), 
                                    obj2.getInt("attemptnumber"), 
                                    obj2.getInt("timecreated"), 
                                    obj2.getInt("timemodified"), 
                                    obj2.getInt("grader"), 
                                    fgrade,
                                    fechaCal
                            );
                            return new Respuesta(asig, "");
                        }
                    }
                }
            }
            
            System.out.println("getCalificacionAsignacion obj: " + respuesta);
            String subida = con.Llamar("mod_assign_get_submission_status", "userid="+hijoId+"&assignid="+asignacionId);
            
            DatosAsignacion noEntregada = new DatosAsignacion();
            noEntregada.setAttemptnumber(0);
            noEntregada.setGrade(-1.0f);
            noEntregada.setGrader(0);
            noEntregada.setTimecreated(subida.contains("\"status\":\"submitted\"") ? 1 : 0);
            noEntregada.setTimemodified(hijoId);
            
            return new Respuesta(noEntregada, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener calificaciones.");
    }
    
    public Respuesta getFechaCalificada(int hijoId, int cursoId, int asignacionId) { //gradereport_user_get_grade_items
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta = con.Llamar("gradereport_user_get_grade_items", "courseid="+cursoId);
            System.out.println("getCalificacionAsignacion json: " + respuesta);
            
            JSONObject jsonObj = new JSONObject(respuesta);
            JSONArray arr = jsonObj.getJSONArray("usergrades");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                //System.out.println(obj.getInt("userid") + "," + hijoId);
                if(obj.getInt("userid") == hijoId){
                    JSONArray arr2 = obj.getJSONArray("gradeitems");
                    //System.out.println(arr2);
                    for (int j = 0; j < arr2.length(); j++) {
                        JSONObject obj2 = arr2.getJSONObject(j);
                        //System.out.println(obj2.getInt("id") + "," + asignacionId);
                        if(obj2.getString("itemmodule").equals("assign") && obj2.getInt("iteminstance") == asignacionId){
                            System.out.println("Fecha revision: " + obj2.getInt("gradedategraded"));
                            return new Respuesta(obj2.getInt("gradedategraded"), "");
                        }
                    }
                }
            }
            
            return new Respuesta(0, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener fecha.");
    }
}
