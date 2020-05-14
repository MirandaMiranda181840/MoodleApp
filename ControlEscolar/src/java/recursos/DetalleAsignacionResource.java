/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.ArrayList;
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
import objectosNegocio.Calificacion;
import objectosNegocio.DetalleAsignacion;
import objectosNegocio.Respuesta;
import servicios.ServicioCalificaciones;

/**
 *
 * @author crisb
 */
@Path("detalleAsignaciones")
public class DetalleAsignacionResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalificacionesResource
     */
    public DetalleAsignacionResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getDetalleAsignacionId(@QueryParam("infoIds") String infoIds) {
        
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();
        Respuesta detalleAsignacion= servicio.getDetalleAsignacion(infoIds);
        //Respuesta alarmas = serv.getAlarmas(alumnoId);
        
        return Response.ok((DetalleAsignacion) detalleAsignacion.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
   
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Calificacion content) {
    }
}
