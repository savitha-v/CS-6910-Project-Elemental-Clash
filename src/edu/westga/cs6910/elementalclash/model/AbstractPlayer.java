package edu.westga.cs6910.elementalclash.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractPlayer provides a base implementation for a player in the Elemental
 * Clash game. It includes common attributes and methods for both human and
 * computer players.
 * 
 * @version 07/14/2024
 * @author Savitha Venkatesh
 */
public abstract class AbstractPlayer implements Play, Serializable {
    private static final long serialVersionUID = 1L;
    
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
     * Plays the best card from the player's hand based on the game state.
     * 
     * @param opponentCard the card played by the opponent, can be null
     * @return the card played by the player
     */
    @Override
    public Card playBestCard(Card opponentCard) {
        Card bestCard = null;
        int bestScore = Integer.MIN_VALUE;

        for (Card card : this.hand) {
            int score = this.evaluateCard(card, opponentCard);
            if (score > bestScore) {
                bestScore = score;
                bestCard = card;
            }
        }

        return bestCard;
    }

    /**
     * Evaluates the score of a card against the opponent's card.
     * 
     * @param card the card to evaluate
     * @param opponentCard the card played by the opponent, can be null
     * @return the score of the card
     */
    private int evaluateCard(Card card, Card opponentCard) {
        int score = 0;

        if (opponentCard == null) {
            return card.getRank().getPower();
        }

        if (this.beats(card.getSuit(), opponentCard.getSuit())) {
            score += card.getRank().getPower();
        } else if (this.beats(opponentCard.getSuit(), card.getSuit())) {
            score -= opponentCard.getRank().getPower();
        }

        return score;
    }

    /**
     * Determines if the first suit beats the second suit based on the game rules.
     * 
     * @param first the first suit
     * @param second the second suit
     * @return true if the first suit beats the second suit, false otherwise
     */
    private boolean beats(Suit first, Suit second) {
        return (first == Suit.FIRE && second == Suit.AIR) 
        		|| (first == Suit.AIR && second == Suit.EARTH) 
        		|| (first == Suit.EARTH && second == Suit.WATER) 
        		|| (first == Suit.WATER && second == Suit.FIRE);
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

    /**
     * Sets the hand of the player.
     * 
     * @param hand the hand to set
     */
    @Override
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    /**
     * Sets the life points of the player.
     * 
     * @param lifePoints the life points to set
     */
    @Override
    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    /**
     * Sets the win count for the player.
     * 
     * @param wins the number of wins to set
     */
    public void setWins(int wins) {
        this.wins = wins;
    }
}