/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Competence;
import java.util.ArrayList;

/**
 *
 * @author guilhem
 */
public class CompetenceController extends BaseController{
    
    public ArrayList<Competence> getListeCompetences(){
        return listeCompetences;
    }
    
    public Competence findCompetenceById(String id){
        Competence result = null;
        
        for(Competence c : listeCompetences){
            if(id.equals(c.getIdC())){
                result = c;
            }
        }
        
        return result;
    }
    
}
