/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author guilhem
 */
public class Mission {
    private Date dateDebut;
    private Date dateFinEstime;
    private Date dateFinReel;
    private int etat;
    private ArrayList<Competence> listeCompetences;
    private ArrayList<Personnel> listePersonnels;
    private int nbPersMin;

    public Mission(Date dateDebut, Date dateFinEstime, Date dateFinReel, int etat, ArrayList<Competence> listeCompetences, ArrayList<Personnel> listePersonnels, int nbPersMin) {
        this.dateDebut = dateDebut;
        this.dateFinEstime = dateFinEstime;
        this.dateFinReel = dateFinReel;
        this.etat = etat;
        this.listeCompetences = null;
        this.listePersonnels = null;
        this.nbPersMin = nbPersMin;
    }
    
    public void ajouterCompetence(Competence c){
        listeCompetences.add(c);
    }
    
    public void supprimerCompetence(Competence c){
        listeCompetences.remove(c);
    }
    
    public void afficherCompetences(){
        for(Competence c : listeCompetences){
            c.toString();
        }
    }
    
    public void ajouterPersonnel(Personnel p){
        listePersonnels.add(p);
    }
    
    public void supprimerPersonnel (Personnel p){
        listePersonnels.remove(p);
    }
    
    public void afficherPersonnel(){
        for (Personnel p : listePersonnels){
            p.toString();
        }
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFinEstime() {
        return dateFinEstime;
    }

    public Date getDateFinReel() {
        return dateFinReel;
    }

    public int getEtat() {
        return etat;
    }

    public ArrayList<Competence> getListeCompetences() {
        return listeCompetences;
    }

    public ArrayList<Personnel> getListePersonnels() {
        return listePersonnels;
    }

    public int getNbPersMin() {
        return nbPersMin;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFinEstime(Date dateFinEstime) {
        this.dateFinEstime = dateFinEstime;
    }

    public void setDateFinReel(Date dateFinReel) {
        this.dateFinReel = dateFinReel;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setListeCompetences(ArrayList<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public void setListePersonnels(ArrayList<Personnel> listePersonnels) {
        this.listePersonnels = listePersonnels;
    }

    public void setNbPersMin(int nbPersMin) {
        this.nbPersMin = nbPersMin;
    }
    
}
