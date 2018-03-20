/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Personnel;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author guilhem
 */
public class PersonnelController extends EntrepriseController{

    public Personnel findByNomPrenom (String nomPrenom){
        Personnel p = null;
        
        for(Map.Entry<Integer, Personnel> e : listePersonnels.entrySet()){
            String np = e.getValue().getNom() + " " + e.getValue().getPrenom();
            
            if(np.equals(nomPrenom)){
                p = e.getValue();
            }
        }
        
        return p;
    }
    
    public Personnel findById (int id){
        Personnel p = null;
        System.out.println("id : "  +id);
        
        for(Map.Entry<Integer, Personnel> e : listePersonnels.entrySet()){
            int idE = e.getValue().getId();
            System.out.println("ide : "  +idE);
            if(idE == id){
                p = e.getValue();
            }
        }
        
        
        
        return p;
    }
    
}


