package view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Public class that represents the main entry point for the JavaFX application.
 *
 * @author DIONNE Clément - PONDAVEN Thibault - ARANDEL cyprien - TREVIAN Benjamin
 * @version 30/05/2024
 */
public class Main extends Application {

    /**
     * Public method that starts the JavaFX application by loading the login scene FXML file and displaying it in a stage.
     *
     * @param stage The primary stage for the application. It cannot be null, a Stage object.
     */
    public void start(Stage stage) {
        try {
            Scene root = FXMLLoader.load(getClass().getResource("/FXML/LoginScene.fxml"));
            

            stage.setTitle("Statumun");
            stage.setScene(root);
            stage.setResizable(true);
            stage.setMinWidth(744.0);
            stage.setMinHeight(455.0);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * The main method that runs the Main class.
    * The entry point of the program.
    *
    * @param args the command line arguments provided during the program's startup, an Array of Strings.
    */
    public static void main(String[] args) {
        launch(args);
    }
}