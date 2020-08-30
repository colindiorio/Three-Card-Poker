/**
* COSC 310-001   Card Games
* Game.java
*
* A class that allows a player object to play a game of 3 card poker
* against the house.
*
* @author Colin Diorio
*/
package main;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
	private Player player,
				   house;
	
	// Constructor
	public Game(Player player, Player house) {
		super();
		this.player = player;
		this.house = house;
		
		this.player.setHandType( determineHand( this.player ) );
		this.house.setHandType( determineHand( this.house ) );
	}
	
	/**
	 * Finds out how much the specific player's hand is worth,
	 * checks for pairs, flushes, straight, etc.
	 * 
	 * @param player the hand to be checked for highest points.
	 * @return points the points the hand is worth.
	 */
	public HandType determineHand(Player player) {
		ArrayList<Card> cards = player.getHand();
		ArrayList<Integer> values = new ArrayList<Integer>();
		boolean flush = true, straight = true;
		int duplicates = 0, duplicated = 0;
		Suit suit = cards.get( 0 ).getSuit();
		
		for ( int i = 0; i < cards.size(); i++ ) {
			int value = cards.get( i ).getWorth().getOrdinal();
			
			// Checks if the hand contains any duplicate numbers.
			if ( values.contains( value ) ) {
				duplicates++;
				duplicated = value;
			}
			
			// Checks if the hand is all of the same suit.
			if ( cards.get( i ).getSuit() != suit ) flush = false;
			values.add( value );
		}
		
		Collections.sort( values );
		
		// Can not be a straight if there are duplicates
		if (duplicates == 0) {
			// Checks if the numbers are increasing by one for all 3.
			for ( int i = 1; i < values.size(); i++ )
				if ( values.get( i ) - values.get( i - 1 ) != 1 ) 
					straight = false;
			
			// Checks if it is a straight using the ace as a ONE.
			if ( values.get( 0 ) == 2 && values.get( 1 ) == 3 &&
					values.get( 2 ) == 14 ) {
				values.set( 2, 3 );
				straight = true;
			}
		} else
			straight = false;
		
		return evaluateHandType( values, flush, straight,
						 duplicates, duplicated );
	}
	
	/**
	 * Finds out what hand rank and value of the hand provided.
	 * 
	 * @param values ordinal values of the hand.
	 * @param flush true if the hand is a flush.
	 * @param straight true if the hand is a straight.
	 * @param duplicates amount of duplicates in the hand.
	 * @param duplicated the duplicated card in the hand.
	 * @return handType the hand converted to the hand type.
	 */
	public HandType evaluateHandType(ArrayList<Integer> values,
					 boolean flush, boolean straight,
					 int duplicates, int duplicated) {
		// Three of a kind
		if ( duplicates == 2 ) 
			return createHandType( HandRank.THREE_OF_A_KIND,
								   values, 1 );
		
		// Pair
		if ( duplicates == 1 ) return createPairType( values );
		
		if ( straight ) {
			// Straight flush
			if ( flush ) 
				return createHandType( HandRank.STRAIGHT_FLUSH,
									   values, 1 );
			
			// Straight
			return createHandType( HandRank.STRAIGHT, values, 1 );
		}
		
		// Flush
		if ( flush ) return createHandType( HandRank.FLUSH, values,
								    3 );
		
		// High
		return createHandType( HandRank.HIGH, values, 3 );
	}
	
	/**
	 * Creates a hand type with the given rank, and builds the values
	 * using another ArrayList.
	 * 
	 * @param values The list of integers to use to create the values.
	 * @param rank the ranking of the hand.
	 * @param index how many integers to take from the values.
	 * @return handType returns the newly created hand type.
	 */
	public HandType createHandType(HandRank rank, ArrayList<Integer> 
					              values, int index) {
		int[] temp = new int[index];
		
		for (int i = 0; i < index; i++)
			temp[i] = values.get( values.size() - i - 1 ).intValue();
		
		return new HandType( rank, temp );
	}
	
	/**
	 * Creates a pair hand type, which includes finding what is the
	 * duplicated integer in the array.
	 * 
	 * @param values the array to check for duplicates
	 * @return handType the created pair hand type.
	 */
	public HandType createPairType(ArrayList<Integer> values) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int duplicated = 0;
		
		for ( int i = 0; i < values.size(); i++ ) {
			int current = values.get( i );
			
			// Finds duplicate in the list and stores + removes it.
			if ( list.contains( current ) ) {
				duplicated = current;
				list.remove( list.indexOf( current ) );
			} else
				list.add( current );
		}
		
		list.add( 0, duplicated );
		
		return createHandType( HandRank.PAIR, list, list.size() );
	}
	
	/**
	 * Checks if the player's hand was higher ranking than the house's
	 * hand.
	 * 
	 * @return playerVictory returns true if the player beat the house.
	 */
	public Outcome winningHand() {
		int[] playerPoints = this.player.getHandType().getValues(),
			  housePoints = this.house.getHandType().getValues();
		
		// When the hands are the same rank (the highest card on low)
		if ( playerPoints[0] == housePoints[0]) {
			
			// Checks other cards if they have a flush/pair/high rank.
			if (playerPoints[0] < 2000) {
				// Iterates through all the other cards in the hands.
				for (int i = 1; i < playerPoints.length; i++) {
					if (playerPoints[i] != housePoints[i])
						return playerPoints[i] > housePoints[i] ? 
								Outcome.WIN : Outcome.LOSE;
				}
			}
			
			return Outcome.PUSH;
		}
		
		return playerPoints[0] > housePoints[0] ? Outcome.WIN 
							: Outcome.LOSE;
	}
	
	// Getters and Setters
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getHouse() {
		return house;
	}

	public void setHouse(Player house) {
		this.house = house;
	}

}
