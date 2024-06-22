package edu.westga.cs6910.elementalclash.model;

public class Game {
    private Play humanPlayer;
    private Play computerPlayer;
    private Deck deck;
    private String lastRoundResult;

    public Game(Play human, Play computer, Deck deck) {
        this.humanPlayer = human;
        this.computerPlayer = computer;
        this.deck = deck;
    }

    public void start() {
        this.humanPlayer = new HumanPlayer("Human", this.deck);
        this.computerPlayer = new ComputerPlayer("Computer", this.deck);
        this.drawInitialHand(this.humanPlayer);
        this.drawInitialHand(this.computerPlayer);
    }

    private void drawInitialHand(Play player) {
        for (int cardQuantity = 0; cardQuantity < 5; cardQuantity++) {
            player.drawCard();
        }
    }

    public void playRound() {
        Card humanCard = this.humanPlayer.drawCard();
        Card computerCard = this.computerPlayer.drawCard();

        int result = this.determineWinner(humanCard, computerCard);

        if (result > 0) {
            this.computerPlayer.reduceLifePoints(result);
            this.lastRoundResult = "Human wins the round! Computer loses " + result + " life points.";
        } else if (result < 0) {
            this.humanPlayer.reduceLifePoints(-result);
            this.lastRoundResult = "Computer wins the round! Human loses " + (-result) + " life points.";
        } else {
            this.lastRoundResult = "It's a tie! No life points lost.";
        }
    }

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

    private boolean beats(Suit first, Suit second) {
        return (first == Suit.FIRE && second == Suit.AIR) 
        		|| (first == Suit.AIR && second == Suit.EARTH) 
        		|| (first == Suit.EARTH && second == Suit.WATER) 
        		|| (first == Suit.WATER && second == Suit.FIRE);
    }

    private int getPowerLevel(Rank rank) {
        String[] parts = rank.toString().split(" ");
        return Integer.parseInt(parts[1]);
    }

    public boolean isGameOver() {
        return this.humanPlayer.getLifePoints() <= 0 || this.computerPlayer.getLifePoints() <= 0;
    }

    public Play getWinner() {
        if (this.humanPlayer.getLifePoints() <= 0) {
            return this.computerPlayer;
        } else if (this.computerPlayer.getLifePoints() <= 0) {
            return this.humanPlayer;
        }
        return null;
    }

    public String getLastRoundResult() {
        return this.lastRoundResult;
    }

    public int getHumanLifePoints() {
        return this.humanPlayer.getLifePoints();
    }

    public int getComputerLifePoints() {
        return this.computerPlayer.getLifePoints();
    }
    
    public Play getHumanPlayer() {
        return this.humanPlayer;
    }

    public Play getComputerPlayer() {
        return this.computerPlayer;
    }
}