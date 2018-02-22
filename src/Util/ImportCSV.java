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
public class ImportCSV implements ImportInterface{

    @Override
    public void importer(File fPersonnels, File fCompetences, File fCompetencesPerso, ArrayList<Personnel> listePersonnels, ArrayList<Competence> listeCompetences) {
        listeCompetences = this.importCompetence(fCompetences);
    }
    
    private ArrayList<Competence> importCompetence(File f){
        
    }
            
    
    
}
