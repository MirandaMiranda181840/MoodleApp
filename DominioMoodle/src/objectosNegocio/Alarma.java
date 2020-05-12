/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectosNegocio;

/**
 *
 * @author Email
 */
public class Alarma {
    int prioridad;
    String mensaje;

    public Alarma(int prioridad, String mensaje) {
        this.prioridad = prioridad;
        this.mensaje = mensaje;
    }

    public Alarma() {
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Alarma{" + "prioridad=" + prioridad + ", mensaje=" + mensaje + '}';
    }
    
    
}
