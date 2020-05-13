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
public class MoodleConexion {
 
    public Connection cn;
    public Statement st;
    
    private static MoodleConexion conexionJDBC;
    
    private MoodleConexion() {}
    
    public static MoodleConexion Instance() {
        if (conexionJDBC== null) {
            conexionJDBC= new MoodleConexion();
            conexionJDBC.conexion();
        }
        
        return conexionJDBC;
    }

    public void conexion(){
       try{
            //------------------------------------configurar con puerto propio-----------------------------------
            String usuario = "moodle-owner"; //moodleuser"; //"root"
            String contra = "moodle123$%"; //yourpassword"; // ""
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodle?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", usuario, contra);
            st = cn.createStatement();
            //JOptionPane.showMessageDialog(null, "Connected", "Moodle", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Connected MOODLE.");
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, "Not connected", "Moodle", JOptionPane.ERROR_MESSAGE);
            System.out.println("Not connected." + e.getMessage());
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
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return alumnos;
    }
    
    public ArrayList<Asignacion> obtenerListaAsignacionesCurso(int idCurso){
           ArrayList <Asignacion> asignaciones= new ArrayList <Asignacion>();
        try {
              String sql = "select * from mdl_assign inner join mdl_course on mdl_assign.course=mdl_course.id where mdl_course.id="+idCurso+"";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  asignaciones.add(new Asignacion(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4)
                  , Integer.parseInt(rs.getString(7))));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return asignaciones;
    }
    public ArrayList <Calificacion> obtenerListaCalificaciones(){
           ArrayList <Calificacion> calificaciones= new ArrayList <Calificacion>();
        try {
              String sql = "SELECT * FROM mdl_grade_grades";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  calificaciones.add(new Calificacion(rs.getInt(1), rs.getInt(2),rs.getInt(3),
                  rs.getFloat(9),rs.getString(16)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return calificaciones;
    }
    
    
    /*
    Obtiene la lista de cursos en los que esta inscrito un alumno dado su id
    */
        public ArrayList <Curso> obtenerListaCursosAlumno(int idAlumno){
           ArrayList <Curso> cursos= new ArrayList <Curso>();
        try {
              String sql = "select * from mdl_course inner join mdl_context on mdl_course.id=mdl_context.instanceid inner join mdl_role_assignments on mdl_context.id=mdl_role_assignments.contextid where mdl_role_assignments.userid="+idAlumno+ " group by mdl_course.fullname";
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  cursos.add(new Curso(Integer.parseInt(rs.getString(1)),rs.getString(4),
                          rs.getString(7)));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return cursos;
    }
    
   
    
}
