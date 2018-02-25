
import Model.Competence;
import Model.Personnel;
import Util.ImportCSV;
import Util.ImportInterface;
import java.io.File;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yanek
 */
public class TestImportCSV {
    public static void main(String[] args) {
        ImportInterface utilImport = new ImportCSV();
        File fPersonnels = new File("static\\liste_personnel.csv");
        File fCompetences = new File("static\\liste_competences.csv");
        File fCompetencesPerso = new File("static\\competences_personnel.csv");
        
        ArrayList<Personnel> listePersonnels = new ArrayList();
        ArrayList<Competence> listeCompetences = new ArrayList();
        
        try{
            utilImport.importer(fPersonnels, fCompetences, fCompetencesPerso, listePersonnels, listeCompetences);
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println(listeCompetences);
        System.out.println("----------Competences----------");
        for(Competence c : listeCompetences){
            System.out.println(c);
        }
        /*
        System.out.println("----------Personnels----------");
        for(Personnel p : listePersonnels){
            System.out.println(p);
        }*/
    }
}
