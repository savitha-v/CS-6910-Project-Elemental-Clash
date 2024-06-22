package edu.westga.cs6910.elementalclash.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Play {
    private String name;
    private int lifePoints;
    private Deck deck;
    private List<Card> hand;

    public AbstractPlayer(String name, Deck deck) {
        this.name = name;
        this.lifePoints = 20;
        this.deck = deck;
        this.hand = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public Card drawCard() {
        Card card = this.deck.drawCard();
        this.hand.add(card);
        return card;
    }

    @Override
    public void reduceLifePoints(int points) {
        this.lifePoints -= points;
    }

    @Override
    public List<Card> getHand() {
        return this.hand;
    }
}