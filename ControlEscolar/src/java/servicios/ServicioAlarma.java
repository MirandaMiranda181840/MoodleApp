/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import conexion.MoodleConexion;
import java.util.ArrayList;
import objectosNegocio.Alarma;
import objectosNegocio.Calificacion;
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
    
    public Respuesta getAlarmas(int alumnoId) {
        MoodleConexion conn = MoodleConexion.Instance();
        
        //ArrayList<Calificacion> califsAlumno = new ArrayList<>();
        ArrayList<Alarma> alarmas = new ArrayList<>();
        
        double promedio = 0.0;
        int num = 0;
        ArrayList<Calificacion> califs = conn.obtenerListaCalificaciones();
        for (Calificacion calif : califs) {
            if(calif.getIduser() == alumnoId) {
                //califsAlumno.add(calif);
                promedio = promedio += calif.getCalificacion();
                num++;
            }
        }
        
        promedio /= num;
        
        if(promedio < 70.00) {
            alarmas.add(new Alarma(2, "!Las calificaciones de tu hijo han bajado!/Promedio: " + promedio));
        }
        
        return new Respuesta(alarmas, "");
    }
}
