package model.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.management.relation.Role;

import model.dao.ReadWriteDatabase;

/**
 * Public class that represents a user in the app.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class User implements Serializable {

    /**
     * Private attribute, the username of the user, a String.
     */
    private String username;
    
    /**
     * Private attribute, the password of the user, a String.
     */
    private String password;
    
    /**
     * Private attribute, the role of the user, a String.
     */
    private String role;

    private String mail;
    private String phone;
    
    /**
     * Private attribute, the database object for the user, a ReadWriteDatabase object.
     */
    private ReadWriteDatabase database;

    public enum Roles {
        user, admin;
    }

    /**
     * Public default constructor, creates a new User object with default values.
     */
    public User() {
        this.username = "DEF";
        this.password = "DEF";
        this.role = "DEF";
        this.database = new ReadWriteDatabase();
    }

    /**
     * Public overloaded constructor, creates a new User object with the specified username, role, and database object.
     *
     * @param username The username of the user. It cannot be null or empty, a String object.
     * @param role The role of the user. It cannot be null or empty, a String object.
     * @param database The database object for the user. It cannot be null, a ReadWriteDatabase object.
     * @throws RuntimeException if username, role, or database are null or empty.
     */
    public User(String username, String role, String mail, String phone, ReadWriteDatabase database){
        this.database = database;
        this.username = username;
        this.role = role;
        this.password = "DEF";
        this.mail = mail;
        this.phone = phone;
    }

    /**
     * Public static method that loads all the users from the database and returns them in an ArrayList.
     *
     * @return An ArrayList of User objects containing all the users from the database, an ArrayList of User object.
     */
    public static ArrayList<User> loadAllDatabaseUsers(){
        ReadWriteDatabase database = new ReadWriteDatabase();
        try {
            database.loadAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return database.getAllObjectsData().getUsersList();
    }

    /**
     * Public method that returns the username of the user.
     *
     * @return The username of the user, a String object.
     */
    public String getUsername() {
        return this.username;
    }

    public String getMail() {
        return this.mail;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setMail(String mail) {
        if(mail == null){
            throw new RuntimeException("[ERREUR:User:setMail] : le parametre 'mail' est 'null' ");
        }
        this.mail = mail;
    }

    public void setPhone(String phone) {
        if(phone == null){
            throw new RuntimeException("[ERREUR:User:setPhone] : le parametre 'phone' est 'null' ");
        }
        this.phone = phone;
    }

    /**
     * Public method that returns the password of the user.
     *
     * @return The password of the user, a String object.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Public method that returns the role of the user.
     *
     * @return The role of the user, a String object.
     */
    public String getRole(){
        return this.role;
    }

    public void setRole(String role){
        if(role == null){
            throw new RuntimeException("[ERREUR:User:setRole] : le parametre 'role' est 'null' ");
        }

        boolean roleExiste = false;
        for (Roles roles : Roles.values() ) {
            if(roles.toString().equals(role)){
                roleExiste = true;
            }
        }

        if(roleExiste == false){
            throw new IllegalArgumentException("[ERREUR:User:setRole] : le parametre 'role' est invalide (le rôle choisi n'existe pas) ");
        }

        this.role = role;
    }

    public ReadWriteDatabase getReadWriteDatabase(){
        return this.database;
    }

    /**
     * Public method that sets the username of the user.
     *
     * @param username The username of the user. It cannot be null or empty, a String object.
     * @throws RuntimeException if username is null or empty.
     */
    public void setUsername(String username) {
        if(username == null){
            throw new RuntimeException("[ERREUR:User:setUsername] : le parametre 'username' est 'null' ");
        }
        this.username = username;
    }

    /**
     * Public method that sets the password of the user.
     *
     * @param password The password of the user. It cannot be null or empty, a String object.
     * @throws RuntimeException if password is null or empty.
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new RuntimeException("[ERREUR:User:setPassword] : le parametre 'password' est 'null' ");
        }
        this.password = password;
    }

    /**
     * Public method that checks if the specified username and password match the user's credentials.
     *
     * @param username The username to be checked. It cannot be null or empty, a String object.
     * @param password The password to be checked. It cannot be null or empty, a String object.
     * @return true if the credentials match, false otherwise, a boolean.
     * @throws RuntimeException if username or password are null or empty.
     */
    public boolean checkLogin(String username, String password) {
        if(username == null){
            throw new RuntimeException("[ERREUR:User:checkLogin] : le parametre 'username' est 'null' ");
        }
        if (password == null) {
            throw new RuntimeException("[ERREUR:User:checkLogin] : le parametre 'password' est 'null' ");
        }

        boolean ret = false;
        this.setUsername(username);
        this.setPassword(password);
        String tmp = this.database.findUserAndGetRole(username, password);
        if(tmp.equals("err")){
            ret = false;
        }
        else{
            this.role = tmp;
            System.out.println(this.role);
            ret = true;
        }
        return ret;
    }

    /**
     * Public static method that adds a new user to the database with the specified username and password.
     *
     * @param username The username of the new user. It cannot be null or empty, a String object.
     * @param password The password of the new user. It cannot be null or empty, a String object.
     * @return true if the user was added successfully, false otherwise, a boolean.
     */
    public static boolean register(String username, String password, String mail, String phone) {
        try{
            ReadWriteDatabase.addNewUser(username, password, mail, phone);
            return true;
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
            return false;
        }
    }

    public void saveUserObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cache.ser"))) {
            User tmp = this.copy();
            oos.writeObject(tmp);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User copy(){
        User user = new User(this.username, this.role, this.mail, this.phone, this.database);
        user.setPassword(this.getPassword());

        return user;
    }

    public static User loadUserObject() {
        User user = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cache.ser"))) {
            user = (User) ois.readObject();
            ois.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return user;
    }
}