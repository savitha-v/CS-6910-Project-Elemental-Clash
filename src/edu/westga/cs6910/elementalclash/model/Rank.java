package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Rank.
 * 
 * Represents the rank of a card in the Elemental Clash game.
 * 
 * @version 07/07/2024
 * @author Savitha Venkatesh
 */
public enum Rank {
    COMMON_1("Common 1", 1), COMMON_2("Common 2", 2), COMMON_3("Common 3", 3), COMMON_4("Common 4", 4), COMMON_5("Common 5", 5),
    COMMON_6("Common 6", 6), COMMON_7("Common 7", 7),
    RARE_8("Rare 8", 8), RARE_9("Rare 9", 9), RARE_10("Rare 10", 10), RARE_11("Rare 11", 11),
    LEGENDARY_12("Legendary 12", 12), LEGENDARY_13("Legendary 13", 13);

    private final String displayName;
    private final int power;

    /**
     * Constructs a new Rank with the specified display name and power.
     * 
     * @param displayName the display name of the rank
     * @param power the power of the rank
     * @precondition displayName != null
     * @postcondition this.displayName == displayName
     */
    Rank(String displayName, int power) {
        this.displayName = displayName;
        this.power = power;
    }

    /**
     * Returns the display name of the rank.
     * 
     * @return the display name of the rank
     */
    @Override
    public String toString() {
        return this.displayName;
    }

    /**
     * Returns the power of the rank.
     * 
     * @return the power of the rank
     */
    public int getPower() {
        return this.power;
    }
}