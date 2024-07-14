package edu.westga.cs6910.elementalclash.view;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.viewmodel.ViewCard;
import edu.westga.cs6910.elementalclash.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
 * @version 07/14/2024
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
        this.showAlert("Restart Round", "Restarts the current round, resetting hands and life points to their previous state. Restarting a new round prior to playing atleast one round results in no action.");
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
        this.showAlert("New Game", "Starts a new game, resetting all scores and life points.");
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
        this.showAlert("Save Game", "Saves the current state of the game, including scores, life points, and card positions.");
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
        this.showAlert("Load Game", "Loads a previously saved game state, restoring scores, life points, and card positions.");
        this.viewModel.loadGame();
        this.displayCards();
    }
    
    /**
     * Handles the display of game objectives.
     * 
     * @precondition none
     * @postcondition the objectives are displayed in an alert
     */
    @FXML
    private void handleObjectives() {
        this.showAlert("Game Objectives", "Objectives:\nThe primary objective of Elemental Clash is to reduce your opponent's life points to zero by playing elemental cards strategically. Players must balance offense and defense, taking advantage of elemental strengths and weaknesses to outsmart their opponent.");
    }

    /**
     * Handles the display of game rules.
     * 
     * @precondition none
     * @postcondition the rules are displayed in an alert
     */
    @FXML
    private void handleRules() {
        this.showAlert("Game Rules", "Rules:\n- Each player starts with 20 life points.\n- Players draw five cards from their deck at the beginning of the game.\n- Each turn, a player can draw one card and play one elemental card.\n- Elemental cards have different strengths and weaknesses:\n  Fire beats Air\n  Air beats Earth\n  Earth beats Water\n  Water beats Fire\n- If an elemental card beats the opponent's card, the opponent loses life points equal to the winning card's power.\n- The game continues until one player’s life points reach zero.");
    }

    /**
     * Handles the display of game scoring.
     * 
     * @precondition none
     * @postcondition the scoring is displayed in an alert
     */
    @FXML
    private void handleScoring() {
        this.showAlert("Game Scoring", "Scoring:\nEach card has a power level indicated on it. When a card wins a clash, the opponent loses life points equal to the power level of the winning card. For example, if a Fire card with a power of 3 beats an Air card, the opponent loses 3 life points.");
    }

    /**
     * Displays an alert with the specified title and content.
     * 
     * @param title the title of the alert
     * @param content the content of the alert
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        
        TextArea textArea = new TextArea(content);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        
        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}