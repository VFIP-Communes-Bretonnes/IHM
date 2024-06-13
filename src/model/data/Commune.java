package model.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Public class that represents a Commune.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprient - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class Commune implements Serializable{

    /**
     * Private attribute, the id of the Commune, an int.
     */
    private int idCommune;

    /**
     * Private attribute, the name of the Commune, a String.
     */
    private String nomCommune;

    /**
     * Private attribute, the Department of the Commune, a Departement object.
     */
    private Departement leDepartement;

    /**
     * Private attribute, the list of neighboring Communes, an ArrayList of Commune object.
     */
    private ArrayList<Commune> listeVoisins;

    /**
     * Public constructor, creates a new Commune object with the specified id, name, and Department.
     *
     * @param idCommune The id of the Commune. It cannot be null, an int.
     * @param nomCommune The name of the Commune. It cannot be null, a String.
     * @param leDepartement The Department of the Commune. It cannot be null, a Departement object.
     * @throws RuntimeException if nomCommune or leDepartement is null.
     */
    public Commune(int idCommune, String nomCommune, Departement leDepartement) {
        this.idCommune = idCommune;
        this.leDepartement = leDepartement;
        this.listeVoisins = new ArrayList<Commune>();

        if(nomCommune != null){
            this.nomCommune = nomCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:Commune] : le parametre 'nomCommune' est 'null' ");
        }

        if(leDepartement != null){
            this.leDepartement = leDepartement;
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:Commune] : le parametre 'leDepartement' est 'null' ");
        }
    }

    /**
     * Public method that returns the id of the Commune.
     *
     * @return The id of the Commune, an int.
     */
    public int getIdCommune() {
        return idCommune;
    }

    /**
     * Public method that returns the name of the Commune.
     *
     * @return The name of the Commune, a String.
     */
    public String getNomCommune() {
        return nomCommune;
    }

    /**
     * Public method that returns the Department of the Commune.
     *
     * @return The Department of the Commune, a Departement object.
     */
    public Departement getLeDepartement() {
        return leDepartement;
    }

    /**
     * Public method that returns the list of neighboring Communes.
     *
     * @return The list of neighboring Communes, an ArrayList of Commune objects.
     */
    public ArrayList<Commune> getListeVoisins() {
        return listeVoisins;
    }

    /**
     * Public method that sets the Department of the Commune.
     *
     * @param leDepartement The Department of the Commune. It cannot be null, a Departement object.
     * @throws RuntimeException if leDepartement is null.
     */
    public void setLeDepartement(Departement leDepartement) {
        if(leDepartement != null){
            this.leDepartement = leDepartement;
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:setLeDepartement] : le parametre 'leDepartement' est 'null' ");
        }
    }
    
    /**
     * Public method that sets the id of the Commune.
     *
     * @param idCommune The id of the Commune. It cannot be null, an int.
     */
    public void setIdCommune(int idCommune){
        this.idCommune = idCommune;
    }

    /**
     * Public method that sets the name of the Commune.
     *
     * @param nomCommune The name of the Commune. It cannot be null, a String.
     * @throws RuntimeException if nomCommune is null.
     */
    public void setNomCommune(String nomCommune){
        if(nomCommune != null){
            this.nomCommune = nomCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:setNomCommune] : le parametre 'nomCommune' est 'null' ");
        }
    }

    /**
     * Public method that adds a neighboring Commune to the list.
     *
     * @param voisin The neighboring Commune to be added. It cannot be null, a Commune object.
     * @throws RuntimeException if voisin is null.
     */
    public void ajouterVoisin(Commune voisin) {
        if(voisin != null){
            listeVoisins.add(voisin);
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:ajouterVoisin] : le parametre 'voisin' est 'null' ");
        }
    }

    /**
     * Public method that removes a neighboring Commune from the list.
     *
     * @param voisin The neighboring Commune to be removed. It cannot be null, a Commune object.
     * @throws RuntimeException if voisin is null.
     */
    public void supprimerVoisin(Commune voisin){
        if(voisin != null){
            listeVoisins.remove(voisin);
        }
        else{
            throw new RuntimeException("[ERREUR:Commune:supprimerVoisin] : le parametre 'voisin' est 'null' ");
        }
    }

    /**
     * Public method that checks if the Commune is in the specified Department.
     *
     * @param leDepartement The Department to check. It cannot be null, a Departement object.
     * @return true if the Commune is in the specified Department, false otherwise.
     * @throws RuntimeException if leDepartement is null.
     */
    public boolean estDansLeDepartement(Departement leDepartement){
        if(leDepartement == null){
            throw new RuntimeException("[ERREUR:Commune:estDansLeDepartement] : le parametre 'leDepartement' est 'null' ");
        }
        else{
            return this.leDepartement.getNomDep().equals(leDepartement.getNomDep());
        }
    }

    /**
     * Public method that checks if the specified Commune is a neighbor of the current Commune.
     *
     * @param laCommune The Commune to check. It cannot be null, a Commune object.
     * @return true if the specified Commune is a neighbor, false otherwise.
     * @throws RuntimeException if laCommune is null.
     */
    public boolean estVoisin(Commune laCommune){
        if(laCommune == null){
            throw new RuntimeException("[ERREUR:Commune:estVoisin] : le parametre 'laCommune' est 'null' ");
        }
        else{
            return this.listeVoisins.contains(laCommune);
        }
    }

    /**
     * Public method that returns a string representation of the Commune object.
     *
     * @return A string representation of the Commune object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += "Commune{idCommune=" + idCommune + ", nomCommune='" + nomCommune + "', leDepartement=" + leDepartement + "}";
        return toRet;
    }
}
