package edu.westga.cs6910.elementalclash.test.computer_player;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;

/**
 * The TestComputerPlayer class.
 * 
 * Tests the functionality of the ComputerPlayer class in the Elemental Clash game.
 * 
 * @version 07/14/2024
 * @author Savitha Venkatesh
 */
public class TestComputerPlayer {

	private ComputerPlayer computerPlayer;
	private Deck deck;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition this.computerPlayer and this.deck are initialized
	 */
	@BeforeEach
	public void setUp() {
		this.deck = new Deck();
		this.computerPlayer = new ComputerPlayer("Computer", this.deck);
	}

	/**
	 * Tests that the computer player is initialized with 20 life points.
	 * 
	 * @precondition none
	 * @postcondition computerPlayer has 20 life points
	 */
	@Test
	public void testComputerPlayerShouldHave20LifePointsInitially() {
		assertEquals(20, this.computerPlayer.getLifePoints());
	}

	/**
	 * Tests that drawing a card adds it to the computer player's hand.
	 * 
	 * @precondition none
	 * @postcondition computerPlayer's hand size is increased by one
	 */
	@Test
	public void testDrawCardShouldAddCardToHand() {
		this.computerPlayer.drawCard();
		assertEquals(1, this.computerPlayer.getHand().size());
	}

	/**
	 * Tests that reducing life points decreases the computer player's life points.
	 * 
	 * @precondition none
	 * @postcondition computerPlayer's life points are decreased by the specified amount
	 */
	@Test
	public void testReduceLifePointsShouldDecreaseLifePoints() {
		this.computerPlayer.reduceLifePoints(5);
		assertEquals(15, this.computerPlayer.getLifePoints());
	}

	/**
	 * Tests that drawing multiple cards adds them to the computer player's hand.
	 * 
	 * @precondition none
	 * @postcondition computerPlayer's hand size is increased by the number of cards drawn
	 */
	@Test
	public void testDrawMultipleCardsShouldAddCardsToHand() {
		for (int i = 0; i < 5; i++) {
			this.computerPlayer.drawCard();
		}
		assertEquals(5, this.computerPlayer.getHand().size());
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
			this.computerPlayer.drawCard();
		}
		assertThrows(IllegalStateException.class, () -> {
			this.computerPlayer.drawCard();
		});
	}
}