package edu.westga.cs6910.elementalclash.view;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.viewmodel.ViewCard;
import edu.westga.cs6910.elementalclash.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.List;

/**
 * ElementalClashCodeBehind defines the "controller" for ElementalClash.fxml.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class ElementalClashCodeBehind {
    
    private ViewCard viewCard;
    private ViewModel viewModel;

    @FXML
    private AnchorPane pane;

    @FXML
    private HBox humanTableFront;

    @FXML
    private HBox computerTableFront;
    
    @FXML
    private ImageView backOfCardImageView;

    @FXML
    private Label roundResultLabel;

    @FXML
    private Label humanWinsLabel;

    @FXML
    private Label humanLifePointsLabel;

    @FXML
    private Label computerWinsLabel;

    @FXML
    private Label computerLifePointsLabel;

    /**
     * Instantiates a new ElementalClashCodeBehind.
     * 
     * @precondition none
     * @postcondition none
     */
    public ElementalClashCodeBehind() {
        this.viewModel = new ViewModel();
        this.viewCard = new ViewCard();
    }

    /**
     * Initializes the GUI components, binding them to the view model properties.
     * 
     * @precondition none
     * @postcondition none
     */
    @FXML
    public void initialize() {
        this.initializeUI();
        this.viewModel.startGame();
        this.bindProperties();
        this.displayCards();
    }

    /**
     * Initializes the UI.
     * 
     * @precondition none
     * @postcondition none
     */
    private void initializeUI() {
        // Create a separate AnchorPane for the background
        AnchorPane backgroundPane = new AnchorPane();
        
        // Load the background image
        Image backgroundImage = new Image("file:images/background.jpg"); 
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(false);

        // Bind the size of the background image view to the size of the main pane
        backgroundImageView.fitWidthProperty().bind(this.pane.widthProperty());
        backgroundImageView.fitHeightProperty().bind(this.pane.heightProperty());

        // Create a translucent white overlay pane
        Pane overlayPane = new Pane();
        overlayPane.setBackground(new Background(new BackgroundFill(
            Color.rgb(255, 255, 255, 0.5), 
            CornerRadii.EMPTY,
            Insets.EMPTY
        )));

        // Bind the size of the overlay pane to the size of the main pane
        overlayPane.prefWidthProperty().bind(this.pane.widthProperty());
        overlayPane.prefHeightProperty().bind(this.pane.heightProperty());

        // Add the background image and overlay pane to the backgroundPane
        backgroundPane.getChildren().addAll(backgroundImageView, overlayPane);

        // Add the backgroundPane to the main pane
        this.pane.getChildren().add(0, backgroundPane); 
    }

    /**
     * Binds the ViewModel properties to the UI components.
     */
    private void bindProperties() {
        this.roundResultLabel.textProperty().bind(this.viewModel.roundResultProperty());
        this.humanWinsLabel.textProperty().bind(this.viewModel.humanWinsProperty().asString());
        this.humanLifePointsLabel.textProperty().bind(this.viewModel.humanLifePointsProperty().asString());
        this.computerWinsLabel.textProperty().bind(this.viewModel.computerWinsProperty().asString());
        this.computerLifePointsLabel.textProperty().bind(this.viewModel.computerLifePointsProperty().asString());
    }

    /**
     * Displays the cards in the human and computer players' hands.
     * 
     * @precondition none
     * @postcondition the cards are displayed in the UI
     */
    private void displayCards() {
        this.humanTableFront.getChildren().clear();
        this.computerTableFront.getChildren().clear();

        List<Card> humanHand = this.viewModel.getHumanHand();
        List<Card> computerHand = this.viewModel.getComputerHand();

        for (Card card : humanHand) {
            this.humanTableFront.getChildren().add(this.viewCard.faceUp(card));
        }

        for (Card card : computerHand) {
            this.computerTableFront.getChildren().add(this.viewCard.faceUp(card));
        }
        
        Image backOfCardImage = this.viewCard.getBackOfCardImage();
        if (backOfCardImage != null) {
            this.backOfCardImageView.setImage(backOfCardImage);
            this.backOfCardImageView.setFitWidth(220.0);
            this.backOfCardImageView.setFitHeight(340.0);
        }
    }

    /**
     * Handles the play round action, updates the UI, and displays round results.
     * 
     * @precondition none
     * @postcondition the round is played and results are displayed
     */
    @FXML
    private void handlePlayRound() {
        this.viewModel.playRound();
        this.displayCards();

        if (this.viewModel.isGameOver()) {
            String winner = this.viewModel.getWinner();

            Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
            gameOverAlert.setTitle("Game Over");
            gameOverAlert.setHeaderText(null);
            gameOverAlert.setContentText("Game over! The winner is: " + winner);
            gameOverAlert.showAndWait();
        }
    }

    /**
     * Handles the restart round action.
     * 
     * @precondition none
     * @postcondition the current round is restarted
     */
    @FXML
    private void handleRestartRound() {
        this.viewModel.restartRound();
        this.displayCards();
    }
    
    /**
     * Handles the new game action.
     * 
     * @precondition none
     * @postcondition the game is restarted
     */
    @FXML
    private void handleNewGame() {
        this.viewModel.newGame();
        this.displayCards();
    }

    /**
     * Handles the save game action.
     * 
     * @precondition none
     * @postcondition the game state is saved
     */
    @FXML
    private void handleSaveGame() {
        this.viewModel.saveGame();
    }

    /**
     * Handles the load game action.
     * 
     * @precondition none
     * @postcondition the game state is loaded
     */
    @FXML
    private void handleLoadGame() {
        this.viewModel.loadGame();
        this.displayCards();
    }
}