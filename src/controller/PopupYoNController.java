package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupYoNController {

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
}