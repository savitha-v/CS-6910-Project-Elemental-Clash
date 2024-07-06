package edu.westga.cs6910.elementalclash.model;

import java.util.List;

/**
 * Play defines the behaviors that a player in the Elemental Clash game must
 * implement.
 * 
 * @version 07/07/2024
 * @author Savitha Venkatesh
 */
public interface Play {

    String getName();

    int getLifePoints();

    Card drawCard();

    void reduceLifePoints(int points);

    List<Card> getHand();

    void setHand(List<Card> hand);

    void setLifePoints(int lifePoints);

    Card playBestCard(Card opponentCard);
}