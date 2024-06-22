package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Game;
import edu.westga.cs6910.elementalclash.model.HumanPlayer;
import edu.westga.cs6910.elementalclash.model.ComputerPlayer;
import edu.westga.cs6910.elementalclash.model.Deck;
import edu.westga.cs6910.elementalclash.model.Play;
import edu.westga.cs6910.elementalclash.model.Card;
import java.util.List;

public class ViewModel {
    private Game game;
    
    public ViewModel() {
        Deck deck = new Deck();
        this.game = new Game(new HumanPlayer("Human", deck), new ComputerPlayer("Computer", deck), deck);
    }

    public void startGame() {
        this.game.start();
    }

    public void playRound() {
        this.game.playRound();
    }

    public boolean isGameOver() {
        return this.game.isGameOver();
    }

    public String getWinner() {
        Play winner = this.game.getWinner();
        if (winner != null) {
            return winner.getName();
        } else {
            return "No winner yet";
        }
    }

    public List<Card> getHumanHand() {
        return this.game.getHumanPlayer().getHand();
    }

    public List<Card> getComputerHand() {
        return this.game.getComputerPlayer().getHand();
    }
    
    public String getLastRoundResult() {
        return this.game.getLastRoundResult();
    }
}