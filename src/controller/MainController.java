package controller;

import java.io.IOException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.Node;
import javafx.scene.Parent;
import model.dao.ReadWriteDatabase;
import model.data.User;

/**
 * Public class that represents the main controller for the application.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class MainController {

    // login page
    
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

    // register page
    
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

    // Button Admin Page
    
    /**
     * Private attribute, the button for the home page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_home_adminpage;
    
    /**
     * Private attribute, the button for the bdd page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_bdd_adminpage;
    
    /**
     * Private attribute, the button for the user register page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_user_register;
    
    /**
     * Private attribute, the button for the stat register page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_stat_register;
    
    /**
     * Private attribute, the button for the settings register page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_settings_register;
    
    /**
     * Private attribute, the button for the deco page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_deco_register;
    
    // tableaux et bdd admin

    @FXML private TableView<User> tableView_user_adminpage;
    @FXML private TableView<Object> tableView_bdd_adminpage;
    @FXML private MenuButton choix_table_bdd_adminpage;
    @FXML private MenuItem itemchoice_dep_bdd_adminpage;
    @FXML private MenuItem itemchoice_aero_bdd_adminpage;
    @FXML private MenuItem itemchoice_annee_bdd_adminpage;
    @FXML private MenuItem itemchoice_com_bdd_adminpage;
    @FXML private MenuItem itemchoice_donnann_bdd_adminpage;
    @FXML private MenuItem itemchoice_gare_bdd_adminpage;
    @FXML private Button button_exportcsv_bdd_pageadmin;

    public void exportDataToCSV(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ReadWriteDatabase database = new ReadWriteDatabase();
        try{
            database.loadAllData();
            database.getAllObjectsData().exportationEnCSV();
            showPopupInfo(stage, "données exporter !");
        }
        catch (SQLException e){
            e.printStackTrace();
            showPopupInfo(stage, "Erreur d'exportation des données !");
        }
    }

    public void loadUserTableAdmin() {
        tableView_user_adminpage.getColumns().clear();
        MainController mainControllerCopy = this;
        ArrayList<User> usersList = User.loadAllDatabaseUsers();
        ObservableList<User> data = FXCollections.observableArrayList(usersList);
        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(100);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(100);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        roleColumn.setEditable(true);
        tableView_user_adminpage.setEditable(true);

        TableColumn<User, Void> buttonCol = new TableColumn<>("Action");
        buttonCol.setMinWidth(100);

        // Use a custom cell factory for the button column
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<User, Void>() {

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            // Create a button without a callback
                            Button button = new Button("Supprimer");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    User user = getTableView().getItems().get(getIndex());
                                    String username = user.getUsername();
                                    ButtonEventHandeler.userRemoverButton(username, (Stage) getScene().getWindow(), mainControllerCopy);
                                }
                            });
                            setGraphic(button);
                        }
                    }
                };
                return cell;
            }
        };

        buttonCol.setCellFactory(cellFactory);

        tableView_user_adminpage.setItems(data);
        tableView_user_adminpage.getColumns().addAll(usernameColumn, roleColumn, buttonCol);
    }

    public void loadDataIntoTable(ArrayList<?> list, TableView tableView) {
        tableView.getColumns().clear();

        if (list.isEmpty()) {
            return;
        }

        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            TableColumn<Object, String> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(cellData -> {
                try {
                    field.setAccessible(true);
                    return new SimpleStringProperty(field.get(cellData.getValue()).toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return new SimpleStringProperty("");
                }
            });
            column.setEditable(true);
            tableView_bdd_adminpage.setEditable(true);
            tableView.getColumns().add(column);
        }

        tableView.getItems().setAll(list);
    }

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
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être null.");
        }
        else if(username.isEmpty()){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être vide.");
        }
        else if(username.length() < 3 || username.length() > 20){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter entre 3 et 20 caractères.");
        }
        else if (!username.matches("^[a-zA-Z0-9-]+$")){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter uniquement des lettres, des chiffres et des tirets.");
        }
        else if(password.length() < 8 || password.length() > 32){
            showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
        }
        else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        }
        else{
            if(user.checkLogin(username, password)){
                try{
                    System.out.println("openAdminPage");
                    Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminScene.fxml"));
                    stage.setScene(registerPage);
                    user.saveUserObject();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            else{
                System.err.println("login err for" + username);
                showPopupInfo(stage, "Erreur de connexion\nLe mot de passe ou le nom d'utilisateur est invalide.");
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
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/RegisterScene.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

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
        String password = textfield_psswrd_register.getText();
        String password_confirm = textfield_psswrd_confirm_register.getText();

        if(username == null){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être null.");
        }
        else if(username.isEmpty()){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur ne doit pas être vide.");
        }
        else if(username.length() < 3 || username.length() > 20){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter entre 3 et 20 caractères.");
        }
        else if (!username.matches("^[a-zA-Z0-9-]+$")){
            showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter uniquement des lettres, des chiffres et des tirets.");
        }
        else if(password.length() < 8 || password.length() > 32){
            showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
        }
        else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            showPopupInfo(stage, "Le mot de passe est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
        }
        else if(!password_confirm.toString().equals(password.toString())){
            showPopupInfo(stage, "La verification de mot de passe est invalide\nLe mot de passe et la confirmation du mot de passe doivent être identique.");
        }
        else{
            boolean wantToContinue = showPopupYoN(stage, "Vous êtes sur le point de créer l'utilisateur '" + username + "'.\nVouslez vous continuer cette action ?");
            
            if (wantToContinue) {
                if(User.register(username, password)){
                    showPopupInfo(stage, "compte utilisateur '" + username + "' bien créer !");
                }
                else{
                    showPopupInfo(stage, "Erreur de création de compte\nLa création de l'utilisateur '" + username + "' à échouer !");
                }
            } 
            else {
                System.out.println("Création annulé !");
            }
        }
    }   

    /**
     * Public method that opens the login page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openLoginPage(ActionEvent event){
        try{
            System.out.println("openRegisterPage");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/LoginScene.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that displays an information popup with the specified message.
     *
     * @param ownerStage The Stage that will own the popup. It cannot be null, a Stage object.
     * @param message The message to be displayed in the popup. It cannot be null or empty, a String object.
     * @throws RuntimeException if ownerStage or message are null or empty.
     */
    public void showPopupInfo(Stage ownerStage, String message){
        try{
            PopupController popupController = new PopupController();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PopupInfo.fxml"));
            loader.setController(popupController);
            Parent root = loader.load();

            Scene popupScene = new Scene(root);

            Stage popupStage = new Stage();
            popupStage.setScene(popupScene);
            popupController.initialize(message);

            popupStage.setResizable(false);
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initOwner(ownerStage);

            popupStage.showAndWait();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Public method that displays a yes or no popup with the specified message.
     *
     * @param ownerStage The Stage that will own the popup. It cannot be null, a Stage object.
     * @param message The message to be displayed in the popup. It cannot be null or empty, a String object.
     * @return true if the user clicks yes, false if the user clicks no or if an error occurs, a boolean.
     * @throws RuntimeException if ownerStage or message are null or empty.
     */
    public boolean showPopupYoN(Stage ownerStage, String message) {
        try{
            PopupController popupController = new PopupController();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PopupYoN.fxml"));
            loader.setController(popupController);
            Parent root = loader.load();

            Scene popupScene = new Scene(root);

            Stage popupStage = new Stage();
            popupStage.setScene(popupScene);
            popupController.initialize(message);

            popupStage.setResizable(false);
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.initStyle(StageStyle.UNDECORATED);
            popupStage.initOwner(ownerStage);

            popupStage.showAndWait();
            boolean result = popupController.getResult();

            return result;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Public method that opens the home page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openHomePageAdmin(ActionEvent event){
        try{
            System.out.println("openHomePageAdmin");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminScene.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that opens the bdd page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openBDDPageAdmin(ActionEvent event){
        try{
            System.out.println("openBDDPageAdmin");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminSceneBDD.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that opens the user page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openUserPageAdmin(ActionEvent event){
        try{
            System.out.println("openUserPageAdmin");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminSceneUsers.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that opens the stat page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openStatPageAdmin(ActionEvent event){
        try{
            System.out.println("openStatPageAdmin");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminSceneStats.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that opens the settings page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openSettingsPageAdmin(ActionEvent event){
        try{
            System.out.println("openSettingsPageAdmin");
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            Scene registerPage = FXMLLoader.load(getClass().getResource("/FXML/AdminSceneSettings.fxml"));
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if(showPopupYoN(stage, "Déconexion :\nVoulez vous vraiment vous déconnecter ?")){
            openLoginPage(event);
        }
    }

    public void changementChoixBddAdmin(ActionEvent event){
        MenuItem clickedItem = (MenuItem) event.getSource();
        choix_table_bdd_adminpage.setText(clickedItem.getText());
        tableView_bdd_adminpage.setEditable(true);
        ReadWriteDatabase database = new ReadWriteDatabase();

        try{
            database.loadAllData();

            if (clickedItem == itemchoice_dep_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getDepartementsList(), tableView_bdd_adminpage);
            } else if (clickedItem == itemchoice_aero_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getAeroportsList(), tableView_bdd_adminpage);
            } else if (clickedItem == itemchoice_annee_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getAnneesList(), tableView_bdd_adminpage);
            } else if (clickedItem == itemchoice_com_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getCommunesList(), tableView_bdd_adminpage);
            } else if (clickedItem == itemchoice_donnann_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getDonneesAnnuellesList(), tableView_bdd_adminpage);
            } else if (clickedItem == itemchoice_gare_bdd_adminpage) {
                loadDataIntoTable(database.getAllObjectsData().getGaresList(), tableView_bdd_adminpage);
            }
            System.out.println(clickedItem.getText());
            System.out.println(database.getAllObjectsData().getDepartementsList().toString());
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
