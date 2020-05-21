
import conexion.RESTConexion;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import objectosNegocio.Asignacion;
import objectosNegocio.Curso;
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
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String token = "b3859261290be0ac64e2e003c59e8dd6";
        Curso[] res = RESTConexion.Instance().getCursos(Curso[].class, token);
        
        System.out.println(res[0]);
        
        int cursoId = res[0].getId();
        int hijoId = 56;
        
        Asignacion[] res2 = RESTConexion.Instance().getAsignaciones(Asignacion[].class, ""+cursoId, token);
        for (int i = 0; i < res2.length; i++) {
            Asignacion asignacion = res2[i];
            System.out.println(asignacion.toString());
        }
    }
    
}
