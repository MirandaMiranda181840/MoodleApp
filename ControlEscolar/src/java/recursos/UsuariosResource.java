/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import moodleapp.ServicioUsuarios;
import objectosNegocio.Alumno;
import objectosNegocio.Respuesta;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("usuarios")
public class UsuariosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuariosResource
     */
    public UsuariosResource() {
    }

    /**
     * Retrieves representation of an instance of recursos.UsuariosResource
     * @return an instance of objectosNegocio.Alumno
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() { 
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        //cambiar despues para todo tipo de usuarios
        Respuesta usuarios = serv.getUsuarios();
        
        return Response.ok((ArrayList<Alumno>) usuarios.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    
    /**
     * PUT method for updating or creating an instance of UsuariosResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Alumno content) {
    }
}
