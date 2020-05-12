/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Email
 */
public class RESTConexion {
    /*private static RESTConexion ins;
    
    private RESTConexion() {}
    
    public static RESTConexion Instance() {
        if (ins == null) {
            ins = new RESTConexion();
        }
        
        return ins;
    }
    */
    
    
    public static class CalificacionesResource_Client {

        private WebTarget webTarget;
        private Client client;
         //------------------------------------configurar con puerto propio-----------------------------------
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public CalificacionesResource_Client() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calificaciones");
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getCalificaciones(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T getCalificacionesAlumnoId(Class<T> responseType, int alumnoId) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("alumnoId", alumnoId);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    public static class UsuariosResource_Client {

        private WebTarget webTarget;
        private Client client;
        //------------------------------------configurar con puerto propio-----------------------------------
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public UsuariosResource_Client() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("usuarios");
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getUsuarios(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    public static class MensajeriaResource_Client {

        private WebTarget webTarget;
        private Client client;
         //------------------------------------configurar con puerto propio-----------------------------------
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public MensajeriaResource_Client() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("mensajeria");
        }

        public <T> T getMensajesRelevantes(Class<T> responseType, String userId) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("userId", userId);
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    public static class AlarmaResource_Client {

        private WebTarget webTarget;
        private Client client;
         //------------------------------------configurar con puerto propio-----------------------------------
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public AlarmaResource_Client() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("alarma");
        }

        public <T> T getAlarmasAlumnoId(Class<T> responseType, String alumnoId) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (alumnoId != null) {
                resource = resource.queryParam("alumnoId", alumnoId);
            }
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    public static class CalificacionesProfesoresResource_Client {

        private WebTarget webTarget;
        private Client client;
         //------------------------------------configurar con puerto propio-----------------------------------
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public CalificacionesProfesoresResource_Client() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calificacionesProfesores");
        }

        public <T> T buscarCalificacionDeAsignacion(Class<T> responseType, String asignacionId) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (asignacionId != null) {
                resource = resource.queryParam("asignacionId", asignacionId);
            }
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

}
