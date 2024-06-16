package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Suit.
 * 
 * Represents the suit of a card in the Elemental Clash game.
 * 
 * @version 06/16/2024
 * @author Savitha Venkatesh
 */
public enum Suit {
    FIRE("Fire"),
    EARTH("Earth"),
    AIR("Air"),
    WATER("Water");

    private final String displayName;

    /**
     * Constructs a new Suit with the specified display name.
     * 
     * @precondition displayName != null
     * @postcondition this.displayName == displayName
     * 
     * @param displayName the display name of the suit
     */
    Suit(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the suit.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the display name of the suit
     */
    @Override
    public String toString() {
        return this.displayName;
    }
}