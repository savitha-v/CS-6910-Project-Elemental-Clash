package edu.westga.cs6910.elementalclash.viewmodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs6910.elementalclash.model.Card;
import edu.westga.cs6910.elementalclash.resources.ExceptionMessages;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The class ViewCard.
 * 
 * @author CS6910
 * @version Summer 2024
 */
public class ViewCard {

    public static final double CARD_WIDTH = 110;
    public static final double CARD_HEIGHT = 170;
    public static final String CARD_BACK_IMAGE = "file:path/to/images/wolfie.jpg";
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
            System.err.println("Image file not found.");
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

    private Node designFrontOfCard() {
        Pane pane = new Pane();
        Shape shape = this.drawRectangle();

        FileInputStream input = null;
        try {
            String imagePath = "file:path/to/images/" + this.card.getSuit().toString().toLowerCase() + "_"
            		+ this.card.getRank().toString().toLowerCase().replace(" ", "_") + ".png";
            input = new FileInputStream(imagePath);
            Image image = new Image(input);
            ImagePattern pattern = new ImagePattern(image);
            shape.setFill(pattern);
            pane.getChildren().add(shape);
        } catch (FileNotFoundException ex) {
            System.err.println("Image file not found.");
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

    private Rectangle drawRectangle() {
        Rectangle shape = new Rectangle();
        shape.setWidth(CARD_WIDTH);
        shape.setHeight(CARD_HEIGHT);
        shape.setArcWidth(CARD_ARC);
        shape.setArcHeight(CARD_ARC);
        return shape;
    }
}