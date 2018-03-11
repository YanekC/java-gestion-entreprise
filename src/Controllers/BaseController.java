/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Competence;
import Model.Personnel;
import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author guilhem
 */
public class BaseController {
    ImportInterface utilImport = new ImportCSV();
    File fPersonnels = new File("resources\\csv\\liste_personnel.csv");
    File fCompetences = new File("resources\\csv\\liste_competences.csv");
    File fCompetencesPerso = new File("resources\\csv\\competences_personnel.csv");
    
    HashMap<Integer, Personnel> listePersonnels = new HashMap<>();
    ArrayList<Competence> listeCompetences = new ArrayList();
    
    public void chargerCSV() throws Exception{
        utilImport.importer(fPersonnels, fCompetences, fCompetencesPerso, listePersonnels, listeCompetences);
    }
    
}
