/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexioncontrolescolar;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;
import objectosNegocio.Mensaje;

/**
 *
 * @author crisb
 */
public class ConexionControlEscolar {
 
    public Connection cn;
    public Statement st;
    
    private static ConexionControlEscolar conexionJDBC;
    
    private ConexionControlEscolar() {}
    
    public static ConexionControlEscolar Instance() {
        if (conexionJDBC== null) {
            conexionJDBC= new ConexionControlEscolar();
            conexionJDBC.conexion();
        }
        
        return conexionJDBC;
    }

    public void conexion(){
       try{
           // //------------------------------------configurar con credenciales propias-----------------------------------
            String usuario = "root"; //"root"
            String contra = ""; // ""
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/control_escolar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", usuario, contra);
            st = cn.createStatement();
            //JOptionPane.showMessageDialog(null, "Connected", "Moodle", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Connected CONTROL ESCOLAR.");
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Not connected", "Moodle", JOptionPane.ERROR_MESSAGE);
            System.out.println("Not connected." + e.getMessage());
        }  
    }
    public ArrayList<Mensaje> obtenerMensajesRelevantes(int userId){
       ArrayList <Mensaje> mensajes = new ArrayList <Mensaje>();
     
        try {
              String sql = "SELECT * FROM mensajes WHERE remitenteId="+userId+ " OR recipienteId="+userId;
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  mensajes.add(new Mensaje(
                          rs.getInt(2),rs.getInt(3),
                          rs.getString(4)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(ConexionControlEscolar.class.getName()).log(Level.SEVERE, null, ex);
          }
          return mensajes;
    }
    
    public void enviarMensaje(int remitenteId, int recipienteId, String contenido){
        try {
            String sql = "INSERT INTO mensajes (remitenteId, recipienteId, contenido) VALUES ("+remitenteId+","+recipienteId+","+contenido+")";
            st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionControlEscolar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static String salt = "salt_1234";
    
    public String registrarUsuario(String nombre, String apellido, String email, String password, String codigoAlumno) throws NoSuchAlgorithmException, Exception{
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((password+salt).getBytes());
            String passwordHash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        
            String token = java.util.UUID.randomUUID().toString();
            MessageDigest md2 = MessageDigest.getInstance("MD5");
            md2.update((token+salt).getBytes());
            String tokenHash = DatatypeConverter.printHexBinary(md2.digest()).toUpperCase();
            
            String sql = "INSERT INTO usuarios (nombre, apellido, email, passwordHash, tokenHash, codigoAlumno) VALUES ("+ nombre +"," + apellido + "," + email + "," + passwordHash + "," + tokenHash + "," + codigoAlumno + ")";
            st.executeQuery(sql);
            
            return token;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionControlEscolar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Fallo al registrar");
    }
    
    public String loguearUsuario(String email, String password) throws Exception {
         try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((password+salt).getBytes());
            String passwordHash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
        
            String token = java.util.UUID.randomUUID().toString();
            MessageDigest md2 = MessageDigest.getInstance("MD5");
            md2.update((token+salt).getBytes());
            String tokenHash = DatatypeConverter.printHexBinary(md2.digest()).toUpperCase();
            
            //buscar
            String sql = "SELECT * FROM usuarios WHERE email="+email+" AND passwordHash="+passwordHash;
            ResultSet rs  = st.executeQuery(sql);
            boolean encontrado = false;
            while(rs.next())
                encontrado = true;

            if(!encontrado)
                throw new Exception("Usuario y/o contraseña incorrectos");

            //loguear
            sql = "UPDATE usuarios SET tokenHash="+tokenHash+" WHERE email="+email+" AND passwordHash="+passwordHash;
            st.executeQuery(sql);
            
            return token;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         
        throw new Exception("Fallo al loguear");
    }
    
    public int obtenerIdDesdeToken(String token) throws NoSuchAlgorithmException, Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((token+salt).getBytes());
        String hash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();

        try {
            String sql = "SELECT id FROM usuarios WHERE tokenHash="+hash;
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
                return rs.getInt("id");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionControlEscolar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new Exception("Usuario no encontrado");
    }
}
