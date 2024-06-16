package model.data;

import java.io.Serializable;

/**
 * Public class that represents an Airport.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class Aeroport implements Serializable{

    /**
     * Private attribute, the name of the Airport, a String.
     */
    private String nom;

    /**
     * Private attribute, the address of the Airport, a String.
     */
    private String adresse;

    /**
     * Private attribute, the Department of the Airport, a Departement object.
     */
    private Departement leDepartement;

    /**
     * Public constructor, creates a new Airport object with the specified parameters.
     *
     * @param nom The name of the Airport. It cannot be null, a String.
     * @param adresse The address of the Airport. It cannot be null, a String.
     * @param leDepartement The Department of the Airport. It cannot be null, a Departement object.
     * @throws RuntimeException if any of the parameters are null.
     */
    public Aeroport(String nom, String adresse, Departement leDepartement) {
        if(nom != null){
            this.nom = nom;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:Aeroport] : le parametre 'nom' est 'null' ");
        }

        if(adresse != null){
            this.adresse = adresse;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:Aeroport] : le parametre 'adresse' est 'null' ");
        }

        if(leDepartement != null){
            this.leDepartement = leDepartement;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:Aeroport] : le parametre 'leDepartement' est 'null' ");
        }
    }

    /**
     * Public method that returns the name of the Airport.
     *
     * @return The name of the Airport, a String.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Public method that returns the address of the Airport.
     *
     * @return The address of the Airport, a String.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Public method that returns the Department of the Airport.
     *
     * @return The Department of the Airport, a Departement object.
     */
    public Departement getLeDepartement() {
        return leDepartement;
    }

    /**
     * Public method that sets the Department of the Airport.
     *
     * @param leDepartement The new Department of the Airport. It cannot be null, a Departement object.
     * @throws RuntimeException if leDepartement is null.
     */
    public void setLeDepartement(Departement leDepartement) {
        if(leDepartement != null){
            this.leDepartement = leDepartement;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:setLeDepartement] : le parametre 'leDepartement' est 'null' ");
        }
    }

    /**
     * Public method that sets the address of the Airport.
     *
     * @param adresse The new address of the Airport. It cannot be null, a String.
     * @throws RuntimeException if adresse is null.
     */
    public void setAdresse(String adresse) {
        if(adresse != null){
            this.adresse = adresse;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:setAdresse] : le parametre 'adresse' est 'null' ");
        }
    }

    /**
     * Public method that sets the name of the Airport.
     *
     * @param nom The new name of the Airport. It cannot be null, a String.
     * @throws RuntimeException if nom is null.
     */
    public void setNom(String nom) {
        if(nom != null){
            this.nom = nom;
        }
        else{
            throw new RuntimeException("[ERREUR:Aeroport:setNom] : le parametre 'nom' est 'null' ");
        }
    }

    /**
     * Public method that checks if the Airport is in the specified Department.
     *
     * @param leDepartement The Department to check. It cannot be null, a Departement object.
     * @return true if the Airport is in the specified Department, false otherwise.
     * @throws RuntimeException if leDepartement is null.
     */
    public boolean estDansLeDepartement(Departement leDepartement){
        if(leDepartement == null){
            throw new RuntimeException("[ERREUR:Aeroport:estDansLeDepartement] : le parametre 'leDepartement' est 'null' ");
        }
        else{
            return this.leDepartement.getNomDep().equals(leDepartement.getNomDep());
        }
    }

    /**
     * Public method that returns a string representation of the Airport object.
     *
     * @return A string representation of the Airport object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += "Aeroport{nom='" + nom + "', adresse='" + adresse + "', leDepartement=" + leDepartement + "}";
        return toRet;
    }
}
