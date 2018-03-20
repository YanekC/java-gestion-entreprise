package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Classe qui implémente un personnel.
 * 
 * @author Yanek, Guilhem, Aléxe
 */
public class Personnel {
    
    public static SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy" );
    private String nom;
    private String prenom;
    private Calendar dateNaiss;
    private ArrayList<String> listeCompetences;
    
    /**
     * Constructeur de base d'un personnel.
     * @param nom Le nom du personnel
     * @param prenom Le prenom du personnel
     * @param dateNaiss La date de naissance du personnel
     * @param id Identitfiant du personnel
     */
    public Personnel(String nom, String prenom, String dateNaiss){
        this.nom = nom;
        this.prenom = prenom;
        this.listeCompetences = new ArrayList<String>();
        this.dateNaiss = Calendar.getInstance();
        try{
            this.dateNaiss.setTime(formatDate.parse(dateNaiss));
        }
        catch(ParseException e){
            e.printStackTrace();
        }
        
    }
    
    public void ajouterCompetence(String c){
        if(!this.listeCompetences.contains(c)){
            this.listeCompetences.add(c);
        }
    }
    
    @Override
    public String toString(){
        return nom+" "+prenom+" "+formatDate.format(dateNaiss.getTime());
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissString() {
        return formatDate.format(dateNaiss.getTime());
    }
    
    public Calendar getDateNaissCalendar() {
        return this.dateNaiss;
    }

    public ArrayList<String> getListeCompetences() {
        return listeCompetences;
    }
    
    
}
