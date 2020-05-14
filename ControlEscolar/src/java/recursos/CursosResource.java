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
import servicios.ServicioCalificaciones;
import objectosNegocio.Calificacion;
import objectosNegocio.Curso;
import objectosNegocio.Respuesta;

/**
 * REST Web Service
 *
 * @author Email
 */
@Path("calificaciones")
public class CursosResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CalificacionesResource
     */
    public CursosResource() {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getCursosAlumnoId(@QueryParam("alumnoId") String alumnoId) {
        //ServicioAlarma serv = ServicioAlarma.Instance();
        int id = Integer.parseInt(alumnoId);
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();
        Respuesta cursos= servicio.getCursosAlumno(id);
        //Respuesta alarmas = serv.getAlarmas(alumnoId);
        
        return Response.ok((ArrayList<Curso>) cursos.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
   
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(Calificacion content) {
    }
}
