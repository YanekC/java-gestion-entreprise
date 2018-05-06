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
    
    public static int addPersonnel(Personnel p) {
         int id = getLastId()+1;
         personnels.put(id, p);
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
    

    public static HashMap<Integer, Mission> getMissions() {
        for(Map.Entry m : missions.entrySet()){
            ((Mission)m.getValue()).updateEtat();
        }
        return missions;
    }
    
    /*
    * Retourne les personnels ayant le plus de competences en commun avec la mission
    */
    public static ArrayList<Integer> getPersonnelSuggere(int idMission){
        
        ArrayList<Integer> ret = new ArrayList<>();
        //HashMaps contenant l'id d'un perso et le nb de competences qu'il a en commun avec la mission
        HashMap<Integer, Integer> tablePersonnelSugg = new HashMap<>();
        
        Mission m = missions.get(idMission);
        
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
