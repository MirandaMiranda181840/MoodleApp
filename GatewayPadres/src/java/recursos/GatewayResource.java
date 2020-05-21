/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objectosNegocio.*;
import servicios.ServicioAlarma;
import servicios.ServicioCalificaciones;
import servicios.ServicioMensajeria;
import servicios.ServicioTareas;
import servicios.ServicioUsuarios;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("gateway")
public class GatewayResource {
    
    @Context
    private UriInfo context;
    private ServicioUsuarios.UsuarioResource_JerseyClient u;
    private ServicioCalificaciones.CalificacionesResource_JerseyClient c;
    private ServicioAlarma.AlarmaResource_JerseyClient a;
    private ServicioMensajeria.MensajeriaResource_JerseyClient m;
    private ServicioTareas.TareasResource_JerseyClient t;

    public GatewayResource() {
        u = new ServicioUsuarios.UsuarioResource_JerseyClient();
        c = new ServicioCalificaciones.CalificacionesResource_JerseyClient();
        a = new ServicioAlarma.AlarmaResource_JerseyClient();
        m = new ServicioMensajeria.MensajeriaResource_JerseyClient();
        t = new ServicioTareas.TareasResource_JerseyClient();
    }

    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("alarmas")
    public Response getAlarmas(@QueryParam("token")String token) {
        Alarma[] alarmas;
        try{
            alarmas = a.getAlarmas(Alarma[].class, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }

        return Response.ok(alarmas, MediaType.APPLICATION_JSON).build();
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("detallesAsignacion")
    public Response getCalificaciones(@QueryParam("token")String token, @QueryParam("cursoId") int cursoId, @QueryParam("asignacionId") int asignacionId) {
        DatosAsignacion cali;
        try{
            cali = c.getCalificaciones(DatosAsignacion.class, asignacionId, cursoId, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }

        return Response.ok(cali, MediaType.APPLICATION_JSON).build();
    }
    
    
    @Path("registrar")
    @GET //post
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(
            @QueryParam("nombre")String nombre, 
            @QueryParam("apellido")String apellido, 
            @QueryParam("email")String email, 
            @QueryParam("password")String password) {
        
        String token;
        try {
            token = u.registrarPadre(String.class, password, apellido, nombre, email);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(token, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("login")
    @GET //post
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loguearUsuario(@QueryParam("email")String email, @QueryParam("password")String password) {
        String token;
        try {
            token = u.loguearPadre(String.class, password, email);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(token, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("user_fullname")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerNombreUsuario(@QueryParam("token")String token, @QueryParam("email")String email) {
        String fullname;
        try {
            fullname = u.obtenerNombreUsuario(String.class, email, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(fullname, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("userid")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerIdUsuario(@QueryParam("token")String token) {
        int id;
        try {
            id = u.obtenerIdUsuario(Integer.class, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(id, MediaType.TEXT_PLAIN).build();
    }
    
    @Path("hijo")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerHijo(@QueryParam("token")String token) {
        DatosHijo hijo;
        try {
            hijo = u.obtenerHijo(DatosHijo.class, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(hijo, MediaType.APPLICATION_JSON).build();
    }
    
    @Path("profesorcurso")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerProfesor(@QueryParam("token")String token, @QueryParam("cursoId") int cursoId) {
        DatosProfesor prof;
        try {
            prof = u.obtenerProfesor(DatosProfesor.class, cursoId, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(prof, MediaType.APPLICATION_JSON).build();
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("cursos")
    public Response getCursos(@QueryParam("token")String token) {
        Curso[] cursos;
        try {
            cursos = t.getCursos(Curso[].class, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(cursos, MediaType.APPLICATION_JSON).build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("asignaciones")
    public Response getAsignaciones(@QueryParam("token")String token, @QueryParam("cursoId")int cursoId) {
        Asignacion[] asigs;
        try {
            asigs = t.getAsignaciones(Asignacion[].class, ""+cursoId, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(asigs, MediaType.APPLICATION_JSON).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("getmessages")
    public Response getMensajesRelevantes(@QueryParam("token") String token, @QueryParam("receptorId") int receptorId) {
        Mensaje[] mensajes;
        try{
            mensajes = m.getMensajesRelevantes(Mensaje[].class, ""+receptorId, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(mensajes, MediaType.APPLICATION_JSON).build();
    }
  
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("sendmessage")
    public Response enviarMensaje(@QueryParam("token") String token, @QueryParam("recipienteId") int recipienteId, @QueryParam("contenido") String mensaje) {
        try{
            m.enviarMensaje(String.class, mensaje, ""+recipienteId, token);
        }catch(ClientErrorException e){
            return Response.status(500/*, e.getMessage()*/).build();
        }
        
        return Response.ok(mensaje, MediaType.TEXT_PLAIN).build();
    }

}
