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
 * @author crisb
 */
public class RESTConexion {

    
    public static class CursosResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public CursosResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calificaciones");
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getCursosAlumnoId(Class<T> responseType, String alumnoId) throws ClientErrorException {
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

    public static class AsignacionesResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public AsignacionesResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("asignaciones");
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getAsignaconesCursoId(Class<T> responseType, String cursoId) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
        
    }

    public static class AlarmaResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public AlarmaResource_JerseyClient() {
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

    public static class AlumnoResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public AlumnoResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("alumno");
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public <T> T getAlumnoId(Class<T> responseType, String alumnoId) throws ClientErrorException {
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

    public static class DetalleAsignacionResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/ControlEscolar/webresources";

        public DetalleAsignacionResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("detalleAsignaciones");
        }

        public <T> T getDetalleAsignacionId(Class<T> responseType, String infoIds) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (infoIds != null) {
                resource = resource.queryParam("infoIds", infoIds);
            }
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void putJson(Object requestEntity) throws ClientErrorException {
            webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
        }

        public void close() {
            client.close();
        }
    }

    

    

    
            

    

   
    

}
