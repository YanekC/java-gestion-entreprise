package Model;

import Util.ExportCSV;
import Util.ExportInterface;
import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe entreprise permet de centraliser les listes de competences et de personnel
 * @author Yanek, SanDeox
 */
public class Entreprise {
    
    private static Entreprise ent = new Entreprise();
    
    private static HashMap<String, Competence> competences;
    private static HashMap<Integer, Personnel> personnels;
    private static HashMap<Integer, Mission> missions;
    
    private Entreprise(){
        competences = new HashMap<>();
        personnels = new HashMap<>();
        missions = new HashMap<>();
    }
        
    public static void chargerFichiers(File fPersonnels, File fCompetences, File fCompetencesPerso, File fMission, File fCompetenceMission, File fPersonnelMission){
        ImportInterface i = new ImportCSV();
        try{
            i.importer(fPersonnels, fCompetences, fCompetencesPerso, fMission, fCompetenceMission, fPersonnelMission, personnels, competences, missions);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void sauverFichiers(File fPersonnels, File fCompetences, File fCompetencesPerso, File fMission, File fCompetenceMission, File fPersonnelMission){
        ExportInterface e = new ExportCSV();
        
        try{
            e.exporter(fPersonnels, fCompetences, fCompetencesPerso, fMission, fCompetenceMission, fPersonnelMission, competences, personnels, missions);
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static Personnel findPersonnelById (int id){
        return personnels.get(id); // retourne la personne par l'id de l'hashmap
    }
    
    public static Mission findMissionById (int id){
        return missions.get(id); // retourne la mission par l'id de l'hashmap
    }
  
    public static String afficherInfoEnt(){
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
        for (Map.Entry m : missions.entrySet()) {
            s += "\n"+m.getKey()+" : "+m.getValue();
        }
        
        return s;
    }
    
    public static String afficherPersonnel(){
        String s = "";
        s = "Liste des Personnels de l'entreprise : ";
        s += "\n Liste du personnel : ";
        for(Map.Entry p : personnels.entrySet()){
            s += "\n"+p.getKey()+" : "+p.getValue();
        }
        
        return s;
    }
    
    public static HashMap<Integer, Personnel> getlistePersonnel(){
        return personnels;
    }

    public static HashMap<String, Competence> getCompetences() {
        
        return competences;
    }
    
    public static HashMap<String, Competence> getPersonnelCompetence(Personnel p){
        HashMap<String, Competence> competenceAcquise = new HashMap();
        // parcours la liste des id des compétences de la personne
        for(String idCompetence : p.getListeCompetences()){
            try{
                if(competences.containsKey(idCompetence)){
                    competenceAcquise.put(idCompetence,competences.get(idCompetence));
                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }
        return competenceAcquise;
    }
    
    public static  HashMap<String, Competence> getMissionCompetence(Mission m){
         HashMap<String, Competence> competenceRecquise = new HashMap();
        // parcours la liste des id des compétences de la personne
        for(String idCompetence : m.getListeCompetences()){
            try{
                if(competences.containsKey(idCompetence)){
                    competenceRecquise.put(idCompetence,competences.get(idCompetence));
                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }
        return competenceRecquise;
    }
    
    public static  HashMap<Integer, Personnel> getMissionPersonnel(Mission m){
        HashMap<Integer, Personnel> personnnelRaccorde = new HashMap();
        // parcours la liste des id des compétences de la personne
        for(String idPersonnels : m.getListePersonnels()){
            try{
                //Pas mal l'ID sous format String du getListePersonnels
                int persoId = Integer.parseInt(idPersonnels);
                if(personnels.containsKey(persoId)){
                    personnnelRaccorde.put(persoId,personnels.get(persoId));
                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }
        return personnnelRaccorde;
    }
    
    
    
    public static int addPersonnel(Personnel p) {
         int id = getLastId()+1;
         personnels.put(id, p);
         return id;
         
    }
    
    public static int addMission(Mission m) {
         int id = getLastId()+1;
         missions.put(id, m);
         return id;
         
    }
    
    public static void removePersonnel (Personnel p, int id) {
        personnels.remove(id, p);
    }
    
    public static void updBasicValuePersonnel (int id, String nom, String prenom, Calendar dateNaiss){
        HashMap<Integer, Personnel> personnelsUPD = personnels;
        personnelsUPD.get(id).setNom(nom);
        personnelsUPD.get(id).setPrenom(prenom);
        personnelsUPD.get(id).setDateNaiss(dateNaiss);
    }
    
    public static void updBasicValueMission (int id, int NbMini, String nom, Calendar dateDeb, Calendar dateFin){
        HashMap<Integer, Mission> missionsUPD = missions;
        missionsUPD.get(id).setNom(nom);
        missionsUPD.get(id).setNbPersMin(NbMini);
        missionsUPD.get(id).setDateDebut(dateDeb);
        missionsUPD.get(id).setDateFinEstime(dateFin);
    }
    
    
    
    public static int getLastId(){
        int id=0;
        for(Map.Entry p : personnels.entrySet()){
             id=(Integer) p.getKey();
        }
        return id;
    }
    
    public static String getIdCompetenceByFrName(String nameFr){
        String idC = "";
        for(Map.Entry<String, Competence> c : competences.entrySet()){
             if(nameFr.equals(c.getValue().getLibelleFra())){
                idC = c.getKey();
                break;
             }
        }
        return idC;
    }
    
    public static int getIdPersonnelByName(String nomPrenom){
        int idP = 0;
        for(Map.Entry<Integer, Personnel> p : personnels.entrySet()){
            String compare = p.getValue().getNom() + " "+ p.getValue().getPrenom();
             if(nomPrenom.equals(compare)){
                idP = p.getKey();
                break;
             }
        }
        return idP;
    }

    public static HashMap<Integer, Mission> getMissions() {
        return missions;
    }
    
    
}
