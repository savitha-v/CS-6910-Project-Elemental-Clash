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
 * @author Savitha Venkatesh
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
		this.viewModel.startGame(); // Ensure the game is started and players have their initial hands
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
	 * @postcondition game is over when life points are zero
	 */
	@Test
	public void testIsGameOverShouldReturnTrueWhenLifePointsAreZero() {
		// Directly reduce human player's life points to 0
		for (int i = 0; i < 20; i++) {
			this.viewModel.playRound();
			this.viewModel.humanLifePointsProperty().set(0); // Set life points to 0
		}
		assertTrue(this.viewModel.isGameOver());
	}

	/**
	 * Tests that the correct winner is returned when the game is over.
	 * 
	 * @precondition none
	 * @postcondition correct winner is returned
	 */
	@Test
	public void testGetWinnerShouldReturnCorrectWinner() {
		// Directly reduce human player's life points to 0
		for (int i = 0; i < 20; i++) {
			this.viewModel.playRound();
			this.viewModel.humanLifePointsProperty().set(0); // Set life points to 0
		}
		assertEquals("Computer", this.viewModel.getWinner());
	}
}