package controller;

import java.io.IOException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
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

/**
 * Public class that represents the main controller for the application.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprient - TREVIAN Benjamin
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
    @FXML private Button button_savetobdd_bdd_pageadmin;
    private HashMap<ComboBox<String>, Integer> comboBoxList;
    private VBox neighborListContainer;

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
    
            if (!fieldType.isPrimitive() && !fieldType.equals(String.class)) {
                createComboBoxColumn(tableView, database, field, fieldType);
            } else {
                createEditableColumn(tableView, field);
            }
        }

        tableView.getItems().setAll(list);
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
                e.printStackTrace();
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
                e.printStackTrace();
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
                e.printStackTrace();
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
            e.printStackTrace();
            return null;
        }
    }

    public class ComboBoxTableCell extends TableCell<Object, String> {
        private final ComboBox<String> comboBox;
        private final Field field;
        private final ReadWriteDatabase database;
        private final String getterName;
        private ArrayList<Object> choice;
        private ArrayList<Object> choiceVoisin;
        private VBox neighborContainer;
    
        public ComboBoxTableCell(Field field, ReadWriteDatabase database, String getterName) {
            this.field = field;
            this.database = database;
            this.getterName = getterName;
            this.comboBox = new ComboBox<>();
            this.choice = new ArrayList<>();
            this.choiceVoisin = new ArrayList<>();
            this.neighborContainer = new VBox();
    
            comboBox.setEditable(true);
    
            try {
                Method getter = database.getAllObjectsData().getClass().getMethod(getterName);
                Object listResult = getter.invoke(database.getAllObjectsData());
                for (Object obj : (ArrayList<?>) listResult) {
                    comboBox.getItems().add(obj.toString());
                    this.choice.add(obj);
                }
            } catch (Exception e) {
                try{
                    Object listResult = database.getAllObjectsData().getCommunesList();
                    for (Object obj : (ArrayList<?>) listResult) {
                        comboBox.getItems().add(obj.toString());
                        this.choice.add(obj);
                    }
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    
            comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    Object rowObject = getTableRow().getItem();
                    if (rowObject != null) {
                        try {
                            field.setAccessible(true);
                            updateMainObject(rowObject, newValue);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                comboBox.setValue(item);
                if (getTableRow().getItem() instanceof Commune && isNeighborField()) {
                    updateNeighborContainer((Commune) getTableRow().getItem());
                    setGraphic(neighborContainer);
                } else {
                    setGraphic(comboBox);
                }
            }
        }
    
        private boolean isNeighborField() {
            return field.getName().equalsIgnoreCase("listeVoisins");
        }
    
        private void updateMainObject(Object rowObject, String newValue) throws Exception {
            if (rowObject instanceof Departement) {
                ((Departement) rowObject).setNomDep(Departement.NomDepartement.valueOf(newValue));
            } else if (rowObject instanceof Aeroport) {
                Object dep = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
                ((Aeroport) rowObject).setLeDepartement((Departement) dep);
            } else if (rowObject instanceof Commune) {
                // No need to update here, handled by UI changes
                try{
                    Object dep = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
                    if(dep instanceof Departement){
                        ((Commune) rowObject).setLeDepartement((Departement) dep);
                    }
                }
                catch (Exception e){
                    // mdr
                }
            } else if (rowObject instanceof DonneesAnnuelles) {
                Object newO = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
                if (newO instanceof Annee) {
                    ((DonneesAnnuelles) rowObject).setAnnee((Annee) newO);
                } else if (newO instanceof Commune) {
                    ((DonneesAnnuelles) rowObject).setLaCommune((Commune) newO);
                }
            } else if (rowObject instanceof Gare) {
                Object newO = choice.get(comboBox.getItems().indexOf(comboBox.getSelectionModel().getSelectedItem()));
                ((Gare) rowObject).setLaCommune((Commune) newO);
            }
        }
    
        private void updateNeighborContainer(Commune commune) {
            neighborContainer.getChildren().clear();
            ArrayList<Commune> neighbors = commune.getListeVoisins();
    
            for (Commune neighbor : neighbors) {
                HBox neighborBox = new HBox();
                ComboBox<String> neighborComboBox = new ComboBox<>();
                Button deleteButton = new Button("Delete");
    
                for (Object obj : choice) {
                    if (obj instanceof Commune) {
                        neighborComboBox.getItems().add(((Commune) obj).toString());
                    }
                }
    
                neighborComboBox.setValue(neighbor.toString());
                neighborComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    Commune newNeighbor = (Commune) choice.get(neighborComboBox.getItems().indexOf(newValue));
                    int index = neighbors.indexOf(neighbor);
                    if (index >= 0) {
                        neighbors.set(index, newNeighbor);
                    }
                });
    
                deleteButton.setOnAction(event -> {
                    neighbors.remove(neighbor);
                    neighborContainer.getChildren().remove(neighborBox);
                });
    
                neighborBox.getChildren().addAll(neighborComboBox, deleteButton);
                neighborContainer.getChildren().add(neighborBox);
            }
    
            Button addButton = new Button("Add Neighbor");
            addButton.setOnAction(event -> {
                HBox newNeighborBox = new HBox();
                ComboBox<String> newNeighborComboBox = new ComboBox<>();
                Button newDeleteButton = new Button("Delete");
    
                for (Object obj : choice) {
                    if (obj instanceof Commune) {
                        newNeighborComboBox.getItems().add(((Commune) obj).toString());
                    }
                }
    
                newNeighborComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    Commune newNeighbor = (Commune) choice.get(newNeighborComboBox.getItems().indexOf(newValue));
                    if (!neighbors.contains(newNeighbor)) {
                        neighbors.add(newNeighbor);
                    }
                });
    
                newDeleteButton.setOnAction(e -> {
                    neighbors.removeIf(c -> c.toString().equals(newNeighborComboBox.getValue()));
                    neighborContainer.getChildren().remove(newNeighborBox);
                });
    
                newNeighborBox.getChildren().addAll(newNeighborComboBox, newDeleteButton);
                neighborContainer.getChildren().add(newNeighborBox);
            });
    
            neighborContainer.getChildren().add(addButton);
        }
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

    public void saveEditedDataToBDD(ActionEvent event){
        String choix = choix_table_bdd_adminpage.getText();

        ObservableList<Object> items = tableView_bdd_adminpage.getItems();

        for (Object item : items) {
            System.out.println(item);
            System.out.println(item.getClass());
            if(item instanceof Commune){
                System.out.println( ((Commune) item).getListeVoisins().toString() );
            }
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
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
