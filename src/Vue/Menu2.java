/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controllers.CompetenceController;
import Controllers.EntrepriseController;
import Controllers.PersonnelController;
import Model.Competence;
import Model.Entreprise;
import Model.Personnel;
import Vue.Competences.AjouterCompetenceJFrame;
import Vue.Components.BoutonTabEditor;
import Vue.Components.BoutonTabRenderer;
import Vue.Personnel.AjouterModifierPersonnelJFrame;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author guilhem, sandeox
 */
public class Menu2 extends javax.swing.JFrame {

    private EntrepriseController entC;
    /**
     * Creates new form test
     */
    public Menu2() throws Exception {
        initComponents();
        this.setLocationRelativeTo(null); // positionner la fenetre au centre de l'écran
        this.setResizable(false); //la fenetre ne peut pas etre redimensionée
        this.entC = new EntrepriseController(); // dans le but de faire évoluer l'application
        remplirTableauPersonnel();
        remplirTableauCompetences();
        
       

        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelPersonnel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDuPersonnel = new javax.swing.JTable();
        jButtonAjouterPersonne = new javax.swing.JButton();
        jBtnModifier = new javax.swing.JButton();
        jPanelCompetence = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCompetences = new javax.swing.JTable();
        jButtonAjouterCompetence = new javax.swing.JButton();
        jPanelMission = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nom");
        model.addColumn("Prénom");
        model.addColumn("Date d'embauche");
        model.addColumn("Compétences");
        model.addColumn("Modifier");
        jTableDuPersonnel.setModel(model);
        jTableDuPersonnel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableDuPersonnel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTableDuPersonnelFocusLost(evt);
            }
        });
        jTableDuPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableDuPersonnelMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableDuPersonnel);
        jTableDuPersonnel.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jButtonAjouterPersonne.setText("Ajouter une personne");
        jButtonAjouterPersonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterPersonneActionPerformed(evt);
            }
        });

        jBtnModifier.setText("Modifier");
        jBtnModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnModifierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelPersonnelLayout = new javax.swing.GroupLayout(jPanelPersonnel);
        jPanelPersonnel.setLayout(jPanelPersonnelLayout);
        jPanelPersonnelLayout.setHorizontalGroup(
            jPanelPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPersonnelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnModifier)
                .addGap(18, 18, 18)
                .addComponent(jButtonAjouterPersonne)
                .addContainerGap())
        );
        jPanelPersonnelLayout.setVerticalGroup(
            jPanelPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPersonnelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAjouterPersonne)
                    .addComponent(jBtnModifier))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Gestion du personel", jPanelPersonnel);

        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("ID");
        model2.addColumn("Dénomination anglaise");
        model2.addColumn("Dénomination française");
        model2.addColumn("Actions");
        jTableCompetences.setModel(model2);
        jScrollPane2.setViewportView(jTableCompetences);

        jButtonAjouterCompetence.setText("Ajouter une compétence");
        jButtonAjouterCompetence.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterCompetenceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCompetenceLayout = new javax.swing.GroupLayout(jPanelCompetence);
        jPanelCompetence.setLayout(jPanelCompetenceLayout);
        jPanelCompetenceLayout.setHorizontalGroup(
            jPanelCompetenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCompetenceLayout.createSequentialGroup()
                .addContainerGap(614, Short.MAX_VALUE)
                .addComponent(jButtonAjouterCompetence)
                .addContainerGap())
        );
        jPanelCompetenceLayout.setVerticalGroup(
            jPanelCompetenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompetenceLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAjouterCompetence)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Gestion des compétences", jPanelCompetence);

        javax.swing.GroupLayout jPanelMissionLayout = new javax.swing.GroupLayout(jPanelMission);
        jPanelMission.setLayout(jPanelMissionLayout);
        jPanelMissionLayout.setHorizontalGroup(
            jPanelMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 789, Short.MAX_VALUE)
        );
        jPanelMissionLayout.setVerticalGroup(
            jPanelMissionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 524, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Gestion des missions", jPanelMission);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAjouterPersonneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterPersonneActionPerformed
        AjouterModifierPersonnelJFrame ajoutP = new AjouterModifierPersonnelJFrame();
        ajoutP.setVisible(true);
        ajoutP.remplirFormPersonnel(-1);
    }//GEN-LAST:event_jButtonAjouterPersonneActionPerformed

    private void jButtonAjouterCompetenceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterCompetenceActionPerformed
        AjouterCompetenceJFrame ajoutC = new AjouterCompetenceJFrame();
        ajoutC.setVisible(true);
    }//GEN-LAST:event_jButtonAjouterCompetenceActionPerformed

    private void jBtnModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnModifierActionPerformed
        /*----- Modifier une personne sélectionné -----*/
        int rowIndex = jTableDuPersonnel.getSelectedRow(); // Récupère la ligne du champ cliqué
        int colIndex = 0; // Place sur la column 0 pour récupérer l'ID
        String idS = jTableDuPersonnel.getModel().getValueAt(rowIndex, colIndex).toString(); //Récupère l'ID (format String)
        int id = Integer.parseInt(idS); // Parce qu'en 2 ligne ça fait pas de mal
       // System.out.println("Id ? : "+id);
        AjouterModifierPersonnelJFrame apf = new AjouterModifierPersonnelJFrame(); // Instanciation de la nouvelle frame
        apf.setVisible(true); //Rend la frame visible
        apf.remplirFormPersonnel(id); // Replir avec la fonction en passant l'ID !

        
    }//GEN-LAST:event_jBtnModifierActionPerformed

    private void jTableDuPersonnelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableDuPersonnelFocusLost

    }//GEN-LAST:event_jTableDuPersonnelFocusLost

    private void jTableDuPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDuPersonnelMouseClicked
        // TODO add your handling code here:
        /* ------ Récupérer l'ID du champ cliqué -----*/
         //System.out.println("Ligne : "+rowIndex);
         //System.out.println("Id ? : "+jTableDuPersonnel.getValueAt(rowIndex, colIndex));
    }//GEN-LAST:event_jTableDuPersonnelMouseClicked

    public void remplirTableauPersonnel() throws Exception{
        HashMap<Integer, Personnel> lePersonnel = entC.getlistePersonnel();
        System.out.println(entC.getlistePersonnel());
        
        for(Map.Entry<Integer, Personnel> e : lePersonnel.entrySet()){
            String line = e.getValue().getId()+";"+e.getValue().getNom()+";"+e.getValue().getPrenom()+";"+e.getValue().getDateNaiss();
            String[] laLigne = line.split(";");
            ((DefaultTableModel) jTableDuPersonnel.getModel()).addRow(laLigne);
            
//            ((DefaultTableModel) jTableDuPersonnel.getModel()).setValueAt(new JButton("modifier"), ((DefaultTableModel) jTableDuPersonnel.getModel()).getRowCount() - 1, 5);
//            
//            //Des commentaires guilhem ? Je comprends pas l'utilisation du dossier Components et tout le reste
//            jTableDuPersonnel.getColumn("Modifier").setCellRenderer(new BoutonTabRenderer());
//            jTableDuPersonnel.getColumn("Modifier").setCellEditor(new BoutonTabEditor(new JTextField(), e.getValue().getId()));
//            System.out.println(e.getValue().getId());
        }
        jTableDuPersonnel.setAutoCreateRowSorter(true);
        
                
         /* ---- Masquer column ID ---- */
        TableColumnModel tcm = jTableDuPersonnel.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        
        
    }
    
    public void remplirTableauCompetences() throws Exception {
        CompetenceController cc = new CompetenceController();
        ArrayList<Competence> lesCompetences = cc.getListeCompetences();
        
        for(Competence c : lesCompetences){
            String line = c.getIdC()+";"+c.getLibelleAng()+";"+c.getLibelleFra();
            String[] laLigne = line.split(";");
            ((DefaultTableModel) jTableCompetences.getModel()).addRow(laLigne);
        }
        jTableCompetences.setAutoCreateRowSorter(true);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Menu2().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Menu2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnModifier;
    private javax.swing.JButton jButtonAjouterCompetence;
    private javax.swing.JButton jButtonAjouterPersonne;
    private javax.swing.JPanel jPanelCompetence;
    private javax.swing.JPanel jPanelMission;
    private javax.swing.JPanel jPanelPersonnel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableCompetences;
    private javax.swing.JTable jTableDuPersonnel;
    // End of variables declaration//GEN-END:variables
}
