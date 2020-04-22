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
public class Calificacion {
    private int id, iduser;
    private float calificacion;
    private String comentario;
    
   public Calificacion(){
       
   }
   public Calificacion(int id, int iduser, float calificacion , String comentario){
       this.id=id;
       this.iduser=iduser;
       this.calificacion=calificacion;
       this.comentario=comentario;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
   
   
}
