/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleapp;

import conexion.JDBConexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import moodleapp.objetosNegocio.Alumno;

/**
 * Clase que muestra los datos de los alumnos.
 * @author Miranda, 
 * @author Cristina, 
 * @author Fernando, 
 * @author Guillermo.
 * 
 */
public class dlgAlumnos extends javax.swing.JDialog {

    //Variable que crea la conexión con la BD.
     JDBConexion conexion;
    ArrayList <Alumno> alumnos;
    
    /**
     * Creates new form dlgAlumnos.
     */
    public dlgAlumnos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Se centra la ventana a el medio de la pantalla
        setLocationRelativeTo(null);
        //Llama al método para conectarse a la BD.
         conexion=JDBConexion.Instance();
         alumnos= new ArrayList <Alumno>();
        //Llama al método que muestra los datos en la tabla.
        mostrartabla();
    }


    
    /**
     * Método que obtiene los datos de la base de datos y los muestra en la tabla.
     */
    void mostrartabla(){
        //Se crea la tabla.
        DefaultTableModel modelo = new DefaultTableModel();
        //Se añaden las columnas según el tipo de dato.
        modelo.addColumn("ID");
        modelo.addColumn("Username");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("email");
        //A la tabla de la interfaz se le da el valor de la creada anteriormente.
        tablaDatos.setModel(modelo);
        
          try {
            Alumno alumno;
           String datos[] = new String[5];
           //obtiene la lista de alumnos de la bdd
            alumnos=conexion.obtenerListaAlumnos();
            for (int i = 0; i < alumnos.size(); i++) {
                //llena los campos de la tabla con los elementos obtenidos de la lista
                alumno= alumnos.get(i);
                datos[0]=Integer.toString(alumno.getId());
                datos[1]=alumno.getUsername();
                datos[2]=alumno.getNombre();
                datos[3]=alumno.getApellido();
                datos[4]=alumno.getEmail();
                if(Integer.parseInt(datos[0]) > 3){
                    modelo.addRow(datos);
                }
            }   
                tablaDatos.setModel(modelo);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No hay datos en esta tabla", "No hay datos", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(dlgAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel1.setText("Alumnos");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaDatos);

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(329, 329, 329))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Botón que ayuda a regresar a la pantalla principal.
     * @param evt Evento que se lleva a cabo.
     */
    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        FrmPrincipal principal = new FrmPrincipal();
        principal.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRegresarActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
