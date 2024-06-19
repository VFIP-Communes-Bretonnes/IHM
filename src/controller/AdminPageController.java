package controller;

import java.io.IOException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.Node;
import javafx.scene.Parent;
import model.dao.ReadWriteDatabase;
import model.data.Aeroport;
import model.data.Annee;
import model.data.Commune;
import model.data.Departement;
import model.data.DonneesAnnuelles;
import model.data.Gare;
import model.data.User;
import javafx.scene.image.ImageView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;


public class AdminPageController {
    
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
     * Private attribute, the button for the settings register page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_settings_register;
    
    /**
     * Private attribute, the button for the deco page on the admin page, a JavaFX Button.
     */
    @FXML private Button button_deco_register;
    
    // BDD :

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
    @FXML private Button button_savetobdd_bdd_pageadmin;
    // pour les TableView
    private HashMap<ComboBox<String>, Integer> comboBoxList;
    private VBox neighborListContainer; 

    ProgressIndicator progressIndicator = new ProgressIndicator();


    // Settings :
    @FXML private Button button_apllysettings_settings;
    @FXML private TextField textfield_username_settings;
    @FXML private TextField textfield_mail_settings;
    @FXML private TextField textfield_phone_settings;
    @FXML private TextField textfield_actualpswrd_settings;
    @FXML private TextField textfield_newpswrd_settings;
    @FXML private TextField textfield_confirmpswrd_settings;

    // admin_user :
    @FXML private TextField textField_username_adduser;
    @FXML private TextField textField_role_adduser;
    @FXML private TextField textfield_mail_adduser;
    @FXML private TextField textfield_phone_adduser;
    @FXML private TextField textfield_psswrd_adduser;

    // stats :
    @FXML private ComboBox combobox_communeA;
    @FXML private ComboBox combobox_communeB;
    @FXML private ComboBox combobox_filtredonnees;
    @FXML private LineChart<String, Number> linechart_stats;
    @FXML private TextArea textarea_communeA;
    @FXML private TextArea textarea_communeB;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;
    @FXML private ImageView graphmap;
    @FXML private ComboBox combobox_graph;


    private double startX, startY;
    private double startTranslateX, startTranslateY;
    private double zoomFactor = 1.1;
    private double minScale = 0.1;
    private double maxScale = 10.0;
    private double startViewportX;
    private double startViewportY;

    public void addNewUserToDatabase(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        String username = textField_username_adduser.getText();
        String role = textField_role_adduser.getText();
        String mail = textfield_mail_adduser.getText();
        String phone = textfield_phone_adduser.getText();
        String psswrd = textfield_psswrd_adduser.getText();

        try{
        User newUser = new User();
        ReadWriteDatabase database = new ReadWriteDatabase();

        newUser.setUsername(username);
        newUser.setMail(mail);
        newUser.setPassword(psswrd);
        newUser.setPhone(phone);
        newUser.setRole(role);
        newUser.setReadWriteDatabase(database);

            database.addNewUserByObjct(newUser);
            new PopupInfoController().showPopupInfo(stage, "l'utilisateur '" + username + "' à bien été ajouter dans la base de données !");
        }
        catch(Exception e){
            new PopupInfoController().showPopupInfo(stage, "l'utilisateur '" + username + "'n'a pas été ajouter dans la base de données :\n\nLes données entrée ne sont pas valides !");
        }

        textField_username_adduser.clear();
        textField_role_adduser.clear();
        textfield_mail_adduser.clear();
        textfield_phone_adduser.clear();
        textfield_psswrd_adduser.clear();

        loadUserTableAdmin();
    }

    public void applySettingsToBDD(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        User currentUser = User.loadUserObject();
        currentUser.saveUserObject();

        String currentpassword = currentUser.getPassword();
        String password = textfield_actualpswrd_settings.getText();
        String new_password = textfield_newpswrd_settings.getText();
        String password_confirm = textfield_confirmpswrd_settings.getText();
        String currentUserName = currentUser.getUsername();

        boolean canTryUpdate = true;
        boolean cestutiledefairecetry = false;

        if(textfield_username_settings.getText() == null || textfield_username_settings.getText().isEmpty()){
            
        }
        else if(textfield_username_settings.getText().length() < 3 || textfield_username_settings.getText().length() > 20){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter entre 3 et 20 caractères.");
            canTryUpdate = false;
        }
        else if (!textfield_username_settings.getText().matches("^[a-zA-Z0-9-]+$")){
            new PopupInfoController().showPopupInfo(stage, "Le nom d'utilisateur est invalide\nLe nom d'utilisateur doit comporter uniquement des lettres, des chiffres et des tirets.");
            canTryUpdate = false;
        }
        else{
            currentUser.setUsername(textfield_username_settings.getText());
            cestutiledefairecetry = true;
        }

        if(textfield_mail_settings.getText() == null  || textfield_mail_settings.getText().isEmpty()){

        }
        else if(!textfield_mail_settings.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            new PopupInfoController().showPopupInfo(stage, "L'email est invalide\nVeuillez entrer un email valide.");
            canTryUpdate = false;
        }
        else{
            currentUser.setMail(textfield_mail_settings.getText());
            cestutiledefairecetry = true;
        }

        if(textfield_phone_settings.getText() == null || textfield_phone_settings.getText().isEmpty()){

        }
        else if(!textfield_phone_settings.getText().matches("^[0-9]{10}$")){
            new PopupInfoController().showPopupInfo(stage, "Le numéro de téléphone est invalide\nVeuillez entrer un numéro de téléphone valide de 10 chiffres.");
            canTryUpdate = false;
        }
        else{
            currentUser.setPhone(textfield_phone_settings.getText());
            cestutiledefairecetry = true;
        }

        if(password == null || password.isEmpty()){
            new_password = currentpassword;
        }
        else if(password.length() < 8 || password.length() > 32){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe actuel est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
            canTryUpdate = false;
        }
        else if(!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new PopupInfoController().showPopupInfo(stage, "Le mot de passe actuel est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
            canTryUpdate = false;
        }
        else if(new_password == null || new_password.isEmpty()){
            new PopupInfoController().showPopupInfo(stage, "Le nouveau mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
            canTryUpdate = false;
        }
        else if(new_password.length() < 8 || new_password.length() > 32){
            new PopupInfoController().showPopupInfo(stage, "Le nouveau mot de passe est invalide\nLe mot de passe doit comporter entre 8 et 32 caractères.");
            canTryUpdate = false;
        }
        else if(!new_password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")){
            new PopupInfoController().showPopupInfo(stage, "Le nouveau mot de passe est invalide\nLe mot de passe doit comporter au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial.");
            canTryUpdate = false;
        }
        else if(!password_confirm.toString().equals(new_password.toString())){
            new PopupInfoController().showPopupInfo(stage, "La verification du nouveau mot de passe est invalide\nLe mot de passe et la confirmation du mot de passe doivent être identique.");
            canTryUpdate = false;
        }
        else{
            currentUser.setPassword(new_password);
            currentpassword = password;
            cestutiledefairecetry = true;
        }

        if(canTryUpdate && cestutiledefairecetry){
            try{
                if(currentUser.getReadWriteDatabase().updateUserDataSettings(currentUserName, currentUser, currentpassword, new_password) != null){
                    new PopupInfoController().showPopupInfo(stage, "compte utilisateur '" + currentUser.getUsername() + "' bien modifier !");
                    currentUser.saveUserObject();
                }
                else{
                    new PopupInfoController(). showPopupInfo(stage, "Erreur de modification de l'utilisateur '" + currentUser.getUsername() + "' !\n le mot de passe actuel est invalide");
                }
            }
            catch(SQLException e){
                e.printStackTrace();
                new PopupInfoController(). showPopupInfo(stage, "Erreur de modification de l'utilisateur '" + currentUser.getUsername() + "' !");
            }   
        } 
    }

    public void exportDataToCSV(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ReadWriteDatabase database = new ReadWriteDatabase();
        String currentDirectory = System.getProperty("user.dir");
        try{
            database.loadAllData();
            database.getAllObjectsData().exportationEnCSV();
            new PopupInfoController().showPopupInfo(stage, "Données exporter dans \n'" + currentDirectory + " dans les 'nomTable.csv'");
        }
        catch (SQLException e){
            e.printStackTrace();
            new PopupInfoController().showPopupInfo(stage, "Erreur d'exportation des données !");
        }
    }

    public void exportUsersToCSV(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        try{
            ReadWriteDatabase database = new ReadWriteDatabase();
            database.loadAllUsers();
            database.getAllObjectsData().exportUserToCSV();
            String currentDirectory = System.getProperty("user.dir");
            new PopupInfoController().showPopupInfo(stage, "Données exporter dans \n'" + currentDirectory + "\\Users.csv'");
        }
        catch(SQLException e){
            new PopupInfoController().showPopupInfo(stage, "Erreur lors de l'exportation !");
            e.printStackTrace();
        }
    }

    public void loadUserTableAdmin() {
        tableView_user_adminpage.getColumns().clear();
        AdminPageController adminPageControllerCopy = this;
        ArrayList<User> usersList = User.loadAllDatabaseUsers();
        ObservableList<User> data = FXCollections.observableArrayList(usersList);

        TableColumn<User, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setMinWidth(200);
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<User, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setMinWidth(80);
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Make the role column editable
        roleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        roleColumn.setOnEditCommit(event -> {
            User user = event.getRowValue();
            try{
                user.setRole(event.getNewValue());
                user.getReadWriteDatabase().updateUserByObject(user);
            }
            catch(SQLException e){
                new PopupInfoController().showPopupInfo((Stage) roleColumn.getTableView().getScene().getWindow(), "Echec de la modification du rôle dans la base de données !");
            }
            catch(IllegalArgumentException e){
                new PopupInfoController().showPopupInfo((Stage) roleColumn.getTableView().getScene().getWindow(), "Ce rôle n'existe pas !");
            }
        });

        TableColumn<User, String> mailColumn = new TableColumn<>("Mail");
        mailColumn.setMinWidth(250);
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));

        TableColumn<User, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setMinWidth(100);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

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
                            button.getStyleClass().add("delete-button");
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                public void handle(ActionEvent event) {
                                    User user = getTableView().getItems().get(getIndex());
                                    String username = user.getUsername();
                                    ButtonEventHandeler.userRemoverButton(username, (Stage) getScene().getWindow(), adminPageControllerCopy);
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
        tableView_user_adminpage.getColumns().addAll(usernameColumn, mailColumn, phoneColumn, roleColumn, buttonCol);
    }

    public void loadDataIntoTable(ArrayList<?> list, TableView<Object> tableView, ReadWriteDatabase database) {
        tableView.getColumns().clear();
    
        if (list.isEmpty()) {
            return;
        }
    
        comboBoxList = new HashMap<>();
    
        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        neighborListContainer = new VBox();
    
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
    
            if ("idDep".equals(field.getName()) 
            || "nom".equals(field.getName()) 
            || "annee".equals(field.getName()) 
            || "codeGare".equals(field.getName())
            || "lAnnee".equals(field.getName())
            || "laCommune".equals(field.getName())
            || "idCommune".equals(field.getName())) {
                createLabelColumn(tableView, field);
            }
            else if (!fieldType.isPrimitive() && !fieldType.equals(String.class)) {
                createComboBoxColumn(tableView, database, field, fieldType);
            } else {
                createEditableColumn(tableView, field);
            }
        }

        tableView.getItems().setAll(list);
    }
    
    private void createLabelColumn(TableView<Object> tableView, Field field) {
        TableColumn<Object, String> column = new TableColumn<>(field.getName());
        column.setCellValueFactory(cellData -> {
            try {
                field.setAccessible(true);
                return new SimpleStringProperty(field.get(cellData.getValue()).toString());
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
                return new SimpleStringProperty("");
            }
        });
    
        column.setCellFactory(param -> new TableCell<Object, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label label = new Label(item);
                    setGraphic(label);
                }
            }
        });
    
        tableView.getColumns().add(column);
    }

    private void createComboBoxColumn(TableView<Object> tableView, ReadWriteDatabase database, Field field, Class<?> fieldType) {
        String className = fieldType.getSimpleName();
        if (className.charAt(className.length() - 1) != 's') {
            className += "s";
        }
        String getterName = "get" + className.substring(0, 1).toUpperCase() + className.substring(1) + "List";
    
        TableColumn<Object, String> column = new TableColumn<>(field.getName());
        column.setCellValueFactory(param -> {
            try {
                Field fieldRef = param.getValue().getClass().getDeclaredField(field.getName());
                fieldRef.setAccessible(true);
                Object value = fieldRef.get(param.getValue());
                return new SimpleStringProperty(value == null ? null : value.toString());
            } catch (Exception e) {
                //e.printStackTrace();
                return new SimpleStringProperty("");
            }
        });
    
        column.setCellFactory(param -> new ComboBoxTableCell(field, database, getterName));
    
        tableView.getColumns().add(column);
    }
    
    private void createEditableColumn(TableView<Object> tableView, Field field) {
        TableColumn<Object, String> column = new TableColumn<>(field.getName());
        column.setCellValueFactory(cellData -> {
            try {
                field.setAccessible(true);
                return new SimpleStringProperty(field.get(cellData.getValue()).toString());
            } catch (IllegalAccessException e) {
                //e.printStackTrace();
                return new SimpleStringProperty("");
            }
        });
    
        column.setCellFactory(TextFieldTableCell.forTableColumn());
    
        column.setOnEditCommit(event -> {
            Object rowObject = event.getRowValue();
            String newValue = event.getNewValue();
            String attributeName = field.getName();
    
            try {
                Class<?> rowClass = rowObject.getClass();
                String setterName = "set" + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                Method setter = rowClass.getMethod(setterName, field.getType());
                Object newValueParsed = parseValue(newValue, field.getType());
                setter.invoke(rowObject, newValueParsed);
            } catch (Exception e) {
                //e.printStackTrace();
            }
    
            event.getTableView().refresh();
        });
    
        tableView.getColumns().add(column);
    }
    
    private Object parseValue(String value, Class<?> targetType) {
        try {
            if (targetType == int.class || targetType == Integer.class) {
                return Integer.parseInt(value);
            } else if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(value);
            } else if (targetType == boolean.class || targetType == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else {
                return value;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            new PopupInfoController().showPopupInfo((Stage) tableView_bdd_adminpage.getScene().getWindow(), "La valeur entrée est invalide !");
            return null;
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
            Scene registerPage = null;
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(registerPage);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Public method that opens the home page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openHomePageAdmin(ActionEvent event){
        try{
            Scene registerPage = null;
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/admin_dashboard.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(registerPage);

            combobox_communeA = (ComboBox) ((Node)root).lookup("#combobox_communeA");
            combobox_communeB = (ComboBox) ((Node)root).lookup("#combobox_communeB");
            combobox_filtredonnees = (ComboBox) ((Node)root).lookup("#combobox_filtredonnees");
            combobox_graph = (ComboBox) ((Node)root).lookup("#combobox_graph");

            User user = User.loadUserObject();
            user.saveUserObject();
            user.getReadWriteDatabase().loadAllData();
            ArrayList<Commune> communesList = user.getReadWriteDatabase().getAllObjectsData().getCommunesList();

            for (Commune commune : communesList) {
                combobox_communeA.getItems().add(commune);
                combobox_communeB.getItems().add(commune);
                combobox_graph.getItems().add(commune);
            }

            combobox_filtredonnees.getItems().add("Maison vendu");
            combobox_filtredonnees.getItems().add("Budget total");
            combobox_filtredonnees.getItems().add("Depenses Culturelles");
            combobox_filtredonnees.getItems().add("Appartement vendu");
            combobox_filtredonnees.getItems().add("Prix du m² moyen");
            combobox_filtredonnees.getItems().add("Prix moyen des logements");
            combobox_filtredonnees.getItems().add("Surface moyen des logements");
            combobox_filtredonnees.getItems().add("Population");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(SQLException e){
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
            Scene registerPage = null;

            Parent root = FXMLLoader.load(getClass().getResource("/FXML/admin_database.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
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
            Scene registerPage = null;
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/admin_user.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(registerPage);

            tableView_user_adminpage = (TableView) ((Node)root).lookup("#tableView_user_adminpage");
            loadUserTableAdmin();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setTableView_user_adminpage(TableView tableView){
        this.tableView_user_adminpage = tableView;
    }

    /**
     * Public method that opens the settings page on the admin page.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void openSettingsPageAdmin(ActionEvent event){
        try{
            Scene registerPage = null;
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/admin_settings.fxml"));
            registerPage = new Scene(root, 1116, 682);

            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(registerPage);

            User currentUser = User.loadUserObject();
            currentUser.saveUserObject();

            textfield_mail_settings = (TextField) ((Node)root).lookup("#textfield_mail_settings");
            textfield_phone_settings = (TextField) ((Node)root).lookup("#textfield_phone_settings");
            textfield_username_settings = (TextField) ((Node)root).lookup("#textfield_username_settings");

            textfield_mail_settings.setPromptText(currentUser.getMail());
            textfield_phone_settings.setPromptText(currentUser.getPhone());
            textfield_username_settings.setPromptText(currentUser.getUsername());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if(new PopupYoNController().showPopupYoN(stage, "Déconexion :\nVoulez vous vraiment vous déconnecter ?")){
            openLoginPage(event);
        }
    }

    public void saveEditedDataToBDD(ActionEvent event) {
        String choix = choix_table_bdd_adminpage.getText();
        ReadWriteDatabase database = new ReadWriteDatabase();
        ObservableList<Object> items = tableView_bdd_adminpage.getItems();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.getStyleClass().add("loading");
        VBox loadingBox = new VBox(10, progressIndicator);
        loadingBox.setAlignment(Pos.CENTER);
        // Show loading indicator
        loadingBox.setVisible(true);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        AnchorPane root = (AnchorPane) stage.getScene().getRoot();
        root.getChildren().add(loadingBox);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (Object item : items) {
                    try {
                        if (item instanceof Departement) {
                            database.updateDepartement((Departement) item);
                        }
                        if (item instanceof Commune) {
                            database.updateCommune((Commune) item);
                        }
                        if (item instanceof Annee) {
                            database.updateAnnee((Annee) item);
                        }
                        if (item instanceof DonneesAnnuelles) {
                            database.updateDonneesAnnuelles((DonneesAnnuelles) item);
                        }
                        if (item instanceof Aeroport) {
                            database.updateAeroport((Aeroport) item);
                        }
                        if (item instanceof Gare) {
                            database.updateGare((Gare) item);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
    
            @Override
            protected void succeeded() {
                // Hide loading indicator
                loadingBox.setVisible(false);
            }
    
            @Override
            protected void failed() {
                // Hide loading indicator and handle the error
                loadingBox.setVisible(false);
                // Optionally, show an error message
                Throwable e = getException();
                //e.printStackTrace();
            }
        };
    
        // Run the task in a background thread
        new Thread(task).start();
    }
    
    public void changementChoixBddAdmin(ActionEvent event){

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.getStyleClass().add("loading");
        VBox loadingBox = new VBox(10, progressIndicator);
        loadingBox.setAlignment(Pos.CENTER);
        loadingBox.setVisible(true);
        loadingBox.toFront();

        Stage stage = (Stage) tableView_bdd_adminpage.getScene().getWindow();
        AnchorPane root = (AnchorPane) stage.getScene().getRoot();
        root.getChildren().add(loadingBox);
        loadingBox.getStyleClass().add("loading-panel");

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Show loading indicator
                Platform.runLater(() -> {
                    loadingBox.setVisible(true);
                });
        
        
                // Update UI with results
                Platform.runLater(() -> {
                    MenuItem clickedItem = (MenuItem) event.getSource();
                    choix_table_bdd_adminpage.setText(clickedItem.getText());
                    tableView_bdd_adminpage.setEditable(true);
                    ReadWriteDatabase database = new ReadWriteDatabase();
                    try{
                        database.loadAllData();
                    }
                    catch(SQLException e){
                        e.printStackTrace();
                    }
                    if (clickedItem == itemchoice_dep_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getDepartementsList(), tableView_bdd_adminpage, database);
                    } else if (clickedItem == itemchoice_aero_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getAeroportsList(), tableView_bdd_adminpage, database);
                    } else if (clickedItem == itemchoice_annee_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getAnneesList(), tableView_bdd_adminpage, database);
                    } else if (clickedItem == itemchoice_com_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getCommunesList(), tableView_bdd_adminpage, database);
                    } else if (clickedItem == itemchoice_donnann_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getDonneesAnnuellesList(), tableView_bdd_adminpage, database);
                    } else if (clickedItem == itemchoice_gare_bdd_adminpage) {
                        loadDataIntoTable(database.getAllObjectsData().getGaresList(), tableView_bdd_adminpage, database);
                    }
                    System.out.println(clickedItem.getText());
                    System.out.println(database.getAllObjectsData().getDepartementsList().toString());
        
                    // Hide loading indicator
                    loadingBox.setVisible(false);
                });
        
                return null;
            }
        
            @Override
            protected void succeeded() {
                // Hide loading indicator
                loadingBox.setVisible(false);
            }
        
            @Override
            protected void failed() {
                // Hide loading indicator and handle the error
                // Optionally, show an error message
                Throwable e = getException();
                loadingBox.setVisible(false);
                //e.printStackTrace();
            }
        };
        
        // Run the task in a background thread
        new Thread(task).start();
    }

    public void selectionCommuneA(ActionEvent event) {
        ComboBox clickedItem = (ComboBox) event.getSource();
        Commune communeA = (Commune) clickedItem.getSelectionModel().getSelectedItem();
        String nomCommuneA = communeA.toString();
        Commune communeB = (Commune) combobox_communeB.getSelectionModel().getSelectedItem();
        ReadWriteDatabase database = new ReadWriteDatabase();
        try {
            database.loadAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<DonneesAnnuelles> listDonneCommuneA = database.getAllObjectsData().getDonneeAnnuelleByCommune(communeA);
        ArrayList<DonneesAnnuelles> listDdonneCommuneB = null;
        DonneesAnnuelles DACommuneB = null;
    
        DonneesAnnuelles DACommuneA = listDonneCommuneA.get(0);
        for (DonneesAnnuelles donneesAnnuelles : listDonneCommuneA) {
            if (DACommuneA.getlAnnee().getAnnee() < donneesAnnuelles.getlAnnee().getAnnee()) {
                DACommuneA = donneesAnnuelles;
            }
        }
    
        String infoCommuneA = nomCommuneA + " en " + DACommuneA.getlAnnee().getAnnee() + "\n\n"
                + "Budget total : " + DACommuneA.getBudgetTotal() + "\n"
                + "Depenses Culturelles Totales : " + DACommuneA.getDepensesCulturellesTotales() + "\n"
                + "Appartement vendu : " + DACommuneA.getNbAppart() + "\n"
                + "Maison vendu : " + DACommuneA.getNbMaison() + "\n"
                + "Population : " + DACommuneA.getPopulation() + "\n"
                + "Prix du m² moyen : " + DACommuneA.getPrixM2Moyen() + "\n"
                + "Prix moyen des logements : " + DACommuneA.getPrixMoyen() + "\n"
                + "Surface moyen des logements : " + DACommuneA.getSurfaceMoy();
    
        textarea_communeA.setText(infoCommuneA);
        textarea_communeA.setWrapText(true);
        textarea_communeA.setVisible(true);
    
        if (communeB != null) {
            String nomCommuneB = communeB.toString();
    
            listDdonneCommuneB = database.getAllObjectsData().getDonneeAnnuelleByCommune(communeB);
    
            DACommuneB = listDdonneCommuneB.get(0);
            for (DonneesAnnuelles donneesAnnuelles : listDdonneCommuneB) {
                if (DACommuneB.getlAnnee().getAnnee() < donneesAnnuelles.getlAnnee().getAnnee()) {
                    DACommuneB = donneesAnnuelles;
                }
            }
    
            String donneeChoisi = (String) combobox_filtredonnees.getSelectionModel().getSelectedItem();
            if (donneeChoisi == null) {
                combobox_filtredonnees.getSelectionModel().selectFirst();
            }
    
            showCharts(donneeChoisi, listDonneCommuneA, listDdonneCommuneB, nomCommuneA, DACommuneA, nomCommuneB, DACommuneB);
        }
    }
    
    public void selectionCommuneB(ActionEvent event) {
        ComboBox clickedItem = (ComboBox) event.getSource();
        Commune communeB = (Commune) clickedItem.getSelectionModel().getSelectedItem();
        String nomCommuneB = communeB.toString();
        Commune communeA = (Commune) combobox_communeA.getSelectionModel().getSelectedItem();
        ReadWriteDatabase database = new ReadWriteDatabase();
        try {
            database.loadAllData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<DonneesAnnuelles> listDonneCommuneB = database.getAllObjectsData().getDonneeAnnuelleByCommune(communeB);
        ArrayList<DonneesAnnuelles> listDonneCommuneA = null;
        DonneesAnnuelles DACommuneA = null;
    
        DonneesAnnuelles DACommuneB = listDonneCommuneB.get(0);
        for (DonneesAnnuelles donneesAnnuelles : listDonneCommuneB) {
            if (DACommuneB.getlAnnee().getAnnee() < donneesAnnuelles.getlAnnee().getAnnee()) {
                DACommuneB = donneesAnnuelles;
            }
        }
    
        String infoCommuneB = nomCommuneB + " en " + DACommuneB.getlAnnee().getAnnee() + "\n\n"
                + "Budget total : " + DACommuneB.getBudgetTotal() + "\n"
                + "Depenses Culturelles Totales : " + DACommuneB.getDepensesCulturellesTotales() + "\n"
                + "Appartement vendu : " + DACommuneB.getNbAppart() + "\n"
                + "Maison vendu : " + DACommuneB.getNbMaison() + "\n"
                + "Population : " + DACommuneB.getPopulation() + "\n"
                + "Prix du m² moyen : " + DACommuneB.getPrixM2Moyen() + "\n"
                + "Prix moyen des logements : " + DACommuneB.getPrixMoyen() + "\n"
                + "Surface moyen des logements : " + DACommuneB.getSurfaceMoy();
    
        textarea_communeB.setText(infoCommuneB);
        textarea_communeB.setWrapText(true);
        textarea_communeB.setVisible(true);
    
        if (communeA != null) {
            String nomCommuneA = communeA.toString();
    
            listDonneCommuneA = database.getAllObjectsData().getDonneeAnnuelleByCommune(communeA);
    
            DACommuneA = listDonneCommuneA.get(0);
            for (DonneesAnnuelles donneesAnnuelles : listDonneCommuneA) {
                if (DACommuneA.getlAnnee().getAnnee() < donneesAnnuelles.getlAnnee().getAnnee()) {
                    DACommuneA = donneesAnnuelles;
                }
            }
    
            String donneeChoisi = (String) combobox_filtredonnees.getSelectionModel().getSelectedItem();
            if (donneeChoisi == null) {
                combobox_filtredonnees.getSelectionModel().selectFirst();
                donneeChoisi = (String) combobox_filtredonnees.getSelectionModel().getSelectedItem();
            }
    
            showCharts(donneeChoisi, listDonneCommuneA, listDonneCommuneB, nomCommuneA, DACommuneA, nomCommuneB, DACommuneB);
        }
    }

    public void reloadChart(){
        linechart_stats.getData().clear();
        linechart_stats.getData().removeAll(linechart_stats.getData());
        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
        XYChart.Series<String, Number> series4 = new XYChart.Series<>();
        linechart_stats.getData().addAll(series3, series4);

        ActionEvent custom2 = new ActionEvent(combobox_communeB, combobox_communeB);
        combobox_communeB.fireEvent(custom2);
    }

    public void showCharts(String donneeChoisi, ArrayList<DonneesAnnuelles> listDonneCommuneA, ArrayList<DonneesAnnuelles> listDonneCommuneB, String nomCommuneA, DonneesAnnuelles DACommuneA, String nomCommuneB, DonneesAnnuelles DACommuneB){
        
        System.out.println("################### chaert");
        System.out.println(donneeChoisi);
        System.out.println(listDonneCommuneA);
        System.out.println(listDonneCommuneB);
        System.out.println(nomCommuneA);
        System.out.println(DACommuneA);
        System.out.println(nomCommuneB);
        System.out.println(DACommuneB);
        System.out.println("################### chaert");
        
        linechart_stats.getData().clear();
            
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Year");
            yAxis.setLabel("Data Value");

            // Creating the line chart
            linechart_stats.setTitle("Comparaison et évolution : " + donneeChoisi);

            XYChart.Series<String, Number> donneeChoisiCommuneA = new XYChart.Series<>();
            donneeChoisiCommuneA.setName("Commune A");

            XYChart.Series<String, Number> donneeChoisiCommuneB = new XYChart.Series<>();
            donneeChoisiCommuneB.setName("Commune B");

            if(donneeChoisi.equals("Maison vendu")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getNbMaison()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getNbMaison()));
                }
            }
            else if(donneeChoisi.equals("Budget total")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getBudgetTotal()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getBudgetTotal()));
                }
            }
            else if(donneeChoisi.equals("Depenses Culturelles")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getDepensesCulturellesTotales()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getDepensesCulturellesTotales()));
                }
            }
            else if(donneeChoisi.equals("Appartement vendu")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getNbAppart()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getNbAppart()));
                }
            }
            else if(donneeChoisi.equals("Prix du m² moyen")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getPrixM2Moyen()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getPrixM2Moyen()));
                }
            }
            else if(donneeChoisi.equals("Prix moyen des logements")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getPrixMoyen()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getPrixMoyen()));
                }
            }
            else if(donneeChoisi.equals("Surface moyen des logements")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getSurfaceMoy()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getSurfaceMoy()));
                }
            }
            else if(donneeChoisi.equals("Population")){
                for (DonneesAnnuelles data : listDonneCommuneA) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneA.getData().add(new XYChart.Data<>(year, data.getPopulation()));
                }
                for (DonneesAnnuelles data : listDonneCommuneB) {
                    String year = String.valueOf(data.getlAnnee().getAnnee());
                    donneeChoisiCommuneB.getData().add(new XYChart.Data<>(year, data.getPopulation()));
                }
            }

            linechart_stats.getData().addAll(donneeChoisiCommuneA, donneeChoisiCommuneB);
            linechart_stats.setVisible(true);
    }

    public void handleCreateGraph(ActionEvent event){
        ComboBox clickedItem = (ComboBox) event.getSource();
        Commune commune = (Commune) clickedItem.getSelectionModel().getSelectedItem();
        if(commune != null){
            int idCommune = commune.getIdCommune();
            try{
                generateGraph(idCommune);
                String currentDirectory = System.getProperty("user.dir");
                Image image = new Image("file:" + currentDirectory + "/graphe.png");

                //applique l'image au graphmap
                graphmap.setImage(image);
                this.graphmap.setPreserveRatio(true);
                this.graphmap.setSmooth(true);
                this.graphmap.setCache(true);
            }
            catch (Exception e) {
                e.printStackTrace();
                new PopupInfoController().showPopupInfo((Stage) linechart_stats.getScene().getWindow(), "Erreur lors de la génération du graphe !");
            }
        }
    }

    public static void generateGraph(int depNum) throws Exception {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("linux")) {
            // Make linux.bin executable
            ProcessBuilder pb = new ProcessBuilder("/bin/bash", "-c", "chmod +x rendering/linux.bin");
            Process process = pb.start();
            process.waitFor();

            pb = new ProcessBuilder("/bin/bash", "-c", "chmod +x rendering/depend.sh");
            process = pb.start();
            process.waitFor();

            pb = new ProcessBuilder("/bin/bash", "-c", "./rendering/depend.sh");
            process = pb.start();
            process.waitFor();

            // Run linux.bin
            pb = new ProcessBuilder("python3", "rendering/main.py", String.valueOf(depNum));
            process = pb.start();
            process.waitFor();
        }
    }


    public void imagePressed(MouseEvent event) {
        Rectangle2D viewport = graphmap.getViewport();
        startX = event.getSceneX();
        startY = event.getSceneY();
        startViewportX = viewport.getMinX();
        startViewportY = viewport.getMinY();
    }

    public void imageDragged(MouseEvent event) {
        double deltaX = event.getSceneX() - startX;
        double deltaY = event.getSceneY() - startY;
        Rectangle2D viewport = graphmap.getViewport();

        double newViewportX = startViewportX - deltaX;
        double newViewportY = startViewportY - deltaY;

        double imageWidth = graphmap.getImage().getWidth();
        double imageHeight = graphmap.getImage().getHeight();

        double maxViewportX = imageWidth - viewport.getWidth();
        double maxViewportY = imageHeight - viewport.getHeight();

        newViewportX = Math.max(0, Math.min(newViewportX, maxViewportX));
        newViewportY = Math.max(0, Math.min(newViewportY, maxViewportY));

        graphmap.setViewport(new Rectangle2D(newViewportX, newViewportY, viewport.getWidth(), viewport.getHeight()));
    }

    public void imageScrolled(ScrollEvent event) {
        if (event.getDeltaY() > 0) {
            zoomIn(this.graphmap);
        } else if (event.getDeltaY() < 0) {
            zoomOut(this.graphmap);
        }
        event.consume();
    }

    private void zoomIn(ImageView imageView) {
        Rectangle2D viewport = imageView.getViewport();
        double currentWidth = viewport.getWidth();
        double currentHeight = viewport.getHeight();
        if (currentWidth * zoomFactor <= imageView.getImage().getWidth() && currentHeight * zoomFactor <= imageView.getImage().getHeight()) {
            imageView.setViewport(new Rectangle2D(viewport.getMinX(), viewport.getMinY(), currentWidth * zoomFactor, currentHeight * zoomFactor));
        }
    }

    private void zoomOut(ImageView imageView) {
        Rectangle2D viewport = imageView.getViewport();
        double currentWidth = viewport.getWidth();
        double currentHeight = viewport.getHeight();
        if (currentWidth / zoomFactor >= minScale && currentHeight / zoomFactor >= minScale) {
            imageView.setViewport(new Rectangle2D(viewport.getMinX(), viewport.getMinY(), currentWidth / zoomFactor, currentHeight / zoomFactor));
        }
    }
}

