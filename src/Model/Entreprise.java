package Model;

import Util.DateModulable;
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
        
    /**
     * Permet de charger les fichiers dans les arraylist de la classe.
     * @param fPersonnels Le fichier de personnel
     * @param fCompetences Le fichier de la liste des competences
     * @param fCompetencesPerso Le fichier de la liste des competence de chaque personnel
     * @param fMission Le fichier de la liste des missions
     * @param fCompetenceMission Le fichier des competence necessaire pour chaque mission
     * @param fPersonnelMission Le fichier des personnels associé a chaque mission
     */
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
    
    /**
     * Permet de sauvegarder les fichiers avec les informations contenu dans les arraylist de la classe.
     * @param fPersonnels Le fichier de personnel
     * @param fCompetences Le fichier de la liste des competences
     * @param fCompetencesPerso Le fichier de la liste des competence de chaque personnel
     * @param fMission Le fichier de la liste des missions
     * @param fCompetenceMission Le fichier des competence necessaire pour chaque mission
     * @param fPersonnelMission Le fichier des personnels associé a chaque mission
     */
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
    
    /**
     * Retourne le personnel de l'id passé en parametre
     * @param id L'id du personnel recherché
     * @return Le personnel recherché
     */
    public static Personnel findPersonnelById (int id){
        return personnels.get(id); // retourne la personne par l'id de l'hashmap
    }
    
    /**
     * Retourne la mission de l'id passé en parametre
     * @param id L'id de la mission recherchée
     * @return La mission recherchée
     */
    public static Mission findMissionById (int id){
        return missions.get(id); // retourne la mission par l'id de l'hashmap
    }
  
    /**
     * Affiche les informations des arrayList de la classe
     * @return Un String contenant les infos de la classe
     */
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
    
    /**
     * Recupere les informations des personnels de la classe
     * @return une string contenant les information de l'arraylist de personnel
     */
    public static String afficherPersonnel(){
        String s = "";
        s = "Liste des Personnels de l'entreprise : ";
        s += "\n Liste du personnel : ";
        for(Map.Entry p : personnels.entrySet()){
            s += "\n"+p.getKey()+" : "+p.getValue();
        }
        
        return s;
    }
    
    /**
     * Getteur de la liste de personnel
     * @return la liste de personnel
     */
    public static HashMap<Integer, Personnel> getlistePersonnel(){
        return personnels;
    }

    /**
     * getteur de la liste de competence
     * @return la liste de competence
     */
    public static HashMap<String, Competence> getCompetences() {
        
        return competences;
    }
    
    /**
     * Recupere les competence d'un personnel contenu dans l'entreprise
     * @param p Le personnel dont on veuxt connaitre les competences
     * @return Les competences du personnel passé en parametre
     */
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
    
    /**
     * Recupere les competence d'une mission contenu dans l'entreprise
     * @param m La mission a analyser
     * @return Les competences de la mission
     */
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
    
    /**
     * Recupere les personnels d'une mission contenu dans l'entreprise
     * @param m La mission a analser
     * @return Les personnels associés a la mission
     */
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
    
    /**
     * AJoute un personnel
     * @param p Le personnel a ajouter
     * @return l'id du nouveau personnel
     */
    public static int addPersonnel(Personnel p) {
         int id = getLastIdPersonnel()+1;
         personnels.put(id, p);
         return id;
         
    }
    
    /**
     * Ajoute une mission
     * @param m la mission a ajouter
     * @return l'id de la nouvelle mission
     */
    public static int addMission(Mission m) {
         int id = getLastIdMission()+1;
         missions.put(id, m);
         return id;
         
    }
    
    /**
     * Supprime le personnel passé en parametre
     * @param p le personnel a supprimer
     * @param id l'id du personnel a supprimer
     */
    public static void removePersonnel (Personnel p, int id) {
        personnels.remove(id, p);
    }
    
    /**
     * Met a jour les infos basique sur un personnel
     * @param id Le nouvel id du personnel
     * @param nom Le nouveau nom 
     * @param prenom Le prenom
     * @param dateNaiss La nouvelle date de naissance
     */
    public static void updBasicValuePersonnel (int id, String nom, String prenom, Calendar dateNaiss){
        HashMap<Integer, Personnel> personnelsUPD = personnels;
        personnelsUPD.get(id).setNom(nom);
        personnelsUPD.get(id).setPrenom(prenom);
        personnelsUPD.get(id).setDateNaiss(dateNaiss);
    }
    
    /**
     * Met a jour les infos basiques sur une mission
     * @param id Le nouvel id de la mission
     * @param NbMini Le nouveau nombre minimum de personnel qui doivent etre affectés 
     * @param nom Le nouveau nom 
     * @param dateDeb La nouvelle date de debut
     * @param dateFin La nouvelle date de fin
     */
    public static void updBasicValueMission (int id, int NbMini, String nom, Calendar dateDeb, Calendar dateFin){
        HashMap<Integer, Mission> missionsUPD = missions;
        missionsUPD.get(id).setNom(nom);
        missionsUPD.get(id).setNbPersMin(NbMini);
        missionsUPD.get(id).setDateDebut(dateDeb);
        missionsUPD.get(id).setDateFinEstime(dateFin);
    }
    
    /**
     * Retourne le nombre de jour travaillé par un personnel
     * @param id id du personnel 
     * @return Le nombre de jour travaillé 
     */
    public static int getNbJourNonTrav(Integer id){
        Personnel p = personnels.get(id);
        Calendar dateFin = Calendar.getInstance();
        dateFin.set(0, 0, 0);
        boolean trouve = false;
        for(Map.Entry m : missions.entrySet()){
            Mission miss = (Mission)m.getValue();
            for(String ident : miss.getListePersonnels()){
                if(ident.equals(String.valueOf(id)) && dateFin.before(miss.getDateFinEstime())){
                    dateFin = miss.getDateFinEstime();
                    trouve = true;
                }
            }
        }
        if(trouve){
            return getNbJour(dateFin);
        }
        return -1;
    } 
    
    private static int getNbJour(Calendar date){
        Calendar today = DateModulable.getDate();
        return today.get(Calendar.DATE)-date.get(Calendar.DATE);
    }
    
    /**
     * Retourne le plus grand id de la liste du personnel
     * @return le dernier id de la liste
     */
    public static int getLastIdPersonnel(){
        int id=0;
        for(Map.Entry p : personnels.entrySet()){
             id=(Integer) p.getKey();
        }
        return id;
    }
    
    /**
     * Retourne le plus grand id de la liste des missions
     * @return le dernier id de la liste
     */
    public static int getLastIdMission(){
        int id=0;
        for(Map.Entry p : missions.entrySet()){
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
    
    public static Competence getCompetenceById(String id){
        Competence monC = null;
        for(Map.Entry<String, Competence> c : competences.entrySet()){
             if(c.getKey() == id){
                monC = c.getValue();
                break;
             }
        }
        return monC;
    }
    
    /**
     * Retounre la liste des personnels qui ne sont pas affectés a des missions
     * @return la liste des personnels
     */
    public static HashMap<Integer, Personnel> getPersoSansMiss(){
        HashMap<Integer, Personnel> ret = new HashMap<>();
        boolean participe;
        for(Map.Entry perso : personnels.entrySet()){
            participe = false;
            for(Map.Entry miss : missions.entrySet()){
                if(((Mission)miss.getValue()).persoParticipe((int)perso.getKey()) && ((Mission)miss.getValue()).getEtat() != Mission.ETAT_TERMINE){
                    participe =true;
                    break;
                }
            }
            if(!participe){
                ret.put((Integer)perso.getKey(), (Personnel)perso.getValue());
            }
            
        }
        return ret;
    }
    
    public static HashMap<Integer, Personnel> getPersoSansMissFromMiss(){
        
        
        HashMap<Integer, Personnel> ret = new HashMap<>();
        boolean participe;
        for(Map.Entry perso : personnels.entrySet()){
            participe = false;
            for(Map.Entry miss : missions.entrySet()){
                if(((Mission)miss.getValue()).persoParticipe((int)perso.getKey()) && ((Mission)miss.getValue()).getEtat() != Mission.ETAT_TERMINE){
                    participe =true;
                    break;
                }
            }
            if(!participe){
                ret.put((Integer)perso.getKey(), (Personnel)perso.getValue());
            }
            
        }
        return ret;
    }
    
    /**
     * Supprime une mission definitivement
     * @param idM l'id de la mission a supprimer
     */
    public static void removeMission(int idM){ 
        Mission miss = findMissionById(idM); 
        HashMap<Integer, Personnel> personnelDeLaMission = getMissionPersonnel(miss); 

        for(Map.Entry<Integer, Personnel> pers : personnelDeLaMission.entrySet()){ 
        miss.supprimerPersonnel(String.valueOf(pers.getKey())); 
        } 

        missions.remove(idM); 
    }

    /**
     * Retourne la liste des missions avec les etats mis a jours.
     * @return la liste des missions
     */
    public static HashMap<Integer, Mission> getMissions() {
        for(Map.Entry m : missions.entrySet()){
            ((Mission)m.getValue()).updateEtat();
        }
        return missions;
    }
    
    /**
    * Retourne les personnels ayant le plus de competences en commun avec la mission
    * @return la liste de personnel
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
        
        //System.out.println(ret);
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
