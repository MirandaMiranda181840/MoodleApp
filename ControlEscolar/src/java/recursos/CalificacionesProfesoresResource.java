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
import objectosNegocio.Asignacion;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;
import servicios.ServicioCalificaciones;
import servicios.ServicioCalificacionesProfesor;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("calificacionesProfesores")
public class CalificacionesProfesoresResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalificacionesProfesoresResource
     */
    public CalificacionesProfesoresResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response buscarCalificacionDeAsignacion(@QueryParam("asignacionId") int asignacionId) {
        ServicioCalificacionesProfesor serv = ServicioCalificacionesProfesor.Instance();
        
        Respuesta califs = serv.buscarCalificacionDeAsignacion(asignacionId);
        
        return Response.ok((ArrayList<Calificacion>) califs.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
}
