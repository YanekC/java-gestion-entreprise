/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Competence;
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

    @Override
    public boolean exporter(File fPersonnels, File fCompetences, File fCompetencesPerso, HashMap<String, Competence> listeCompetences, HashMap<Integer, Personnel> listePersonnel) {
        
        String stringPerso = "";
        String stringCompetence = "";
        String stringCompetencesPerso = "";
        String competenceConcat = "";
        
        for(Map.Entry c : listeCompetences.entrySet()){
            Competence cpt = (Competence)c.getValue();
            stringCompetence += c.getKey()+";"+cpt.getLibelleAng()+";"+cpt.getLibelleFra()+"\n";
        }
        
        for(Map.Entry e : listePersonnel.entrySet()){
            Personnel p = (Personnel)e.getValue();
            stringPerso += p.getPrenom()+";"+p.getNom()+";"+Personnel.formatDate.format(p.getDateNaissCalendar().getTime())+";"+e.getKey()+"\n";
            
            competenceConcat = "";
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
            
        }
        catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        return false;
    }
    
}
