package model.data;

import java.io.Serializable;

/**
 * Public class that represents a Departement.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class Departement implements Serializable {

    /**
     * public enum that represents the possible names of a Departement.
     */
    public enum NomDepartement {
        MORBIHAN, ILLE_ET_VILAINE, COTES_D_ARMOR, FINISTERE;
    }

    /**
     * Private attribute, the id of the Departement, an int.
     */
    private int idDep;

    /**
     * Private attribute, the name of the Departement, a NomDepartement.
     */
    private NomDepartement nomDep;

    /**
     * Private attribute, the cultural investment of the Departement in 2019, a double.
     */
    private double investissementCulturel2019;

    /**
     * Public constructor, creates a new Departement object with the specified parameters.
     *
     * @param idDep The id of the Departement. It cannot be null, an int.
     * @param nomDep The name of the Departement. It cannot be null, a NomDepartement.
     * @param investissementCulturel2019 The cultural investment of the Departement in 2019. It cannot be null, a double.
     * @throws RuntimeException if nomDep is null.
     */
    public Departement(int idDep, NomDepartement nomDep, double investissementCulturel2019) {
        this.idDep = idDep;
        this.investissementCulturel2019 = investissementCulturel2019;

        if(nomDep != null){
            this.nomDep = nomDep;
        }
        else{
            throw new RuntimeException("[ERREUR:Departement:Departement] : le parametre 'nomDep' est 'null' ");
        }
    }

    /**
     * Public method that returns the id of the Departement.
     *
     * @return The id of the Departement, an int.
     */
    public int getIdDep() {
        return idDep;
    }

    /**
     * Public method that returns the name of the Departement.
     *
     * @return The name of the Departement, a NomDepartement.
     */
    public NomDepartement getNomDep() {
        return nomDep;
    }

    /**
     * Public method that returns the cultural investment of the Departement in 2019.
     *
     * @return The cultural investment of the Departement in 2019, a double.
     */
    public double getInvestissementCulturel2019() {
        return investissementCulturel2019;
    }

    /**
     * Public method that sets the cultural investment of the Departement in 2019.
     *
     * @param investissementCulturel2019 The cultural investment of the Departement in 2019. It cannot be null, a double.
     */
    public void setInvestissementCulturel2019(double investissementCulturel2019) {
        this.investissementCulturel2019 = investissementCulturel2019;
    }

    /**
     * Public method that sets the name of the Departement.
     *
     * @param nomDep The name of the Departement. It cannot be null, a NomDepartement.
     * @throws RuntimeException if nomDep is null.
     */
    public void setNomDep(NomDepartement nomDep){
        if(nomDep != null){
            this.nomDep = nomDep;
        }
        else{
            throw new RuntimeException("[ERREUR:Departement:setNomDep] : le parametre 'nomDep' est 'null' ");
        }
    }

    /**
     * Public method that sets the id of the Departement.
     *
     * @param idDep The id of the Departement. It cannot be null, an int.
     */
    public void setIdDep(int idDep){
        this.idDep = idDep;
    }

    /**
     * Public method that compares the cultural investment of the current Departement with the specified Departement.
     *
     * @param leDepartement The Departement to compare with. It cannot be null, a Departement object.
     * @return The Departement with the higher cultural investment, or the current Departement if they are equal.
     * @throws RuntimeException if leDepartement is null.
     */
    public Departement compareInvestissement(Departement leDepartement){
        if(leDepartement == null){
            throw new RuntimeException("[ERREUR:Departement:compareInvestissement] : le parametre 'leDepartement' est 'null' ");
        }
        else{
            Departement ret = this;
            if(this.getInvestissementCulturel2019() < leDepartement.getInvestissementCulturel2019()){
                ret = leDepartement;
            }
            return ret;
        }
    }

    /**
     * Public method that returns a string representation of the Departement object.
     *
     * @return A string representation of the Departement object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += "Departement{idDep=" + idDep + ", nomDep='" + nomDep + "', investissementCulturel2019=" + investissementCulturel2019 + "}";
        return toRet;
    }
}
