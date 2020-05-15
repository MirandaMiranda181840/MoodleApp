/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import objectosNegocio.Curso;
import objectosNegocio.Respuesta;
import org.json.JSONException;
import org.json.JSONObject;
import servicios.ServicioCalificaciones;

/**
 *
 * @author crisb
 */
@Path("asignaciones")
public class AsignacionesResource {
    @Context
    private UriInfo context;
    /*
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
    }*/
    
    public Response getAsignaciones(@QueryParam("token") String token) {
        ServicioCalificaciones servicio = ServicioCalificaciones.Instance();
        Respuesta res = servicio.getAsignaciones(token);
        
        ArrayList<Asignacion> asignaciones = new ArrayList<>();
        
        JSONObject jsonObject = new JSONObject(res.getRespuesta());
        
        Iterator<String> keys = null;
        try {
            keys = jsonObject.getJSONObject("courses").keys();
        } catch (JSONException ex) {
            Logger.getLogger(AsignacionesResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(keys != null){
            while(keys.hasNext()) {
                String key = keys.next(); //curso[id,fullname,shortname,timemodified,assignments]
                try {
                    if (jsonObject.getJSONObject("courses").get(key) instanceof JSONObject) {
                        JSONObject kCurso = (JSONObject) jsonObject.get(key);
                        //int id, int idcurso, String nombre, String introduccion, int numSubidas
                        
                        JSONObject kAsigs = kCurso.getJSONObject("assignments");
                        Iterator<String> keys2 = kAsigs.keys();
                        
                        while(keys2.hasNext()) {
                            String key2 = keys2.next();
                            
                            JSONObject kAsig = (JSONObject) kAsigs.get(key2);
                            asignaciones.add(new Asignacion(kAsig.getInt("id"), kCurso.getInt("id"), kAsig.getString("name"), kAsig.getString("intro"), kAsig.getInt("nosubmissions")));
                        }
                    }
                } catch (JSONException ex) {
                    Logger.getLogger(AsignacionesResource.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return Response.ok(asignaciones, MediaType.APPLICATION_JSON).build();
        }
        
        return Response.serverError().build();
    } 
}
