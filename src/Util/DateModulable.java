/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.util.Calendar;

/**
 *
 * @author Yanek
 */
public class DateModulable {
    private static Calendar date = Calendar.getInstance();
    
    /*
    * Retourne la date actuelle voulu pour l'application
    */
    public static Calendar getDate(){
        return date;
    }
    
    /*
    * Synchronise la date de l'application avec la date actuelle.
    */
    public static void sync(){
        date = Calendar.getInstance();
    }
    
    /**
     * Modifie la date de l'application du nombre de jour/mois/année passés en param
     * Possibilité de passer des jours/mois/année négatifs
     * @param jour Nombre de jour a modifier
     * @param mois Nombre de mois a modifier
     * @param annee Nombre d'année a modifier
     */
    public static void inc(int jour, int mois, int annee){
        date.add(Calendar.HOUR, jour);
        date.add(Calendar.MONTH, mois);
        date.add(Calendar.YEAR, annee);
    }
}
