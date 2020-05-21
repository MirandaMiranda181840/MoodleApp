/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexiones.Conexion;
import java.util.ArrayList;
import objectosNegocio.Alarma;
import objectosNegocio.Asignacion;
import objectosNegocio.Curso;
import objectosNegocio.DatosAsignacion;
import objectosNegocio.DatosProfesor;
import objectosNegocio.Respuesta;

/**
 *
 * @author Email
 */
public class ServicioAlarma {
private static ServicioAlarma s;
    
    private ServicioAlarma() {}
    
    public static ServicioAlarma Instance() {
        if (s == null) {
            s = new ServicioAlarma();
        }
        
        return s;
    }
    
    public Respuesta getAlarmas(String token) {
        Conexion.TareasResource_JerseyClient conTareas = new Conexion.TareasResource_JerseyClient();
        Conexion.CalificacionesResource_JerseyClient con = new Conexion.CalificacionesResource_JerseyClient();
        Conexion.UsuarioResource_JerseyClient conUsuarios = new Conexion.UsuarioResource_JerseyClient();
        
        ArrayList<Alarma> lista = new ArrayList<>();
        
        int id = conUsuarios.obtenerIdUsuario(Integer.class, token);

        long hoy = System.currentTimeMillis() / 1000L;
        float acumulado = 0.0f;
        int contadas = 0;
        
        boolean padre = true;
        
        Curso[] cursos = conTareas.getCursos(Curso[].class, token);
        for (Curso curso : cursos) {
            Asignacion[] asigs = conTareas.getAsignaciones(Asignacion[].class, ""+curso.getId(), token);
            if(asigs.length > 0){
                System.out.println("itera");
                
                DatosProfesor prof = null;
                try{ 
                    prof = conUsuarios.obtenerProfesor(DatosProfesor.class, ""+curso.getId(), token);
                }catch(Exception e){}
                
                if(prof!= null && prof.getId() == id){ //es profesor
                    padre = false;
                    System.out.println("es profesor");
                    for (Asignacion asig : asigs) {
                        System.out.println("PROFESOR: " + asig.toString());
                        DatosAsignacion[] detalles = null;
                        try{
                            detalles = con.getCalificacionesProfesor(DatosAsignacion[].class, ""+curso.getId(), ""+asig.getId(), token);
                            System.out.println("PROFESOR DETALLES: "+detalles.toString());
                        }catch(Exception e){}
                        
                        if(detalles != null){
                            boolean alarmado = false;
                            int subidasCalificadas = 0;
                            int total = 0;
                            for (DatosAsignacion detalle : detalles) {
                                System.out.println(detalle.toString());
                                System.out.println("ENTREGA: " + detalle.getFechaentrega());
                                System.out.println("HOY: " + hoy);
                                if(detalle.getFechaentrega() < hoy && detalle.getGrade() <= 0.0) {
                                    alarmado = true;
                                    lista.add(new Alarma(2, "Se te ha pasado la fecha para revisar algunas de las asignaciones " + asig.getNombre() + " del curso " + curso.getNombre() + "/-"));
                                }
                                if(detalle.getGrade() > 0.0){
                                    subidasCalificadas++;
                                }
                                total++;
                            }
                            if(total > 0 && subidasCalificadas < total && alarmado == false){
                                lista.add(new Alarma(1, "Te faltan tareas por revisar de la asignacion: " + asig.getNombre() + " del curso " + curso.getNombre() + "/-"));
                            }
                            System.out.println("SUBIDAS:: " + total);
                            System.out.println("SUBIDAS CALIFICADAS: " + subidasCalificadas);
                        }
                    }
                }
                else{
                    System.out.println("es padre");
                    for (Asignacion asig : asigs) {
                        try{
                            DatosAsignacion detalles = con.getCalificaciones(DatosAsignacion.class, ""+asig.getId(), ""+curso.getId(), token);

                            if(detalles.getFechacalificada() > 0) {
                                acumulado += detalles.getGrade();
                                contadas++;
                            }
                        }catch(Exception e){}
                    }
                }
            }
        }

        float promedio = acumulado/contadas;
        if(padre && contadas > 0 && promedio < 70.00)
           lista.add(new Alarma(1, "Cuidado, tu hijo tiene bajo rendimiento./El promedio de tu hijo es: " + promedio));

        if(lista.isEmpty()){
            if(padre && contadas>0){
                lista.add(new Alarma(0, "No se encontraron alarmas./El promedio de tu hijo es: " + promedio));
            }else{
                lista.add(new Alarma(0, "No se encontraron alarmas./-"));
            }
        }
        
        Alarma[] _arr = new Alarma[lista.size()]; 
        _arr = lista.toArray(_arr); 
        System.out.println("listo");
        return new Respuesta(_arr, "");
    }
}
