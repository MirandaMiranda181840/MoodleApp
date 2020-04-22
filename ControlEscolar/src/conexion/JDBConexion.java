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

/**
 *
 * @author crisb
 */
public class JDBConexion {
 
    public Connection cn;
    public Statement st;
    
    private static JDBConexion conexionJDBC;
    
    private JDBConexion() {}
    
    public static JDBConexion Instance() {
        if (conexionJDBC== null) {
            conexionJDBC= new JDBConexion();
        }
        
        return conexionJDBC;
    }

    public void conexion(){
       try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodle?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            st = cn.createStatement();
            JOptionPane.showMessageDialog(null, "Connected", "Moodle", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Not connected", "Moodle", JOptionPane.ERROR_MESSAGE);
        }  
    }
    public ArrayList<Alumno> obtenerListaAlumnos(){
       ArrayList <Alumno> alumnos= new ArrayList <Alumno>();
        try {
              String sql = "SELECT * FROM mdl_user";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  alumnos.add(new Alumno(Integer.parseInt(rs.getString(1)),rs.getString(8),rs.getString(11),rs.getString(12),rs.getString(13)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return alumnos;
    }
    
    public ArrayList<Asignacion> obtenerListaAsignacion(){
           ArrayList <Asignacion> asignaciones= new ArrayList <Asignacion>();
        try {
              String sql = "SELECT * FROM mdl_assign";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  asignaciones.add(new Asignacion(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4)
                  , Integer.parseInt(rs.getString(7))));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return asignaciones;
    }
    public ArrayList <Calificacion> obtenerListaCalificaciones(){
           ArrayList <Calificacion> calificaciones= new ArrayList <Calificacion>();
        try {
              String sql = "SELECT * FROM mdl_grade_grades";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  calificaciones.add(new Calificacion(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(3)),
                  Float.parseFloat(rs.getString(9)),rs.getString(16)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return calificaciones;
    }
    
        public ArrayList <Curso> obtenerListaCursos(){
           ArrayList <Curso> cursos= new ArrayList <Curso>();
        try {
              String sql = "SELECT * FROM mdl_course";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  cursos.add(new Curso(Integer.parseInt(rs.getString(1)),rs.getString(4),
                          rs.getString(7)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return cursos;
    }
    
    
    
    
}
