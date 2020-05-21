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

    
    public static class TareasResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/ServicioTareas/webresources";

        public TareasResource_JerseyClient() {
            System.setProperty("javax.net.ssl.trustStore", "C:\\certs\\moodle\\keystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "moodle");
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("tareas");
        }

        public <T> T getCursos(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("cursos");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T getAsignaciones(Class<T> responseType, String cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("asignaciones");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

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

        public <T> T obtenerProfesor(Class<T> responseType, String cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("obtenerprofesor");
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

        public <T> T obtenerIdUsuario(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("userid");
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

        public <T> T obtenerPadres(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("obtenerpadres");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

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

        public <T> T getCalificacionesProfesor(Class<T> responseType, String cursoId, String asignacionId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            if (asignacionId != null) {
                resource = resource.queryParam("asignacionId", asignacionId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("detallesAsignacionProfesor");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T getCalificaciones(Class<T> responseType, String cursoId, String asignacionId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            if (asignacionId != null) {
                resource = resource.queryParam("asignacionId", asignacionId);
            }
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
