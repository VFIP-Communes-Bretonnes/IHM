package model.data;

import java.io.Serializable;

/**
 * Public class that represents a train station.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class Gare implements Serializable{

    /**
     * Private attribute, the code of the train station, an int.
     */
    private int codeGare;

    /**
     * Private attribute, the name of the train station, a String.
     */
    private String nomGare;

    /**
     * Private attribute, a boolean indicating whether the train station is for freight, a boolean.
     */
    private boolean estFret;

    /**
     * Private attribute, a boolean indicating whether the train station is for passengers, a boolean.
     */
    private boolean estVoyageur;

    /**
     * Private attribute, the commune in which the train station is located, a Commune object.
     */
    private Commune laCommune;

    /**
     * Public constructor, creates a new Gare object with the specified parameters.
     *
     * @param codeGare The code of the train station. It cannot be null, an int.
     * @param nomGare The name of the train station. It cannot be null, a String.
     * @param estFret A boolean indicating whether the train station is for freight, a boolean.
     * @param estVoyageur A boolean indicating whether the train station is for passengers, a boolean.
     * @param laCommune The commune in which the train station is located. It cannot be null, a Commune object.
     * @throws RuntimeException if nomGare or laCommune is null.
     */
    public Gare(int codeGare, String nomGare, boolean estFret, boolean estVoyageur, Commune laCommune) {
        this.codeGare = codeGare;
        this.estFret = estFret;
        this.estVoyageur = estVoyageur;

        if(nomGare != null){
            this.nomGare = nomGare;
        }
        else{
            throw new RuntimeException("[ERREUR:Gare:Gare] : le parametre 'nomGare' est 'null'");
        }

        if(laCommune != null){
            this.laCommune = laCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:Gare:Gare] : le parametre 'laCommune' est 'null'");
        }
    }

    /**
     * Public method that returns the code of the train station.
     *
     * @return The code of the train station, an int.
     */
    public int getCodeGare() {
        return codeGare;
    }

    /**
     * Public method that returns the name of the train station.
     *
     * @return The name of the train station, a String.
     */
    public String getNomGare() {
        return nomGare;
    }

    /**
     * Public method that returns a boolean indicating whether the train station is for freight.
     *
     * @return A boolean indicating whether the train station is for freight, a boolean.
     */
    public boolean getEstFret() {
        return estFret;
    }

    /**
     * Public method that returns a boolean indicating whether the train station is for passengers.
     *
     * @return A boolean indicating whether the train station is for passengers, a boolean.
     */
    public boolean getEstVoyageur() {
        return estVoyageur;
    }

    /**
     * Public method that returns the commune in which the train station is located.
     *
     * @return The commune in which the train station is located, a Commune object.
     */
    public Commune getLaCommune() {
        return laCommune;
    }

    /**
     * Public method that sets the code of the train station.
     *
     * @param codeGare The code of the train station. It must be 8 digits long, an int.
     * @throws RuntimeException if codeGare is not 8 digits long.
     */
    public void setCodeGare(int codeGare) {
        if (String.valueOf(codeGare).length() == 8) {
            this.codeGare = codeGare;
        } else {
            throw new RuntimeException("[ERREUR:Gare:setCodeGare] : le parametre 'codeGare' n'est pas conforme à la convention");
        }
    }

    /**
     * Public method that sets the name of the train station.
     *
     * @param nomGare The name of the train station. It cannot be null, a String.
     * @throws RuntimeException if nomGare is null.
     */
    public void setNomGare(String nomGare) {
        if (nomGare != null) {
            this.nomGare = nomGare;
        } else {
            throw new RuntimeException("[ERREUR:Gare:setNomGare] : le paramètre 'nomGare' est null");
        }
    }

    /**
     * Public method that sets a boolean indicating whether the train station is for freight.
     *
     * @param estFret A boolean indicating whether the train station is for freight, a boolean.
     */
    public void setEstFret(boolean estFret) {
        this.estFret = estFret;
    }

    /**
     * Public method that sets a boolean indicating whether the train station is for passengers.
     *
     * @param estVoyageur A boolean indicating whether the train station is for passengers, a boolean.
     */
    public void setEstVoyageur(boolean estVoyageur) {
        this.estVoyageur = estVoyageur;
    }

    /**
     * Public method that sets the commune in which the train station is located.
     *
     * @param laCommune The commune in which the train station is located. It cannot be null, a Commune object.
     * @throws RuntimeException if laCommune is null.
     */
    public void setLaCommune(Commune laCommune) {
        if (laCommune != null){
            this.laCommune = laCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:Gare:setLaCommune] : le parametre 'laCommune' est 'null' ");
        }
    }

    /**
     * Public method that checks if the train station is located in the specified commune.
     *
     * @param laCommune The commune to check. It cannot be null, a Commune object.
     * @return true if the train station is located in the specified commune, false otherwise.
     * @throws RuntimeException if laCommune is null.
     */
    public boolean estDansLaCommune(Commune laCommune){
        if(laCommune == null){
            throw new RuntimeException("[ERREUR:Aeroport:estDansLaCommune] : le parametre 'laCommune' est 'null' ");
        }
        else{
            return this.laCommune.getIdCommune() == laCommune.getIdCommune();
        }
    }

    /**
     * Public method that returns a string representation of the Gare object.
     *
     * @return A string representation of the Gare object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += nomGare;
        return toRet;
    }
}
