package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.data.User;

public class LoginController {
    
    /**
     * Private attribute, the button for the login page, a JavaFX Button.
     */
    @FXML private Button button_login_connexion;
    
    /**
     * Private attribute, the button for the register page on the login page, a JavaFX Button.
     */
    @FXML private Button button_register_connexion;
    
    /**
     * Private attribute, the text field for the username on the login page, a JavaFX TextField.
     */
    @FXML private TextField textfield_username_connexion;
    
    /**
     * Private attribute, the text field for the password on the login page, a JavaFX TextField.
     */
    @FXML private TextField textfield_psswrd_connexion;

     /**
     * Public method that tries to log in the user with the specified username and password.
     * If the login is successful, the admin page is opened.
     * If the login is unsuccessful, an error message is displayed.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void tryLogin(ActionEvent event){
        System.out.println("tryLogin");
        User user = new User();
        String username = textfield_username_connexion.getText();
        String password = textfield_psswrd_connexion.getText();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        if(username == null){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être null.");
        }
        else if(username.isEmpty()){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être vide.");
        }
        else if(username.length() < 3 || username.length() > 20){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter entre 3 et 20 caractères.");
        }
        else if(password.length() < 8 || password.length() > 32){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
        }
        else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        }
        else{
            if(user.checkLogin(username, password)){
                try{
                    System.out.println("openAdminPage");

                    Scene registerPage = null;
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/AdminScene.fxml"));
                    registerPage = new Scene(root, 1116, 682);
        
                    stage.setScene(registerPage);

                    user.saveUserObject();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            else{
                System.err.println("login err for" + username);
                new PopupInfoController().showPopupInfo(stage, "Erreur de connexion\nLe mot de passe ou le nom d'utilisateur est invalide.");
            }
        }
    }

    /**
     * Public method that opens the register page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openRegisterPage(ActionEvent event){
        try{
            System.out.println("openRegisterPage");

            Scene registerPage = null;
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/RegisterScene.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}