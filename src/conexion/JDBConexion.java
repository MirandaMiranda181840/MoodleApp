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
import moodleapp.objetosNegocio.Alumno;
import moodleapp.objetosNegocio.Asignacion;
import moodleapp.objetosNegocio.Calificacion;
import moodleapp.objetosNegocio.Curso;

/**
 *
 * @author crisb
 */
public class JDBConexion {
 //clase que se encarga de conectar a la base de datos de moodle y obtener los datos necesarios
    public Connection cn;
    public Statement st;
    
    private static JDBConexion conexionJDBC;
    
    private JDBConexion() {}
    
    //uso de patron singleton para tener una sola instancia de esta clase
    public static JDBConexion Instance() {
        if (conexionJDBC== null) {
            conexionJDBC= new JDBConexion();
        }
        
        return conexionJDBC;
    }
//metodo que realiza la conexion con la base de datos
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
    
    //metodo para obtener la lista de alumnos de la bdd
    public ArrayList<Alumno> obtenerListaAlumnos(){
        //lista que contiene los alumnos 
       ArrayList <Alumno> alumnos= new ArrayList <Alumno>();
        try {
            //consulta de la tabla que se necesita para la lista de alumnos
              String sql = "SELECT * FROM mdl_user";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  //se agrega los alumnos obtenidos de la bdd a la lista
                  alumnos.add(new Alumno(Integer.parseInt(rs.getString(1)),rs.getString(8),rs.getString(11),rs.getString(12),rs.getString(13)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return alumnos;
    }
    
    //metodo para obtener la lista de asignaciones de la  bdd
    public ArrayList<Asignacion> obtenerListaAsignacion(){
           ArrayList <Asignacion> asignaciones= new ArrayList <Asignacion>();
        try {
              String sql = "SELECT * FROM mdl_assign";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  //de la tabla solo se seleccionan los campos necesarios para crear un objeto de tipo alumno
                  asignaciones.add(new Asignacion(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4)
                  , Integer.parseInt(rs.getString(7))));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(JDBConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return asignaciones;
    }
    //metodo para obtener la lista de calificaciones de la bdd
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
    
    //metodo para obtener la lista de cursos de la bdd
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
