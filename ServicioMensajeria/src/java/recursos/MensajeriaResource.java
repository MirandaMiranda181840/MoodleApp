/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import conexioncontrolescolar.ConexionControlEscolar;
import conexiones.Conexion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objectosNegocio.Mensaje;
import objectosNegocio.Respuesta;
import servicios.ServicioMensajeria;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("mensajeria")
public class MensajeriaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MensajeriaResource
     */
    public MensajeriaResource() {
    }

    /**
     * Retrieves representation of an instance of recursos.MensajeriaResource
     * @param token
     * @param receptorId
     * @return an instance of objectosNegocio.Mensaje
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("obtenermensajes")
    public Response getMensajesRelevantes(@QueryParam("token") String token, @QueryParam("receptorId") int receptorId) {
        ServicioMensajeria serv = ServicioMensajeria.Instance();
        Conexion.UsuarioResource_JerseyClient con = new Conexion.UsuarioResource_JerseyClient();
        
        int userId = -1;
        try {
            userId = con.obtenerIdUsuario(Integer.class, token);
        } catch (Exception ex) {
            Logger.getLogger(MensajeriaResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(userId == -1) 
            return Response.serverError().build();
        
        Respuesta mensajes = serv.getMensajesRelevantes(userId, receptorId);
        
        ArrayList<Mensaje> list = (ArrayList<Mensaje>) mensajes.getRespuesta();
        
        Mensaje[] _arr = new Mensaje[list.size()]; 
        _arr = list.toArray(_arr); 
        
        return Response.ok(_arr, MediaType.APPLICATION_JSON).build();
    }
    
    /**
     *
     * @param token
     * @param recipienteId
     * @param mensaje
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("enviarmensaje")
    public Response enviarMensaje(@QueryParam("token") String token, @QueryParam("recipienteId") int recipienteId, @QueryParam("contenido") String mensaje) {
        System.out.println(mensaje);
        System.out.println(recipienteId);
        ServicioMensajeria serv = ServicioMensajeria.Instance();
        Conexion.UsuarioResource_JerseyClient con = new Conexion.UsuarioResource_JerseyClient();
        
        int remitenteId = -1;
        try {
            remitenteId = con.obtenerIdUsuario(Integer.class, token);
        } catch (Exception ex) {
            Logger.getLogger(MensajeriaResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(remitenteId==-1)
            return Response.serverError().build();
        
        serv.enviarMensaje(remitenteId, recipienteId, mensaje);
        
        return Response.ok(mensaje, MediaType.TEXT_PLAIN).build();
    }

}
