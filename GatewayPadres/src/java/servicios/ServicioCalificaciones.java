/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Email
 */
public class ServicioCalificaciones {
     public static class CalificacionesResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/ServicioCalificaciones/webresources";

        public CalificacionesResource_JerseyClient() {
            System.setProperty("javax.net.ssl.trustStore", "C:\\certs\\moodle\\keystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "moodle");
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calificaciones");
        }

        public <T> T getCalificaciones(Class<T> responseType, int asignacionId, int cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("asignacionId", asignacionId);
            resource = resource.queryParam("cursoId", cursoId);
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("detallesAsignacion");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }
}
