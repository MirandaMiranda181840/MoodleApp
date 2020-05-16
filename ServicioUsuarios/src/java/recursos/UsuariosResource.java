/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import conexioncontrolescolar.ConexionControlEscolar;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import servicios.ServicioUsuarios;
import objectosNegocio.Alumno;
import objectosNegocio.Mensaje;
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
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("registrar")
    public Response registrarPadre(
            @QueryParam("nombre")String nombre, 
            @QueryParam("apellido")String apellido, 
            @QueryParam("email")String email, 
            @QueryParam("password")String password, 
            @QueryParam("codigoAlumno")String codigoAlumno) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        ConexionControlEscolar con = ConexionControlEscolar.Instance();
        
        Respuesta res = serv.registrarPadre(nombre, apellido, email, password, codigoAlumno); //token
        if(!res.getMensaje().isEmpty())
            return Response.serverError().build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public Response loguearPadre(@QueryParam("email")String email, @QueryParam("password")String password) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        ConexionControlEscolar con = ConexionControlEscolar.Instance();
        
        Respuesta res = serv.loguearPadre(email, password); //token
        if(!res.getMensaje().isEmpty())
            return Response.serverError().build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
}
