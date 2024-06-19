package model.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

import model.data.*;

/**
 * Public class that represents the data access object (DAO) for the application.
 * 
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class ReadWriteDatabase implements Serializable{

    /**
     * Private static attribute, the AllObjectsData object that contains all the data for the application.
     */
    private static AllObjectsData allObjectsData;
    
    /**
     * Private static final attribute, the URL of the database.
     */
    final static String databaseUrl = "jdbc:mysql://localhost:3306/bdsae";
    
    /**
     * Private static final attribute, the default username for the database.
     */
    final static String defaultUserName = "application";
    
    /**
     * Private static final attribute, the default password for the database.
     */
    final static String defaultUserPassword = "application";

    /**
     * Public default constructor, creates a new ReadWriteDatabase object and initializes the allObjectsData attribute.
     */
    public ReadWriteDatabase(){
        allObjectsData = new AllObjectsData();
    }

    public void loadDepartement() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ///////// DEPARTEMENT

        ResultSet res = DatabaseStatement.executeQuery("select * from departement");
        while(res.next()){
            int investissementCulturel2019 = res.getInt("investissementCulturel2019");
            String nomDepartement = res.getString("nomDep").replace('-', '_').replace('\'', '_');
            int idDepartement = res.getInt("idDep");
            Departement departement = new Departement(idDepartement, Departement.NomDepartement.valueOf(nomDepartement) , investissementCulturel2019);
            allObjectsData.addDepartement(departement);
        }
    }

    public void loadCommune() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from commune");
        
        ///////// COMMUNE

        while(res.next()){
            int idCommune = res.getInt("idCommune");
            String nomCommune = res.getString("nomCommune");
            int idDepartement = res.getInt("leDepartement");
            Commune commune = new Commune(idCommune, nomCommune , allObjectsData.getDepartementAvecID(idDepartement));
            allObjectsData.addCommune(commune);
        }
    }

    public void loadGare() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from gare");

                ///////// GARE

        while(res.next()){
            int codeGare = res.getInt("codeGare");
            String nomGare = res.getString("nomGare");
            boolean estFret = res.getBoolean("estFret");
            boolean estVoyageur = res.getBoolean("estVoyageur");
            int laCommune = res.getInt("laCommune");
            Gare gare = new Gare(codeGare, nomGare, estFret, estVoyageur, allObjectsData.getCommuneAvecID(laCommune));
            allObjectsData.addGare(gare);
        }
        
    }

    public void loadVoisinage() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from voisinage");
        
        ///////// VOISINAGE

        while(res.next()){
            int idCommune = res.getInt("commune");
            int idCommuneVoisine = res.getInt("communeVoisine");

            allObjectsData.getCommuneAvecID(idCommune).ajouterVoisin(allObjectsData.getCommuneAvecID(idCommuneVoisine));
        }
    }

    public void loadAnnee() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from annee");

        ///////// ANNEE

        while(res.next()){
            int idAnnee = res.getInt("annee");
            double tauxInflation = res.getFloat("tauxInflation");
            Annee annee = new Annee(idAnnee, tauxInflation);
            allObjectsData.addAnnee(annee);
        }
        
    }

    public void loadDonneesAnnuelles() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from donneesannuelles");

        ///////// DONNEESANNUELLES

        while(res.next()){
            int lAnnee = res.getInt("lAnnee");
            int laCommune = res.getInt("laCommune");
            int nbMaison = res.getInt("nbMaison");
            int nbAppart = res.getInt("nbAppart");
            double prixMoyen = res.getFloat("prixMoyen");
            double prixM2Moyen = res.getFloat("prixM2Moyen");
            double surfaceMoy = res.getFloat("SurfaceMoy");
            double depensesCulturellesTotales = res.getFloat("depensesCulturellesTotales");
            double budgetTotal = res.getFloat("budgetTotal");
            int population = res.getInt("population");
            DonneesAnnuelles donneesAnnuelles = new DonneesAnnuelles(allObjectsData.getLAnneeAvecID(lAnnee), allObjectsData.getCommuneAvecID(laCommune), nbMaison, nbAppart, prixMoyen, prixM2Moyen, surfaceMoy, depensesCulturellesTotales, budgetTotal, population);
            allObjectsData.addDonneesAnnuelles(donneesAnnuelles);
        }
        
    }

    public void loadAeroport() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from aeroport");

        ///////// AEROPORT
        
        while(res.next()){
            String nom = res.getString("nom");
            String adresse = res.getString("adresse");
            int leDepartement = res.getInt("leDepartement");
            Aeroport aeroport = new Aeroport(nom, adresse, allObjectsData.getDepartementAvecID(leDepartement));
            allObjectsData.addAeroport(aeroport);
        }
        
    }

    public void loadAllData() throws SQLException{
        loadDepartement();
        loadCommune();
        loadGare();
        loadVoisinage();
        loadAnnee();
        loadDonneesAnnuelles();
        loadAeroport();
    }

    /**
     * Public method that loads all the users from the database and stores them in the allObjectsData attribute.
     *
     * @throws SQLException if an error occurs while accessing the database.
     */
    public void loadAllUsers() throws SQLException{
        Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        Statement DatabaseStatement = DatabaseConnection.createStatement();
        ResultSet res = DatabaseStatement.executeQuery("select * from utilisateurs");
        while(res.next()){
            String nom = res.getString("nom");
            String role = res.getString("roles");
            String mail = res.getString("mail");
            String pswrd = res.getString("pswrd");
            String phone = res.getString("phone");
            User user = new User(nom,role, mail, phone ,this);
            user.setPassword(pswrd);
            allObjectsData.addUser(user);
        }
    }

     /**
     * Public method that returns the AllObjectsData object that contains all the data for the application.
     *
     * @return The AllObjectsData object that contains all the data for the application, an AllObjectsData object.
     */
    public AllObjectsData getAllObjectsData(){
        return allObjectsData;
    }

    /**
     * Public method that finds a user in the database with the specified username and password and returns the user's role.
     *
     * @param utilisateur The username of the user. It cannot be null or empty, a String object.
     * @param motDePasse The password of the user. It cannot be null or empty, a String object.
     * @return The user's role if the user is found in the database with the specified username and password, "err" otherwise, a String object.
     * @throws RuntimeException if utilisateur or motDePasse are null or empty.
     */
    public User findUserAndGetRole(String utilisateur, String motDePasse){
        User ret = null;
        boolean userLoggedin = false;
        if(utilisateur == null){
            throw new RuntimeException("[ERREUR:ReadWriteDatabase:setCurrentUser] : le parametre 'utilisateur' est 'null' ");
        }
        if(motDePasse == null){
            throw new RuntimeException("[ERREUR:ReadWriteDatabase:setCurrentUser] : le parametre 'motDePasse' est 'null' ");
        }
        try{
            Connection DatabaseConnection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            Statement DatabaseStatement = DatabaseConnection.createStatement();
            ResultSet res = DatabaseStatement.executeQuery("select * from utilisateurs");
            while(res.next() && userLoggedin == false){
                String nom = res.getString("nom");
                String pswrd = res.getString("pswrd");
                String roles = res.getString("roles");
                String mail = res.getString("mail");
                String phone = res.getString("phone");

                if(nom.equals(utilisateur) && pswrd.equals(motDePasse)){
                    userLoggedin = true;
                }

                if(mail.equals(utilisateur) && pswrd.equals(motDePasse)){
                    userLoggedin = true;
                }

                if(phone.equals(utilisateur) && pswrd.equals(motDePasse)){
                    userLoggedin = true;
                }

                if(userLoggedin){
                    User user = new User();
                    user.setMail(mail);
                    user.setUsername(nom);
                    user.setPassword(pswrd);
                    user.setPhone(phone);
                    user.setRole(roles);
                    user.setReadWriteDatabase(this);
                    ret = user;
                }
            }
        }
        catch(SQLException e){
            System.err.println(e.getStackTrace());
        }
        return ret;
    }

    /**
     * Public static method that adds a new user to the database with the specified username, password, and role.
     *
     * @param userName The username of the new user. It cannot be null or empty, a String object.
     * @param password The password of the new user. It cannot be null or empty, a String object.
     * @throws SQLException if an error occurs while accessing the database.
     */
    public static void addNewUser(String userName, String password, String mail, String phone) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateurs (nom, pswrd, roles, phone, mail) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "user");
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, mail);

            preparedStatement.executeUpdate();
        }
    }

    public static void addNewUserByObjct(User user) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateurs (nom, pswrd, roles, phone, mail) VALUES (?, ?, ?, ?, ?)")) {

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setString(4, user.getPhone());
                preparedStatement.setString(5, user.getMail());

            preparedStatement.executeUpdate();
        }
    }

    public void deleteUserByUsername(String userName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM utilisateurs WHERE nom = ?;")) {

            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();
        }
    }

    public void updateUserByObject(User user) throws SQLException{
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE utilisateurs SET roles = ? WHERE nom = ?;")) {

            preparedStatement.setString(1, user.getRole());
            preparedStatement.setString(2, user.getUsername());

            preparedStatement.executeUpdate();
        }
    }

    public User updateUserDataSettings(String currentUserName, User user, String pswrd, String newpswrd) throws SQLException{
        User toRet = null;
        if(findUserAndGetRole(currentUserName, pswrd) == null){
            toRet = null;
        }
        else{
            try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM utilisateurs WHERE nom = ?;")) {
                preparedStatement.setString(1, currentUserName);
                preparedStatement.executeUpdate();
            }
            try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateurs (nom, pswrd, roles, phone, mail) VALUES (?, ?, ?, ?, ?)")) {
    
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, newpswrd);
                preparedStatement.setString(3, user.getRole());
                preparedStatement.setString(4, user.getPhone());
                preparedStatement.setString(5, user.getMail());
                preparedStatement.executeUpdate();
                toRet = new User();
            }
        }
        return toRet;
    }

    public void updateDepartement(Departement departement) throws SQLException {
        String sql = "UPDATE departement SET nomDep = ?, investissementCulturel2019 = ? WHERE idDep = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, departement.getNomDep().toString());
            preparedStatement.setDouble(2, departement.getInvestissementCulturel2019());
            preparedStatement.setInt(3, departement.getIdDep());
    
            preparedStatement.executeUpdate();
        }
    }

    public void updateCommune(Commune commune) throws SQLException {
        String sql = "UPDATE commune SET nomCommune = ?, leDepartement = ? WHERE idCommune = ?";
        Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        try {
    
            preparedStatement.setString(1, commune.getNomCommune());
            preparedStatement.setInt(2, commune.getLeDepartement().getIdDep());
            preparedStatement.setInt(3, commune.getIdCommune());
    
            preparedStatement.executeUpdate();
            
            
            ArrayList<Departement> departements = new ArrayList<Departement>();

            Statement DatabaseStatement = connection.createStatement();
            ResultSet res = DatabaseStatement.executeQuery("select * from departement");
            while(res.next()){
                int investissementCulturel2019 = res.getInt("investissementCulturel2019");
                String nomDepartement = res.getString("nomDep").replace('-', '_').replace('\'', '_');
                int idDepartement = res.getInt("idDep");
                Departement departement = new Departement(idDepartement, Departement.NomDepartement.valueOf(nomDepartement) , investissementCulturel2019);
                departements.add(departement);
            }
            
            AllObjectsData dataList = new AllObjectsData();

            Commune communeOriginal = null;
            DatabaseStatement = connection.createStatement();
            res = DatabaseStatement.executeQuery("select * from commune");
            while(res.next()){
                int idCommune = res.getInt("idCommune");
                String nomCommune = res.getString("nomCommune");
                int idDepartement = res.getInt("leDepartement");
                
                dataList.setDepartementsList(departements);
                Commune commune2 = new Commune(idCommune, nomCommune , dataList.getDepartementAvecID(idDepartement));
                if(idCommune == commune.getIdCommune()){
                    communeOriginal = commune2;
                }
                dataList.addCommune(commune2);
            }

            DatabaseStatement = connection.createStatement();
            res = DatabaseStatement.executeQuery("select * from voisinage");    
            while(res.next()){
                int idCommune = res.getInt("commune");
                int idCommuneVoisine = res.getInt("communeVoisine");
                if(idCommune == communeOriginal.getIdCommune()){
                    communeOriginal.ajouterVoisin(dataList.getCommuneAvecID(idCommuneVoisine));
                }
            }

            //System.out.println(communeOriginal.getListeVoisins());        

            for (Commune voisin : communeOriginal.getListeVoisins()) {

                try {
                    
                    sql = "DELETE FROM voisinage WHERE (commune=? AND communeVoisine=?);";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, commune.getIdCommune());
                    preparedStatement.setInt(2, voisin.getIdCommune());
                    preparedStatement.executeUpdate();
                }
                catch (Exception e) {

                }   
            }

            
            for (Commune voisin : commune.getListeVoisins()) {
                sql = "INSERT INTO `voisinage` (`commune`,`communeVoisine`) VALUES (?,?);";
                preparedStatement = connection.prepareStatement(sql);
                try {
            
                    preparedStatement.setInt(1, commune.getIdCommune());
                    preparedStatement.setInt(2, voisin.getIdCommune());
            
                    preparedStatement.executeUpdate();
                }
                catch (Exception e) {
                    
                }  
            }
        }
        catch(Exception e){

        }
        finally{
            connection.close();
        }
    }
    
    public void updateGare(Gare gare) throws SQLException {
        String sql = "UPDATE Gare SET nomGare = ?, estFret = ?, estVoyageur = ?, laCommune = ? WHERE codeGare = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, gare.getNomGare());
            preparedStatement.setBoolean(2, gare.getEstFret());
            preparedStatement.setBoolean(3, gare.getEstVoyageur());
            preparedStatement.setInt(4, gare.getLaCommune().getIdCommune());
            preparedStatement.setInt(5, gare.getCodeGare());
    
            preparedStatement.executeUpdate();
        }
    }

    public void updateAnnee(Annee annee) throws SQLException {
        String sql = "UPDATE Annee SET tauxInflation = ? WHERE annee = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setDouble(1, annee.getTauxInflation());
            preparedStatement.setInt(2, annee.getAnnee());
    
            preparedStatement.executeUpdate();
        }
    }

    public void updateDonneesAnnuelles(DonneesAnnuelles donneesAnnuelles) throws SQLException {
        String sql = "UPDATE donneesannuelles SET nbMaison = ?, nbAppart = ?, prixMoyen = ?, prixM2Moyen = ?, SurfaceMoy = ?, depensesCulturellesTotales = ?, budgetTotal = ?, population = ? WHERE lAnnee = ? AND laCommune = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setInt(1, donneesAnnuelles.getNbMaison());
            preparedStatement.setInt(2, donneesAnnuelles.getNbAppart());
            preparedStatement.setDouble(3, donneesAnnuelles.getPrixMoyen());
            preparedStatement.setDouble(4, donneesAnnuelles.getPrixM2Moyen());
            preparedStatement.setDouble(5, donneesAnnuelles.getSurfaceMoy());
            preparedStatement.setDouble(6, donneesAnnuelles.getDepensesCulturellesTotales());
            preparedStatement.setDouble(7, donneesAnnuelles.getBudgetTotal());
            preparedStatement.setInt(8, donneesAnnuelles.getPopulation());
            preparedStatement.setInt(9, donneesAnnuelles.getlAnnee().getAnnee());
            preparedStatement.setInt(10, donneesAnnuelles.getLaCommune().getIdCommune());
    
            preparedStatement.executeUpdate();
        }
    }

    public void updateAeroport(Aeroport aeroport) throws SQLException {
        String sql = "UPDATE aeroport SET adresse = ?, leDepartement = ? WHERE nom = ?";
        try (Connection connection = DriverManager.getConnection(databaseUrl, defaultUserName, defaultUserPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
    
            preparedStatement.setString(1, aeroport.getAdresse());
            preparedStatement.setInt(2, aeroport.getLeDepartement().getIdDep());
            preparedStatement.setString(3, aeroport.getNom());
    
            preparedStatement.executeUpdate();
        }
    }
}