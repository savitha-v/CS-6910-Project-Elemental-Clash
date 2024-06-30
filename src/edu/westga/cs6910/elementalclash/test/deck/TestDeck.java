package edu.westga.cs6910.elementalclash.test.deck;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

/**
 * The TestDeck class.
 * 
 * Tests the functionality of the Deck class in the Elemental Clash game.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class TestDeck {

	private Deck deck;

	/**
	 * Sets up the test fixture.
	 * 
	 * @precondition none
	 * @postcondition deck is initialized
	 */
	@BeforeEach
	public void setUp() {
		this.deck = new Deck();
	}

	/**
	 * Tests that the deck is initialized with 52 cards.
	 * 
	 * @precondition none
	 * @postcondition deck has 52 cards
	 */
	@Test
	public void testDeckShouldHave52CardsInitially() {
		assertEquals(52, this.deck.size());
	}

	/**
	 * Tests that drawing a card decreases the deck size by one.
	 * 
	 * @precondition none
	 * @postcondition deck size is decreased by one
	 */
	@Test
	public void testDrawCardShouldDecreaseDeckSizeByOne() {
		this.deck.drawCard();
		assertEquals(51, this.deck.size());
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
			this.deck.drawCard();
		}
		assertThrows(IllegalStateException.class, () -> {
			this.deck.drawCard();
		});
	}

	/**
	 * Tests that adding a card to the deck increases its size by one.
	 * 
	 * @precondition none
	 * @postcondition deck size is increased by one
	 */
	@Test
	public void testAddCardShouldIncreaseDeckSizeByOne() {
		Card card = new Card(Rank.COMMON_1, Suit.FIRE);
		this.deck.addCard(card);
		assertEquals(53, this.deck.size());
	}

	/**
	 * Tests that adding a null card to the deck throws an exception.
	 * 
	 * @precondition card is null
	 * @postcondition IllegalArgumentException is thrown
	 */
	@Test
	public void testAddNullCardShouldThrowException() {
		assertThrows(IllegalArgumentException.class, () -> {
			this.deck.addCard(null);
		});
	}

	/**
	 * Tests that the deck is correctly shuffled.
	 * 
	 * @precondition none
	 * @postcondition deck is shuffled
	 */
	@Test
	public void testShuffleDeckShouldShuffleTheDeck() {
		Deck newDeck = new Deck();
		newDeck.shuffleDeck();
		assertNotEquals(this.deck.drawCard(), newDeck.drawCard());
	}
}