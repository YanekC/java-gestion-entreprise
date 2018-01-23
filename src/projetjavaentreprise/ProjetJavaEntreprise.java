/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjavaentreprise;

import Model.Personnel;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import util.ImportCSV;

/**
 *
 * @author user
 */
public class ProjetJavaEntreprise {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Branche Yanek");
        
        HashMap<Integer,Personnel> listePersonnel = new HashMap<>();
        
        try{
            listePersonnel = ImportCSV.importPersonnel(new File("\\\\ens.fsi\\ens\\HOME-ETUD\\cln2258a\\Downloads\\fichiers\\liste_personnel.csv"));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        
        for(Map.Entry entry : listePersonnel.entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
        
    }
    
}
