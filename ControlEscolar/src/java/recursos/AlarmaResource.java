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
import objectosNegocio.Alarma;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;
import servicios.ServicioAlarma;
//import servicios.ServicioCalificaciones;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("alarma")
public class AlarmaResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlarmaResource
     */
    public AlarmaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAlarmasAlumnoId(@QueryParam("alumnoId") int alumnoId) {
        ServicioAlarma serv = ServicioAlarma.Instance();
        
        Respuesta alarmas = serv.getAlarmas(alumnoId);
        
        return Response.ok((ArrayList<Alarma>) alarmas.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }   
}
