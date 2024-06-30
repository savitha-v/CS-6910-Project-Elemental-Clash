package edu.westga.cs6910.elementalclash.test.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;
import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;

/**
 * The TestCard class.
 * 
 * Tests the functionality of the Card class in the Elemental Clash game.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class TestCard {

	private Card card;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition card is initialized
	 */
	@BeforeEach
	public void setUp() {
		this.card = new Card(Rank.COMMON_1, Suit.FIRE);
	}

	/**
	 * Tests that a card is created with the correct rank.
	 * 
	 * @precondition none
	 * @postcondition card has the correct rank
	 */
	@Test
	public void testCardShouldHaveCorrectRank() {
		assertEquals(Rank.COMMON_1, this.card.getRank());
	}

	/**
	 * Tests that a card is created with the correct suit.
	 * 
	 * @precondition none
	 * @postcondition card has the correct suit
	 */
	@Test
	public void testCardShouldHaveCorrectSuit() {
		assertEquals(Suit.FIRE, this.card.getSuit());
	}

	/**
	 * Tests that a card throws an exception if rank is null.
	 * 
	 * @precondition none
	 * @postcondition IllegalArgumentException is thrown
	 */
	@Test
	public void testCardShouldThrowExceptionIfRankIsNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card(null, Suit.FIRE);
		});
		assertEquals(ExceptionMessages.INVALID_RANK, exception.getMessage());
	}

	/**
	 * Tests that a card throws an exception if suit is null.
	 * 
	 * @precondition none
	 * @postcondition IllegalArgumentException is thrown
	 */
	@Test
	public void testCardShouldThrowExceptionIfSuitIsNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Card(Rank.COMMON_1, null);
		});
		assertEquals(ExceptionMessages.INVALID_SUIT, exception.getMessage());
	}

	/**
	 * Tests the toString method of the Card class.
	 * 
	 * @precondition none
	 * @postcondition toString returns the correct string
	 */
	@Test
	public void testCardToStringShouldReturnCorrectString() {
		assertEquals("Common 1 of Fire", this.card.toString());
	}
}