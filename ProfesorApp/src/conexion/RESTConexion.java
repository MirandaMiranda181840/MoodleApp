/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author crisb
 */
public class RESTConexion {

    public static class UsuarioResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:64550/ServicioUsuarios/webresources";

        public UsuarioResource_JerseyClient() {
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
        
        public <T> T obtenerProfesor(Class<T> responseType, int cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("cursoId", cursoId);
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("obtenerprofesor");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
        private static final String BASE_URI = "http://localhost:64550/ServicioCalificaciones/webresources";

        public CalificacionesResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("calificaciones");
        }

        public <T> T getCursos(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("cursos");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T getAsignaciones(Class<T> responseType, int cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.queryParam("cursoId", cursoId);
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("asignaciones");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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

    public static class AlarmaResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:64550/ServicioAlarma/webresources";

        public AlarmaResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("alarma");
        }

        public <T> T getAlarmas(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("alarmas");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

    public static class MensajeriaResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:64550/ServicioMensajeria/webresources";

        public MensajeriaResource_JerseyClient() {
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

        public <T> T getMensajesRelevantes(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
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
