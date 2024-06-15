package edu.westga.cs6910.elementalclash.model;

/**
 * The enum Rank.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public enum Rank {
    // Common ranks
    COMMON_1("Common 1"),
    COMMON_2("Common 2"),
    COMMON_3("Common 3"),
    COMMON_4("Common 4"),
    COMMON_5("Common 5"),
    COMMON_6("Common 6"),
    COMMON_7("Common 7"),

    // Rare ranks
    RARE_8("Rare 8"),
    RARE_9("Rare 9"),
    RARE_10("Rare 10"),
    RARE_11("Rare 11"),

    // Legendary ranks
    LEGENDARY_12("Legendary 12"),
    LEGENDARY_13("Legendary 13");

    private final String displayName;

    Rank(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
