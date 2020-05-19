/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import conexioncontrolescolar.ConexionControlEscolar;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objectosNegocio.DatosPadre;
import objectosNegocio.DatosProfesor;
import objectosNegocio.Respuesta;
import servicios.ServicioUsuarios;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("usuario")
public class UsuarioResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioResource() {
    }
/*
    @Path("registrar")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
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
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
    
    @Path("login")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loguearPadre(@QueryParam("email")String email, @QueryParam("password")String password) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        ConexionControlEscolar con = ConexionControlEscolar.Instance();
        
        Respuesta res = serv.loguearPadre(email, password); //token
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
    */
    
    @Path("registrar")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarPadre(
            @QueryParam("nombre")String nombre, 
            @QueryParam("apellido")String apellido, 
            @QueryParam("email")String email, 
            @QueryParam("password")String password) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.registrarUsuario(email, password, nombre, apellido); //token
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
    
    @Path("login")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loguearPadre(@QueryParam("email")String email, @QueryParam("password")String password) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.autenticarUsuario(email, password); //token
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok(res.getRespuesta(), MediaType.TEXT_PLAIN).build();
    }
    
    @Path("info")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerNombreUsuario(@QueryParam("token")String token, @QueryParam("email")String email) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.getNombreUsuario(token, email);
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        String fullname = (String) res.getRespuesta();
        System.out.println("NOMBRE: " + fullname);
        
        return Response.ok(fullname, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("userid")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerIdUsuario(@QueryParam("token")String token) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.getIdUsuario(token);
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        int id = (int) res.getRespuesta();
        System.out.println("ID: " + id);
        
        return Response.ok(id, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("obtenerhijo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerHijo(@QueryParam("token")String token) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.getHijo(token);
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok(res.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    
    @Path("obtenerprofesor")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerProfesor(@QueryParam("token")String token, @QueryParam("cursoId") int cursoId) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.getProfesor(token, cursoId);
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok((DatosProfesor) res.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    
    @Path("obtenerpadres")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerPadres(@QueryParam("token")String token) {
        ServicioUsuarios serv = ServicioUsuarios.Instance();
        
        Respuesta res = serv.getPadres(token);
        if(!res.getMensaje().isEmpty())
            return Response.status(500, res.getMensaje()).build();
        
        return Response.ok((DatosPadre[]) res.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
}
