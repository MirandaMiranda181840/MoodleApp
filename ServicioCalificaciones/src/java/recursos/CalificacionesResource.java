/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import conexiones.Conexion;
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
import objectosNegocio.Curso;
import objectosNegocio.DatosAsignacion;
import objectosNegocio.DatosHijo;
import objectosNegocio.Respuesta;
import servicios.ServicioCalificaciones;

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

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("cursos")
    public Response getCursos(@QueryParam("token")String token) {
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();

        int hijoId = ((DatosHijo) new Conexion.UsuarioResource_JerseyClient().obtenerHijo(DatosHijo.class, token)).getId();

        Respuesta cursos = servicio.getCursos(hijoId);

        return Response.ok((Curso[]) cursos.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("asignaciones")
    public Response getAsignaciones(@QueryParam("token")String token, @QueryParam("cursoId")int cursoId) {
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();

        int hijoId = ((DatosHijo) new Conexion.UsuarioResource_JerseyClient().obtenerHijo(DatosHijo.class, token)).getId();

        Respuesta asigs = servicio.getAsignaciones(cursoId);

        return Response.ok((Asignacion[]) asigs.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("detallesAsignacion")
    public Response getCalificaciones(@QueryParam("token")String token, @QueryParam("cursoId") int cursoId, @QueryParam("asignacionId") int asignacionId) {
        ServicioCalificaciones servicio= ServicioCalificaciones.Instance();

        int hijoId = ((DatosHijo) new Conexion.UsuarioResource_JerseyClient().obtenerHijo(DatosHijo.class, token)).getId();

        Respuesta cali = servicio.getCalificacionAsignacion(hijoId, cursoId, asignacionId);

        return Response.ok((DatosAsignacion) cali.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
}
