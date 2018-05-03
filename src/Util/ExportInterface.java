/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Model.Competence;
import Model.Personnel;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Yanek
 */
public interface ExportInterface {
    
    boolean exporter(File fPersonnels, File fCompetences, File fCompetencesPerso, HashMap<String, Competence> listeCompetences, HashMap<Integer, Personnel> listePersonnel);
}
