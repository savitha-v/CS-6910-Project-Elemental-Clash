package edu.westga.cs6910.elementalclash.test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.ComputerPlayer;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;

/**
 * The TestGame class.
 * 
 * Tests the functionality of the Game class in the Elemental Clash game.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class TestGame {

	private Game game;
	private HumanPlayer humanPlayer;
	private ComputerPlayer computerPlayer;
	private Deck deck;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition game, humanPlayer, computerPlayer, and deck are initialized
	 */
	@BeforeEach
	public void setUp() {
		this.deck = new Deck();
		this.humanPlayer = new HumanPlayer("Human", this.deck);
		this.computerPlayer = new ComputerPlayer("Computer", this.deck);
		this.game = new Game(this.humanPlayer, this.computerPlayer, this.deck);
	}

	/**
	 * Tests that the game starts with players having initial hands drawn.
	 * 
	 * @precondition none
	 * @postcondition players have 5 cards in hand
	 */
	@Test
	public void testStartShouldDrawInitialHands() {
		this.game.start();
		System.out.println("Human hand size after start: " + this.humanPlayer.getHand().size());
		System.out.println("Computer hand size after start: " + this.computerPlayer.getHand().size());
		assertEquals(5, this.humanPlayer.getHand().size());
		assertEquals(5, this.computerPlayer.getHand().size());
	}

	/**
	 * Tests that playing a round updates the round result.
	 * 
	 * @precondition none
	 * @postcondition round result is updated
	 */
	@Test
	public void testPlayRoundShouldUpdateRoundResult() {
		this.game.start();
		this.game.playRound();
		assertNotNull(this.game.getLastRoundResult());
	}

	/**
	 * Tests that the game is over when a player's life points reach zero.
	 * 
	 * @precondition none
	 * @postcondition game is over when life points are zero
	 */
	@Test
	public void testIsGameOverShouldReturnTrueWhenLifePointsAreZero() {
		this.humanPlayer.reduceLifePoints(20);
		assertTrue(this.game.isGameOver());
	}

	/**
	 * Tests that the game returns the correct winner.
	 * 
	 * @precondition none
	 * @postcondition correct winner is returned
	 */
	@Test
	public void testGetWinnerShouldReturnCorrectWinner() {
		this.humanPlayer.reduceLifePoints(20);
		assertEquals(this.computerPlayer, this.game.getWinner());
	}
}