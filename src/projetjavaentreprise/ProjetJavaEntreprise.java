/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjavaentreprise;

/**
 *
 * @author user
 */
public class ProjetJavaEntreprise {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Branche de dev d'alex
        System.out.println("Branche alex");
        //Récupérer l'adresse du fichier
        String csvFile = "\\\\ens.fsi\\ens\\HOME-ETUD\\psl2094a\\Downloads\\fichiers\\liste_competences.csv";
        //Vérifier l'extension du fichier
        if(csvFile.matches(".*(.csv)$")){
           FichierLoader test = new FichierLoader(csvFile); 
        }
        if(csvFile.matches(".*(.xml)$")){
            
        }
        
    }
    
}
