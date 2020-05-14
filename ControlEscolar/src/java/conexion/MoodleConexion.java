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
import objectosNegocio.Alumno;
import objectosNegocio.Asignacion;
import objectosNegocio.Calificacion;
import objectosNegocio.Curso;
import objectosNegocio.DetalleAsignacion;
import objectosNegocio.Prueba;

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
            String usuario = "moodleuser"; //moodleuser"; //"root" //"moodle-owner"
            String contra = "yourpassword"; //yourpassword"; // ""//"moodle123$%"
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
    
    public Alumno obtenerAlumnoId(int alumnoId){
        Alumno alumno= new Alumno();
        try {
              String sql = "SELECT * FROM mdl_user where mdl_user.id=" + alumnoId;
              ResultSet rs  = st.executeQuery(sql);
              while(rs.next()){
                  alumno.setId(Integer.parseInt(rs.getString(1)));
                  alumno.setUsername(rs.getString(8));
                  alumno.setNombre(rs.getString(11));
                  alumno.setApellido(rs.getString(12));
                  alumno.setEmail(rs.getString(13));
            }
            
          } catch (SQLException ex) {
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
  
          return alumno;
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
        
        
     public DetalleAsignacion obtenerDetalleAsignacion(String infoId){
        String ids[] =infoId.split(",");
        int idAsignacion = Integer.parseInt(ids[0]);
        int idCurso = Integer.parseInt(ids[1]);
        int idAlumno= Integer.parseInt(ids[2]);
         DetalleAsignacion detalleAsignacion=new DetalleAsignacion();
     detalleAsignacion.setIdAsignacion(idAsignacion);
        try {
            
            //obtiene descripcion y fecha de entrega
              String sql1 = "select intro,duedate from mdl_assign where mdl_assign.id="+ idAsignacion;
              ResultSet rs  = st.executeQuery(sql1);
              while(rs.next()){
                  detalleAsignacion.setDescripcion(rs.getString(1));
              detalleAsignacion.setFechaDeEntrega(rs.getString(2));       
              }
                  
             //obtiene si la tarea fue entregada
              String sql2= "select status from mdl_assign_submission where mdl_assign_submission.assignment="+ idAsignacion+ " and mdl_assign_submission.userid=" + idAlumno;
              ResultSet rs2= st.executeQuery(sql2);
              while(rs2.next()){
                  if(rs2.getString(1).equalsIgnoreCase("submitted")){
                  detalleAsignacion.setStatus(true);
              }else{
                  detalleAsignacion.setStatus(false);
              }
                  System.out.println(rs2.getString(1));
              }
              
              //obtiene la calificacion
              String sql3= "select grade from mdl_assign_grades where mdl_assign_grades.assignment="+idAsignacion+" and mdl_assign_grades.userid=" + idAlumno;
              ResultSet rs3=st.executeQuery(sql3);
              while(rs3.next()){
                 
                  detalleAsignacion.setCalificacion(Double.parseDouble(rs3.getString(1)));
              
              }
              
             
          } catch (SQLException ex) {
              Logger.getLogger(MoodleConexion.class.getName()).log(Level.SEVERE, null, ex);
          }
          return detalleAsignacion;
    }
     
     public Prueba getPrueba(){
         Prueba prueba= new Prueba("holi");
         return prueba;
     }
   
    
}
