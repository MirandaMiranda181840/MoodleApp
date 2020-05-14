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
import objectosNegocio.Asignacion;
import objectosNegocio.Calificacion;
import objectosNegocio.Respuesta;
import servicios.ServicioCalificaciones;

/**
 *
 * @author crisb
 */
@Path("asignaciones")
public class AsignacionesResource {
     @Context
    private UriInfo context;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getAsignaconesCursoId(@QueryParam("cursoId") String cursoId) {
     
        int id = Integer.parseInt(cursoId);
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();
        Respuesta asignaciones= servicio.getAsignacionesCurso(id);
        
        return Response.ok((ArrayList<Asignacion>) asignaciones.getRespuesta(), MediaType.APPLICATION_JSON).build();
    } 
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Calificacion content) {
    }
}
