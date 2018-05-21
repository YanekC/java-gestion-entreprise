package Model;

/**
 * Represente une competence avec son libellé anglais et francais.
 * @author guilhem
 */
public class Competence {
    private String libelleAng;
    private String libelleFra;
    
    /**
     * Creer une competence.
     * @param libelleAng Le libellé en Anglais
     * @param libelleFra Le libellé en Francais
     */
    public Competence(String libelleAng, String libelleFra){
        this.libelleAng = libelleAng;
        this.libelleFra = libelleFra;
    }
    
    @Override
    public String toString(){
        return this.libelleAng+" "+this.libelleFra;
    }

    /**
     * getteur pour le libellé Anglais
     * @return le libellé Anglais
     */
    public String getLibelleAng() {
        return libelleAng;
    }

    /**
     * getteur pour le libellé Francais
     * @return le libellé Francais
     */
    public String getLibelleFra() {
        return libelleFra;
    }    
}
