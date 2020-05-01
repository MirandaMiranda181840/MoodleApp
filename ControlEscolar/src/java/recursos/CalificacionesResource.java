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
import servicios.ServicioCalificaciones;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("calificaciones")
public class CalificacionesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalificacionesResource
     */
    public CalificacionesResource() {
    }

    /**
     * Retrieves representation of an instance of recursos.CalificacionesResource
     * @return an instance of objectosNegocio.Calificacion
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCalificaciones() {
        ServicioCalificaciones serv = ServicioCalificaciones.Instance();
        
        Respuesta califs = serv.getCalificaciones();
        
        return Response.ok((ArrayList<Calificacion>) califs.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCalificacionesAlumnoId(@QueryParam("alumnoId") int alumnoId) {
        ServicioCalificaciones serv = ServicioCalificaciones.Instance();
        
        Respuesta califs = serv.getCalificacionesAlumnoId(alumnoId);
        if(califs.getMensaje().length() > 0) {
            //hacer algo
        }
        
        return Response.ok((ArrayList<Calificacion>) califs.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    */
    /**
     * PUT method for updating or creating an instance of CalificacionesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Calificacion content) {
    }
}
