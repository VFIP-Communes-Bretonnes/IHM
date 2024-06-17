package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.data.User;

public class RegisterController {
    
    /**
     * Private attribute, the button for the register page, a JavaFX Button.
     */
    @FXML private Button button_register_register;
    
    /**
     * Private attribute, the button for the back page on the register page, a JavaFX Button.
     */
    @FXML private Button button_back_register;
    
    /**
     * Private attribute, the text field for the username on the register page, a JavaFX TextField.
     */
    @FXML private TextField textfield_username_register;
    
    /**
     * Private attribute, the text field for the password on the register page, a JavaFX TextField.
     */
    @FXML private TextField textfield_psswrd_register;
    
    /**
     * Private attribute, the text field for the confirm password on the register page, a JavaFX TextField.
     */
    @FXML private TextField textfield_psswrd_confirm_register;

    @FXML private CheckBox checkbox_cgu_register;
    @FXML private DatePicker datepicker_datenaissance_register;
    @FXML private TextField textfield_mail_register;
    @FXML private TextField textfield_phone_register;

    
    /**
     * Public method that tries to register the user with the specified username, password, and confirm password.
     * If the registration is successful, a success message is displayed.
     * If the registration is unsuccessful, an error message is displayed.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void tryRegister(ActionEvent event){
        System.out.println("tryRegister");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        String username = textfield_username_register.getText();
        String mail = textfield_mail_register.getText();
        String phone = textfield_phone_register.getText();
        Boolean cgu = checkbox_cgu_register.isSelected();
        String password = textfield_psswrd_register.getText();
        String password_confirm = textfield_psswrd_confirm_register.getText();

        if(username == null){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être null.");
        }
        else if(username.isEmpty()){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être vide.");
        }
        else if(username.length() < 3 || username.length() > 20){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter entre 3 et 20 caractères.");
        }
        else if (!username.matches("^[a-zA-Z0-9-]+$")){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter uniquement des lettres, des chiffres et des tirets.");
        }
        else if(!mail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            new PopupInfoController().showPopupInfo(stage, "L'email est invalide\nVeuillez entrer un email valide.");
        }
        else if(!phone.matches("^[0-9]{10}$")){
            new PopupInfoController().showPopupInfo(stage, "Le numéro de téléphone est invalide\nVeuillez entrer un numéro de téléphone valide de 10 chiffres.");
        }
        else if(!cgu){
            new PopupInfoController().showPopupInfo(stage, "Vous devez accepter les conditions générales d'utilisation.");
        }
        else if(password.length() < 8 || password.length() > 32){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
        }
        else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        }
        else if(!password_confirm.toString().equals(password.toString())){
            new PopupInfoController().showPopupInfo(stage, "La verification de mot de passe est invalide\nLe mot de passe et la confirmation du mot de passe doivent être identique.");
        }
        else{
            boolean wantToContinue = new PopupYoNController().showPopupYoN(stage, "Vous êtes sur le point de créer l'utilisateur '" + username + "'.\nVouslez vous continuer cette action ?");
        
            if (wantToContinue) {
                if(User.register(username, password, mail, phone)){
                    new PopupInfoController().showPopupInfo(stage, "compte utilisateur '" + username + "' bien créer !");
                }
                else{
                    new PopupInfoController(). showPopupInfo(stage, "Erreur de création de compte\nLa création de l'utilisateur '" + username + "' à échouer !");
                }
            }
            else {
                System.out.println("Création annulé !");
            }
        }
    }   
}