package edu.westga.cs6910.elementalclash.model;

/**
 * ComputerPlayer represents a computer-controlled player in the Elemental Clash
 * game. It extends the AbstractPlayer class and inherits its functionality.
 * 
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class ComputerPlayer extends AbstractPlayer {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ComputerPlayer with the specified name and deck.
	 * 
	 * @param name the name of the computer player
	 * @param deck the deck of cards for the computer player
	 * @precondition name != null && deck != null
	 * @postcondition getName() == name && getLifePoints() == 20 && getHand().size()
	 *                == 0
	 */
	public ComputerPlayer(String name, Deck deck) {
		super(name, deck);
	}
}