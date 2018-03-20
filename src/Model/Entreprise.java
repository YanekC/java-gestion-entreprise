/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yanek
 */
public class Entreprise {
    
    ArrayList<Competence> competences;
    HashMap<Integer, Personnel> personnels;
    ArrayList<Mission> missions;
    
    File fPersonnels = new File("resources\\csv\\liste_personnel.csv");
    File fCompetences = new File("resources\\csv\\liste_competences.csv");
    File fCompetencesPerso = new File("resources\\csv\\competences_personnel.csv");
    
    public Entreprise(){
        this.competences = new ArrayList<>();
        this.personnels = new HashMap<>();
        this.missions = new ArrayList<>();
        
        ImportInterface i = new ImportCSV();
        try{
            i.importer(fPersonnels, fCompetences, fCompetencesPerso, personnels, competences);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }        
    }
    
    public String toString(){
        String s = "";
        s = "Liste des competences de l'entreprise : ";
        for(Competence c : competences){
            s += "\n"+c;
        }
        s += "\n Liste du personnel : ";
        for(Map.Entry p : personnels.entrySet()){
            s += "\n"+p.getKey()+" : "+p.getValue();
        }
        s += "\n Liste des missions : ";
        for (Mission m : missions) {
            s += "\n"+m;
        }
        
        return s;
    }
    
    public HashMap<Integer, Personnel> getlistePersonnel(){
        
        return personnels;
    }

    public ArrayList<Competence> getCompetences() {
        return competences;
    }
    
    
}
