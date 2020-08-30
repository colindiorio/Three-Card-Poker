/**
* COSC 310-001   Card Games
* Card.java
*
* A class for storing and retrieving the information stored on a card
* such as it's suit and worth.
*
* @author Colin Diorio
*/
package main;

public class Card {
	
	private Suit suit;
	private Worth worth;
	
	// Constructor
	public Card(Suit suit, Worth worth) {
		super();
		this.suit = suit;
		this.worth = worth;
	}
	
	/**
	 * Checks if two cards are the exact same suit and worth.
	 * 
	 * @param anotherObject the object to compare to.
	 * @return isEqual if the two cards are the same.
	 */
	@Override
	public boolean equals(Object o) {
		if ( !( o instanceof Card ) ) return false;
		
		Card card = ( Card ) o;
		
		return ( this.suit == card.getSuit() &&
				this.worth == card.getWorth() );
	}
	
	/**
	 * Gets the spoken way you would describe a cards attributes.
	 * 
	 * @return cardInfo the card info presented normally.
	 */
	public String toString() {
		return this.worth + " of " + this.suit;
	}
	
	// Getters and Setters
	public Suit getSuit() {
		return suit;
	}
	
	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public Worth getWorth() {
		return worth;
	}
	
	public void setWorth(Worth worth) {
		this.worth = worth;
	}

}
