package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe qui implémente un personnel.
 * 
 * @author Yanek, Guilhem, Aléxe
 */
public class Personnel {
    
    static SimpleDateFormat formatDate = new SimpleDateFormat( "dd/MM/yyyy" );
    private String nom;
    private String prenom;
    private Calendar dateNaiss;
    
    /**
     * Constructeur de base d'un personnel.
     * @param nom Le nom du personnel
     * @param prenom Le prenom du personnel
     * @param dateNaiss La date de naissance du personnel
     */
    public Personnel(String nom, String prenom, Calendar dateNaiss){
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaiss = dateNaiss;
    }
    
    @Override
    public String toString(){
        return nom+" "+prenom+" "+formatDate.format(dateNaiss.getTime());
    }
}
