/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author crisb
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MoodleConexion m= MoodleConexion.Instance();
        System.out.println(m.getPrueba().toString());
        
    }
    
}
