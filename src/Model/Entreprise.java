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
        
    }
}
