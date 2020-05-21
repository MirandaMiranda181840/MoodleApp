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
    static GatewayResource_JerseyClient ins;
    
    private RESTConexion(){}
    
    public static GatewayResource_JerseyClient Instance(){
        if(ins==null)
            ins = new GatewayResource_JerseyClient();
        
        return ins;
    }
    
    public static class GatewayResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "https://localhost:8443/GatewayPadres/webresources";

        public GatewayResource_JerseyClient() {
            System.setProperty("javax.net.ssl.trustStore", "C:\\certs\\moodle\\keystore.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "moodle");
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("gateway");
        }

        public <T> T getCursos(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("cursos");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T registrarUsuario(Class<T> responseType, String password, String apellido, String nombre, String email) throws ClientErrorException {
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

        public <T> T obtenerIdUsuario(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("userid");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public <T> T getAlarmas(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("alarmas");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T getMensajesRelevantes(Class<T> responseType, String receptorId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (receptorId != null) {
                resource = resource.queryParam("receptorId", receptorId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("getmessages");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public <T> T loguearUsuario(Class<T> responseType, String password, String email) throws ClientErrorException {
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

        public <T> T obtenerHijo(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("hijo");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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

        public <T> T obtenerProfesor(Class<T> responseType, String cursoId, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (cursoId != null) {
                resource = resource.queryParam("cursoId", cursoId);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("profesorcurso");
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
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
            resource = resource.path("sendmessage");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
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

        public <T> T obtenerNombreUsuario(Class<T> responseType, String email, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (email != null) {
                resource = resource.queryParam("email", email);
            }
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("user_fullname");
            return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(responseType);
        }

        public <T> T obtenerPadres(Class<T> responseType, String token) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (token != null) {
                resource = resource.queryParam("token", token);
            }
            resource = resource.path("listpadres");
            System.out.println(resource.getUri().toString());
            return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
        }

        public void close() {
            client.close();
        }
    }

}
