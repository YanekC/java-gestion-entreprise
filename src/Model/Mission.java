/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Personnel.formatDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.*;

//@TODO Ajouter des constante pour l'état

/**
 *
 * @author guilhem
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
    
    public final int ETAT_PLANIFIE = 0;
    public final int ETAT_EN_COURS = 1;
    public final int ETAT_TERMINE = 2;

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
    
    public String getEtatString(){
         String etat = "";
        switch (this.etat){
            case ETAT_PLANIFIE :
                etat = "Planifée";
                break;
            case ETAT_EN_COURS : 
                etat = "En cours";
                break;
            case ETAT_TERMINE : 
                etat = "Terminée";
                break;
        }
        return etat;
    }
    
    public void ajouterCompetence(String c){
        // Regex pour matcher le code d'une Competence
        Pattern p = Pattern.compile("[a-zA-Z]\\.\\d\\.");
        Matcher m = p.matcher(c);
        if(m.matches()){
            listeCompetences.add(c);
        }
    }
    
    public void supprimerCompetence(String c){
        listeCompetences.remove(c);
    }
    
    public void afficherCompetences(){
        for(String c : listeCompetences){
            System.out.println(c);
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
            System.out.println(p);
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
    
    public void updateEtat(){
        Calendar today = Calendar.getInstance();
        //On demarre la mission que si il y a assez de personnel
        if(dateDebut.before(today) && listePersonnels.size() >= nbPersMin){
            this.etat = ETAT_EN_COURS;
            if(this.dateFinReel.before(today)){
                this.etat = ETAT_TERMINE;
            }
        }
        else{
            this.etat = ETAT_PLANIFIE;
        }
    }

    public String getNom() {
        return nom;
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

    public int getNbPersMin() {
        return nbPersMin;
    }
    
    
    
    
}