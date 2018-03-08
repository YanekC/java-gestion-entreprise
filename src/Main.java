
import Model.Entreprise;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanek
 */
public class Main {
    public static void main(String[] args) {
        File fPersonnels = new File("static\\competences_personnel.csv");
        File fCompetencesPerso = new File("static\\liste_personnel.csv");
        File fCompetences = new File("static\\liste_competences.csv");
        
        Entreprise ent = new Entreprise(fPersonnels, fCompetences, fCompetencesPerso);
        
    }
}
