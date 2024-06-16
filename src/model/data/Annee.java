package model.data;

import java.io.Serializable;

/**
 * Public class that represents a Year.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class Annee implements Serializable{

    /**
     * Private attribute, the year, an int.
     */
    private int annee;

    /**
     * Private attribute, the inflation rate of the year, a double.
     */
    private double tauxInflation;

    /**
     * Public constructor, creates a new Year object with the specified parameters.
     *
     * @param annee The year. It must be a valid integer value, an int.
     * @param tauxInflation The inflation rate of the year, a double.
     */
    public Annee(int annee, double tauxInflation) {
        this.annee = annee;
        this.tauxInflation = tauxInflation;
    }

    /**
     * Public method that returns the year.
     *
     * @return The year, an int.
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * Public method that returns the inflation rate of the year.
     *
     * @return The inflation rate of the year, a double.
     */
    public double getTauxInflation() {
        return tauxInflation;
    }

    /**
     * Public method that sets the year.
     *
     * @param annee The new year, an int.
     */
    public void setAnnee(int annee){
        this.annee = annee;
    }

    /**
     * Public method that sets the inflation rate of the year.
     *
     * @param tauxInflation The new inflation rate of the year, a double.
     */
    public void setTauxInflation(double tauxInflation){
        this.tauxInflation = tauxInflation;
    }

    /**
     * Public method that compares the inflation rate of the current year with the specified year.
     *
     * @param lAnnee The year to compare with. It cannot be null, an Annee object.
     * @return The year with the higher inflation rate, or the current year if they are equal.
     * @throws RuntimeException if lAnnee is null.
     */
    public Annee compareInflation(Annee lAnnee){
        if(lAnnee == null){
            throw new RuntimeException("[ERREUR:Annee:compareInflation] : le parametre 'lAnnee' est 'null' ");
        }
        else{
            Annee ret = this;
            if(this.getTauxInflation() < lAnnee.getTauxInflation()){
                ret = lAnnee;
            }
            return ret;
        }
    }

    /**
     * Public method that returns a string representation of the Year object.
     *
     * @return A string representation of the Year object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += annee;
        return toRet;
    }
}
