/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleapp.objetosNegocio;

/**
 *
 * @author crisb
 */
public class Asignacion {
    private int id, idcurso, numSubidas;
    private String nombre, introduccion;
    
   public Asignacion(){
       
   }
   public Asignacion(int id, int idcurso, String nombre, String introduccion, int numSubidas){
       this.id=id;
       this.idcurso=idcurso;
       this.nombre=nombre;
       this.introduccion=introduccion;
       this.numSubidas=numSubidas;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public int getNumSubidas() {
        return numSubidas;
    }

    public void setNumSubidas(int numSubidas) {
        this.numSubidas = numSubidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }
   
   
    
}
