package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.AbstractPlayer;
import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;
import edu.westga.cs6910.elementalclash.model.Play;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * ViewModel provides the bridge between the view and the model for the
 * Elemental Clash game. It manages the game's state and provides methods for
 * the view to interact with the game.
 * 
 * @version 07/14/2024
 * @author Savitha Venkatesh
 */
public class ViewModel {
    private Game game;
    private StringProperty roundResult;
    private IntegerProperty humanWins;
    private IntegerProperty computerWins;
    private IntegerProperty humanLifePoints;
    private IntegerProperty computerLifePoints;

    /**
     * Constructs a new ViewModel and initializes the game.
     * 
     * @precondition none
     * @postcondition game is initialized with a new deck and players
     */
    public ViewModel() {
        Deck deck = new Deck();
        this.game = new Game(new HumanPlayer("Human", deck), new ComputerPlayer("Computer", deck), deck);
        this.roundResult = new SimpleStringProperty();
        this.humanWins = new SimpleIntegerProperty();
        this.computerWins = new SimpleIntegerProperty();
        this.humanLifePoints = new SimpleIntegerProperty();
        this.computerLifePoints = new SimpleIntegerProperty();
        this.updateLifePoints();
    }

    /**
     * Starts the game by initializing the players and drawing their initial hands.
     * 
     * @precondition none
     * @postcondition players have initial hands drawn and game is ready to play
     */
    public void startGame() {
        this.game.start();
        this.updateWins();
        this.updateLifePoints();
        this.roundResult.set("");
    }

    /**
     * Plays a round of the game.
     * 
     * @precondition none
     * @postcondition the round result is determined and properties are updated
     */
    public void playRound() {
        try {
            this.game.playRound();
            this.roundResult.set(this.game.getLastRoundResult());
            this.updateWins();
            this.updateLifePoints();
        } catch (IllegalStateException illegalException) {
            this.roundResult.set("Deck is empty, refilling...");
            this.game.getDeck().refillDeck();
            this.playRound();
        }
    }

    /**
     * Restarts the current round without resetting the game.
     */
    public void restartRound() {
        this.game.restartRound();
        this.updateWins();
        this.updateLifePoints();
        this.roundResult.set(this.game.getLastRoundResult());
    }
    
    /**
     * Starts new game
     * 
     * @precondition none
     * @postcondition the game is restarted with new hands
     */
    public void newGame() {
        Deck deck = new Deck();
        this.game = new Game(new HumanPlayer("Human", deck), new ComputerPlayer("Computer", deck), deck);
        this.startGame();
    }

    /**
     * Updates the win counts for each player.
     */
    private void updateWins() {
        this.humanWins.set(((AbstractPlayer) this.game.getHumanPlayer()).getWins());
        this.computerWins.set(((AbstractPlayer) this.game.getComputerPlayer()).getWins());
    }

    /**
     * Updates the life points for each player.
     */
    private void updateLifePoints() {
        this.humanLifePoints.set(this.game.getHumanPlayer().getLifePoints());
        this.computerLifePoints.set(this.game.getComputerPlayer().getLifePoints());
    }

    /**
     * Checks if the game is over, which occurs if either player's life points reach
     * zero.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return this.game.isGameOver();
    }

    /**
     * Gets the winner of the game.
     * 
     * @return the name of the player who won the game, or "No winner yet" if there
     *         is no winner
     */
    public String getWinner() {
        Play winner = this.game.getWinner();
        if (winner != null) {
            return winner.getName();
        } else {
            return "No winner yet";
        }
    }

    /**
     * Gets the human player's hand.
     * 
     * @return the list of cards in the human player's hand
     */
    public List<Card> getHumanHand() {
        return this.game.getHumanPlayer().getHand();
    }

    /**
     * Gets the computer player's hand.
     * 
     * @return the list of cards in the computer player's hand
     */
    public List<Card> getComputerHand() {
        return this.game.getComputerPlayer().getHand();
    }

    /**
     * Saves the game state to a file.
     */
    public void saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game_state.dat"))) {
            out.writeObject(this.game);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads the game state from a file.
     */
    public void loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("game_state.dat"))) {
            this.game = (Game) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        this.updateWins();
        this.updateLifePoints();
        this.roundResult.set("");
    }

    /**
     * Gets the property representing the result of the last round.
     *
     * @return the StringProperty representing the last round result
     */
    public StringProperty roundResultProperty() {
        return this.roundResult;
    }

    /**
     * Gets the property representing the number of wins of the human player.
     *
     * @return the IntegerProperty representing the human player's wins
     */
    public IntegerProperty humanWinsProperty() {
        return this.humanWins;
    }

    /**
     * Gets the property representing the number of wins of the computer player.
     *
     * @return the IntegerProperty representing the computer player's wins
     */
    public IntegerProperty computerWinsProperty() {
        return this.computerWins;
    }

    /**
     * Gets the property representing the life points of the human player.
     *
     * @return the IntegerProperty representing the human player's life points
     */
    public IntegerProperty humanLifePointsProperty() {
        return this.humanLifePoints;
    }

    /**
     * Gets the property representing the life points of the computer player.
     *
     * @return the IntegerProperty representing the computer player's life points
     */
    public IntegerProperty computerLifePointsProperty() {
        return this.computerLifePoints;
    }
}