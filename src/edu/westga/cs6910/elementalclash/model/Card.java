package edu.westga.cs6910.elementalclash.model;

import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;

/**
 * The Card class.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public class Card {

	private Rank rank;
	private Suit suit;

	/**
	 * Creates a playing card with the specified rank and suit.
	 * 
	 * @precondition valid rank and suit
	 * @postcondition getValue() == value && getSuit() == suit
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
	 * @return the rank
	 */
	public int getRank() {
		return this.rank.getValue();
	}

	/**
	 * Returns the suit of this card.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the suit
	 */
	public String getSuit() {
		return this.suit.toString();
	}
}
