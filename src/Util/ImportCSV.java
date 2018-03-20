/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Personnel;
import java.io.File;
import java.util.ArrayList;
import Model.Competence;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Yanek
 */
public class ImportCSV implements ImportInterface{
        
    private HashMap<Integer,Personnel> listePersonnels;
    private HashMap<String, Competence> listeCompetences;
    private HashMap<Integer,ArrayList<String>> listeCompetencesPerso;
    
    @Override
    public void importer(File fPersonnels, File fCompetences, File fCompetencesPerso, HashMap<Integer, Personnel> lstPerso, HashMap<String, Competence> lstComp) throws Exception{
        this.importPersonnel(fPersonnels);
        this.importCompetence(fCompetences);
        this.importCompetencesPerso(fCompetencesPerso);
        
        //Remplissage des competences dans le personnel
        
        //Creation d'un index contenant les id des competences existantes
        ArrayList<String> indexIdComp = new ArrayList<>();
        for(Map.Entry c : listeCompetences.entrySet()){
            indexIdComp.add((String)c.getKey());
            lstComp.put((String)c.getKey(), (Competence)c.getValue());
        }
        //Maintenant on peut parser la liste de competences et les ajouter a chaque personnel
        for(Map.Entry compPers : listeCompetencesPerso.entrySet()){
            ArrayList<String> comps = (ArrayList)compPers.getValue();
            for(String idComp : comps){
                if(indexIdComp.contains(idComp)){
                    listePersonnels.get((Integer)compPers.getKey()).ajouterCompetence(idComp);
                }
                else{
                    throw new Exception("La competence suivante n'existe dans aucun fichier : "+idComp);
                }
            }
        }
        
        for(Map.Entry pers : listePersonnels.entrySet()){
            lstPerso.put((Integer)pers.getKey(), (Personnel)pers.getValue());
        }
    }
    
    private void importCompetencesPerso(File fCompetencesPerso) throws Exception{
        BufferedReader br = null;
        String ligne = "";
        int compteurLigne = 0;
        String delimiteur = ";";
        String[] ligneDecoupee;
        listeCompetencesPerso = new HashMap<>();
        ArrayList<String> competencesLigne;
        Integer idLigne;

        try {
            
            br = new BufferedReader(new FileReader(fCompetencesPerso));
            while ((ligne = br.readLine()) != null) {
                compteurLigne++;
                
                //Initialisation des valeur a null
                competencesLigne = new ArrayList<>();
                idLigne = null;
                
                //Decoupe de la ligne en cours de process
                ligneDecoupee = ligne.split(delimiteur);
                
                if(ligneDecoupee.length >= 1){
                    idLigne = Integer.parseInt(ligneDecoupee[0]);
                    for(int i = 1; i < ligneDecoupee.length; i++){
                        competencesLigne.add(ligneDecoupee[i]);
                    }
                    listeCompetencesPerso.put(idLigne, competencesLigne);
                }
                else{
                    throw new Exception("Fichier CSV Competences par personnel mal formé. Erreur a la ligne : "+ compteurLigne);
                }
            }
            
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void importPersonnel(File fPersonnels) throws Exception{
        
        BufferedReader br = null;
        String ligne = "";
        int compteurLigne = 0;
        String delimiteur = ";";
        String[] ligneDecoupee;
        listePersonnels = new HashMap<>();

        try {

            br = new BufferedReader(new FileReader(fPersonnels));
            while ((ligne = br.readLine()) != null) {
                compteurLigne++;
                
                ligneDecoupee = ligne.split(delimiteur);
                
                if(ligneDecoupee.length >= 4){
                    listePersonnels.put(Integer.parseInt(ligneDecoupee[3]), new Personnel(ligneDecoupee[0], ligneDecoupee[1], ligneDecoupee[2]));
                }
                else{
                    throw new Exception("Fichier CSV Personnels mal formé. Erreur a la ligne : "+ compteurLigne);
                }
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void importCompetence(File fCompetences) throws Exception{
        
        BufferedReader br = null;
        String ligne = "";
        int compteurLigne = 0;
        String delimiteur = ";";
        String[] ligneDecoupee;
        listeCompetences = new HashMap<>();

        try {

            br = new BufferedReader(new FileReader(fCompetences));
            while ((ligne = br.readLine()) != null) {
                compteurLigne++;
                
                ligneDecoupee = ligne.split(delimiteur);
                
                if(ligneDecoupee.length >= 3){
                    listeCompetences.put(ligneDecoupee[0], new Competence(ligneDecoupee[1], ligneDecoupee[2]));
                }
                else{
                    throw new Exception("Fichier CSV Competences mal formé. Erreur a la ligne : "+ compteurLigne);
                }
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importer(File fPersonnels, File fCompetences, File fCompetencesPerso, ArrayList<Personnel> listePersonnels, ArrayList<Competence> listeCompetences) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}