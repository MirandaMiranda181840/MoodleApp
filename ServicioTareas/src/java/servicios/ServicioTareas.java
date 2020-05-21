/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexionapimoodle.ConexionMoodle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objectosNegocio.Asignacion;
import objectosNegocio.Curso;
import objectosNegocio.Respuesta;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Email
 */
public class ServicioTareas {
    private static ServicioTareas s;
    
    private ServicioTareas() {}
    
    public static ServicioTareas Instance() {
        if (s == null) {
            s = new ServicioTareas();
        }
        
        return s;
    }
    
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
            Logger.getLogger(ServicioTareas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ServicioTareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Respuesta(null, "Error al obtener asignaciones.");
    }
    
}
