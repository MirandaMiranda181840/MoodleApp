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
public class Mensaje {
    private int remitenteId;
    private int receptorId;
    private String contenido;

    public Mensaje() {
    }

    public Mensaje(int remitenteId, int receptorId, String contenido) {
        this.remitenteId = remitenteId;
        this.receptorId = receptorId;
        this.contenido = contenido;
    }

    public int getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(int remitenteId) {
        this.remitenteId = remitenteId;
    }

    public int getReceptorId() {
        return receptorId;
    }

    public void setReceptorId(int receptorId) {
        this.receptorId = receptorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "remitenteId=" + remitenteId + ", receptorId=" + receptorId + ", contenido=" + contenido + '}';
    }
    
    
}
