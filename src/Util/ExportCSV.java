/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Competence;
import Model.Mission;
import Model.Personnel;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Yanek
 */
public class ExportCSV implements ExportInterface{

    /**
     * Réécris les fichiers passés en parametre avec les HashMaps passés en parametre
     * @param fPersonnels
     * @param fCompetences
     * @param fCompetencesPerso
     * @param listeCompetences
     * @param listePersonnel
     * @return Vrai si les fichiers ont bien été réécris
     * @throws Exception 
     */
    @Override
    public boolean exporter(File fPersonnels, File fCompetences, File fCompetencesPerso, File fMission, File fCompetenceMission, File fPersonnelMission,
            HashMap<String, Competence> listeCompetences, HashMap<Integer, Personnel> listePersonnel, HashMap<Integer, Mission> lstMission) throws Exception{
        
        boolean ret;
        String stringPerso = "";
        String stringCompetence = "";
        String stringCompetencesPerso = "";
        String stringMission = "";
        String stringCompMission = "";
        String stringPersoMission = "";
        
        
        //Competence\\
        for(Map.Entry c : listeCompetences.entrySet()){
            Competence cpt = (Competence)c.getValue();
            stringCompetence += c.getKey()+";"+cpt.getLibelleAng()+";"+cpt.getLibelleFra()+"\n";
        }
        
        //Personnel\\
        for(Map.Entry e : listePersonnel.entrySet()){
            Personnel p = (Personnel)e.getValue();
            stringPerso += p.getNom()+";"+p.getPrenom()+";"+Personnel.formatDate.format(p.getDateNaissCalendar().getTime())+";"+e.getKey()+"\n";
            
            String competenceConcat = "";
            Iterator it = p.getListeCompetences().iterator();
            if(it.hasNext()){
                competenceConcat = (String) it.next();
                while(it.hasNext()){
                    competenceConcat += ";"+it.next();
                }
                competenceConcat += "\n";
            }
            
            stringCompetencesPerso += (Integer)e.getKey()+";"+competenceConcat;
        }
        
        //Missions\\
        for(Map.Entry m : lstMission.entrySet()){
            Mission miss = (Mission)m.getValue();
            stringMission += m.getKey()+";"+miss.getNom()+";"+miss.getDateDebutString()+";"+miss.getDateFinEstimeString()+";"+miss.getDateFinReelString()+";"+miss.getNbPersMin()+"\n";
            
            String competenceConcat = "";
            Iterator itMiss = miss.getListeCompetences().iterator();
            if(itMiss.hasNext()){
                competenceConcat = (String) itMiss.next();
                while(itMiss.hasNext()){
                    competenceConcat += ";"+itMiss.next();
                }
            }
            
            stringCompMission += (Integer)m.getKey()+";"+competenceConcat+"\n";
            
            String persoConcat = "";
            Iterator itPers = miss.getListePersonnels().iterator();
            if(itPers.hasNext()){
                persoConcat = (String) itPers.next();
                while(itPers.hasNext()){
                    persoConcat += ";"+itPers.next();
                }
            }
            
            stringPersoMission += (Integer)m.getKey()+";"+persoConcat+"\n";
            
        }
        
        try{
            //Ecriture dans le fichier personnel
            BufferedWriter writerPerso = new BufferedWriter(new FileWriter(fPersonnels));
            writerPerso.write(stringPerso);
            writerPerso.close();
            
            //Ecriture dans le fichier competence
            BufferedWriter writerCpt = new BufferedWriter(new FileWriter(fCompetences));
            writerCpt.write(stringCompetence);
            writerCpt.close();
            
            //Ecriture dans le fichier competence_perso
            BufferedWriter writerCptPers = new BufferedWriter(new FileWriter(fCompetencesPerso));
            writerCptPers.write(stringCompetencesPerso);
            writerCptPers.close();
            
            //Ecriture dans le fichier mission
            BufferedWriter writerMiss = new BufferedWriter(new FileWriter(fMission));
            writerMiss.write(stringMission);
            writerMiss.close();
            
            //Ecriture dans le fichier mission
            BufferedWriter writerCptMiss = new BufferedWriter(new FileWriter(fCompetenceMission));
            writerCptMiss.write(stringCompMission);
            writerCptMiss.close();
            
            //Ecriture dans le fichier mission
            BufferedWriter writerPersMiss = new BufferedWriter(new FileWriter(fPersonnelMission));
            writerPersMiss.write(stringPersoMission);
            writerPersMiss.close();
            
            ret = true;
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
            ret = false;
            throw new Exception("Probleme lors de l'enregistrement des fichiers");
        }
        
        return false;
    } 
}
