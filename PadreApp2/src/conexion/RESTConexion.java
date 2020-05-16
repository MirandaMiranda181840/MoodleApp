/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author crisb
 */
public class RESTConexion {

    static class APIResource_JerseyClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080//webresources";

        public APIResource_JerseyClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("api");
        }

        public <T> T RegistrarPadre(Class<T> responseType, String password, String codigoAlumno, String apellido, String nombre, String email) throws ClientErrorException {
            WebTarget resource = webTarget;
            if (password != null) {
                resource = resource.queryParam("password", password);
            }
            if (codigoAlumno != null) {
                resource = resource.queryParam("codigoAlumno", codigoAlumno);
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
            resource = resource.path("reigstrar");
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

    //generar aqui


}
