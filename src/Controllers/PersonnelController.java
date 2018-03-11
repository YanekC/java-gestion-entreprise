/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Personnel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author guilhem
 */
public class PersonnelController extends BaseController{
    
    public HashMap<Integer, Personnel> getlistePersonnel(){
        
        return listePersonnels;
    }
    
    public HashMap<Integer, Personnel> getListePersonnelSort(){
        //listePersonnels unsortMap = new HashMap<String, String>();
        return listePersonnels;
    }
    
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
    
//    public Personnel findById (Integer Id){
//        Personnel p = null;
//        
//        for(Map.Entry<Integer, Personnel> e : listePersonnels.entrySet()){
//            String np = e.getValue().getNom() + " " + e.getValue().getPrenom();
//            
//            if(np.equals(nomPrenom)){
//                p = e.getValue();
//            }
//        }
//        
//        return p;
//    }
    
}
