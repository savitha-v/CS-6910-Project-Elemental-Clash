package edu.westga.cs6910.elementalclash.model;

/**
 * The Play interface. 
 * The interface Play defines the method signatures for the behaviors that every player object must implement.
 * 
 * @version 06/22/2024
 * @author Savitha Venkatesh
 */
public interface Play {
	
    String getName();
    
    int getLifePoints();
    
    Card drawCard();
    
    void reduceLifePoints(int points);
}