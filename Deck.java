/**
* COSC 310-001   Card Games
* Deck.java
*
* A class to setup a normal playing deck of 52 cards.  It stores it in
* an ArrayList of cards and has ways to deal, and shuffle cards.
*
* @author Colin Diorio
*/
package main;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> deck;
	
	// Constructor
	public Deck() {
		ArrayList<Card> freshDeck = new ArrayList<Card>();
		
		// Creates one of each card for the deck
		for ( Suit suit : Suit.values() )
			for ( Worth worth : Worth.values() )
				freshDeck.add( new Card( suit, worth ) );
			
		this.deck = freshDeck;
	}
	
	/**
	 * Shuffles the deck of cards so it is organized randomly, by
	 * default it is like a brand new deck of cards.
	 * 
	 * @return deck the current deck object (for method chaining).
	 */
	public Deck shuffle() {
		Collections.shuffle( deck );
		return this;
	}
	
	/**
	 * Removes and returns the card at the top of the deck of cards.
	 * 
	 * @return topCard card removed from the top of the deck.
	 */
	public Card removeTop() {
		return deck.remove( 0 );
	}
	
	/**
	 * Retrieves the amount of cards inside of the deck.
	 * 
	 * @return size amount of cards in the deck.
	 */
	public int size() {
		return deck.size();
	}

	// Getters and Setters
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
}
