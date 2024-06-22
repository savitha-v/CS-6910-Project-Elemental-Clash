package edu.westga.cs6910.elementalclash.test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;

/**
 * The TestGame class.
 * 
 * Tests the functionality of the Game class in the Elemental Clash game.
 * 
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class TestGame {

	private Game game;
	private Deck deck;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition this.game and this.deck are initialized
	 */
	@BeforeEach
	public void setUp() {
		this.deck = new Deck();
		this.game = new Game(new HumanPlayer("Human", this.deck), new ComputerPlayer("Computer", this.deck), this.deck);
	}

	/**
	 * Tests that the game starts with the correct initial conditions.
	 * 
	 * @precondition none
	 * @postcondition game is started with initial hands drawn
	 */
	@Test
	public void testGameShouldStartWithCorrectInitialConditions() {
		this.game.start();
		assertEquals(5, this.game.getHumanPlayer().getHand().size());
		assertEquals(5, this.game.getComputerPlayer().getHand().size());
	}

	/**
	 * Tests that playing a round decreases the deck size.
	 * 
	 * @precondition none
	 * @postcondition deck size is decreased by 2
	 */
	@Test
	public void testPlayRoundShouldDecreaseDeckSize() {
		this.game.start();
		int initialDeckSize = this.deck.size();
		this.game.playRound();
		assertEquals(initialDeckSize - 2, this.deck.size());
	}

	/**
	 * Tests that playing multiple rounds results in correct life point deductions.
	 * 
	 * @precondition none
	 * @postcondition life points are deducted correctly
	 */
	@Test
	public void testPlayMultipleRoundsShouldResultInCorrectLifePointDeductions() {
		this.game.start();
		for (int i = 0; i < 10; i++) {
			this.game.playRound();
		}
		assertTrue(this.game.getHumanLifePoints() < 20 || this.game.getComputerLifePoints() < 20);
	}

	/**
	 * Tests that the game is over when a player has zero life points.
	 * 
	 * @precondition none
	 * @postcondition game is over when a player has zero life points
	 */
	@Test
	public void testIsGameOverShouldReturnTrueWhenPlayerHasZeroLifePoints() {
		this.game.start();
		while (this.game.getHumanLifePoints() > 0 && this.game.getComputerLifePoints() > 0) {
			this.game.playRound();
		}
		assertTrue(this.game.isGameOver());
	}

	/**
	 * Tests that the winner is correctly determined when the game is over.
	 * 
	 * @precondition none
	 * @postcondition the winner is correctly determined
	 */
	@Test
	public void testGetWinnerShouldReturnCorrectWinnerWhenGameIsOver() {
		this.game.start();
		while (this.game.getHumanLifePoints() > 0 && this.game.getComputerLifePoints() > 0) {
			this.game.playRound();
		}
		assertNotNull(this.game.getWinner());
	}
}