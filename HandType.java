/**
* COSC 310-001   Card Games
* HandType.java
*
* A class to setup a way to rank the hand a player has for 3 card
* poker.
*
* @author Colin Diorio
*/
package main;

public class HandType {
	
	private HandRank rank;
	private int[] values;
	
	// Constructor
	public HandType(HandRank rank, int[] values) {
		// Gives the first value more power based off the hand rank.
		values[0] = ( int ) ( values[0] * 
				  Math.pow( 10, rank.ordinal() ) );
		
		this.rank = rank;
		this.values = values;
	}
	
	/**
	 * A way to return the hand type in the way someone would say it.
	 */
	public String toString() {
		HandRank rank = this.rank;
		String name = this.rank.toString();
		
		// Pair and High have to include the value of the card.
		if (rank == HandRank.PAIR)
			name += " OF " + Worth.values()[(values[0] / 10) - 2] +
					"S";
		
		
		if (rank == HandRank.HIGH)
			name = Worth.values()[values[0] - 2] + " " + name;
		
		return name;
	}

	// Getters and Setters
	public HandRank getRank() {
		return rank;
	}

	public void setRank(HandRank rank) {
		this.rank = rank;
	}

	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

}
