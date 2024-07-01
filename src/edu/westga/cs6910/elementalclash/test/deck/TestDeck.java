package edu.westga.cs6910.elementalclash.test.deck;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

import java.util.ArrayList;
import java.util.List;

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
		List<Card> originalCards = new ArrayList<>(this.deck.getCards());
		this.deck.shuffleDeck();
		assertNotEquals(originalCards, this.deck.getCards());
	}

	/**
	 * Tests that setting the deck cards works correctly.
	 * 
	 * @precondition none
	 * @postcondition deck has the specified cards
	 */
	@Test
	public void testSetCardsShouldWorkCorrectly() {
		List<Card> newCards = new ArrayList<>();
		newCards.add(new Card(Rank.RARE_10, Suit.WATER));
		newCards.add(new Card(Rank.LEGENDARY_13, Suit.FIRE));
		this.deck.setCards(newCards);
		assertEquals(2, this.deck.size());
		assertEquals(newCards, this.deck.getCards());
	}

	/**
	 * Tests that getting the deck cards works correctly.
	 * 
	 * @precondition none
	 * @postcondition correct list of cards is returned
	 */
	@Test
	public void testGetCardsShouldReturnCorrectListOfCards() {
		List<Card> cards = this.deck.getCards();
		assertEquals(52, cards.size());
	}

	/**
	 * Tests that the deck can be refilled correctly.
	 * 
	 * @precondition none
	 * @postcondition deck is refilled to 52 cards
	 */
	@Test
	public void testRefillDeckShouldRefillTo52Cards() {
		for (int i = 0; i < 52; i++) {
			this.deck.drawCard();
		}
		assertEquals(0, this.deck.size());
		this.deck.refillDeck();
		assertEquals(52, this.deck.size());
	}

	/**
	 * Tests that the deck is empty check works correctly.
	 * 
	 * @precondition none
	 * @postcondition correctly identifies if the deck is empty
	 */
	@Test
	public void testIsEmptyShouldReturnCorrectValue() {
		assertFalse(this.deck.isEmpty());
		for (int i = 0; i < 52; i++) {
			this.deck.drawCard();
		}
		assertTrue(this.deck.isEmpty());
	}
}