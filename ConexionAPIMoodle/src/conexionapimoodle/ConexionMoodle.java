/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionapimoodle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Email
 */
public class ConexionMoodle {
    String token;
    String dominio;
    
    public ConexionMoodle(int permiso) {
        if(permiso == 0){
            this.token = "6a184382969c536d063d0f909581ec36"; //token admin
        }else if(permiso == 1) {
            this.token = "6ea014ed3c695a1168c2b817d7317cd6"; //token creacion de usuarios
        }
    }
   
    public ConexionMoodle(String token) {
        this.token = token;
        this.dominio = "http://localhost/moodle";
    }
    
    public String Llamar(String funcion, String parametros) throws IOException {
        String formato = "&moodlewsrestformat=json";

        String serverurl = this.dominio + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=" + funcion + formato;

        HttpURLConnection con = (HttpURLConnection) new URL(serverurl).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Language", "en-US");
        con.setDoOutput(true);
        con.setUseCaches (false);
        con.setDoInput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parametros);
        wr.flush();
        wr.close();

        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while((line = rd.readLine()) != null){
            response.append(line);
            response.append('\r');
        }
        rd.close();

        return response.toString();
    }
    
    public String AutenticarUsuario(String username, String password) throws IOException {
        String formato = "&moodlewsrestformat=json";

        String serverurl = this.dominio + "/webservice/rest/server.php" + "?username=" + username + "&password=" + password + "&service=moodle_mobile_app";

        HttpURLConnection con = (HttpURLConnection) new URL(serverurl).openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Content-Language", "en-US");
        con.setDoOutput(true);
        con.setUseCaches (false);
        con.setDoInput(true);

        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("username=" + username + "&password=" + password);
        wr.flush();
        wr.close();

        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder response = new StringBuilder();
        while((line = rd.readLine()) != null){
            response.append(line);
            response.append('\r');
        }
        rd.close();

        return response.toString();
    }
}
