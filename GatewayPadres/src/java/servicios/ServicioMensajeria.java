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
public class ServicioMensajeria {

    public static class MensajeriaResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/ServicioMensajeria/webresources";

        public MensajeriaResource_JerseyClient() {
            System.setProperty("javax.net.ssl.trustStore", "C:\\certs\\moodle\\keystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "moodle");
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("mensajeria");
        }

        public <T> T enviarMensaje(Class<T> responseType, String contenido, String recipienteId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (contenido != null) {
                resource = resource.queryParam("contenido", contenido);
            }
            if (recipienteId != null) {
                resource = resource.queryParam("recipienteId", recipienteId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("enviarmensaje");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public <T> T getMensajesRelevantes(Class<T> responseType, String receptorId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("receptorId", receptorId);
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("obtenermensajes");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }
    
   

}
