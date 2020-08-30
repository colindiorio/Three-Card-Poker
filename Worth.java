/**
* COSC 310-001   Card Games
* Worth.java
*
* A class for setting up a public enum for the worth of a card.
*
* @author Colin Diorio
*/
package main;
enum Worth {
	TWO, THREE, FOUR, FIVE, SIX, SEVEN,
	EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	
	/**
	 * Adjusts the value of the ordinal to fit cards better.
	 * 
	 * @return adjustedOrdinal the original ordinal + 2.
	 */
	public int getOrdinal() {
		return ordinal() + 2;
	}
}