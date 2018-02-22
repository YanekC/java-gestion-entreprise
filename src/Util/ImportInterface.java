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

/**
 *
 * @author Yanek
 */
public interface ImportInterface {
    
    /**
     * Cette methode lit les fichiers passés en parametre et modifie les ArrayList 
     * en ajoutant les personnels ainsi que leurs competences
     * @param fPersonnels Le fichier contenant la liste des personnels
     * @param fCompetences Le fichier contenant la liste des competences 
     * @param fCompetencesPerso Le fichier contenant les competences de chaque personnels
     * @param listePersonnels L'arraylist regroupant le personnel dans l'appli
     * @param listeCompetences L'arrayList regroupant les competences de l'appli
     */
    void importer(File fPersonnels, File fCompetences, File fCompetencesPerso, 
            ArrayList<Personnel> listePersonnels, ArrayList<Competence> listeCompetences);    
}
