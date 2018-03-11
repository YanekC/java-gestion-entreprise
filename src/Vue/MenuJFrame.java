/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controllers.CompetenceController;
import Controllers.PersonnelController;
import Model.Competence;
import Model.Personnel;
import Vue.Competences.AjouterCompetenceJFrame;
import Vue.Competences.AfficherCompetenceJFrame;
import Vue.Competences.ModifierCompetenceJFrame;
import Vue.Competences.SupprimerCompetenceJFrame;
import Vue.Missions.AjouterMissionJFrame;
import Vue.Missions.AfficherMissionJFrame;
import Vue.Missions.ModifierMissionJFrame;
import Vue.Missions.SupprimerMissionJFrame;
import Vue.Personnel.AjouterPersonnelJFrame;
import Vue.Personnel.AfficherPersonnelJFrame;
import Vue.Personnel.ModifierPersonnelJFrame;
import Vue.Personnel.SupprimerPersonnelJFrame;
import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author guilhem
 */
public class MenuJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MenuJFrame
     */
    public MenuJFrame() throws Exception {
        initComponents();
        this.setLocationRelativeTo(null); // positionner la fenetre au centre de l'écran
        Container content = this.getContentPane();
        content.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(false); //la fenetre ne peut pas etre redimensionée
        this.remplirPersonnel();
        this.remplirCompetences();
        this.setTitle("Projet 1");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        OngletMenu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        OngletPersonnel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboboxListePersonnel = new javax.swing.JComboBox<>();
        jButtonVoirInfoPersonnel = new javax.swing.JButton();
        jButtonAjouterPersonnel = new javax.swing.JButton();
        jToggleButtonSupprimerPersonnel = new javax.swing.JToggleButton();
        jButtonModifierPersonnel = new javax.swing.JButton();
        OngletMissions = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboboxListeMissions = new javax.swing.JComboBox<>();
        jButtonVoirInfosMissions = new javax.swing.JButton();
        jButtonModifierMission = new javax.swing.JButton();
        jToggleButtonSupprimerMission = new javax.swing.JToggleButton();
        jButtonAjouterMission = new javax.swing.JButton();
        OngletCompetences = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboBoxCompetences = new javax.swing.JComboBox<>();
        jButtonVoirInfoCompetences = new javax.swing.JButton();
        jButtonModifierCompetence = new javax.swing.JButton();
        jToggleButtonSupprimerCometence = new javax.swing.JToggleButton();
        jButtonAjouterCompetence = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Gestionnaire du personnel");

        jLabel3.setText("Choisissez parmis les onglets");

        jLabel4.setText("présentés en haut");

        javax.swing.GroupLayout OngletMenuLayout = new javax.swing.GroupLayout(OngletMenu);
        OngletMenu.setLayout(OngletMenuLayout);
        OngletMenuLayout.setHorizontalGroup(
            OngletMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletMenuLayout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(jLabel1)
                .addGap(0, 133, Short.MAX_VALUE))
            .addGroup(OngletMenuLayout.createSequentialGroup()
                .addGroup(OngletMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OngletMenuLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(jLabel3))
                    .addGroup(OngletMenuLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OngletMenuLayout.setVerticalGroup(
            OngletMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletMenuLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Menu", OngletMenu);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Personnel");

        comboboxListePersonnel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxListePersonnel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxListePersonnelActionPerformed(evt);
            }
        });

        jButtonVoirInfoPersonnel.setBackground(new java.awt.Color(0, 51, 255));
        jButtonVoirInfoPersonnel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoirInfoPersonnel.setText("Voir les informations");
        jButtonVoirInfoPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVoirInfoPersonnelMouseClicked(evt);
            }
        });

        jButtonAjouterPersonnel.setBackground(new java.awt.Color(0, 102, 0));
        jButtonAjouterPersonnel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAjouterPersonnel.setText("Ajouter un Employé");
        jButtonAjouterPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAjouterPersonnelMouseClicked(evt);
            }
        });

        jToggleButtonSupprimerPersonnel.setBackground(new java.awt.Color(255, 0, 51));
        jToggleButtonSupprimerPersonnel.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonSupprimerPersonnel.setText("x");
        jToggleButtonSupprimerPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButtonSupprimerPersonnelMouseClicked(evt);
            }
        });

        jButtonModifierPersonnel.setBackground(new java.awt.Color(255, 153, 51));
        jButtonModifierPersonnel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModifierPersonnel.setText("Modifier");
        jButtonModifierPersonnel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModifierPersonnelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OngletPersonnelLayout = new javax.swing.GroupLayout(OngletPersonnel);
        OngletPersonnel.setLayout(OngletPersonnelLayout);
        OngletPersonnelLayout.setHorizontalGroup(
            OngletPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletPersonnelLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(OngletPersonnelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(OngletPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OngletPersonnelLayout.createSequentialGroup()
                        .addComponent(comboboxListePersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonVoirInfoPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(OngletPersonnelLayout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButtonAjouterPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonModifierPersonnel, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonSupprimerPersonnel)
                .addGap(14, 14, 14))
        );
        OngletPersonnelLayout.setVerticalGroup(
            OngletPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletPersonnelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(OngletPersonnelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxListePersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonSupprimerPersonnel)
                    .addComponent(jButtonVoirInfoPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifierPersonnel))
                .addGap(46, 46, 46)
                .addComponent(jButtonAjouterPersonnel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Personnel", OngletPersonnel);

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Missions");

        comboboxListeMissions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonVoirInfosMissions.setBackground(new java.awt.Color(0, 51, 255));
        jButtonVoirInfosMissions.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoirInfosMissions.setText("Voir les informations");
        jButtonVoirInfosMissions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVoirInfosMissionsMouseClicked(evt);
            }
        });

        jButtonModifierMission.setBackground(new java.awt.Color(255, 153, 51));
        jButtonModifierMission.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModifierMission.setText("Modifier");
        jButtonModifierMission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModifierMissionMouseClicked(evt);
            }
        });

        jToggleButtonSupprimerMission.setBackground(new java.awt.Color(255, 0, 51));
        jToggleButtonSupprimerMission.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonSupprimerMission.setText("x");
        jToggleButtonSupprimerMission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButtonSupprimerMissionMouseClicked(evt);
            }
        });

        jButtonAjouterMission.setBackground(new java.awt.Color(0, 102, 0));
        jButtonAjouterMission.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAjouterMission.setText("Ajouter une Mission");
        jButtonAjouterMission.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAjouterMissionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OngletMissionsLayout = new javax.swing.GroupLayout(OngletMissions);
        OngletMissions.setLayout(OngletMissionsLayout);
        OngletMissionsLayout.setHorizontalGroup(
            OngletMissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletMissionsLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(comboboxListeMissions, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVoirInfosMissions, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonModifierMission, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonSupprimerMission)
                .addGap(14, 14, 14))
            .addGroup(OngletMissionsLayout.createSequentialGroup()
                .addGroup(OngletMissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OngletMissionsLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel5))
                    .addGroup(OngletMissionsLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(jButtonAjouterMission, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        OngletMissionsLayout.setVerticalGroup(
            OngletMissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletMissionsLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addGap(43, 43, 43)
                .addGroup(OngletMissionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboboxListeMissions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonSupprimerMission)
                    .addComponent(jButtonVoirInfosMissions, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifierMission))
                .addGap(46, 46, 46)
                .addComponent(jButtonAjouterMission, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Missions", OngletMissions);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setText("Compétences");

        comboBoxCompetences.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButtonVoirInfoCompetences.setBackground(new java.awt.Color(0, 51, 255));
        jButtonVoirInfoCompetences.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVoirInfoCompetences.setText("Voir les informations");
        jButtonVoirInfoCompetences.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonVoirInfoCompetencesMouseClicked(evt);
            }
        });

        jButtonModifierCompetence.setBackground(new java.awt.Color(255, 153, 51));
        jButtonModifierCompetence.setForeground(new java.awt.Color(255, 255, 255));
        jButtonModifierCompetence.setText("Modifier");
        jButtonModifierCompetence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonModifierCompetenceMouseClicked(evt);
            }
        });

        jToggleButtonSupprimerCometence.setBackground(new java.awt.Color(255, 0, 51));
        jToggleButtonSupprimerCometence.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonSupprimerCometence.setText("x");
        jToggleButtonSupprimerCometence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jToggleButtonSupprimerCometenceMouseClicked(evt);
            }
        });

        jButtonAjouterCompetence.setBackground(new java.awt.Color(0, 102, 0));
        jButtonAjouterCompetence.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAjouterCompetence.setText("Ajouter une Compétence");
        jButtonAjouterCompetence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAjouterCompetenceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout OngletCompetencesLayout = new javax.swing.GroupLayout(OngletCompetences);
        OngletCompetences.setLayout(OngletCompetencesLayout);
        OngletCompetencesLayout.setHorizontalGroup(
            OngletCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletCompetencesLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(OngletCompetencesLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(comboBoxCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonVoirInfoCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonModifierCompetence, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButtonSupprimerCometence)
                .addGap(14, 14, 14))
            .addGroup(OngletCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OngletCompetencesLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(jButtonAjouterCompetence, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(170, 170, 170)))
        );
        OngletCompetencesLayout.setVerticalGroup(
            OngletCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OngletCompetencesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addGap(43, 43, 43)
                .addGroup(OngletCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonSupprimerCometence)
                    .addComponent(jButtonVoirInfoCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModifierCompetence))
                .addContainerGap(183, Short.MAX_VALUE))
            .addGroup(OngletCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OngletCompetencesLayout.createSequentialGroup()
                    .addContainerGap(186, Short.MAX_VALUE)
                    .addComponent(jButtonAjouterCompetence, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84)))
        );

        jTabbedPane1.addTab("Compétences", OngletCompetences);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Personnel
    private void jButtonVoirInfoPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVoirInfoPersonnelMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AfficherPersonnelJFrame infosPersonnel = new AfficherPersonnelJFrame();
                try {
                    infosPersonnel.remplirInfoPersonnel((String) comboboxListePersonnel.getSelectedItem());
                } catch (Exception ex) {
                    Logger.getLogger(MenuJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                infosPersonnel.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonVoirInfoPersonnelMouseClicked

    private void jButtonModifierPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierPersonnelMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModifierPersonnelJFrame modifierPersonnel = new ModifierPersonnelJFrame();
                modifierPersonnel.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonModifierPersonnelMouseClicked

    private void jToggleButtonSupprimerPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonSupprimerPersonnelMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SupprimerPersonnelJFrame supprimerPersonnel = new SupprimerPersonnelJFrame();
                supprimerPersonnel.setVisible(true);
            }
        });
    }//GEN-LAST:event_jToggleButtonSupprimerPersonnelMouseClicked

    private void jButtonAjouterPersonnelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterPersonnelMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AjouterPersonnelJFrame ajouterPersonnel = new AjouterPersonnelJFrame();
                ajouterPersonnel.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonAjouterPersonnelMouseClicked

    private void remplirPersonnel() throws Exception{
        PersonnelController pc = new PersonnelController();
        pc.chargerCSV();
        HashMap<Integer, Personnel> lePersonnel = pc.getlistePersonnel();
        
        comboboxListePersonnel.removeAllItems();
        comboboxListePersonnel.addItem("Sélectionner un employé"); // Plutôt que " " 
        
        for(Map.Entry<Integer, Personnel> e : lePersonnel.entrySet()){
            comboboxListePersonnel.addItem(e.getValue().getNom() + " " + e.getValue().getPrenom());
        }
        
       // comboboxListePersonnel.setSelectedItem(" "); A quoi ça sert ?
        
    }
    
    // Missions
    private void jButtonVoirInfosMissionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVoirInfosMissionsMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AfficherMissionJFrame infosMission = new AfficherMissionJFrame();
                infosMission.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonVoirInfosMissionsMouseClicked

    private void jButtonModifierMissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierMissionMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModifierMissionJFrame modifierMission = new ModifierMissionJFrame();
                modifierMission.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonModifierMissionMouseClicked

    private void jToggleButtonSupprimerMissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonSupprimerMissionMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SupprimerMissionJFrame supprimerMission = new SupprimerMissionJFrame();
                supprimerMission.setVisible(true);
            }
        });
    }//GEN-LAST:event_jToggleButtonSupprimerMissionMouseClicked

    private void jButtonAjouterMissionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterMissionMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AjouterMissionJFrame ajouterMission = new AjouterMissionJFrame();
                ajouterMission.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonAjouterMissionMouseClicked

    public void remplirMissions(){
        
    }
    
    //Compétences
    private void jButtonVoirInfoCompetencesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonVoirInfoCompetencesMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AfficherCompetenceJFrame infoCompetence = new AfficherCompetenceJFrame();
                try {
                    infoCompetence.remplirInfoCompetences((String) comboBoxCompetences.getSelectedItem());
                } catch (Exception ex) {
                    Logger.getLogger(MenuJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                infoCompetence.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonVoirInfoCompetencesMouseClicked

    private void jButtonModifierCompetenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModifierCompetenceMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModifierCompetenceJFrame modifierCompetence = new ModifierCompetenceJFrame();
                modifierCompetence.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonModifierCompetenceMouseClicked

    private void jToggleButtonSupprimerCometenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonSupprimerCometenceMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SupprimerCompetenceJFrame supprimerCompetence = new SupprimerCompetenceJFrame();
                supprimerCompetence.setVisible(true);
            }
        });
    }//GEN-LAST:event_jToggleButtonSupprimerCometenceMouseClicked

    private void jButtonAjouterCompetenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAjouterCompetenceMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AjouterCompetenceJFrame ajouterCompetence = new AjouterCompetenceJFrame();
                ajouterCompetence.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButtonAjouterCompetenceMouseClicked

    private void comboboxListePersonnelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxListePersonnelActionPerformed
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, comboboxListePersonnel.getItemAt(0));
    }//GEN-LAST:event_comboboxListePersonnelActionPerformed
    
    private void remplirCompetences() throws Exception{ //rempli la combobox avec la liste des competences
        CompetenceController cc = new CompetenceController();
        cc.chargerCSV();
        ArrayList<Competence> lesCompetences = cc.getListeCompetences();
        
        comboBoxCompetences.removeAllItems();
        comboBoxCompetences.addItem(" ");
        
        for (Competence laC : lesCompetences){
            comboBoxCompetences.addItem(laC.getIdC());
        }
        comboBoxCompetences.setSelectedItem(" ");
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
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuJFrame().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MenuJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel OngletCompetences;
    private javax.swing.JPanel OngletMenu;
    private javax.swing.JPanel OngletMissions;
    private javax.swing.JPanel OngletPersonnel;
    private javax.swing.JComboBox<String> comboBoxCompetences;
    private javax.swing.JComboBox<String> comboboxListeMissions;
    private javax.swing.JComboBox<String> comboboxListePersonnel;
    private javax.swing.JButton jButtonAjouterCompetence;
    private javax.swing.JButton jButtonAjouterMission;
    private javax.swing.JButton jButtonAjouterPersonnel;
    private javax.swing.JButton jButtonModifierCompetence;
    private javax.swing.JButton jButtonModifierMission;
    private javax.swing.JButton jButtonModifierPersonnel;
    private javax.swing.JButton jButtonVoirInfoCompetences;
    private javax.swing.JButton jButtonVoirInfoPersonnel;
    private javax.swing.JButton jButtonVoirInfosMissions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToggleButton jToggleButtonSupprimerCometence;
    private javax.swing.JToggleButton jToggleButtonSupprimerMission;
    private javax.swing.JToggleButton jToggleButtonSupprimerPersonnel;
    // End of variables declaration//GEN-END:variables
}
