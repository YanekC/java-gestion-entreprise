/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Personnel.formatDate;
import Util.DateModulable;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

//@TODO Ajouter des constante pour l'état

/**
 *
 * @author guilhem, SanDeox
 */
public class Mission {
    
    public static SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy" );
    
    private String nom;
    private Calendar dateDebut;
    private Calendar dateFinEstime;
    private Calendar dateFinReel;
    private int etat;
    private ArrayList<String> listeCompetences;
    private ArrayList<String> listePersonnels;
    private int nbPersMin;
    
    public static final int ETAT_EN_PREPARATION = 0;
    public static final int ETAT_PLANIFIE = 1;
    public static final int ETAT_EN_COURS = 2;
    public static final int ETAT_TERMINE = 3;
    
    /**
     * Creation d'une mission
     * @param nom la dénomination
     * @param dateDebut la date de debut
     * @param dateFinEstime la date de fin estimée
     * @param dateFinReel la date de fin réelle
     * @param nbPersMin le nombre de personne minimum pour la mission
     */
    public Mission(String nom, String dateDebut, String dateFinEstime, String dateFinReel, int nbPersMin) {
        
        this.nom = nom;
        
        this.dateDebut = Calendar.getInstance();
        this.dateFinEstime = Calendar.getInstance();
        this.dateFinReel = Calendar.getInstance();
        
        try{
            this.dateDebut.setTime(formatDate.parse(dateDebut));
            this.dateFinEstime.setTime(formatDate.parse(dateFinEstime));
            this.dateFinReel.setTime(formatDate.parse(dateFinReel));
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        
        this.listeCompetences = new ArrayList<>();
        this.listePersonnels = new ArrayList<>();
        this.nbPersMin = nbPersMin;
        this.updateEtat();
    }
    
    /**
     * Creation d'une mission
     * @param nom la dénomination
     * @param dateDebut la date de debut
     * @param dateFinEstime la date de fin estimée
     * @param dateFinReel la date de fin réelle
     * @param nbPersMin le nombre de personne minimum pour la mission
     */
    public Mission(String nom, Calendar dateDebut, Calendar dateFinEstime, Calendar dateFinReel, int nbPersMin) {
        
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFinEstime = dateFinEstime;
        this.dateFinReel = dateFinReel;
        this.listeCompetences = new ArrayList<>();
        this.listePersonnels = new ArrayList<>();
        this.nbPersMin = nbPersMin;
        this.updateEtat();
    }

    public Calendar getDateDebut() {
        return dateDebut;
    }
    
    @Override
    public String toString(){
        String etat = getEtatString();
        
        String lcomp = "";
        String lmis = "";
        
        for(String c : listeCompetences){
            lcomp += c+",";
        }
        for(String p : listePersonnels){
            lmis += p+",";
        }
        
        return "Date de debut : "+formatDate.format(this.dateDebut.getTime())+
                "\nDate de fin estimée : "+formatDate.format(this.dateFinEstime.getTime())+
                "\nEtat : "+etat+
                "\nCompetences requises : "+lmis+
                "\nPersonnels participants : "+lcomp+
                "\nNombre de personnes requises : "+this.nbPersMin;
    }

    public Calendar getDateFinReel() {
        return dateFinReel;
    }

    public int getEtat() {
        return etat;
    }
    
    /**
     * Retounre l'etat de la mission sous forme de string
     * @return le string de l'état de la mission
     */
    public String getEtatString(){
         String etat = "";
        switch (this.etat){
            case ETAT_PLANIFIE :
                etat = "Planifiée";
                break;
            case ETAT_EN_COURS : 
                etat = "En cours";
                break;
            case ETAT_TERMINE : 
                etat = "Terminée";
                break;
            case ETAT_EN_PREPARATION : 
                etat = "En préparation";
                break;
        }
        return etat;
    }
    
    /**
     * Retourne la couleur associé a l'id passé en parametre
     * @param etat l'id de l'etat
     * @return la couleur 
     */
    public static Color getCouleurEtat(String etat){
        Color ret = null;
        switch (etat){
            case "Planifiée" :
                //Jaune
                ret = new Color(200,206,31);
                break;
            case "En cours" :
                //Bleu
                ret = new Color(5,127,141);
                break;
            case "Terminée" : 
                //Vert
                ret = new Color(0,102,51);
                break;
            case "En préparation" : 
                //Rouge
                ret = new Color(215,81,4);;
                break;
        }
        return ret;
    }
    
    /**
     * Ajoute une competence a la mission
     * @param c l'id de la competence
     */
    public void ajouterCompetence(String c){
        //System.out.println(c);
        // Regex pour matcher le code d'une Competence
        Pattern p = Pattern.compile("[a-zA-Z]\\.\\d+\\.");
        Matcher m = p.matcher(c);
        if(m.matches()){
            //System.out.println(c);
            listeCompetences.add(c);
        }
    }
    
    public void supprimerCompetence(String c){
        listeCompetences.remove(c);
    }
    
    public void afficherCompetences(){
        for(String c : listeCompetences){
            //System.out.println(c);
        }
    }

    public ArrayList<String> getListeCompetences() {
        ArrayList<String> ret = new ArrayList<>();
        for(String s : listeCompetences){
            ret.add(s);
        }
        return ret;
    }
    
    public void ajouterPersonnel(String p){
        // Regex pour matcher le code d'une Competence
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(p);
        if(matcher.matches()){
            listePersonnels.add(p);
        }
    }
    
    public void supprimerPersonnel (String p){
        listePersonnels.remove(p);
    }
    
    public void afficherPersonnel(){
        for (String p : listePersonnels){
            //System.out.println(p);
        }
    }
    
    /**
     * 
     * @return la liste des ids des personnels
     */
    public ArrayList<String> getListePersonnels() {
        ArrayList<String> ret = new ArrayList<>();
        for(String s : listePersonnels){
            ret.add(s);
        }
        return ret;
    }
    
    /**
     * Met a jour l'état de la mission en fonction des dates et et du personnel associé
     */
    public void updateEtat(){
        Calendar today = DateModulable.getDate();
        //Y a t'il assez de personne ?
        ArrayList<String> personnels = getListePersonnels();
        int nbPersonnel = personnels.size();
        if(this.nbPersMin <= nbPersonnel){
            if(isCompleteCompetence()){
                if(dateDebut.before(today)){
                    this.etat = ETAT_EN_COURS;
                    if(this.dateFinEstime.before(today)){
                        this.etat = ETAT_TERMINE;
                    }
                }
                else{
                   
                    this.etat = ETAT_PLANIFIE;
                }
            }
            else{
               this.etat = ETAT_EN_PREPARATION; 
            }
        }
        else{
            this.etat = ETAT_EN_PREPARATION;
        }
    }
    
    /**
     * Retourne vrai si la mission est prete a etre effectuée cad que les personnel sont asocié avec les bonne competences
     * @return vrai si la mission peut etre demarrée
     */
    public boolean isCompleteCompetence(){
            ArrayList<String> comptToCheck = listeCompetences;
            if(listeCompetences.isEmpty()){
                return false;
            }
            else{
                //Initialise à false les compétences
                HashMap<String, Boolean> allVerified = null;
                //System.err.println("Mes Comp :"+listeCompetences);
                //System.err.println("comptToCheck :"+comptToCheck);
                allVerified = setListeCompetenceToFalse(comptToCheck);  
                //System.err.println("Jai passé all verified: "+allVerified);

                //Récupère les personnels sur la mission
                ArrayList<String> personnels = listePersonnels;
                for(String idP : personnels){
                    //Instancie chaque personnel
                    Personnel p = Entreprise.getPersonnelById(Integer.valueOf(idP));
                    //Pour chaque personnel on regarde chaque compétence
                    ArrayList<String> compPerso = p.getListeCompetences();
                    for(String idC : compPerso){
                        //Pour chaque compétence à check on passe à true si la personne a la compétence
                        for(String idCToCheck : comptToCheck){
                            if(idC.equals(idCToCheck)){
                                allVerified.put(idC, true);
                            }
                        }
                    }


                }
                //System.err.println(allVerified);
              
                if(listeCompetenceVerified(allVerified)){
                    //System.err.println("verifié :"+allVerified);
                    return true;
                }
                else{
                    return false;
                } 
            }
    }
    
    public boolean listeCompetenceVerified(HashMap<String, Boolean> hsCheckComp){
        for(Map.Entry<String, Boolean> hsC : hsCheckComp.entrySet()){
            if(!hsC.getValue()){
                 return false;
            }
        }
        return true;  
    }
    
    public HashMap setListeCompetenceToFalse(ArrayList<String> competences) {
        //System.err.println("SLCTF : "+competences);
        HashMap<String, Boolean> hsToFalse = new HashMap();
        //System.err.println("Hashmap1 : ");
        for(String idC : competences){
                hsToFalse.put(idC, false);
        }
        return hsToFalse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNbPersMin(int nbPersMin) {
        this.nbPersMin = nbPersMin;
    }

    public String getDateDebutString() {
        return formatDate.format(dateDebut.getTime());
    }

    public String getDateFinEstimeString() {
        return formatDate.format(dateFinEstime.getTime());
    }

    public String getDateFinReelString() {
        return formatDate.format(dateFinReel.getTime());
    }

    public void setDateDebut(Calendar dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFinEstime(Calendar dateFinEstime) {
        this.dateFinEstime = dateFinEstime;
    }

    public int getNbPersMin() {
        return nbPersMin;
    }
    
    /**
     * Retourne vrai si le personnel participe a la mission
     * @param idPers id du personnel a analyser
     * @return vrai si le personnel participe
     */
    public boolean persoParticipe(Integer idPers){
        boolean ret = false;
        for(String p : listePersonnels){
            if(Integer.parseInt(p) == idPers){
                ret = true;
                break;
            }
        }
        return ret;
    }

    public Calendar getDateFinEstime() {
        return dateFinEstime;
    }
    
    public void setCompetences(ArrayList<String> lsComp){
        this.listeCompetences = lsComp;
    }
    
    public void setPersonnels(ArrayList<String> lsPers){
        this.listePersonnels = lsPers;
    }
    
}
