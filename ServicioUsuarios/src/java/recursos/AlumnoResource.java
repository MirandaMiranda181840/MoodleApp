/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import objectosNegocio.Alumno;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;
import servicios.ServicioUsuarios;

/**
 *
 * @author crisb
 */
@Path("alumno")
public class AlumnoResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalificacionesResource
     */
    public AlumnoResource() {
    }
    
    /* no ocupamos este metodo
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAlumnoId(@QueryParam("alumnoId") String alumnoId) {
        int id = Integer.parseInt(alumnoId);
        ServicioUsuarios servicio= ServicioUsuarios.Instance();
        Respuesta cursos= servicio.getAlumnoId(id);
        
        return Response.ok((Alumno) cursos.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    */
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response registrarUsuario(
            @QueryParam("username")String username, 
            @QueryParam("password")String password, 
            @QueryParam("firstname")String firstname, 
            @QueryParam("lastname")String lastname, 
            @QueryParam("email")String email
    ) {
        Respuesta respuesta = ServicioUsuarios.Instance().registrarUsuario(username, password, firstname, lastname, email);
        if(respuesta.getMensaje().isEmpty()){
            return Response.ok((Alumno) respuesta.getRespuesta(), MediaType.APPLICATION_JSON).build();
        } 
        
        return Response.serverError().build();
    }
}
