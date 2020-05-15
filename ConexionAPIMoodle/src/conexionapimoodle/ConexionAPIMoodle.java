/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionapimoodle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Email
 */
public class ConexionAPIMoodle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //LOGIN http://localhost/moodle/login/token.php?username=usuario&password=contra&service=moodle_mobile_app
        try {
            System.out.println(
                    new ConexionMoodle("6a184382969c536d063d0f909581ec36").Llamar("core_course_get_courses", "")
            );
        } catch (IOException ex) {
            Logger.getLogger(ConexionAPIMoodle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
