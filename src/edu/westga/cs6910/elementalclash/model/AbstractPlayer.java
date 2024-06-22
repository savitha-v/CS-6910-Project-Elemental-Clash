package edu.westga.cs6910.elementalclash.model;

public abstract class AbstractPlayer implements Play {
    private String name;
    private int lifePoints;
    private Deck deck;

    public AbstractPlayer(String name, Deck deck) {
        this.name = name;
        this.lifePoints = 20;
        this.deck = deck;
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
        return this.deck.drawCard();
    }

    @Override
    public void reduceLifePoints(int points) {
        this.lifePoints -= points;
    }
}