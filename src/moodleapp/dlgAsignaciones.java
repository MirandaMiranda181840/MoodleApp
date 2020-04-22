/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moodleapp;

import conexion.JDBConexion;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import moodleapp.objetosNegocio.Asignacion;

/**
 * Clase que muestra los datos de las asignaciones.
 * @author Miranda, 
 * @author Cristina, 
 * @author Fernando, 
 * @author Guillermo.
 * 
 */
public class dlgAsignaciones extends javax.swing.JDialog {

    //Variable que crea la conexión con la BD.
       JDBConexion conexion;
   ArrayList <Asignacion> asignaciones;
    
    /**
     * Creates new form dlgAsignaciones.
     */
    public dlgAsignaciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Se centra la ventana a el medio de la pantalla
        setLocationRelativeTo(null);
        //Llama al método para conectarse a la BD.
         conexion=JDBConexion.Instance();
       asignaciones= new ArrayList <Asignacion>();
        //Llama al método que muestra los datos en la tabla.
        mostrartabla();
    }

    
    /**
     * Método que obtiene los datos de la base de datos y los muestra en la tabla.
     */
    void mostrartabla() {
        //Se crea la tabla.
        DefaultTableModel modelo = new DefaultTableModel();
        //Se añaden las columnas según el tipo de dato.
        modelo.addColumn("ID");
        modelo.addColumn("ID Curso");
        modelo.addColumn("Nombre");
        modelo.addColumn("Intro");
        modelo.addColumn("No. Submissions");
        //A la tabla de la interfaz se le da el valor de la creada anteriormente.
        tablaDatos.setModel(modelo);
       
        try { 
          //obtiene la lista de asignaciones de la bdd
            asignaciones = conexion.obtenerListaAsignacion();
            Asignacion asignacion;
            String datos[] = new String[5];
            //llena los campos de la tabla con los datos obtenidos
            for (int i = 0; i < asignaciones.size(); i++) {

                asignacion = asignaciones.get(i);
                datos[0] = Integer.toString(asignacion.getId());
                datos[1] = Integer.toString(asignacion.getIdcurso());
                datos[2] = asignacion.getNombre();
                datos[3] = asignacion.getIntroduccion();
                datos[4] = Integer.toString(asignacion.getNumSubidas());
                modelo.addRow(datos);
            }

            tablaDatos.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No hay datos en esta tabla", "No hay datos", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(dlgAsignaciones.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1.setText("Asignaciones disponibles");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(270, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(257, 257, 257))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(27, Short.MAX_VALUE))
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
