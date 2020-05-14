/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.util.ArrayList;
import objectosNegocio.Alumno;
import objectosNegocio.Asignacion;
import objectosNegocio.Curso;
import objectosNegocio.DetalleAsignacion;

/**
 *
 * @author crisb
 */
public class RestTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//MoodleConexion moodle =MoodleConexion.Instance();
ArrayList <Curso> cursos = new ArrayList<Curso>();
        RESTConexion.CursosResource_JerseyClient conexionCursos = new RESTConexion.CursosResource_JerseyClient();
        Curso[] cursos2= conexionCursos.getCursosAlumnoId(Curso[].class, "5");
        for(Curso curso: cursos2){
            cursos.add(curso);
        }
        System.out.println(cursos.toString());
        
        
        RESTConexion.AsignacionesResource_JerseyClient conexionAsignaciones= new RESTConexion.AsignacionesResource_JerseyClient();
        
        ArrayList <Asignacion> asignaciones = new ArrayList <Asignacion>();
        Asignacion [] asignaciones2 = conexionAsignaciones.getAsignaconesCursoId(Asignacion[].class, "2");
        for(Asignacion asignacion: asignaciones2){
            asignaciones.add(asignacion);
        }
        System.out.println(asignaciones.toString());
        
        
        RESTConexion.DetalleAsignacionResource_JerseyClient detalle = new RESTConexion.DetalleAsignacionResource_JerseyClient();
        DetalleAsignacion d= detalle.getDetalleAsignacionId(DetalleAsignacion.class, "2,2,5");
        System.out.println(d.toString());
        
        RESTConexion.AlumnoResource_JerseyClient alumno = new RESTConexion.AlumnoResource_JerseyClient();
        Alumno a= alumno.getAlumnoId(Alumno.class, "5");
        System.out.println(a.toString());
        
       
    }

    
    
    
}
