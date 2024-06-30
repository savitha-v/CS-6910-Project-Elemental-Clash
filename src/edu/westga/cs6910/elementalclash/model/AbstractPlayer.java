package edu.westga.cs6910.elementalclash.model;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractPlayer provides a base implementation for a player in the Elemental
 * Clash game. It includes common attributes and methods for both human and
 * computer players.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public abstract class AbstractPlayer implements Play {
    private String name;
    private int lifePoints;
    private Deck deck;
    private List<Card> hand;
    private int wins;

    /**
     * Constructs a new AbstractPlayer with the specified name and deck.
     * 
     * @param name the name of the player
     * @param deck the deck of cards for the player
     * @precondition name != null && deck != null
     * @postcondition getName() == name && getLifePoints() == 20 && getHand().size() == 0
     */
    public AbstractPlayer(String name, Deck deck) {
        this.name = name;
        this.lifePoints = 20;
        this.deck = deck;
        this.hand = new ArrayList<>();
        this.wins = 0;
    }

    /**
     * Gets the name of the player.
     * 
     * @return the name of the player
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the life points of the player.
     * 
     * @return the life points of the player
     */
    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    /**
     * Draws a card from the player's deck and adds it to the player's hand.
     * 
     * @return the drawn card
     */
    @Override
    public Card drawCard() {
        Card card = this.deck.drawCard();
        this.hand.add(card);
        return card;
    }

    /**
     * Reduces the player's life points by the specified amount.
     * 
     * @param points the amount of points to reduce
     * @precondition points > 0
     * @postcondition getLifePoints() == old life points - points
     */
    @Override
    public void reduceLifePoints(int points) {
        this.lifePoints -= points;
    }

    /**
     * Gets the player's hand.
     * 
     * @return the hand of the player
     */
    @Override
    public List<Card> getHand() {
        return this.hand;
    }

    /**
     * Increases the win count for the player.
     */
    public void addWin() {
        this.wins++;
    }

    /**
     * Gets the win count for the player.
     * 
     * @return the number of wins
     */
    public int getWins() {
        return this.wins;
    }
}