package edu.westga.cs6910.elementalclash.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;

/**
 * The Deck class.
 * 
 * Represents a deck of cards in the Elemental Clash game.
 * 
 * @version 06/16/2024
 * @author Savitha Venkatesh
 */
public class Deck {

    private List<Card> cards;

    /**
     * Creates a new deck of 52 cards.
     * 
     * @precondition none
     * @postcondition this.cards.size() == 52
     */
    public Deck() {
        this.cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                this.cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(this.cards);
    }

    /**
     * Draws a card from the deck.
     * 
     * @precondition none
     * @postcondition deck size is decreased by one
     * 
     * @return the drawn card
     * @throws IllegalStateException if the deck is empty
     */
    public Card drawCard() {
        if (this.cards.isEmpty()) {
            throw new IllegalStateException(ExceptionMessages.NULL_CARD);
        }
        return this.cards.remove(this.cards.size() - 1);
    }

    /**
     * Returns the number of cards remaining in the deck.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the number of cards
     */
    public int size() {
        return this.cards.size();
    }

    /**
     * Adds a card back to the deck.
     * 
     * @precondition card != null
     * @postcondition deck size is increased by one
     * 
     * @param card the card to add
     * @throws IllegalArgumentException if card is null
     */
    public void addCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_CARD);
        }
        this.cards.add(card);
    }
}