/**
* COSC 310-001   Card Games
* Player.java
*
* A class for keeping track of a specific players cards.
*
* @author Colin Diorio
*/
package main;

import java.util.ArrayList;

public class Player {
	
	private ArrayList<Card> hand;
	private HandType handType;
	
	// Constructor
	public Player() {
		this.hand = new ArrayList<Card>();
		this.handType = null;
	}
	
	/**
	 * Displays a hand of a player within the game.
	 * 
	 * @param name the display name of the player.
	 */
	public void showHand(String name) {
		// Makes sure the hand type is defined and it has enough cards.
		if (this.handType == null || this.hand.size() != 3) {
			System.out.println("ERROR: INVALID HAND");
			return;
		}
		
		final String line = "----------------------------------" + 
				            "----------------------------------";
		
		System.out.printf( line + "\n" + name + ": %19s %19s %19s\n",
						  this.hand.get( 0 ),
						  this.hand.get( 1 ),
						  this.hand.get( 2 ) );
		System.out.println(line + "\n" + this.getHandType());
	}
	
	/**
	 * Adds a new card to the player's hand.
	 * 
	 * @param card the card to be added to the player's hand.
	 */
	public void add(Card card) {
		this.hand.add( card );
	}
	
	/**
	 * Sets the player's hand back to having no cards.
	 */
	public void reset() {
		this.hand.clear();
		this.handType = null;
	}
	
	// Getters and Setters
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public HandType getHandType() {
		return handType;
	}

	public void setHandType(HandType handType) {
		this.handType = handType;
	}

}
