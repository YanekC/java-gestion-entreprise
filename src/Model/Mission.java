/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.*;

//@TODO Ajouter des constante pour l'état

/**
 *
 * @author guilhem
 */
public class Mission {
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

    public Mission(Date dateDebut, Date dateFinEstime, Date dateFinReel, int etat, ArrayList<Competence> listeCompetences, ArrayList<Personnel> listePersonnels, int nbPersMin) {
        
        this.dateDebut = Calendar.getInstance();
        this.dateDebut.setTime(dateDebut);
        this.dateFinEstime = Calendar.getInstance();
        this.dateFinEstime.setTime(dateFinEstime);
        this.dateFinReel = Calendar.getInstance();
        this.dateFinReel.setTime(dateFinReel);
        
        this.etat = etat;
        this.listeCompetences = null;
        this.listePersonnels = null;
        this.nbPersMin = nbPersMin;
    }
    
    public String toString(){
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
        
        return "Date de debut : "+this.dateDebut+
                "\nDate de fin estimée : "+this.dateFinEstime+
                "\nEtat : "+etat+
                "\nCompetences requises : "+
                "\nPersonnels participants : "+
                "\nNombre de personnes requises : "+this.nbPersMin;
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
    
    public ArrayList<String> getListePersonnel() {
        ArrayList<String> ret = new ArrayList<>();
        for(String s : listePersonnels){
            ret.add(s);
        }
        return ret;
    }
}