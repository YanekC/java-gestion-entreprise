/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author guilhem
 */
public class Competence {
    private String libelleAng;
    private String libelleFra;
    
    public Competence(String libelleAng, String libelleFra){
        this.libelleAng = libelleAng;
        this.libelleFra = libelleFra;
    }
    
    @Override
    public String toString(){
        return this.libelleAng+" "+this.libelleFra;
    }

    public String getLibelleAng() {
        return libelleAng;
    }

    public String getLibelleFra() {
        return libelleFra;
    }    
}
