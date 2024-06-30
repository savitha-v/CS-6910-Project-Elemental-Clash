package edu.westga.cs6910.elementalclash;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * ElementalClashApplication extends the JavaFX Application class to build the
 * GUI and start program execution.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class ElementalClashApplication extends Application {

    public static final String GUI_FXML = "view/ElementalClash.fxml";
    public static final String APP_NAME = "CS6910 Elemental Clash by Savitha Venkatesh";

    /**
     * Constructs a new Application.
     * 
     * @precondition none
     * @postcondition none
     */
    public ElementalClashApplication() {
        super();
    }

    /**
     * Starts the JavaFX application.
     * 
     * @precondition primaryStage != null
     * @postcondition primaryStage is set up and shown
     * 
     * @param primaryStage the primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane pane = this.loadGui();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle(APP_NAME);
            primaryStage.show();
        } catch (IllegalStateException | IOException anException) {
            anException.printStackTrace();
        }
    }

    /**
     * Loads the GUI from the FXML file.
     *
     * @precondition none
     * @postcondition none
     * @return the loaded Pane
     * @throws IOException if the FXML file cannot be loaded
     */
    private Pane loadGui() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(GUI_FXML));
        return (Pane) loader.load();
    }

    /**
     * The main method - entry point for the program.
     * 
     * @precondition none
     * @postcondition none
     * @param args - the command line arguments not used
     */
    public static void main(String[] args) {
        launch(args);
    }
}