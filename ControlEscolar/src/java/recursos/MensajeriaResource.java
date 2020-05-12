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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import objectosNegocio.Calificacion;
import objectosNegocio.Mensaje;
import objectosNegocio.Respuesta;
import servicios.ServicioCalificaciones;
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
     * @param userId
     * @return an instance of objectosNegocio.Mensaje
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getMensajesRelevantes(@QueryParam("userId") int userId) {
        ServicioMensajeria serv = ServicioMensajeria.Instance();
        
        Respuesta mensajes = serv.getMensajesRelevantes(userId);
        
        return Response.ok((ArrayList<Mensaje>) mensajes.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
}
