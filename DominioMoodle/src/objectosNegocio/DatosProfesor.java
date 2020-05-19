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
public class DatosProfesor {
    int id;
    String fullname;
    
    public DatosProfesor(){}
    
    public DatosProfesor(int id, String fullname){
        this.id = id;
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "DatosProfesor{" + "id=" + id + ", fullname=" + fullname + '}';
    }
    
    
}
