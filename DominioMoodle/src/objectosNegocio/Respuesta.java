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
public class Respuesta {
    private Object respuesta;
    private String mensaje;

    public Respuesta() {
        
    }

    public Respuesta(Object respuesta) {
        this.respuesta = respuesta;
    }

    public Respuesta(Object respuesta, String mensaje) {
        this.respuesta = respuesta;
        this.mensaje = mensaje;
    }

    public Object getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Object respuesta) {
        this.respuesta = respuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
