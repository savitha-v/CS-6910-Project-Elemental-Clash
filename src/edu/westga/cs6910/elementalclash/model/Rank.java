package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Rank.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public enum Rank {

	ACE(1);

	private int value;

	/**
	 * Creates the given rank with the specified value.
	 * 
	 * @precondition none
	 * @postcondition getValue() == value
	 * @param value the numeric value of this rank
	 */
	Rank(int value) {
		this.value = value;
	}

	/**
	 * Returns the numeric value of this rank.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the numeric value of this rank
	 */
	public int getValue() {
		return this.value;
	}
}
