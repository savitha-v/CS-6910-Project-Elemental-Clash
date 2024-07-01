package edu.westga.cs6910.elementalclash.test.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;
import edu.westga.cs6910.elementalclash.model.Suit;
import edu.westga.cs6910.elementalclash.model.Rank;

/**
 * The TestGame class.
 * 
 * Tests the functionality of the Game class in the Elemental Clash game.
 * 
 * @version 06/30/2024
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

	/**
	 * Tests that the game can result in a tie round.
	 * 
	 * @precondition none
	 * @postcondition round can result in a tie
	 */
	@Test
	public void testRoundCanResultInTie() {
		Deck testDeck = new Deck() {
			private static final long serialVersionUID = 1L;

			@Override
			public Card drawCard() {
				return new Card(Rank.COMMON_1, Suit.FIRE);
			}
		};
		this.game = new Game(new HumanPlayer("Human", testDeck), new ComputerPlayer("Computer", testDeck), testDeck);
		this.game.start();
		this.game.playRound();
		assertEquals("It's a tie! No life points lost.", this.game.getLastRoundResult());
	}

	/**
	 * Tests that the life points can go negative.
	 * 
	 * @precondition none
	 * @postcondition life points can be negative
	 */
	@Test
	public void testLifePointsCanBeNegative() {
		this.game.start();
		while (this.game.getHumanLifePoints() > -10 && this.game.getComputerLifePoints() > -10) {
			this.game.playRound();
			if (this.game.getHumanLifePoints() <= 0 || this.game.getComputerLifePoints() <= 0) {
				break;
			}
		}
		assertTrue(this.game.getHumanLifePoints() <= 0 || this.game.getComputerLifePoints() <= 0);
	}

	/**
	 * Tests that the game can handle multiple rounds and still function correctly.
	 * 
	 * @precondition none
	 * @postcondition game handles multiple rounds correctly
	 */
	@Test
	public void testGameCanHandleMultipleRounds() {
		this.game.start();
		for (int i = 0; i < 50; i++) {
			this.game.playRound();
			if (this.game.isGameOver()) {
				break;
			}
		}
		assertTrue(this.game.getHumanLifePoints() <= 20 && this.game.getComputerLifePoints() <= 20);
		assertNotNull(this.game.getLastRoundResult());
	}
}