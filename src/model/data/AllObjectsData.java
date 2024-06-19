package model.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import model.data.Departement.NomDepartement;

/**
 * Public class that represents a collection of all the objects in the app.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprient - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class AllObjectsData implements Serializable {

    /**
     * Private attribute, the list of Departement objects, an ArrayList of Departement object.
     */
    private ArrayList<Departement> departementsList;
    
    /**
     * Private attribute, the list of Aeroport objects, an ArrayList of Aeroport object.
     */
    private ArrayList<Aeroport> aeroportsList;
    
    /**
     * Private attribute, the list of Annee objects, an ArrayList of Annee object.
     */
    private ArrayList<Annee> anneesList;
    
    /**
     * Private attribute, the list of Commune objects, an ArrayList of Commune object.
     */
    private ArrayList<Commune> communesList;
    
    /**
     * Private attribute, the list of DonneesAnnuelles objects, an ArrayList of DonneesAnnuelles object.
     */
    private ArrayList<DonneesAnnuelles> donneesAnnuellesList;
    
    /**
     * Private attribute, the list of Gare objects, an ArrayList of Gare object.
     */
    private ArrayList<Gare> garesList;
    
    /**
     * Private attribute, the list of User objects, an ArrayList of User object.
     */
    private ArrayList<User> usersList;

    /**
     * Public default constructor, creates a new AllObjectsData object with empty ArrayLists.
     */
    public AllObjectsData(){
        departementsList = new ArrayList<Departement>();
        aeroportsList = new ArrayList<Aeroport>();
        anneesList = new ArrayList<Annee>();
        communesList = new ArrayList<Commune>();
        donneesAnnuellesList = new ArrayList<DonneesAnnuelles>();
        garesList = new ArrayList<Gare>();
        usersList = new ArrayList<User>();
    }

    /**
     * Public method that sets the departementsList attribute of the AllObjectsData object.
     *
     * @param departementsList The new value for the departementsList attribute. It cannot be null, an ArrayList of Departement object.
     * @throws RuntimeException if departementsList is null.
     */
    public void setDepartementsList(ArrayList<Departement> departementsList) {
        if(departementsList != null){
            this.departementsList = departementsList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setDepartementsList] : le parametre 'departementsList' est 'null' ");
        }
    }

     /**
     * Public method that returns the usersList attribute of the AllObjectsData object.
     *
     * @return The usersList attribute of the AllObjectsData object, an ArrayList of User object.
     */
    public ArrayList<User> getUsersList(){
        return this.usersList;
    }

    public ArrayList<Departement.NomDepartement> getNomDepartementsList(){
        return new ArrayList<>(Arrays.asList(Departement.NomDepartement.values()));
    }

    /**
     * Public method that adds a User object to the usersList attribute of the AllObjectsData object.
     *
     * @param user The User object to be added. It cannot be null, a User object.
     * @throws RuntimeException if user is null.
     */
    public void addUser(User user){
        this.usersList.add(user);
    }

     /**
     * Public method that returns the departementsList attribute of the AllObjectsData object.
     *
     * @return The departementsList attribute of the AllObjectsData object, an ArrayList of Departement object.
     */
    public ArrayList<Departement> getDepartementsList() {
        return departementsList;
    }

    /**
     * Public method that sets the aeroportsList attribute of the AllObjectsData object.
     *
     * @param aeroportsList The new value for the aeroportsList attribute. It cannot be null, an ArrayList of Aeroport object.
     * @throws RuntimeException if aeroportsList is null.
     */
    public void setAeroportsList(ArrayList<Aeroport> aeroportsList) {
        if(aeroportsList != null){
            this.aeroportsList = aeroportsList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setAeroportsList] : le parametre 'aeroportsList' est 'null' ");
        }
    }

    /**
     * Public method that returns the aeroportsList attribute of the AllObjectsData object.
     *
     * @return The aeroportsList attribute of the AllObjectsData object, an ArrayList of Aeroport object.
     */
    public ArrayList<Aeroport> getAeroportsList() {
        return aeroportsList;
    }

    /**
     * Public method that sets the anneesList attribute of the AllObjectsData object.
     *
     * @param anneesList The new value for the anneesList attribute. It cannot be null, an ArrayList of Annee object.
     * @throws RuntimeException if anneesList is null.
     */
    public void setAnneesList(ArrayList<Annee> anneesList) {
        if(anneesList != null){
            this.anneesList = anneesList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setAnneesList] : le parametre 'anneesList' est 'null' ");
        }
    }

    /**
     * Public method that returns the anneesList attribute of the AllObjectsData object.
     *
     * @return The anneesList attribute of the AllObjectsData object, an ArrayList of Annee object.
     */
    public ArrayList<Annee> getAnneesList() {
        return anneesList;
    }

    /**
     * Public method that sets the communesList attribute of the AllObjectsData object.
     *
     * @param communesList The new value for the communesList attribute. It cannot be null, an ArrayList of Commune object.
     * @throws RuntimeException if communesList is null.
     */
    public void setCommunesList(ArrayList<Commune> communesList) {
        if(communesList != null){
            this.communesList = communesList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setCommunesList] : le parametre 'communesList' est 'null' ");
        }
    }

    /**
     * Public method that returns the communesList attribute of the AllObjectsData object.
     *
     * @return The communesList attribute of the AllObjectsData object, an ArrayList of Commune object.
     */
    public ArrayList<Commune> getCommunesList() {
        return communesList;
    }

    /**
     * Public method that sets the donneesAnnuellesList attribute of the AllObjectsData object.
     *
     * @param donneesAnnuellesList The new value for the donneesAnnuellesList attribute. It cannot be null, an ArrayList of DonneesAnnuelles object.
     * @throws RuntimeException if donneesAnnuellesList is null.
     */
    public void setDonneesAnnuellesList(ArrayList<DonneesAnnuelles> donneesAnnuellesList) {
        if(donneesAnnuellesList != null){
            this.donneesAnnuellesList = donneesAnnuellesList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setDonneesAnnuellesList] : le parametre 'donneesAnnuellesList' est 'null' ");
        }
    }

    /**
     * Public method that returns the donneesAnnuellesList attribute of the AllObjectsData object.
     *
     * @return The donneesAnnuellesList attribute of the AllObjectsData object, an ArrayList of DonneesAnnuelles object.
     */
    public ArrayList<DonneesAnnuelles> getDonneesAnnuellesList() {
        return donneesAnnuellesList;
    }

    /**
     * Public method that sets the garesList attribute of the AllObjectsData object.
     *
     * @param garesList The new value for the garesList attribute. It cannot be null, an ArrayList of Gare object.
     * @throws RuntimeException if garesList is null.
     */
    public void setGaresList(ArrayList<Gare> garesList) {
        if(garesList != null){
            this.garesList = garesList;
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:setGaresList] : le parametre 'garesList' est 'null' ");
        }
    }

    /**
     * Public method that returns the garesList attribute of the AllObjectsData object.
     *
     * @return The garesList attribute of the AllObjectsData object, an ArrayList of Gare object.
     */
    public ArrayList<Gare> getGaresList() {
        return garesList;
    }

    /**
     * Public method that adds a Departement object to the departementsList attribute of the AllObjectsData object.
     *
     * @param departement The Departement object to be added. It cannot be null, a Departement object.
     * @throws RuntimeException if departement is null.
     */
    public void addDepartement(Departement departement) {
        if(departement != null){
            this.departementsList.add(departement);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addDepartement] : le parametre 'departement' est 'null' ");
        }
    }

    /**
     * Public method that adds an Aeroport object to the aeroportsList attribute of the AllObjectsData object.
     *
     * @param aeroport The Aeroport object to be added. It cannot be null, an Aeroport object.
     * @throws RuntimeException if aeroport is null.
     */
    public void addAeroport(Aeroport aeroport) {
        if(aeroport != null){
            this.aeroportsList.add(aeroport);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addAeroport] : le parametre 'aeroport' est 'null' ");
        }
    }

    /**
     * Public method that adds an Annee object to the anneesList attribute of the AllObjectsData object.
     *
     * @param annee The Annee object to be added. It cannot be null, an Annee object.
     * @throws RuntimeException if annee is null.
     */
    public void addAnnee(Annee annee) {
        if(annee != null){
            this.anneesList.add(annee);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addAnnee] : le parametre 'annee' est 'null' ");
        }
    }

    /**
     * Public method that adds a Commune object to the communesList attribute of the AllObjectsData object.
     *
     * @param commune The Commune object to be added. It cannot be null, a Commune object.
     * @throws RuntimeException if commune is null.
     */
    public void addCommune(Commune commune) {
        if(commune != null){
            this.communesList.add(commune);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addCommune] : le parametre 'commune' est 'null' ");
        }
    }

    /**
     * Public method that adds a DonneesAnnuelles object to the donneesAnnuellesList attribute of the AllObjectsData object.
     *
     * @param donneesAnnuelles The DonneesAnnuelles object to be added. It cannot be null, a DonneesAnnuelles object.
     * @throws RuntimeException if donneesAnnuelles is null.
     */
    public void addDonneesAnnuelles(DonneesAnnuelles donneesAnnuelles) {
        if(donneesAnnuelles != null){
            this.donneesAnnuellesList.add(donneesAnnuelles);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addDonneesAnnuelles] : le parametre 'donneesAnnuelles' est 'null' ");
        }
    }

    /**
     * Public method that adds a Gare object to the garesList attribute of the AllObjectsData object.
     *
     * @param gare The Gare object to be added. It cannot be null, a Gare object.
     * @throws RuntimeException if gare is null.
     */
    public void addGare(Gare gare) {
        if(gare != null){
            this.garesList.add(gare);
        }
        else{
            throw new RuntimeException("[ERREUR:AllObjectsData:addGare] : le parametre 'gare' est 'null' ");
        }
    }

    /**
     * Public method that returns the Departement object with the specified ID from the departementsList attribute of the AllObjectsData object.
     *
     * @param idDepartement The ID of the Departement object to be returned. It cannot be null, an int object.
     * @return The Departement object with the specified ID, a Departement object.
     * @throws RuntimeException if idDepartement is null or if the Departement object with the specified ID is not found in the departementsList attribute.
     */
    public Departement getDepartementAvecID(int idDepartement) {
        Departement ret = null;
        for (Departement departement : this.departementsList) {
            if(idDepartement == departement.getIdDep()){
                ret = departement;
            }
        }
        if(ret == null){
            throw new RuntimeException("[ERREUR:AllObjectsData:getDepartementAvecID] : L'objet Departement d'ID '" + idDepartement + "' n'a pas été trouvé dans la liste.");
        }
        return ret;
    }

    /**
     * Public method that returns the Commune object with the specified ID from the communesList attribute of the AllObjectsData object.
     *
     * @param idCommune The ID of the Commune object to be returned. It cannot be null, an int object.
     * @return The Commune object with the specified ID, a Commune object.
     * @throws RuntimeException if idCommune is null or if the Commune object with the specified ID is not found in the communesList attribute.
     */
    public Commune getCommuneAvecID(int idCommune) {
        Commune ret = null;
        for (Commune commune : this.communesList) {
            if(idCommune == commune.getIdCommune()){
                ret = commune;
            }
        }
        if(ret == null){
            throw new RuntimeException("[ERREUR:AllObjectsData:getCommuneAvecID] : L'objet Commune d'ID '" + idCommune + "' n'a pas été trouvé dans la liste.");
        }
        return ret;
    }

     /**
     * Public method that returns the Annee object with the specified ID from the anneesList attribute of the AllObjectsData object.
     *
     * @param idAnnee The ID of the Annee object to be returned. It cannot be null, an int object.
     * @return The Annee object with the specified ID, an Annee object.
     * @throws RuntimeException if idAnnee is null or if the Annee object with the specified ID is not found in the anneesList attribute.
     */
    public Annee getLAnneeAvecID(int idAnnee) {
        Annee ret = null;
        for (Annee annee : this.anneesList) {
            if(idAnnee == annee.getAnnee()){
                ret = annee;
            }
        }
        if(ret == null){
            throw new RuntimeException("[ERREUR:AllObjectsData:getCommuneAvecID] : L'objet Annee d'ID '" + idAnnee + "' n'a pas été trouvé dans la liste.");
        }
        return ret;
    }

    public void exportationEnCSV() {
        String cheminFichier = "Departement.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("ID,Nom,Investissement culturel 2019");
            for (Departement departement : departementsList) {
                pw.println(departement.getIdDep() + "," + departement.getNomDep() + "," + departement.getInvestissementCulturel2019());
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        cheminFichier = "Aeroports.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("Nom,Adresse,Département");
            for (Aeroport aeroport : aeroportsList) {
                pw.println(aeroport.getNom() + "," + aeroport.getAdresse() + "," + aeroport.getLeDepartement().getIdDep());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        cheminFichier = "Annees.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("Année,Taux d'inflation");
            for (Annee annee : anneesList) {
                pw.println(annee.getAnnee() + "," + annee.getTauxInflation());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        cheminFichier = "Communes.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("ID,Nom,Département");
            for (Commune commune : communesList) {
                pw.println(commune.getIdCommune() + "," + commune.getNomCommune() + "," + commune.getLeDepartement().getNomDep());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        cheminFichier = "Voisins.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("ID,Nom,IDVoisin,NomVoisin");
            for (Commune commune : communesList) {
                for(Commune communeVoisin : commune.getListeVoisins()){
                    pw.println(commune.getIdCommune() + "," + commune.getNomCommune() + "," + communeVoisin.getIdCommune() + "," + communeVoisin.getNomCommune());
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }


        cheminFichier = "DonneesAnnuelles.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("Année,Commune,Nombre de maisons,Nombre d'appartements,Prix moyen,Prix moyen au mètre carré,Surface moyenne,Dépenses culturelles totales,Budget total,Population");
            for (DonneesAnnuelles donneesAnnuelles : donneesAnnuellesList) {
                pw.println(donneesAnnuelles.getlAnnee().getAnnee() + "," + donneesAnnuelles.getLaCommune().getIdCommune() + "," + donneesAnnuelles.getNbMaison() + "," + donneesAnnuelles.getNbAppart() + "," + donneesAnnuelles.getPrixMoyen() + "," + donneesAnnuelles.getPrixM2Moyen() + "," + donneesAnnuelles.getSurfaceMoy() + "," + donneesAnnuelles.getDepensesCulturellesTotales() + "," + donneesAnnuelles.getBudgetTotal() + "," + donneesAnnuelles.getPopulation());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }

        cheminFichier = "Gare.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("Code gare,Nom gare,Fret,Voyageur,Commune");
            for (Gare gare : garesList) {
                pw.println(gare.getCodeGare() + "," + gare.getNomGare() + "," + gare.getEstFret() + "," + gare.getEstVoyageur() + "," + gare.getLaCommune().getIdCommune());
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void exportUserToCSV(){
        String cheminFichier = "Users.csv";
        try (PrintWriter pw = new PrintWriter(new File(cheminFichier))) {
            pw.println("nom,pswrd,roles,phone,mail");
            for (User user : usersList) {
                pw.println(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "," + user.getPhone() + "," + user.getMail());
            }
            pw.close();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public Object findObject(Object o){
        Object retO = null;

        if(departementsList.indexOf(o) != -1){
            retO = departementsList.get(departementsList.indexOf(o));
        }
        else if(aeroportsList.indexOf(o) != -1){
            retO = aeroportsList.get(aeroportsList.indexOf(o));
        }
        else if(anneesList.indexOf(o) != -1){
            retO = anneesList.get(anneesList.indexOf(o));
        }
        else if(communesList.indexOf(o) != -1){
            retO = communesList.get(communesList.indexOf(o));
        }
        else if(donneesAnnuellesList.indexOf(o) != -1){
            retO = donneesAnnuellesList.get(donneesAnnuellesList.indexOf(o));
        }
        else if(donneesAnnuellesList.indexOf(o) != -1){
            retO = donneesAnnuellesList.get(donneesAnnuellesList.indexOf(o));
        }
        else if(usersList.indexOf(o) != -1){
            retO = usersList.get(usersList.indexOf(o));
        }

        return retO;
    }

    public ArrayList<DonneesAnnuelles> getDonneeAnnuelleByCommune(Commune commune){
        ArrayList<DonneesAnnuelles> toRet = new ArrayList<DonneesAnnuelles>();
        for (DonneesAnnuelles donneesAnnuelles : this.donneesAnnuellesList) {
            if (donneesAnnuelles.getLaCommune().getIdCommune() == commune.getIdCommune()){
                toRet.add(donneesAnnuelles);
            }
        }
        return toRet;
    }
}