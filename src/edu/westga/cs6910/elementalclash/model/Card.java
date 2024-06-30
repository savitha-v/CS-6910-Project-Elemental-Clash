package edu.westga.cs6910.elementalclash.model;

import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;
import java.io.Serializable;

/**
 * The Card class.
 * 
 * Represents a card in the Elemental Clash game with a specific rank and suit.
 * Each card has a rank (common, rare, legendary) and a suit (fire, earth, air,
 * water).
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Rank rank;
    private final Suit suit;

	/**
	 * Creates a card with the specified rank and suit.
	 * 
	 * @precondition rank != null && suit != null
	 * @postcondition getRank() == rank && getSuit() == suit
	 * 
	 * @param rank the rank of this card
	 * @param suit the suit of this card
	 */
	public Card(Rank rank, Suit suit) {
		if (rank == null) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_RANK);
		}
		if (suit == null) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_SUIT);
		}
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Returns the rank of this card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the rank
	 */
	public Rank getRank() {
		return this.rank;
	}

	/**
	 * Returns the suit of this card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the suit
	 */
	public Suit getSuit() {
		return this.suit;
	}

	/**
	 * Returns a string representation of the card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the string representation of the card
	 */
	@Override
	public String toString() {
		return this.rank + " of " + this.suit;
	}
}