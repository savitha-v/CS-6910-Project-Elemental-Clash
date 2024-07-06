package edu.westga.cs6910.elementalclash.model;

import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Game represents a game of Elemental Clash between a human player and a
 * computer player. It manages the game state, including players, deck, and the
 * result of each round.
 * 
 * @version 06/30/2024
 * @author Savitha Venkatesh
 */
public class Game implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Play humanPlayer;
    private Play computerPlayer;
    private Deck deck;
    private String lastRoundResult;

    private List<Card> previousHumanHand;
    private List<Card> previousComputerHand;
    private List<Card> previousDeck;
    private int previousHumanLifePoints;
    private int previousComputerLifePoints;
    private int previousHumanWins;
    private int previousComputerWins;

    /**
     * Constructs a new Game with the specified human and computer players and deck.
     * 
     * @param human the human player
     * @param computer the computer player
     * @param deck the deck of cards
     * @precondition human != null && computer != null && deck != null
     * @postcondition this.humanPlayer == human && this.computerPlayer == computer
     *                && this.deck == deck
     */
    public Game(Play human, Play computer, Deck deck) {
        if (human == null || computer == null || deck == null) {
            throw new IllegalArgumentException("Players and deck cannot be null.");
        }
        this.humanPlayer = human;
        this.computerPlayer = computer;
        this.deck = deck;
    }

    /**
     * Starts the game by initializing the players and drawing their initial hands.
     * 
     * @precondition none
     * @postcondition players have initial hands drawn
     */
    public void start() {
        this.humanPlayer = new HumanPlayer("Human", this.deck);
        this.computerPlayer = new ComputerPlayer("Computer", this.deck);
        this.drawInitialHand(this.humanPlayer);
        this.drawInitialHand(this.computerPlayer);
    }

    /**
     * Draws the initial hand of cards for the specified player.
     * 
     * @param player the player to draw the initial hand for
     * @precondition player != null
     * @postcondition player has 5 cards in hand
     */
    private void drawInitialHand(Play player) {
        for (int cardQuantity = 0; cardQuantity < 5; cardQuantity++) {
            player.drawCard();
        }
    }

    /**
     * Saves the current state of the game before playing a round.
     * 
     * @precondition none
     * @postcondition previous state is saved
     */
    private void saveState() {
        this.previousHumanHand = new ArrayList<>(this.humanPlayer.getHand());
        this.previousComputerHand = new ArrayList<>(this.computerPlayer.getHand());
        this.previousDeck = new ArrayList<>(this.deck.getCards());
        this.previousHumanLifePoints = this.humanPlayer.getLifePoints();
        this.previousComputerLifePoints = this.computerPlayer.getLifePoints();
        this.previousHumanWins = ((AbstractPlayer) this.humanPlayer).getWins();
        this.previousComputerWins = ((AbstractPlayer) this.computerPlayer).getWins();
    }

    /**
     * Plays a round of the game where both players draw a card and a winner is
     * determined.
     * 
     * @precondition none
     * @postcondition the round result is determined and life points are adjusted
     *                accordingly
     */
    public void playRound() {
        if (this.isGameOver()) {
            return;
        }

        this.saveState();

        Card humanCard = this.humanPlayer.drawCard();  // Draw card for human player
        Card computerCard = this.computerPlayer.drawCard();  // Draw card for computer player

        int result = this.determineWinner(humanCard, computerCard);

        if (result > 0) {
            this.computerPlayer.reduceLifePoints(result);
            this.lastRoundResult = "Human wins the round! Computer loses " + result + " life points.";
            ((AbstractPlayer) this.humanPlayer).addWin();
        } else if (result < 0) {
            this.humanPlayer.reduceLifePoints(-result);
            this.lastRoundResult = "Computer wins the round! Human loses " + (-result) + " life points.";
            ((AbstractPlayer) this.computerPlayer).addWin();
        } else {
            this.lastRoundResult = "It's a tie! No life points lost.";
        }

        // Add the played cards back to the deck
        this.deck.addCard(humanCard);
        this.deck.addCard(computerCard);
        this.deck.shuffleDeck();

        if (this.isGameOver()) {
            this.lastRoundResult += " Game over!";
        }
    }

    /**
     * Restores the game state to the state saved before the last round was played.
     * 
     * @precondition none
     * @postcondition game state is restored to before the last round
     */
    public void restartRound() {
        this.humanPlayer.setHand(new ArrayList<>(this.previousHumanHand));
        this.computerPlayer.setHand(new ArrayList<>(this.previousComputerHand));
        this.deck.setCards(new ArrayList<>(this.previousDeck));
        this.humanPlayer.setLifePoints(this.previousHumanLifePoints);
        this.computerPlayer.setLifePoints(this.previousComputerLifePoints);
        ((AbstractPlayer) this.humanPlayer).setWins(this.previousHumanWins);
        ((AbstractPlayer) this.computerPlayer).setWins(this.previousComputerWins);
        this.deck.shuffleDeck(); // Ensure the deck is shuffled to get a different card next time
        this.lastRoundResult = null;
    }

    /**
     * Determines the winner of a round based on the cards drawn by each player.
     * 
     * @param humanCard the card drawn by the human player
     * @param computerCard the card drawn by the computer player
     * @return the result of the round: positive if human wins, negative if computer
     *         wins, 0 if tie
     * @precondition humanCard != null && computerCard != null
     * @postcondition none
     */
    private int determineWinner(Card humanCard, Card computerCard) {
        Suit humanElement = humanCard.getSuit();
        Suit computerElement = computerCard.getSuit();
        int humanPower = this.getPowerLevel(humanCard.getRank());
        int computerPower = this.getPowerLevel(computerCard.getRank());

        if (this.beats(humanElement, computerElement)) {
            return humanPower;
        } else if (this.beats(computerElement, humanElement)) {
            return -computerPower;
        } else {
            return 0;
        }
    }

    /**
     * Determines if the first suit beats the second suit based on the game rules.
     * 
     * @param first the first suit
     * @param second the second suit
     * @return true if the first suit beats the second suit, false otherwise
     */
    private boolean beats(Suit first, Suit second) {
        return (first == Suit.FIRE && second == Suit.AIR) || 
               (first == Suit.AIR && second == Suit.EARTH) ||
               (first == Suit.EARTH && second == Suit.WATER) || 
               (first == Suit.WATER && second == Suit.FIRE);
    }

    /**
     * Gets the power level of the specified rank.
     * 
     * @param rank the rank of the card
     * @return the power level of the rank
     */
    private int getPowerLevel(Rank rank) {
        return rank.getPower();
    }

    /**
     * Checks if the game is over, which occurs if either player's life points reach
     * zero.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return this.humanPlayer.getLifePoints() <= 0 || this.computerPlayer.getLifePoints() <= 0;
    }

    /**
     * Gets the winner of the game.
     * 
     * @return the player who won the game, or null if there is no winner yet
     */
    public Play getWinner() {
        if (this.humanPlayer.getLifePoints() <= 0) {
            return this.computerPlayer;
        } else if (this.computerPlayer.getLifePoints() <= 0) {
            return this.humanPlayer;
        }
        return null;
    }

    /**
     * Gets the result of the last round played.
     * 
     * @return the result of the last round
     */
    public String getLastRoundResult() {
        return this.lastRoundResult;
    }

    /**
     * Gets the life points of the human player.
     * 
     * @return the life points of the human player
     */
    public int getHumanLifePoints() {
        return this.humanPlayer.getLifePoints();
    }

    /**
     * Gets the life points of the computer player.
     * 
     * @return the life points of the computer player
     */
    public int getComputerLifePoints() {
        return this.computerPlayer.getLifePoints();
    }

    /**
     * Gets the human player.
     * 
     * @return the human player
     */
    public Play getHumanPlayer() {
        return this.humanPlayer;
    }

    /**
     * Gets the computer player.
     * 
     * @return the computer player
     */
    public Play getComputerPlayer() {
        return this.computerPlayer;
    }

    /**
     * Gets the deck.
     * 
     * @return the deck
     */
    public Deck getDeck() {
        return this.deck;
    }
}