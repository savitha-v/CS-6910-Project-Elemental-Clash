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
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class TestDeck {

    private Deck deck;

    /**
     * Sets up the test fixture.
     * 
     * @precondition none
     * @postcondition this.deck is initialized
     */
    @BeforeEach
    public void setUp() {
        this.deck = new Deck();
    }

    /**
     * Tests that the deck initially contains 52 cards.
     * 
     * @precondition none
     * @postcondition none
     */
    @Test
    public void testDeckShouldHave52CardsInitially() {
        assertEquals(52, this.deck.size());
    }

    /**
     * Tests that drawing a card reduces the deck size by one.
     * 
     * @precondition none
     * @postcondition deck size is decreased by one
     */
    @Test
    public void testDrawCardShouldReduceDeckSize() {
        this.deck.drawCard();
        assertEquals(51, this.deck.size());
    }

    /**
     * Tests that adding a card increases the deck size by one.
     * 
     * @precondition none
     * @postcondition deck size is increased by one
     */
    @Test
    public void testAddCardShouldIncreaseDeckSize() {
        Card card = new Card(Rank.COMMON_1, Suit.FIRE);
        this.deck.addCard(card);
        assertEquals(53, this.deck.size());
    }

    /**
     * Tests that drawing all cards empties the deck.
     * 
     * @precondition none
     * @postcondition deck size is zero
     */
    @Test
    public void testDrawAllCardsShouldEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            this.deck.drawCard();
        }
        assertEquals(0, this.deck.size());
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
     * Tests that adding a null card throws an exception.
     * 
     * @precondition none
     * @postcondition IllegalArgumentException is thrown
     */
    @Test
    public void testAddNullCardShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.deck.addCard(null);
        });
    }
}