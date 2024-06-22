package edu.westga.cs6910.elementalclash.view;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.viewmodel.ViewCard;
import edu.westga.cs6910.elementalclash.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Elemental Clash defines the "controller" for ElementalClash.fxml.
 * 
 * @version 06/16/2024
 * @autor Savitha Venkatesh
 */
public class ElementalClashCodeBehind {

    private static final Color ELEMENTAL_CLASH_TABLE_BACKGROUND = Color.GREEN.deriveColor(1, 1, 1, 0.8);

    private ViewCard viewCard;
    private ViewModel viewModel;

    @FXML
    private AnchorPane pane;

    @FXML
    private HBox humanTableFront;

    @FXML
    private HBox computerTableFront;

    @FXML
    private HBox humanTableBack;

    @FXML
    private HBox computerTableBack;

    /**
     * Instantiates a new elementalclash-code-behind.
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
        this.displayCards();
    }

    /**
     * Initializes the UI.
     * 
     * @precondition none
     * @postcondition none
     */
    private void initializeUI() {
        BackgroundFill fill = new BackgroundFill(ELEMENTAL_CLASH_TABLE_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(fill);
        this.pane.setBackground(background);
    }

    /**
     * Displays the cards.
     * 
     * @precondition none
     * @postcondition none
     */
    private void displayCards() {
        this.humanTableFront.getChildren().clear();
        this.computerTableFront.getChildren().clear();
        this.humanTableBack.getChildren().clear();
        this.computerTableBack.getChildren().clear();

        List<Card> humanHand = this.viewModel.getHumanHand();
        List<Card> computerHand = this.viewModel.getComputerHand();

        System.out.println("Human Hand: " + humanHand);
        System.out.println("Computer Hand: " + computerHand);

        for (Card card : humanHand) {
            System.out.println("Adding human card: " + card);
            this.humanTableFront.getChildren().add(this.viewCard.faceUp(card));
            this.humanTableBack.getChildren().add(this.viewCard.faceDown(card));
        }
        
        for (Card card : computerHand) {
            System.out.println("Adding computer card: " + card);
            this.computerTableFront.getChildren().add(this.viewCard.faceUp(card));
            this.computerTableBack.getChildren().add(this.viewCard.faceDown(card));
        }
    }

    /**
     * Handles the play round action.
     * 
     * @precondition none
     * @postcondition none
     */
    @FXML
    private void handlePlayRound() {
        this.viewModel.playRound();
        this.displayCards();

        String roundResult = this.viewModel.getLastRoundResult();
        System.out.println(roundResult);  // Print the round result to the console

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Round Result");
        alert.setHeaderText(null);
        alert.setContentText(roundResult);
        alert.showAndWait();

        if (this.viewModel.isGameOver()) {
            String winner = this.viewModel.getWinner();
            System.out.println("Game over! The winner is: " + winner); 

            Alert gameOverAlert = new Alert(Alert.AlertType.INFORMATION);
            gameOverAlert.setTitle("Game Over");
            gameOverAlert.setHeaderText(null);
            gameOverAlert.setContentText("Game over! The winner is: " + winner);
            gameOverAlert.showAndWait();
        }
    }
}