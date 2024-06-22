package edu.westga.cs6910.elementalclash.model;

import java.util.List;

public interface Play {
    String getName();
    int getLifePoints();
    Card drawCard();
    void reduceLifePoints(int points);
    List<Card> getHand();
}