/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Util.ExportCSV;
import Util.ExportInterface;
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
    
    public Entreprise(File fPersonnels, File fCompetences, File fCompetencesPerso){
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

        ExportInterface ei = new ExportCSV();
        ei.exporter(fPersonnels, fCompetences, fCompetencesPerso, competences, personnels);
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
}
