package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

/**
 * The Class CasinoViewModel.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public class ViewModel {

	/**
	 * Deals the Ace of Spades.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the Ace of Spades
	 */
	// TODO
	public Card dealCard() {
		return new Card(Rank.ACE, Suit.SPADES);
	}
}
