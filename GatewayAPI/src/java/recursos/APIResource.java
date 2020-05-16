/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import conexiones.Conexiones;
/**
 * REST Web Service
 *
 * @author Email
 */
@Path("api")
public class APIResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of APIResource
     */
    public APIResource() {
    }

    
    /**
     * Retrieves representation of an instance of recursos.APIResource
     * @param nombre
     * @param apellido
     * @param email
     * @param password
     * @param codigoAlumno
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("registrar")
    public Response registrarPadre(
            @QueryParam("nombre") String nombre, 
            @QueryParam("apellido") String apellido, 
            @QueryParam("email") String email, 
            @QueryParam("password") String password, 
            @QueryParam("codigoAlumno") String codigoAlumno) {
        String token = new Conexiones.UsuariosResource_JerseyClient().registrarPadre(String.class, password, codigoAlumno, apellido, nombre, email);
        return Response.ok(token, MediaType.TEXT_PLAIN).build();
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response loguearPadre(@QueryParam("email")String email, @QueryParam("password")String password) {
        String token = new Conexiones.UsuariosResource_JerseyClient().loguearPadre(String.class, password, email);
        
        return Response.ok(token, MediaType.TEXT_PLAIN).build();
    }
    
   
}
