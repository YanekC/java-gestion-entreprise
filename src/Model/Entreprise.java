package Model;

import Util.ExportCSV;
import Util.ExportInterface;
import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.*;

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
    
    public static Personnel getPersonnelById(int id){
        Personnel monP = null;
        for(Map.Entry<Integer, Personnel> p : personnels.entrySet()){
             if(p.getKey() == id){
                monP = p.getValue();
                break;
             }
        }
        return monP;
    }
    
    public static void removeMission(int idM){ 
        Mission miss = findMissionById(idM); 
        HashMap<Integer, Personnel> personnelDeLaMission = getMissionPersonnel(miss); 

        for(Map.Entry<Integer, Personnel> pers : personnelDeLaMission.entrySet()){ 
        miss.supprimerPersonnel(String.valueOf(pers.getKey())); 
        } 

        missions.remove(idM); 
    }

    public static HashMap<Integer, Mission> getMissions() {
        for(Map.Entry m : missions.entrySet()){
            ((Mission)m.getValue()).updateEtat();
        }
        return missions;
    }
    
    /*
    * Retourne les personnels ayant le plus de competences en commun avec la mission
    */
    public static ArrayList<Integer> getPersonnelSuggere(Mission m){
        
        ArrayList<Integer> ret = new ArrayList<>();
        //HashMaps contenant l'id d'un perso et le nb de competences qu'il a en commun avec la mission
        HashMap<Integer, Integer> tablePersonnelSugg = new HashMap<>();
        
        
        for(Map.Entry p : personnels.entrySet()){
            
            //Si la personne n'a pas deja été attribué a la mission
            if(!m.persoParticipe((Integer)p.getKey())){

                Personnel personnel = (Personnel)p.getValue();
                int nbMatchComp = 0;
                
                for(String compMiss : m.getListeCompetences()){
                    for(String compPers : personnel.getListeCompetences()){

                        if(compMiss.equals(compPers)){
                            nbMatchComp++;
                        }
                    }
                }
                tablePersonnelSugg.put((Integer)p.getKey(), nbMatchComp);
            }
        }
        
        for(Map.Entry perso : sortByComparator(tablePersonnelSugg, false).entrySet()){
            ret.add((Integer)perso.getKey());
        }
        
        System.out.println(ret);
        return ret;
    }
    
    
    
    
    //Tri d'une map
    private static Map<Integer, Integer> sortByComparator(Map<Integer, Integer> unsortMap, final boolean order)
    {

        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>()
        {
            public int compare(Map.Entry<Integer, Integer> o1,
                    Map.Entry<Integer, Integer> o2)
            {
                if (order)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Integer, Integer> sortedMap = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
