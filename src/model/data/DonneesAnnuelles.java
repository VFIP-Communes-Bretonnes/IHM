package model.data;

import java.io.Serializable;

/**
 * Public class that represents annual data.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 23/05/2024
 */
public class DonneesAnnuelles implements Serializable{

    /**
     * Private attribute, the year, an Annee object.
     */
    private Annee lAnnee;

    /**
     * Private attribute, the commune, a Commune object.
     */
    private Commune laCommune;

    /**
     * Private attribute, the number of houses, an int.
     */
    private int nbMaison;

    /**
     * Private attribute, the number of apartments, an int.
     */
    private int nbAppart;

    /**
     * Private attribute, the average price, a double.
     */
    private double prixMoyen;

    /**
     * Private attribute, the average price per square meter, a double.
     */
    private double prixM2Moyen;

    /**
     * Private attribute, the average surface area, a double.
     */
    private double surfaceMoy;

    /**
     * Private attribute, the total cultural expenses, a double.
     */
    private double depensesCulturellesTotales;

    /**
     * Private attribute, the total budget, a double.
     */
    private double budgetTotal;

    /**
     * Private attribute, the population, an int.
     */
    private int population;

    /**
     * Public constructor, creates a new DonneesAnnuelles object with the specified parameters.
     *
     * @param lAnnee The year. It cannot be null, an Annee object.
     * @param laCommune The commune. It cannot be null, a Commune object.
     * @param nbMaison The number of houses. It cannot be null, an int.
     * @param nbAppart The number of apartments. It cannot be null, an int.
     * @param prixMoyen The average price. It cannot be null, a double.
     * @param prixM2Moyen The average price per square meter. It cannot be null, a double.
     * @param surfaceMoy The average surface area. It cannot be null, a double.
     * @param depensesCulturellesTotales The total cultural expenses. It cannot be null, a double.
     * @param budgetTotal The total budget. It cannot be null, a double.
     * @param population The population. It cannot be null, an int.
     * @throws RuntimeException if lAnnee or laCommune is null.
     */
    public DonneesAnnuelles(Annee lAnnee, Commune laCommune, int nbMaison, int nbAppart, double prixMoyen, double prixM2Moyen, double surfaceMoy, double depensesCulturellesTotales, double budgetTotal, int population) {
        this.nbMaison = nbMaison;
        this.nbAppart = nbAppart;
        this.prixMoyen = prixMoyen;
        this.prixM2Moyen = prixM2Moyen;
        this.surfaceMoy = surfaceMoy;
        this.depensesCulturellesTotales = depensesCulturellesTotales;
        this.budgetTotal = budgetTotal;
        this.population = population;

        if(lAnnee != null){
            this.lAnnee = lAnnee;
        }
        else{
            throw new RuntimeException("[ERREUR:DonneesAnnuelles:DonneesAnnuelles] : le parametre 'lAnnee' est 'null' ");
        }

        if(laCommune != null){
            this.laCommune = laCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:DonneesAnnuelles:DonneesAnnuelles] : le parametre 'laCommune' est 'null' ");
        }
    }

    /**
     * Public method that returns the year.
     *
     * @return The year, an Annee object.
     */
    public Annee getlAnnee() {
        return lAnnee;
    }

    /**
     * Public method that returns the commune for which the data is collected.
     *
     * @return The commune for which the data is collected, a Commune object.
     */
    public Commune getLaCommune() {
        return laCommune;
    }

    /**
     * Public method that returns the number of houses in the commune.
     *
     * @return The number of houses in the commune, an int.
     */
    public int getNbMaison() {
        return nbMaison;
    }

    /**
     * Public method that returns the number of apartments in the commune.
     *
     * @return The number of apartments in the commune, an int.
     */
    public int getNbAppart() {
        return nbAppart;
    }

    /**
     * Public method that returns the average price of a property in the commune.
     *
     * @return The average price of a property in the commune, a double.
     */
    public double getPrixMoyen() {
        return prixMoyen;
    }

    /**
     * Public method that returns the average price per square meter in the commune.
     *
     * @return The average price per square meter in the commune, a double.
     */
    public double getPrixM2Moyen() {
        return prixM2Moyen;
    }

    /**
     * Public method that returns the average surface area of a property in the commune.
     *
     * @return The average surface area of a property in the commune, a double.
     */
    public double getSurfaceMoy() {
        return surfaceMoy;
    }

    /**
     * Public method that returns the total cultural expenses of the commune.
     *
     * @return The total cultural expenses of the commune, a double.
     */
    public double getDepensesCulturellesTotales() {
        return depensesCulturellesTotales;
    }

    /**
     * Public method that returns the total budget of the commune.
     *
     * @return The total budget of the commune, a double.
     */
    public double getBudgetTotal() {
        return budgetTotal;
    }

    /**
     * Public method that returns the population of the commune.
     *
     * @return The population of the commune, an int.
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Public method that sets the population of the commune.
     *
     * @param population The population of the commune, an int.
     */
    public void setPopulation(int population){
        this.population = population;
    }

    /**
     * Public method that sets the total budget of the commune.
     *
     * @param budgetTotal The total budget of the commune, a double.
     */
    public void setBudgetTotal(double budgetTotal){
        this.budgetTotal = budgetTotal;
    }

    /**
     * Public method that sets the total cultural expenses of the commune.
     *
     * @param depensesCulturellesTotales The total cultural expenses of the commune, a double.
     */
    public void setDepensesCulturellesTotales(double depensesCulturellesTotales){
        this.depensesCulturellesTotales = depensesCulturellesTotales;
    }

    /**
     * Public method that sets the average surface area of a property in the commune.
     *
     * @param surfaceMoy The average surface area of a property in the commune, a double.
     */
    public void setSurfaceMoy(double surfaceMoy){
        this.surfaceMoy = surfaceMoy;
    }

    /**
     * Public method that sets the average price per square meter in the commune.
     *
     * @param prixM2Moyen The average price per square meter in the commune, a double.
     */
    public void setPrixM2Moyen(double prixM2Moyen){
        this.prixM2Moyen = prixM2Moyen;
    }

    /**
     * Public method that sets the average price of a property in the commune.
     *
     * @param prixMoyen The average price of a property in the commune, a double.
     */
    public void setPrixMoyen(double prixMoyen){
        this.prixMoyen = prixMoyen;
    }

    /**
     * Public method that sets the number of apartments in the commune.
     *
     * @param nbAppart The number of apartments in the commune, an int.
     */
    public void setNbAppart(int nbAppart){
        this.nbAppart = nbAppart;
    }

    /**
     * Public method that sets the number of houses in the commune.
     *
     * @param nbMaison The number of houses in the commune, an int.
     */
    public void setNbMaison(int nbMaison){
        this.nbMaison = nbMaison;
    }

    /**
     * Public method that sets the commune for which the data is collected.
     *
     * @param laCommune The commune for which the data is collected. It cannot be null, a Commune object.
     * @throws RuntimeException if laCommune is null.
     */
    public void setLaCommune(Commune laCommune){
        if(laCommune != null){
            this.laCommune = laCommune;
        }
        else{
            throw new RuntimeException("[ERREUR:DonneesAnnuelles:setLaCommune] : le parametre 'laCommune' est 'null' ");
        }
    }

    /**
     * Public method that sets the year of the data.
     *
     * @param annee The year of the data. It cannot be null, an Annee object.
     * @throws RuntimeException if annee is null.
     */
    public void setAnnee(Annee annee){
        if(annee != null){
            this.lAnnee = annee;
        }
        else{
            throw new RuntimeException("[ERREUR:DonneesAnnuelles:setAnnee] : le parametre 'annee' est 'null' ");
        }
    }

    /**
     * Public method that checks if the data belongs to the specified commune.
     *
     * @param laCommune The commune to check. It cannot be null, a Commune object.
     * @return true if the data belongs to the specified commune, false otherwise.
     * @throws RuntimeException if laCommune is null.
     */
    public boolean appartienA(Commune laCommune){
        if(laCommune == null){
            throw new RuntimeException("[ERREUR:DonneesAnnuelles:appartienA] : le parametre 'laCommune' est 'null' ");
        }
        else{
            return this.laCommune.getIdCommune() == laCommune.getIdCommune();
        }
    }

    /**
     * Public method that returns a string representation of the DonneesAnnuelles object.
     *
     * @return A string representation of the DonneesAnnuelles object, a String.
     */
    public String toString() {
        String toRet = "";
        toRet += "DonneesAnnuelles{" + lAnnee;
        toRet += ", " + laCommune + "}";
        return toRet;
    }
}
