package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Suit.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public enum Suit {
    FIRE("Fire"),
    EARTH("Earth"),
    AIR("Air"),
    WATER("Water");

    private final String displayName;

    Suit(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
