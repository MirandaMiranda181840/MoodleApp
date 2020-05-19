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
public class DatosHijo {
    String fullname;
    int id;
    
    public DatosHijo(){
        
    }
    
    public DatosHijo(int id, String fullname){
        this.fullname = fullname;
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DatosHijo{" + "fullname=" + fullname + ", id=" + id + '}';
    }
    
    
}
