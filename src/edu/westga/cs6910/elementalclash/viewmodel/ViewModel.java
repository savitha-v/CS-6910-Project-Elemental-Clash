package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.*;
import javafx.beans.property.*;

/**
 * ViewModel provides the bridge between the view and the model for the
 * Elemental Clash game. It manages the game's state and provides methods for
 * the view to interact with the game.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class ViewModel {
	private Game game;
	private StringProperty roundResult;
	private IntegerProperty humanWins;
	private IntegerProperty computerWins;

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
	}

	/**
	 * Starts the game by initializing the players and drawing their initial hands.
	 * 
	 * @precondition none
	 * @postcondition players have initial hands drawn and game is ready to play
	 */
	public void startGame() {
		this.game.start();
		this.roundResult.set("");
		this.humanWins.set(0);
		this.computerWins.set(0);
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
			this.humanWins.set(((AbstractPlayer) this.game.getHumanPlayer()).getWins());
			this.computerWins.set(((AbstractPlayer) this.game.getComputerPlayer()).getWins());
		} catch (IllegalStateException e) {
			this.roundResult.set("Deck is empty, refilling...");
			this.game.getDeck().refillDeck();
			this.playRound();
		}
	}

	/**
	 * Gets the round result property.
	 * 
	 * @return the round result property
	 */
	public StringProperty roundResultProperty() {
		return this.roundResult;
	}

	/**
	 * Gets the human wins property.
	 * 
	 * @return the human wins property
	 */
	public IntegerProperty humanWinsProperty() {
		return this.humanWins;
	}

	/**
	 * Gets the computer wins property.
	 * 
	 * @return the computer wins property
	 */
	public IntegerProperty computerWinsProperty() {
		return this.computerWins;
	}
}