/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import conexiones.Conexion;
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
import objectosNegocio.Asignacion;
import objectosNegocio.DatosHijo;
import objectosNegocio.Respuesta;
import servicios.ServicioAlarma;

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
    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    @GET
    @Path("alarmas")
    public Response getAlarmas(@QueryParam("token")String token) {
        ServicioAlarma servicio= ServicioAlarma.Instance();

        Respuesta alarma = servicio.getAlarmas(token);

        return Response.ok((Alarma[]) alarma.getRespuesta(), MediaType.APPLICATION_JSON).build();
    }
}
