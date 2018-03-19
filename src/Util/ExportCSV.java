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
    public boolean exporter(File fPersonnels, File fCompetences, File fCompetencesPerso, ArrayList<Competence> listeCompetences, HashMap<Integer, Personnel> listePersonnel) {
        
        String stringPerso = "";
        String stringCompetence = "";
        String stringCompetencesPerso = "";
        String competenceConcat = "";
        
        for(Competence c : listeCompetences){
            stringCompetence += c.getIdC()+";"+c.getLibelleAng()+";"+c.getLibelleFra()+"\n";
        }
        
        for(Map.Entry e : listePersonnel.entrySet()){
            Personnel p = (Personnel)e.getValue();
            stringPerso += p.getPrenom()+";"+p.getNom()+";"+Personnel.formatDate.format(p.getDateNaiss().getTime())+";"+e.getKey()+"\n";
            
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
            BufferedWriter writer = new BufferedWriter(new FileWriter(fPersonnels));
            writer.write(stringPerso);

            writer.close();
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fCompetences));
            writer.write(stringCompetence);

            writer.close();
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fCompetencesPerso));
            writer.write(stringCompetencesPerso);

            writer.close();
        }catch(IOException ioe){
            System.err.println(ioe.getMessage());
        }
        
        return false;
    }
    
}
