/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Competence;
import Model.Entreprise;
import Model.Personnel;
import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author guilhem
 */
public class EntrepriseController {
    
    HashMap<Integer, Personnel> listePersonnels = new HashMap<>();
    ArrayList<Competence> listeCompetences = new ArrayList();
    Entreprise ent;

    public EntrepriseController() {
        ent = new Entreprise();
        listePersonnels = ent.getlistePersonnel();
        listeCompetences = ent.getCompetences();
    }
    
    public HashMap<Integer, Personnel> getlistePersonnel(){
        
        return listePersonnels;
    }

    public ArrayList<Competence> getListeCompetences() {
        return listeCompetences;
    }
    
    
    public String toString(){
        String s = "";
        s = "Liste des competences de l'entreprise : ";
        for(Competence c : listeCompetences){
            s += "\n"+c;
        }
        s += "\n Liste du personnel : ";
        for(Map.Entry p : listePersonnels.entrySet()){
            s += "\n"+p.getKey()+" : "+p.getValue();
        }
        
        return s;
    }
    
}
