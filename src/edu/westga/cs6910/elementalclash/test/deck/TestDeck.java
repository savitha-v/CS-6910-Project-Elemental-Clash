package edu.westga.cs6910.elementalclash.test.deck;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

public class TestDeck {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        this.deck = new Deck();
    }

    @Test
    public void testDeckShouldHave52CardsInitially() {
        assertEquals(52, this.deck.size());
    }

    @Test
    public void testDrawCardShouldReduceDeckSize() {
        this.deck.drawCard();
        assertEquals(51, this.deck.size());
    }

    @Test
    public void testAddCardShouldIncreaseDeckSize() {
        Card card = new Card(Rank.COMMON_1, Suit.FIRE);
        this.deck.addCard(card);
        assertEquals(53, this.deck.size());
    }

    @Test
    public void testDrawAllCardsShouldEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            this.deck.drawCard();
        }
        assertEquals(0, this.deck.size());
    }

    @Test
    public void testDrawCardFromEmptyDeckShouldThrowException() {
        for (int i = 0; i < 52; i++) {
            this.deck.drawCard();
        }
        assertThrows(IllegalStateException.class, () -> {
            this.deck.drawCard();
        });
    }

    @Test
    public void testAddNullCardShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            this.deck.addCard(null);
        });
    }
}