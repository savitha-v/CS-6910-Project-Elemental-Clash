package edu.westga.cs6910.elementalclash.viewmodel;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The class ViewCard.
 * 
 * @version 06/23/2024
 * @author Savitha Venkatesh
 */
public class ViewCard {

    public static final double CARD_WIDTH = 110;
    public static final double CARD_HEIGHT = 170;
    public static final String CARD_BACK_IMAGE = "images/wolfie.jpg"; 
    public static final double CARD_ARC = 20;

    private Card card;

    /**
     * Returns a card face up.
     * 
     * @precondition card != null
     * @postcondition none
     * @param card the card to create a visual representation for
     * @return the card face up
     */
    public Node faceUp(Card card) {
        if (card == null) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_CARD);
        }
        this.card = card;
        return this.designFrontOfCard();
    }

    /**
     * Returns a card face down.
     * 
     * @precondition card != null
     * @postcondition none
     * @param card the card to create a visual representation for
     * @return the card face down
     */
    public Node faceDown(Card card) {
        if (card == null) {
            throw new IllegalArgumentException(ExceptionMessages.NULL_CARD);
        }
        this.card = card;
        return this.designBackOfCard();
    }

    /**
     * Designs the back of the card.
     * 
     * @precondition none
     * @postcondition none
     * @return the back of the card
     */
    private Node designBackOfCard() {
        Pane pane = new Pane();
        Shape shape = this.drawRectangle();

        FileInputStream input = null;
        try {
            input = new FileInputStream(CARD_BACK_IMAGE);
            Image image = new Image(input);
            ImagePattern pattern = new ImagePattern(image);
            shape.setFill(pattern);
            pane.getChildren().add(shape);
        } catch (FileNotFoundException ex) {
            System.err.println("Image file not found: " + CARD_BACK_IMAGE);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.err.println("Error closing file input stream.");
                }
            }
        }

        return pane;
    }

    /**
     * Designs the front of the card.
     * 
     * @precondition none
     * @postcondition none
     * @return the front of the card
     */
    private Node designFrontOfCard() {
        Pane pane = new Pane();
        Shape shape = this.drawRectangle();

        FileInputStream input = null;
        try {
            String imagePath = "images/" + this.card.getSuit().toString().toLowerCase() + "_"
                + this.card.getRank().toString().toLowerCase().replace(" ", "_") + ".jpg";
            System.out.println("Loading image: " + imagePath);
            input = new FileInputStream(imagePath);
            Image image = new Image(input);
            ImagePattern pattern = new ImagePattern(image);
            shape.setFill(pattern);
            pane.getChildren().add(shape);
        } catch (FileNotFoundException ex) {
            System.err.println("Image file not found: " + ex.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    System.err.println("Error closing file input stream.");
                }
            }
        }

        return pane;
    }

    /**
     * Draws a rectangle with predefined properties.
     * 
     * @precondition none
     * @postcondition none
     * @return the drawn rectangle
     */
    private Rectangle drawRectangle() {
        Rectangle shape = new Rectangle();
        shape.setWidth(CARD_WIDTH);
        shape.setHeight(CARD_HEIGHT);
        shape.setArcWidth(CARD_ARC);
        shape.setArcHeight(CARD_ARC);
        return shape;
    }
}