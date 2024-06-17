package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.model.Rank;
import edu.westga.cs6910.elementalclash.model.Suit;

/**
 * The Class ViewModel.
 * 
 * @version 06/16/2024
 * @author Savitha Venkatesh
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
        //return new Card(Rank.COMMON_1, Suit.AIR);
    	//return new Card(Rank.RARE_9, Suit.AIR);
    	//return new Card(Rank.LEGENDARY_13, Suit.AIR);
    	
        //return new Card(Rank.COMMON_1, Suit.FIRE);
    	//return new Card(Rank.RARE_9, Suit.FIRE);
    	//return new Card(Rank.LEGENDARY_13, Suit.FIRE);
    	
        //return new Card(Rank.COMMON_1, Suit.WATER);
    	//return new Card(Rank.RARE_9, Suit.WATER);
    	//return new Card(Rank.LEGENDARY_13, Suit.WATER);
    	
        return new Card(Rank.COMMON_1, Suit.EARTH);
    	//return new Card(Rank.RARE_9, Suit.EARTH);
    	//return new Card(Rank.LEGENDARY_13, Suit.EARTH);
    }
}
