
import conexion.RESTConexion;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import objectosNegocio.Asignacion;
import objectosNegocio.Curso;
import objectosNegocio.DatosPadre;
import objectosNegocio.DatosProfesor;
import objectosNegocio.Respuesta;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Email
 */
public class pruebas2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String token = "6a184382969c536d063d0f909581ec36";
        /*
        DatosPadre[] res = RESTConexion.Instance().obtenerPadres(DatosPadre[].class, token);
        for (DatosPadre re : res) {
            System.out.println(re.toString());
        }
        */
        int cursoId = 4;
        
        DatosProfesor res2 = RESTConexion.Instance().obtenerProfesor(DatosProfesor.class, ""+cursoId, token);
        System.out.println(res2.toString());
    }
    
}
