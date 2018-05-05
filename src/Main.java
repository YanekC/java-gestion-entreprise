
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
        
        File fPersonnels = new File("resources\\csv\\liste_personnel.csv");
        File fCompetences = new File("resources\\csv\\liste_competences.csv");
        File fCompetencesPerso = new File("resources\\csv\\competences_personnel.csv");
        File fMission = new File("resources\\csv\\liste_missions.csv");
        File fCompetenceMission = new File("resources\\csv\\competence_mission.csv");
        File fPersonnelMission = new File("resources\\csv\\personnel_mission.csv");
        
        // dans le but de faire évoluer l'application
        Entreprise.chargerFichiers(fPersonnels, fCompetences, fCompetencesPerso, fMission, fCompetenceMission, fPersonnelMission);
        
        Entreprise.sauverFichiers(fPersonnels, fCompetences, fCompetencesPerso, fMission, fCompetenceMission, fPersonnelMission);
    }
}
