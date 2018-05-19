/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Missions;

import Model.Competence;
import Model.Entreprise;
import Model.Mission;
import Model.Personnel;
import static Model.Personnel.formatDate;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author guilhem, SanDeox
 */
public class AjouterMissionJFrame extends javax.swing.JFrame {
    private int id;
    private JTable jtB;
    private int rInd;
    private int cInd;
    private ColorUIResource colorResource;
    private AjouterMissionJFrame apf;
    private boolean etatModif = false;
    private ArrayList<String> lComp;
    private ArrayList<String> lPers;
    
    /**
     * Creates new form AjouterMissionJFrame
     */
    public AjouterMissionJFrame() {
        initComponents();
        this.setLocationRelativeTo(null); // positionner la fenetre au centre de l'écran
        Container content = this.getContentPane();
        content.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(false); //la fenetre ne peut pas etre redimensionée
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //fermer la JFrame sans arrêter l'application
        this.setTitle("Ajouter/Modifier la mission");
        setButtonAction();
        
        
        
        
        /* ----- Fermeture réupload de la JProgressBar -------- */
        this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    updateUIManager();
                    if(etatModif){
                       String ObjButtons[] = {"Oui","Non"};
                        int PromptResult = JOptionPane.showOptionDialog(null, 
                            "Les compétences et personnels non enregistrées vont être perdu, continuer ?", "Quitter sans enregistrer", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                            ObjButtons,ObjButtons[1]);
                        if(PromptResult==0)
                        {
                          hideFrame();
                        }
                   }
                   else{
                       dispose();
                   }
                }
        });
    }
    
    public void updateUIManager(){
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(AjouterMissionJFrame.class.getName()).log(Level.SEVERE, null, ex);
          }
        SwingUtilities.updateComponentTreeUI(apf);
        apf.pack();
    }
    
     public void hideFrame(){
        if(id==-1){
            dispose(); //ferme la fenêtre
        }
        else{
            Mission m = Entreprise.findMissionById(id);
            m.setCompetences(lComp);
            m.setPersonnels(lPers);
            
            dispose(); //ferme la fenêtre
        }
    }
    
    public void setLabel(){
        //On ajoute !
        this.setTitle("Ajouter une Mission");
        jBtnEnregistrer.setText("Ajouter");
       
        jBtnDelMission.setVisible(false);
    }
    
    public void remplirFormMission(int id, JTable jtB, int rI, int cI, AjouterMissionJFrame apf){
        this.jtB = jtB;
        this.rInd=rI;
        this.cInd=cI;
        this.apf = apf;
        
        if(id==-1){
            setLabel(); //Définir les valeurs vides
            //remplirCompetenceEmpty(); Besoin qu'en 1 action
            jPanelCompetences.setVisible(false);
            jPanelParticipants.setVisible(false);
            this.pack();
            
        }
        else{
            //On modifie !
           this.setTitle("Modifier une Mission");
           jBtnEnregistrer.setText("Enregistrer");
           Mission m = Entreprise.findMissionById(id);

           //System.out.println(m);
           
           
           jDCDateDeb.setCalendar(m.getDateDebut());
           jDCDateFin.setCalendar(m.getDateFinEstime());
           jTextFieldNom.setText(m.getNom());
           jTextNbPersMin.setText(String.valueOf(m.getNbPersMin()));
           this.id = id; //Stock l'id pour la modification
           
            //Cancel (clone fonctionne pas, copy non plus, on s'amuse en java)
           lComp = (ArrayList<String>)m.getListeCompetences().clone();
           lPers = (ArrayList<String>)m.getListePersonnels().clone();
           
           /* ---- Set current etat of the mission ------*/
           setEtatOfTheMission(m.getEtatString());
           remplirListesCompetencesMission(m);
           remplirListesParticipants(m);
           
        }
    }
    
    public void remplirListesParticipants(Mission m){
        /* --- Remplir participant de la mission --- */
        HashMap<String, Personnel> personnelSurLaMission = remplirMesPersonnels(m);

        /* --- Remplir les personnels potentiel sans les personnels déjà présent --- */
        remplirListesParticipantsPotentiel(personnelSurLaMission);
    }
    
    
    public HashMap remplirMesPersonnels(Mission m){
         /*---- Fill personnel added to the Mission ------*/
       //Define model
        DefaultListModel modelListePersonnel = new DefaultListModel();
        //Get skill from Enterprise in Hashmap
        HashMap<Integer, Personnel> personnels = Entreprise.getMissionPersonnel(m);
        //Loop the hashmap lulz
        try{
            
        
        for(Map.Entry<Integer, Personnel> personnel : personnels.entrySet()) {
            //Nom
            String nomPers = personnel.getValue().getNom()+" "+ personnel.getValue().getPrenom();
            modelListePersonnel.addElement(nomPers);
        }
        }catch(Exception e){System.out.println(e.getMessage());}
        //Set the model on the IHM
        jListParticipant.setModel(modelListePersonnel);
       
        //System.out.println(competences);
        return personnels;
    }
    
    public void remplirListesParticipantsPotentiel(HashMap<String, Personnel> addedPersonnel){
         /*---- Fill Personnel ------*/
        //Define model
        DefaultListModel modelAddCompetence = new DefaultListModel();
        ArrayList<Integer> unaddedPersonnel = getUnAddedPersonnel(addedPersonnel);
        for(int idP : unaddedPersonnel ) {
            Personnel p = Entreprise.getPersonnelById(idP);
            try{
                //Nom
            String nomPers = p.getNom()+" "+ p.getPrenom();
            modelAddCompetence.addElement(nomPers);
            }catch(Exception e){System.out.println(e.getMessage());}
            
        }
        //Set the model in the IHM
        jListAjouterParticipant.setModel(modelAddCompetence);
    }
    
    public ArrayList getUnAddedPersonnel(HashMap<String, Personnel> addedPersonnel){
       /*--- Get unknown skill ---- */
       Mission m = Entreprise.findMissionById(id);
       //New ArrayList
       ArrayList<Integer> unAddedPersonnel= new ArrayList();
       //Get all skill from enterprise
       ArrayList<Integer> personnelEntreprise = Entreprise.getPersonnelSuggere(m);
       //compare with skill of the mission
       for(int idP : personnelEntreprise) {
            try{
               if(!addedPersonnel.containsKey(idP)){
                    Personnel p = Entreprise.getPersonnelById(idP);
                    //Nom
                    String nomPers = p.getNom()+" "+ p.getPrenom();
                    //Set the hasmap with the personnel
                    unAddedPersonnel.add(idP);
                    //unAddedPersonnel.put(idPersonnels,personnels.get(idPersonnels));
               }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        }
       return unAddedPersonnel;
    }
    
    
    
    public void remplirListesCompetencesMission(Mission m){
        /* --- Remplir compétences de la mission --- */
        HashMap<String, Competence> missionCompetence = remplirMesCompetences(m);
        //System.out.println(personnalCompetence);
        /* --- Remplir les compétences non acquise avec les compétences de la mission --- */
        remplirListesCompetencesNonAcquise(missionCompetence);
        
    }
    
    public HashMap remplirMesCompetences(Mission m){
        /*---- Fill known skill ------*/
       //Define model
        DefaultListModel modelListeCompetence = new DefaultListModel();
        //Get skill from Enterprise in Hashmap
        HashMap<String, Competence> competences = Entreprise.getMissionCompetence(m);
        //Loop the hashmap lulz
        try{
            
        
        for(Map.Entry<String, Competence> competence : competences.entrySet()) {
            //Fr lib
            String libFra = competence.getValue().getLibelleFra();
            modelListeCompetence.addElement(libFra);
            
        }
        
        }catch(Exception e){System.out.println(e.getMessage());}
        //Set the model on the IHM
        jListCompetences.setModel(modelListeCompetence);
       
        //System.out.println(competences);
        return competences;
    }
    
    public void remplirListesCompetencesNonAcquise(HashMap<String, Competence> knownCompetence){
        /*---- Fill unknown skill ------*/
        //Define model
        DefaultListModel modelAddCompetence = new DefaultListModel();
        HashMap<String, Competence> unknownCompetences = getUnknownCompetence(knownCompetence);
        for(Map.Entry<String, Competence> competence : unknownCompetences.entrySet()) {
            try{
                //Fr lib
                String libFra = competence.getValue().getLibelleFra();
                modelAddCompetence.addElement(libFra);
            }catch(Exception e){System.out.println(e.getMessage());}
            
        }
        //Set the model in the IHM
        jListAjouterCompetence.setModel(modelAddCompetence);
    }
    
    public HashMap getUnknownCompetence(HashMap<String, Competence> knownCompetence){
       /*--- Get unknown skill ---- */
       //New Hashmap
       HashMap<String, Competence> unknownSkill = new HashMap();
       //Get all skill from enterprise
       HashMap<String, Competence> competencesEnterprise = Entreprise.getCompetences();
       //compare with skill of the mission
       for(Map.Entry<String, Competence> competence : competencesEnterprise.entrySet()) {
            try{
               if(!knownCompetence.containsKey(competence.getKey())){
                   //Fr lib
                    String libFra = competence.getValue().getLibelleFra();
                    //Set the hasmap with the competence
                    unknownSkill.put(competence.getKey(), competencesEnterprise.get(competence.getKey()));
               }
            }catch(Exception e){System.out.println(e.getMessage());}
            
        }
       return unknownSkill;
    }
    
    public void setButtonAction(){
        /* ---- Set color to the button ------*/
        //En préparation
        setColorToPanel(jButtonPrepare, Color.magenta);
        //Planification
        setColorToPanel(jButtonPlan, Color.yellow);
        //En cours
        setColorToPanel(jButtonInProg, Color.blue);
        //Terminée
        setColorToPanel(jButtonEnd, Color.green);
        
        /* ---- Set current etat of the mission ------*/
        setEtatOfTheMission("En préparation");
    }
    
    public void setColorToPanel(JButton jBtn, Color color){
        jBtn.setBackground(color);
        jBtn.setForeground(Color.BLACK);
    }
    
    public void setEtatOfTheMission(String etat){
        Mission m = Entreprise.findMissionById(id);
        //Switch to ID to upd
        Color mColor = m.getCouleurEtat(etat);
        switch(etat){
            case "Planifiée" : setEnableButton(mColor, jButtonEnd, jButtonInProg, jButtonPrepare,jButtonPlan, 50);changeProgressBarColor(etat);break;
            case "En cours" : disableMission();setEnableButton(mColor, jButtonEnd, jButtonPlan, jButtonPrepare, jButtonInProg, 75);changeProgressBarColor(etat);break;
            case "Terminée" : disableMission();setEnableButton(mColor, jButtonPlan, jButtonInProg, jButtonPrepare, jButtonEnd, 100);changeProgressBarColor(etat);break;
            case "En préparation" : setEnableButton(mColor, jButtonEnd, jButtonInProg, jButtonPlan, jButtonPrepare, 25);changeProgressBarColor(etat);break;
            default:break;
        }
    }
    
    
    
    public void changeProgressBarColor(String etat){
        Mission m = Entreprise.findMissionById(id);
        Color color = m.getCouleurEtat(etat);
        colorResource = new ColorUIResource(color);
        UIManager.put("nimbusOrange",colorResource);       
    }
    
    public void disableMission(){
        jDCDateDeb.setEnabled(false);
        jDCDateFin.setEnabled(false);
        jButtonSupprComp.setEnabled(false);
        jButtonAjouterComp.setEnabled(false);
        jButtonSupprParticip.setEnabled(false);
        jButtonAddParticip.setEnabled(false);
        jButtonAddParticip.setVisible(false);
        jButtonSupprParticip.setVisible(false);
        jButtonSupprComp.setVisible(false);
        jButtonAjouterComp.setVisible(false);
        jTextFieldNom.setEnabled(false);
        jTextNbPersMin.setEnabled(false);
        jBtnEnregistrer.setText("Fermer");
        jBtnDelMission.setVisible(false);
        jBtnCancel.setVisible(false);
    }
    public void setEnableButton(Color color, JButton jBtn1, JButton jBtn2, JButton jBtn3, JButton jBtnToEnable, int progress){
        jBtn1.setEnabled(false);
        jBtn2.setEnabled(false);
        jBtn3.setEnabled(false);
        jBtnToEnable.setEnabled(true);
        getContentPane().setBackground(color);
        jBtnToEnable.setBackground(color);
        jPBEtat.setValue(0);
        jPBEtat.setValue(progress);
        jPBEtat.setStringPainted(true);
        Color colorText;
        switch(progress){
            case 25 : colorText = Color.BLACK;break;
            case 50 : colorText = Color.BLACK;break;
            default: colorText = Color.WHITE;break;
        
        }
        jPBEtat.setForeground(colorText);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelGauche = new javax.swing.JPanel();
        jPanelGeneral = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextNbPersMin = new javax.swing.JTextField();
        jDCDateDeb = new com.toedter.calendar.JDateChooser();
        jDCDateFin = new com.toedter.calendar.JDateChooser();
        jPanelCompetences = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListCompetences = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListAjouterCompetence = new javax.swing.JList<>();
        jButtonSupprComp = new javax.swing.JButton();
        jButtonAjouterComp = new javax.swing.JButton();
        jPanelSlider = new javax.swing.JPanel();
        jPBEtat = new javax.swing.JProgressBar();
        jButtonPrepare = new javax.swing.JButton();
        jButtonPlan = new javax.swing.JButton();
        jButtonInProg = new javax.swing.JButton();
        jButtonEnd = new javax.swing.JButton();
        jPanelParticipants = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListParticipant = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListAjouterParticipant = new javax.swing.JList<>();
        jButtonSupprParticip = new javax.swing.JButton();
        jButtonAddParticip = new javax.swing.JButton();
        jPanelValidation = new javax.swing.JPanel();
        jBtnDelMission = new javax.swing.JButton();
        jBtnCancel = new javax.swing.JButton();
        jBtnEnregistrer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder("Informations générales"));

        jLabel1.setText("Date de début");

        jLabel2.setText("Date de fin estimée");

        jLabel3.setText("Nom de la mission");

        jLabel4.setText("Nombre de personne minimum");

        jTextNbPersMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNbPersMinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGeneralLayout = new javax.swing.GroupLayout(jPanelGeneral);
        jPanelGeneral.setLayout(jPanelGeneralLayout);
        jPanelGeneralLayout.setHorizontalGroup(
            jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGeneralLayout.createSequentialGroup()
                        .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelGeneralLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldNom)
                    .addComponent(jTextNbPersMin, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jDCDateDeb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDCDateFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );
        jPanelGeneralLayout.setVerticalGroup(
            jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGeneralLayout.createSequentialGroup()
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextNbPersMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelGeneralLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1))
                    .addComponent(jDCDateDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDCDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        jPanelCompetences.setBorder(javax.swing.BorderFactory.createTitledBorder("Compétences"));

        jListCompetences.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListCompetences);

        jListAjouterCompetence.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListAjouterCompetence);

        jButtonSupprComp.setText("Supprimer");
        jButtonSupprComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprCompActionPerformed(evt);
            }
        });

        jButtonAjouterComp.setText("Ajouter");
        jButtonAjouterComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterCompActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCompetencesLayout = new javax.swing.GroupLayout(jPanelCompetences);
        jPanelCompetences.setLayout(jPanelCompetencesLayout);
        jPanelCompetencesLayout.setHorizontalGroup(
            jPanelCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompetencesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanelCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAjouterComp, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .addComponent(jButtonSupprComp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelCompetencesLayout.setVerticalGroup(
            jPanelCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCompetencesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCompetencesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCompetencesLayout.createSequentialGroup()
                        .addComponent(jButtonSupprComp)
                        .addGap(70, 70, 70)
                        .addComponent(jButtonAjouterComp))
                    .addGroup(jPanelCompetencesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelSliderLayout = new javax.swing.GroupLayout(jPanelSlider);
        jPanelSlider.setLayout(jPanelSliderLayout);
        jPanelSliderLayout.setHorizontalGroup(
            jPanelSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSliderLayout.createSequentialGroup()
                .addComponent(jPBEtat, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelSliderLayout.setVerticalGroup(
            jPanelSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSliderLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPBEtat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonPrepare.setText("En préparation");

        jButtonPlan.setText("Planifiée");
        jButtonPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlanActionPerformed(evt);
            }
        });

        jButtonInProg.setText("En cours");
        jButtonInProg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInProgActionPerformed(evt);
            }
        });

        jButtonEnd.setText("Terminée");
        jButtonEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGaucheLayout = new javax.swing.GroupLayout(jPanelGauche);
        jPanelGauche.setLayout(jPanelGaucheLayout);
        jPanelGaucheLayout.setHorizontalGroup(
            jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGaucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCompetences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelGaucheLayout.createSequentialGroup()
                        .addGroup(jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelGaucheLayout.createSequentialGroup()
                                    .addComponent(jButtonPrepare, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonInProg, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonEnd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jPanelSlider, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 105, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelGaucheLayout.setVerticalGroup(
            jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGaucheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGaucheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonPrepare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEnd, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(jButtonInProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonPlan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanelCompetences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanelParticipants.setBorder(javax.swing.BorderFactory.createTitledBorder("Participants"));

        jListParticipant.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jListParticipant);

        jListAjouterParticipant.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jListAjouterParticipant);

        jButtonSupprParticip.setText("Supprimer");
        jButtonSupprParticip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprParticipActionPerformed(evt);
            }
        });

        jButtonAddParticip.setText("Ajouter");
        jButtonAddParticip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddParticipActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelParticipantsLayout = new javax.swing.GroupLayout(jPanelParticipants);
        jPanelParticipants.setLayout(jPanelParticipantsLayout);
        jPanelParticipantsLayout.setHorizontalGroup(
            jPanelParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParticipantsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParticipantsLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSupprParticip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelParticipantsLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAddParticip, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelParticipantsLayout.setVerticalGroup(
            jPanelParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelParticipantsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSupprParticip)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelParticipantsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelParticipantsLayout.createSequentialGroup()
                        .addComponent(jButtonAddParticip)
                        .addContainerGap(142, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
        );

        jBtnDelMission.setText("Supprimer la mission");
        jBtnDelMission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnDelMissionActionPerformed(evt);
            }
        });

        jBtnCancel.setText("Annuler");
        jBtnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelActionPerformed(evt);
            }
        });

        jBtnEnregistrer.setText("Enregistrer");
        jBtnEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEnregistrerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelValidationLayout = new javax.swing.GroupLayout(jPanelValidation);
        jPanelValidation.setLayout(jPanelValidationLayout);
        jPanelValidationLayout.setHorizontalGroup(
            jPanelValidationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelValidationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBtnDelMission)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jBtnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEnregistrer)
                .addContainerGap())
        );
        jPanelValidationLayout.setVerticalGroup(
            jPanelValidationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelValidationLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelValidationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnDelMission)
                    .addComponent(jBtnCancel)
                    .addComponent(jBtnEnregistrer))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelValidation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanelParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelValidation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPlanActionPerformed

    private void jButtonInProgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInProgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInProgActionPerformed

    private void jButtonEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonEndActionPerformed

    private void jBtnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelActionPerformed
         if(etatModif){
                       String ObjButtons[] = {"Oui","Non"};
                        int PromptResult = JOptionPane.showOptionDialog(null, 
                            "Les compétences non enregistrées vont être perdu, continuer ?", "Quitter sans enregistrer", 
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                            ObjButtons,ObjButtons[1]);
                        if(PromptResult==0)
                        {
                          hideFrame();
                        }
                   }
                   else{
                       updateUIManager();
                       dispose();
                   }
    }//GEN-LAST:event_jBtnCancelActionPerformed

    private void jButtonAjouterCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterCompActionPerformed
          if(!jListAjouterCompetence.isSelectionEmpty()){
            //Get the actual Mission
            Mission m = Entreprise.findMissionById(id);
            //Switch to ID to upd
            String id = Entreprise.getIdCompetenceByFrName(jListAjouterCompetence.getSelectedValue());
            m.ajouterCompetence(id);
            //Upd both List
              remplirListesCompetencesMission(m);
            jListAjouterCompetence.setSelectedIndex(0);
            updateEtatMission();
              remplirListesParticipants(m);
              etatModif=true;
        }
    }//GEN-LAST:event_jButtonAjouterCompActionPerformed
    public void updateEtatMission(){
       Mission m = Entreprise.findMissionById(id);
       m.updateEtat();
       String etat = m.getEtatString();
       setEtatOfTheMission(etat);
       updateUIManager();
    }
    private void jButtonSupprCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprCompActionPerformed
        if(!jListCompetences.isSelectionEmpty()){
            //Get the actual Mission
            Mission m = Entreprise.findMissionById(id);
            //Switch to ID to upd
            String id = Entreprise.getIdCompetenceByFrName(jListCompetences.getSelectedValue());
            //System.out.println("ID :"+id);
            m.supprimerCompetence(id);
            //Upd both List
            remplirListesCompetencesMission(m);
            jListCompetences.setSelectedIndex(0);
            updateEtatMission();
            remplirListesParticipants(m);
            etatModif=true;
            
            
        }
    }//GEN-LAST:event_jButtonSupprCompActionPerformed

    private void jBtnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEnregistrerActionPerformed
        // TODO add your handling code here:
        //On est en modification
        if(jBtnEnregistrer.getText()=="Enregistrer"){
            try{
               modifier(); 
            }catch(Exception e){System.err.println(e.getMessage());}
            
        }
        //On est en ajout
        if(jBtnEnregistrer.getText()=="Ajouter"){
            ajouter();
        }
        //On est en mission terminé
        if(jBtnEnregistrer.getText()=="Fermer"){
            Mission m = Entreprise.findMissionById(id);
            String etat = m.getEtatString();
            this.jtB.setValueAt(etat, this.rInd, this.cInd+4);
            updateUIManager();
            dispose();
        }
    }//GEN-LAST:event_jBtnEnregistrerActionPerformed

    private void jButtonSupprParticipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprParticipActionPerformed
        if(!jListParticipant.isSelectionEmpty()){
            //Get the actual Mission
            Mission m = Entreprise.findMissionById(id);
            //Switch to ID to upd
            int pId = Entreprise.getIdPersonnelByName(jListParticipant.getSelectedValue());
            //System.out.println("ID :"+id);
            String stringId = String.valueOf(pId);
            m.supprimerPersonnel(stringId);
            //Upd both List
            remplirListesParticipants(m);
            jListParticipant.setSelectedIndex(0);
            updateEtatMission();
            etatModif=true;
        }
        
    }//GEN-LAST:event_jButtonSupprParticipActionPerformed

    private void jButtonAddParticipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddParticipActionPerformed
        if(!jListAjouterParticipant.isSelectionEmpty()){
            //Get the actual Mission
            Mission m = Entreprise.findMissionById(id);
            //Switch to ID to upd
            int pId = Entreprise.getIdPersonnelByName(jListAjouterParticipant.getSelectedValue());
            String stringId = String.valueOf(pId);
            m.ajouterPersonnel(stringId);
            //Upd both List
              remplirListesParticipants(m);
            jListAjouterParticipant.setSelectedIndex(0);
            etatModif=true;
            updateEtatMission();
        }
    }//GEN-LAST:event_jButtonAddParticipActionPerformed

    private void jTextNbPersMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNbPersMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNbPersMinActionPerformed

    private void jBtnDelMissionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnDelMissionActionPerformed
         String ObjButtons[] = {"Oui","Non"};
    int PromptResult = JOptionPane.showOptionDialog(null, 
                       "La mission sera définitivement supprimé, continuer ?", "Supprimer la mission", 
                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                       ObjButtons,ObjButtons[1]);
    if(PromptResult==0){
        try{
        Entreprise.removeMission(id);
        int rowToDel = this.jtB.convertRowIndexToModel(rInd);
        ((DefaultTableModel)this.jtB.getModel()).removeRow(rowToDel);
        }catch(Exception e){System.out.println(e.getMessage());}
        jtB.setColumnSelectionInterval(0,0);
        jtB.setRowSelectionInterval(0,0);
        updateUIManager();
        dispose();
    }
    }//GEN-LAST:event_jBtnDelMissionActionPerformed
    
    public boolean valide(){
        int ok = 0;
         //Test du nom
        if(jTextFieldNom.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez renseigner le nom de la mission");
            return false;
        }
        else{ok++;}
         //Test du nb Personne
        if(jTextNbPersMin.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Veuillez renseigner le nombre de personne minimum de la mission");
            return false;
        }
        else{
            try{
                int isInt = Integer.parseInt(jTextNbPersMin.getText());
                ok++;
            }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "La valeur inséré n'est pas un nombre");
            }
        }
        //Test des dates
        //Test de la date
        // before
        try{
           jDCDateDeb.getCalendar().toString(); 
           ok++;
           //after
           try{
                jDCDateFin.getCalendar().toString(); 
                ok++;
           //Logic
                if(jDCDateDeb.getCalendar().after( jDCDateFin.getCalendar())){
                   JOptionPane.showMessageDialog(null, "Les dates sont incohérentes"); 
                   return false;
                }
                else{ok++;}
            }
             catch(Exception e){
               JOptionPane.showMessageDialog(null, "Veuillez renseigner la date de fin de la mission"); 
               return false;
            }
        }
         catch(Exception e){
           JOptionPane.showMessageDialog(null, "Veuillez renseigner la date de début de la mission"); 
           return false;
        }
        
        //Tout les tests passent
        return ok==5;
    }
    
    public void modifier() throws ParseException{
        if(valide()){
            
           String nomMission = jTextFieldNom.getText();
           int nbMini = Integer.parseInt(jTextNbPersMin.getText());
           
           //upd the personnel with corresponding value
           Entreprise.updBasicValueMission(this.id, nbMini, nomMission, jDCDateDeb.getCalendar(), jDCDateFin.getCalendar());
           Mission m = Entreprise.findMissionById(id);
           String etat = m.getEtatString();
           
           /* ------ Update du Jtable ------*/
           this.jtB.setValueAt(nomMission, this.rInd, this.cInd);
           this.jtB.setValueAt(m.getDateDebutString(), this.rInd, this.cInd+1);
           this.jtB.setValueAt(m.getDateFinEstimeString(), this.rInd, this.cInd+2);
           this.jtB.setValueAt(nbMini, this.rInd, this.cInd+3);
           this.jtB.setValueAt(etat, this.rInd, this.cInd+4);
           updateUIManager();
           dispose(); //ferme la fenêtre
        }
    }

    
    public void ajouter(){
        /* ---- Valide alors je l'ajoute ---- */
        if(valide()){
            String nomMission = jTextFieldNom.getText();
           int nbMini = Integer.parseInt(jTextNbPersMin.getText());
           
           Mission m = new Mission(nomMission, jDCDateDeb.getCalendar(), jDCDateFin.getCalendar(), jDCDateFin.getCalendar(), nbMini);
           int idM = Entreprise.addMission(m);
           Mission mToUpd = Entreprise.findMissionById(idM);

           /*-------- Ajout au Jtable ----------*/
           String line = idM+";"+nomMission+";"+mToUpd.getDateDebutString()+";"+mToUpd.getDateFinEstimeString()+";"+nbMini+";"+m.getEtatString();
            String[] laLigne = line.split(";");
           ((DefaultTableModel) this.jtB.getModel()).addRow(laLigne);
           // Focus sur la row pour la ré-ouvrir
           int rowTofocus = getRowById(jtB, idM);
           jtB.setColumnSelectionInterval(0,0);
           jtB.setRowSelectionInterval(0,0);
           jtB.changeSelection(rowTofocus, ICONIFIED, rootPaneCheckingEnabled, rootPaneCheckingEnabled);
            
           /* ---- Reload la frame pour passer en mode modifier et ajouter les compétences --- */
            //Load Frame with selected ID
            AjouterMissionJFrame apf = new AjouterMissionJFrame();
            apf.setVisible(true);
            /* -- Envoie de l'id pour remplir la frame, envois de la ligne pour actualiser --------*/
            apf.remplirFormMission(idM, jtB,jtB.getSelectedRow(), 0, apf);   
            updateUIManager();
            dispose(); //ferme la fenêtre
            }
    }
    
    public int getRowById(JTable model, int jtId) {
        
        int found = 0;
        int idToFound = -1;

        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            try{
                Object colIdValue = model.getModel().getValueAt(jtB.convertRowIndexToModel(i), 0);
                 
                   //parse object to string then int
                   String stringId = (String) colIdValue;
                    idToFound = Integer.parseInt(stringId);
                    //System.out.println("idToFind"+idToFound);
            }catch(Exception e){} 
                if (idToFound == jtId) {
                    found = i;
                    //System.out.println("fund"+found);
                    break;
                }
           
        }
        return found;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancel;
    private javax.swing.JButton jBtnDelMission;
    private javax.swing.JButton jBtnEnregistrer;
    private javax.swing.JButton jButtonAddParticip;
    private javax.swing.JButton jButtonAjouterComp;
    private javax.swing.JButton jButtonEnd;
    private javax.swing.JButton jButtonInProg;
    private javax.swing.JButton jButtonPlan;
    private javax.swing.JButton jButtonPrepare;
    private javax.swing.JButton jButtonSupprComp;
    private javax.swing.JButton jButtonSupprParticip;
    private com.toedter.calendar.JDateChooser jDCDateDeb;
    private com.toedter.calendar.JDateChooser jDCDateFin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jListAjouterCompetence;
    private javax.swing.JList<String> jListAjouterParticipant;
    private javax.swing.JList<String> jListCompetences;
    private javax.swing.JList<String> jListParticipant;
    private javax.swing.JProgressBar jPBEtat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCompetences;
    private javax.swing.JPanel jPanelGauche;
    private javax.swing.JPanel jPanelGeneral;
    private javax.swing.JPanel jPanelParticipants;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JPanel jPanelValidation;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextNbPersMin;
    // End of variables declaration//GEN-END:variables
}
