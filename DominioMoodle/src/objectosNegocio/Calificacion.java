/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objectosNegocio;

/**
 *
 * @author crisb
 */
public class Calificacion {
    private int id;
    private int iduser;
    private float calificacion;
    private int assignment;
    private String comentario;
    
   public Calificacion(){
       
   }
   
   public Calificacion(int id, int assignment, int iduser, float calificacion, String comentario){
       this.id=id;
       this.assignment=assignment;
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

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        return "Calificacion{" + "id=" + id + ", iduser=" + iduser + ", calificacion=" + calificacion + ", assignment=" + assignment + ", comentario=" + comentario + '}';
    }
   
}
