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
import objectosNegocio.Calificacion;
import objectosNegocio.Curso;
import objectosNegocio.DatosAsignacion;
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
        Conexion.CalificacionesResource_JerseyClient con = new Conexion.CalificacionesResource_JerseyClient();
        
        ArrayList<Alarma> lista = new ArrayList<>();
        
        float acumulado = 0.0f;
        int contadas = 0;
        
        Curso[] cursos = con.getCursos(Curso[].class, token);
        for (Curso curso : cursos) {
            Asignacion[] asigs = con.getAsignaciones(Asignacion[].class, curso.getId(), token);
            
            for (Asignacion asig : asigs) {
                DatosAsignacion detalles = con.getCalificaciones(DatosAsignacion.class, asig.getId(), curso.getId(), token);
                if(detalles.getFechacalificada() > 0) {
                    acumulado += detalles.getGrade();
                    contadas++;
                }
            }
        }
        
        float promedio = acumulado/contadas;
        if(promedio < 70.00)
           lista.add(new Alarma(1, "Cuidado, tu hijo tiene bajo rendimiento./El promedio de tu hijo es: " + promedio));
        
        if(lista.isEmpty()){
            if(contadas>0){
                lista.add(new Alarma(0, "No se encontraron alarmas./El promedio de tu hijo es: " + promedio));
            }else{
                lista.add(new Alarma(0, "No se encontraron alarmas./-"));
            }
        }
        Alarma[] _arr = new Alarma[lista.size()]; 
        _arr = lista.toArray(_arr); 
        
        return new Respuesta(_arr, "");
    }
}
