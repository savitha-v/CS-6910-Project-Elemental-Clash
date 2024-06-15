package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

/**
 * The Class ViewModel.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public class ViewModel {

    /**
     * Deals a specific card.
     * 
     * @precondition none
     * @postcondition none
     * @return the dealt card
     */
    public Card dealCard() {
        return new Card(Rank.COMMON_1, Suit.FIRE);
    }
}
