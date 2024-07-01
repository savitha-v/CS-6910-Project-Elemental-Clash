package edu.westga.cs6910.elementalclash.test.view_model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs6910.elementalclash.viewmodel.ViewModel;

/**
 * The TestViewModel class.
 * 
 * Tests the functionality of the ViewModel class in the Elemental Clash game.
 * 
 * @version 06/30/2024
 * author Savitha Venkatesh
 */
public class TestViewModel {

    private ViewModel viewModel;

    /**
     * Sets up the test fixture.
     * 
     * @precondition none
     * @postcondition viewModel is initialized
     */
    @BeforeEach
    public void setUp() {
        this.viewModel = new ViewModel();
        this.viewModel.startGame();
    }

    /**
     * Tests that the ViewModel initializes the game correctly.
     * 
     * @precondition none
     * @postcondition game is initialized with correct initial values
     */
    @Test
    public void testViewModelShouldInitializeGameCorrectly() {
        assertNotNull(this.viewModel.getHumanHand());
        assertNotNull(this.viewModel.getComputerHand());
        assertEquals(5, this.viewModel.getHumanHand().size());
        assertEquals(5, this.viewModel.getComputerHand().size());
    }

    /**
     * Tests that playing a round updates the round result.
     * 
     * @precondition none
     * @postcondition round result is updated
     */
    @Test
    public void testPlayRoundShouldUpdateRoundResult() {
        this.viewModel.playRound();
        assertNotNull(this.viewModel.roundResultProperty().get());
    }

    /**
     * Tests that the game is over when a player's life points reach zero.
     * 
     * @precondition none
     * @postcondition game is over when life points are zero or below
     */
    @Test
    public void testIsGameOverShouldReturnTrueWhenLifePointsAreZeroOrBelow() {
        while (this.viewModel.humanLifePointsProperty().get() > 0 && this.viewModel.computerLifePointsProperty().get() > 0) {
            this.viewModel.playRound();
        }
        assertTrue(this.viewModel.isGameOver());
    }

    /**
     * Tests that the winner is correctly determined when the game is over.
     * 
     * @precondition none
     * @postcondition the winner is correctly determined
     */
    @Test
    public void testGetWinnerShouldReturnCorrectWinnerWhenGameIsOver() {
        while (this.viewModel.humanLifePointsProperty().get() > 0 && this.viewModel.computerLifePointsProperty().get() > 0) {
            this.viewModel.playRound();
        }
        assertNotNull(this.viewModel.getWinner());
    }

    /**
     * Tests that playing a round after restarting works correctly.
     * 
     * @precondition none
     * @postcondition game functions correctly after restarting a round
     */
    @Test
    public void testPlayRoundAfterRestartShouldWorkCorrectly() {
        this.viewModel.playRound();
        this.viewModel.restartRound();
        this.viewModel.playRound();

        assertNotNull(this.viewModel.roundResultProperty().get());
        assertTrue(this.viewModel.humanLifePointsProperty().get() <= 20);
        assertTrue(this.viewModel.computerLifePointsProperty().get() <= 20);
    }

    /**
     * Tests that saving the game state works correctly.
     * 
     * @precondition none
     * @postcondition game state is saved to a file
     */
    @Test
    public void testSaveGameShouldSaveGameState() {
        this.viewModel.saveGame();
        // Manually check that game_state.dat file is created and contains serialized data
    }

    /**
     * Tests that loading the game state works correctly.
     * 
     * @precondition none
     * @postcondition game state is loaded from a file
     */
    @Test
    public void testLoadGameShouldLoadGameState() {
        this.viewModel.saveGame(); // Ensure there is a saved state to load
        this.viewModel.loadGame();

        assertNotNull(this.viewModel.getHumanHand());
        assertNotNull(this.viewModel.getComputerHand());
        assertEquals(5, this.viewModel.getHumanHand().size());
        assertEquals(5, this.viewModel.getComputerHand().size());
        assertNotNull(this.viewModel.roundResultProperty().get());
        assertTrue(this.viewModel.humanLifePointsProperty().get() <= 20);
        assertTrue(this.viewModel.computerLifePointsProperty().get() <= 20);
    }

    /**
     * Tests that life points can go negative.
     * 
     * @precondition none
     * @postcondition life points can be negative
     */
    @Test
    public void testLifePointsCanGoNegative() {
        this.viewModel.startGame();
        while (this.viewModel.humanLifePointsProperty().get() > -10 && this.viewModel.computerLifePointsProperty().get() > -10) {
            this.viewModel.playRound();
            if (this.viewModel.humanLifePointsProperty().get() <= 0 || this.viewModel.computerLifePointsProperty().get() <= 0) {
                break;
            }
        }
        assertTrue(this.viewModel.humanLifePointsProperty().get() <= 0 || this.viewModel.computerLifePointsProperty().get() <= 0);
    }

    /**
     * Tests that the game can handle multiple rounds and still function correctly.
     * 
     * @precondition none
     * @postcondition game handles multiple rounds correctly
     */
    @Test
    public void testGameCanHandleMultipleRounds() {
        this.viewModel.startGame();
        for (int i = 0; i < 50; i++) {
            this.viewModel.playRound();
            if (this.viewModel.isGameOver()) {
                break;
            }
        }
        assertTrue(this.viewModel.humanLifePointsProperty().get() <= 20 && this.viewModel.computerLifePointsProperty().get() <= 20);
        assertNotNull(this.viewModel.roundResultProperty().get());
    }
}