/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author Email
 */
public class Conexion {
    
    public static class UsuarioResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/ServicioUsuarios/webresources";

        public UsuarioResource_JerseyClient() {
            System.setProperty("javax.net.ssl.trustStore", "C:\\certs\\moodle\\keystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "moodle");
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("usuario");
        }

        public <T> T obtenerHijo(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("obtenerhijo");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T registrarPadre(Class<T> responseType, String password, String apellido, String nombre, String email) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (password != null) {
                resource = resource.queryParam("password", password);
            }
            if (apellido != null) {
                resource = resource.queryParam("apellido", apellido);
            }
            if (nombre != null) {
                resource = resource.queryParam("nombre", nombre);
            }
            if (email != null) {
                resource = resource.queryParam("email", email);
            }
            resource = resource.path("registrar");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public <T> T obtenerNombreUsuario(Class<T> responseType, String email, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (email != null) {
                resource = resource.queryParam("email", email);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("info");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }
        
        public <T> T obtenerIdUsuario(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("userid");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public <T> T loguearPadre(Class<T> responseType, String password, String email) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (password != null) {
                resource = resource.queryParam("password", password);
            }
            if (email != null) {
                resource = resource.queryParam("email", email);
            }
            resource = resource.path("login");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    
}
