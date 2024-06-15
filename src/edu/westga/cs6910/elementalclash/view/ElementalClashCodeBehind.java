package edu.westga.cs6910.elementalclash.view;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.viewmodel.ViewCard;
import edu.westga.cs6910.elementalclash.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Elemental Clash defines the "controller" for ElementalClash.fxml.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public class ElementalClashCodeBehind {

	private static final Color ELEMENTAL_CLASH_TABLE_BACKGROUND = Color.GREEN.deriveColor(1, 1, 1, 0.8);

	private ViewCard viewCard;
	private ViewModel viewModel;

	@FXML
	private AnchorPane pane;

	@FXML
	private HBox table;

	/**
	 * Instantiates a new elementalclash-code-behind.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public ElementalClashCodeBehind() {
		this.viewModel = new ViewModel();
		this.viewCard = new ViewCard();
	}

	/**
	 * Initializes the GUI components, binding them to the view model properties
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	@FXML
	public void initialize() {
		this.initializeUI();
	}

	private void initializeUI() {
		BackgroundFill fill = new BackgroundFill(ELEMENTAL_CLASH_TABLE_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY);
		Background background = new Background(fill);
		this.pane.setBackground(background);
		this.displayCards();
	}

	private void displayCards() {
		this.table.getChildren().clear();
		Card card = this.viewModel.dealCard();
		this.table.getChildren().add(this.viewCard.faceUp(card));
		this.table.getChildren().add(this.viewCard.faceDown(card));
	}

}
