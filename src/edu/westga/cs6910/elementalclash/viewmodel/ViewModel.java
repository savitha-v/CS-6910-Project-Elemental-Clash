package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Play;
import edu.westga.cs6910.elementalclash.model.Card;
import java.util.List;

/**
 * ViewModel provides the bridge between the view and the model for the Elemental Clash game.
 * It manages the game's state and provides methods for the view to interact with the game.
 * 
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class ViewModel {
    private Game game;
    
    /**
     * Constructs a new ViewModel and initializes the game.
     * 
     * @precondition none
     * @postcondition game is initialized with a new deck and players
     */
    public ViewModel() {
        Deck deck = new Deck();
        this.game = new Game(new HumanPlayer("Human", deck), new ComputerPlayer("Computer", deck), deck);
    }

    /**
     * Starts the game by initializing the players and drawing their initial hands.
     * 
     * @precondition none
     * @postcondition players have initial hands drawn and game is ready to play
     */
    public void startGame() {
        this.game.start();
    }

    /**
     * Plays a round of the game where both players draw a card and a winner is determined.
     * 
     * @precondition none
     * @postcondition the round result is determined and life points are adjusted accordingly
     */
    public void playRound() {
        this.game.playRound();
    }

    /**
     * Checks if the game is over, which occurs if either player's life points reach zero.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return this.game.isGameOver();
    }

    /**
     * Gets the winner of the game.
     * 
     * @return the name of the player who won the game, or "No winner yet" if there is no winner
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
     * Gets the result of the last round played.
     * 
     * @return the result of the last round
     */
    public String getLastRoundResult() {
        return this.game.getLastRoundResult();
    }
}