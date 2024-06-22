package edu.westga.cs6910.elementalclash.test.human_player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;

/**
 * The TestHumanPlayer class.
 * 
 * Tests the functionality of the HumanPlayer class in the Elemental Clash game.
 * 
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class TestHumanPlayer {

	private HumanPlayer humanPlayer;
	private Deck deck;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition this.humanPlayer and this.deck are initialized
	 */
	@BeforeEach
	public void setUp() {
		this.deck = new Deck();
		this.humanPlayer = new HumanPlayer("Human", this.deck);
	}

	/**
	 * Tests that the human player is initialized with 20 life points.
	 * 
	 * @precondition none
	 * @postcondition humanPlayer has 20 life points
	 */
	@Test
	public void testHumanPlayerShouldHave20LifePointsInitially() {
		assertEquals(20, this.humanPlayer.getLifePoints());
	}

	/**
	 * Tests that drawing a card adds it to the human player's hand.
	 * 
	 * @precondition none
	 * @postcondition humanPlayer's hand size is increased by one
	 */
	@Test
	public void testDrawCardShouldAddCardToHand() {
		this.humanPlayer.drawCard();
		assertEquals(1, this.humanPlayer.getHand().size());
	}

	/**
	 * Tests that reducing life points decreases the human player's life points.
	 * 
	 * @precondition none
	 * @postcondition humanPlayer's life points are decreased by the specified amount
	 */
	@Test
	public void testReduceLifePointsShouldDecreaseLifePoints() {
		this.humanPlayer.reduceLifePoints(5);
		assertEquals(15, this.humanPlayer.getLifePoints());
	}

	/**
	 * Tests that drawing multiple cards adds them to the human player's hand.
	 * 
	 * @precondition none
	 * @postcondition humanPlayer's hand size is increased by the number of cards drawn
	 */
	@Test
	public void testDrawMultipleCardsShouldAddCardsToHand() {
		for (int i = 0; i < 5; i++) {
			this.humanPlayer.drawCard();
		}
		assertEquals(5, this.humanPlayer.getHand().size());
	}

	/**
	 * Tests that drawing a card from an empty deck throws an exception.
	 * 
	 * @precondition deck is empty
	 * @postcondition IllegalStateException is thrown
	 */
	@Test
	public void testDrawCardFromEmptyDeckShouldThrowException() {
		for (int i = 0; i < 52; i++) {
			this.humanPlayer.drawCard();
		}
		assertThrows(IllegalStateException.class, () -> {
			this.humanPlayer.drawCard();
		});
	}
}