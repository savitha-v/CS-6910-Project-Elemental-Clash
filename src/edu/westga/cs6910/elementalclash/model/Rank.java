package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Rank.
 * 
 * Represents the rank of a card in the Elemental Clash game.
 * 
 * @version 06/16/2024
 * @author Savitha Venkatesh
 */
public enum Rank {
    COMMON_1("Common 1"),
    COMMON_2("Common 2"),
    COMMON_3("Common 3"),
    COMMON_4("Common 4"),
    COMMON_5("Common 5"),
    COMMON_6("Common 6"),
    COMMON_7("Common 7"),

    RARE_8("Rare 8"),
    RARE_9("Rare 9"),
    RARE_10("Rare 10"),
    RARE_11("Rare 11"),

    LEGENDARY_12("Legendary 12"),
    LEGENDARY_13("Legendary 13");

    private final String displayName;

    /**
     * Constructs a new Rank with the specified display name.
     * 
     * @precondition displayName != null
     * @postcondition this.displayName == displayName
     * 
     * @param displayName the display name of the rank
     */
    Rank(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the display name of the rank.
     * 
     * @precondition none
     * @postcondition none
     * 
     * @return the display name of the rank
     */
    @Override
    public String toString() {
        return this.displayName;
    }
}