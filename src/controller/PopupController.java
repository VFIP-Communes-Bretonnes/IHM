package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Public class that represents the controller for the popup window.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class PopupController {

    /**
     * Private attribute, the button for the yes option in the popup window, a JavaFX Button.
     */
    @FXML private Button ouiButton_popupyon;

    /**
     * Private attribute, the button for the no option in the popup window, a JavaFX Button.
     */
    @FXML private Button nonButton_popupyon;
    
    /**
     * Private attribute, the label for the message in the popup window, a JavaFX Label.
     */
    @FXML private Label message_popup;
    
    /**
     * Private attribute, the result of the user's choice in the popup window, a boolean.
     */
    private boolean result = false;

    /**
     * Public method that initializes the popup window with the specified message.
     *
     * @param message The message to be displayed in the popup window. It cannot be null or empty, a String object.
     * @throws RuntimeException if message is null or empty.
     */
    public void initialize(String message) {
        message_popup.setText(message);
        message_popup.setWrapText(true);
    }

    /**
     * Public method that handles the user's choice of the yes option in the popup window.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void handleOuiButton(ActionEvent event) {
        result = true;
        closePopup();
    }

    /**
     * Public method that handles the user's choice of the no option in the popup window.
     *
     * @param event The ActionEvent that triggered the method. It cannot be null, an ActionEvent object.
     * @throws RuntimeException if event is null.
     */
    public void handleNonButton(ActionEvent event) {
        result = false;
        closePopup();
    }

    /**
     * Public method that returns the result of the user's choice in the popup window.
     *
     * @return true if the user chose the yes option, false if the user chose the no option or if an error occurred, a boolean.
     */
    public boolean getResult() {
        return result;
    }

    /**
     * Private method that closes the popup window.
     */
    private void closePopup() {
        Stage stage = (Stage) ouiButton_popupyon.getScene().getWindow();
        stage.close();
    }
}