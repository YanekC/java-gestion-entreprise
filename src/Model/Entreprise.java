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
 * La classe entreprise permet de centraliser les listes de competences et de personnel
 * @author Yanek
 */
public class Entreprise {
    
    private static Entreprise ent = new Entreprise();
    
    private static HashMap<String, Competence> competences;
    private static HashMap<Integer, Personnel> personnels;
    private static ArrayList<Mission> missions;
    
    private Entreprise(){
        competences = new HashMap<>();
        personnels = new HashMap<>();
        missions = new ArrayList<>();
    }
        
    public static void chargerFichiers(File fPersonnels, File fCompetences, File fCompetencesPerso){
        ImportInterface i = new ImportCSV();
        try{
            i.importer(fPersonnels, fCompetences, fCompetencesPerso, personnels, competences);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public static Personnel findById (int id){
        return personnels.get(id);
    }
  
    public String toString(){
        String s = "";
        s = "Liste des competences de l'entreprise : ";
        for(Map.Entry c : competences.entrySet()){
            s += "\n"+c.getKey()+" : "+c.getValue();
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
    
    public static HashMap<Integer, Personnel> getlistePersonnel(){
        
        return personnels;
    }

    public static HashMap<String, Competence> getCompetences() {
        return competences;
    }
    
    
}
