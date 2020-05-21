/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexionapimoodle.ConexionMoodle;
import conexiones.Conexion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectosNegocio.Alumno;
import objectosNegocio.Asignacion;
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
                            long fechaCal = (long) this.getFechaCalificada(hijoId, cursoId, asignacionId).getRespuesta();
                            System.out.println(fechaCal);
                            DatosAsignacion asig = new DatosAsignacion(
                                    obj.getInt("assignmentid"), 
                                    obj2.getInt("attemptnumber"), 
                                    obj2.getLong("timecreated"), 
                                    obj2.getLong("timemodified"), 
                                    obj2.getInt("grader"), 
                                    fgrade,
                                    fechaCal,
                                    0
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
            noEntregada.setTimemodified(0);
            
            return new Respuesta(noEntregada, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener calificaciones.");
    }
    
    public Respuesta getCalificacionAsignacionProfesor(int profesorId, int cursoId, int asignacionId, String token) {
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta = con.Llamar("mod_assign_get_grades", "assignmentids[0]=" + asignacionId);
            System.out.println("getCalificacionAsignacion json: " + respuesta);
            
            Conexion.TareasResource_JerseyClient con2 = new Conexion.TareasResource_JerseyClient();
            Asignacion[] asigs = con2.getAsignaciones(Asignacion[].class, ""+cursoId, token);
            
            ArrayList<DatosAsignacion> list = new ArrayList<>();
            HashMap<String, Boolean> revisadas = new HashMap<>();
      
            JSONObject jsonObj = new JSONObject(respuesta);
            JSONArray arr = jsonObj.getJSONArray("assignments");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                if(obj.getInt("assignmentid") == asignacionId){
                    JSONArray arr2 = obj.getJSONArray("grades");
                    for (int j = 0; j < arr2.length(); j++) {
                        JSONObject obj2 = arr2.getJSONObject(j);
                        if(obj2.getInt("grader") == profesorId){
                            System.out.println(obj2.getDouble("grade"));
                            double grade = obj2.getDouble("grade");
                            float fgrade = (float) grade;
                            long fechaCal = (long) this.getFechaCalificada(cursoId, asignacionId).getRespuesta();
                            System.out.println(fechaCal);
                            
                            long fechaEntrega = 0;
                            for (Asignacion asig : asigs) {
                                if(asig.getId() == obj.getInt("assignmentid")){
                                    fechaEntrega = asig.getFechaEntrega();
                                    break;
                                }
                            }
                            System.out.println("Fecha entrega: " + fechaEntrega);
                            DatosAsignacion asig = new DatosAsignacion(
                                    obj.getInt("assignmentid"), 
                                    obj2.getInt("attemptnumber"), 
                                    obj2.getLong("timecreated"), 
                                    obj2.getLong("timemodified"), 
                                    obj2.getInt("grader"), 
                                    fgrade,
                                    fechaCal,
                                    fechaEntrega
                            );
                            list.add(asig);
                            revisadas.put(""+obj2.getInt("userid"), true);
                        }
                    }
                }
            }
             
            String respuesta2 = con.Llamar("mod_assign_get_submissions", "assignmentids[0]=" + asignacionId);
            System.out.println("RESPUESTA SUBMISSIONS: " + respuesta2);
            JSONObject jsonObj2 = new JSONObject(respuesta2);
            JSONArray arr2 = jsonObj2.getJSONArray("assignments");
            for (int i = 0; i < arr2.length(); i++) {
                JSONObject obj = arr2.getJSONObject(i);
                if(obj.getInt("assignmentid") == asignacionId){
                    JSONArray arr3 = obj.getJSONArray("submissions");
                    for (int j = 0; j < arr3.length(); j++) {
                        JSONObject obj2 = arr3.getJSONObject(j);
                        if(obj2.toString().contains("\"status\":\"submitted\"")){
                            boolean revisada = revisadas.get(""+obj2.getInt("userid")) != null;
                            if(!revisada){
                                int fechaEntrega = 0;
                                for (Asignacion asig : asigs) {
                                    if(asig.getId() == asignacionId){
                                        fechaEntrega = asig.getFechaEntrega();
                                        break;
                                    }
                                }
                                
                                long fechaCal = (long) this.getFechaCalificada(cursoId, asignacionId).getRespuesta();
                                DatosAsignacion asig = new DatosAsignacion(
                                    obj.getInt("assignmentid"), 
                                    obj2.getInt("attemptnumber"), 
                                    obj2.getLong("timecreated"), 
                                    obj2.getLong("timemodified"), 
                                    profesorId, 
                                    -1.0f,
                                    fechaCal,
                                    fechaEntrega
                                );
                                
                                list.add(asig); //NO ENTREGADA
                            }
                        }
                    }
                }
            }
             
            DatosAsignacion[] _arr = new DatosAsignacion[list.size()]; 
            _arr = list.toArray(_arr); 
            
            return new Respuesta(_arr, "");
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
                            long fechaRevision = 0;
                            try{
                                fechaRevision = obj2.getInt("gradedategraded");
                                fechaRevision = obj2.getLong("gradedategraded");
                            }catch(Exception e){}
                            System.out.println("Fecha revision: " + fechaRevision);
                            return new Respuesta(fechaRevision, "");
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
    
    public Respuesta getFechaCalificada(int cursoId, int asignacionId) { //gradereport_user_get_grade_items
        try {
            ConexionMoodle con = new ConexionMoodle(0);
            
            String respuesta = con.Llamar("gradereport_user_get_grade_items", "courseid="+cursoId);
            System.out.println("getCalificacionAsignacion json: " + respuesta);
            
            JSONObject jsonObj = new JSONObject(respuesta);
            JSONArray arr = jsonObj.getJSONArray("usergrades");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                //System.out.println(obj.getInt("userid") + "," + hijoId);
                //if(obj.getInt("userid") == hijoId){
                    JSONArray arr2 = obj.getJSONArray("gradeitems");
                    //System.out.println(arr2);
                    for (int j = 0; j < arr2.length(); j++) {
                        JSONObject obj2 = arr2.getJSONObject(j);
                        //System.out.println(obj2.getInt("id") + "," + asignacionId);
                        if(obj2.getString("itemmodule").equals("assign") && obj2.getInt("iteminstance") == asignacionId){
                            long fechaRevision = 0;
                            try{
                                fechaRevision = obj2.getInt("gradedategraded");
                                fechaRevision = obj2.getLong("gradedategraded");
                            }catch(Exception e){}
                            System.out.println("Fecha revision: " + fechaRevision);
                            return new Respuesta(fechaRevision, "");
                        }
                    }
                //}
            }
            
            return new Respuesta(0, "");
        } catch (Exception ex) {
            Logger.getLogger(ServicioCalificaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener fecha.");
    }
}
