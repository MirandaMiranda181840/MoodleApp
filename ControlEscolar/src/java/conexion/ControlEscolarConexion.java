/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objectosNegocio.Alumno;
import objectosNegocio.Asignacion;
import objectosNegocio.Calificacion;
import objectosNegocio.Curso;
import objectosNegocio.Mensaje;

/**
 *
 * @author crisb
 */
public class ControlEscolarConexion {
 
    public Connection cn;
    public Statement st;
    
    private static ControlEscolarConexion conexionJDBC;
    
    private ControlEscolarConexion() {}
    
    public static ControlEscolarConexion Instance() {
        if (conexionJDBC== null) {
            conexionJDBC= new ControlEscolarConexion();
            conexionJDBC.conexion();
        }
        
        return conexionJDBC;
    }

    public void conexion(){
       try{
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
              Logger.getLogger(ControlEscolarConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return mensajes;
    }
    
}
