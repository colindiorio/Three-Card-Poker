/**
* COSC 310-001   Card Games
* c1.java
*
* The main class of the program, it's what handles user input to play
* 3 card poker and does all the setup for the game to be played.
*
* @author Colin Diorio
*/
package main;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Test {
	
	public static void main(String[] args) {
		Scanner input = new Scanner( System.in );
		int balance = 50000;
		
		// Plays until the player is out of money.
		while ( balance > 0 ) {
			int bet = getBet( input, balance );
			Outcome outcome = playMatch( input );
			balance = handleOutcome( outcome, balance, bet );
		}
		
		System.out.println( "You ran out of money. " + 
							"Thanks for playing!" );
		input.close();
	}
	
	/**
	 * Asks the player to input their bet amount and makes sure it
	 * is entered correctly.
	 * 
	 * @param input a scanner to get the input from the player.
	 * @param balance the current balance of the player.
	 * @return bet the bet the player wishes to use.
	 */
	private static int getBet(Scanner input, int balance) {
		// nf stands for NumberFormat
		NumberFormat nf = NumberFormat.getNumberInstance( Locale.US );
		int bet = 0;
		System.out.println( "You have $" + nf.format( balance ) +
						    ", how much do you want to bet?" +
							" (quit to stop playing)" );
		
		while ( bet <= 0 ) {
			// Makes sure the input is an integer.
			if ( !input.hasNextInt() ) {
				if (input.hasNext() &&
						input.next().toLowerCase().equals("quit"))
					System.exit(0);
				continue;
			}
			
			bet = input.nextInt();
			
			// Makes sure the integer is positive and not too much.
			if ( bet <= 0 || bet > balance / 2 ) {
				System.out.println( "Has to be greater than 0, " + 
								   "and no more than half your" +
								   " total." );
				bet = 0;
			}
			
		}
		
		return bet;
	}
	
	/**
	 * Runs the game of 3 card poker and returns the outcome of it.
	 * 
	 * @param input scanner to retrieve player input.
	 * @return outcome the outcome of the poker match.
	 */
	private static Outcome playMatch(Scanner input) {
		final int CARDS = 3;
		Deck deck = new Deck().shuffle();
		Game game = prepareGame( deck, CARDS );
		
		// Gets the player's decision before showing the house's hand.
		game.getPlayer().showHand("You");
		String choice = getChoice( input );
		game.getHouse().showHand("House");
		
		if ( choice.equals( "stay" ) ) return Outcome.STAY;
		return game.winningHand();
	}
	
	/**
	 * Asks the player for their decision on if they would like
	 * to play their hand and double down, or stay and just lose
	 * the starting bet.
	 * 
	 * @param input scanner to retrieve player input.
	 * @return choice the player's selected choice between play & stay.
	 */
	private static String getChoice(Scanner input) {
		String c = "N/A"; // c stands for choice
		System.out.println("\nDo you want to play or stay? " + 
						   "(playing doubles your bet)");
		
		// The player chooses to play or stay with their shown cards.
		while ( !c.equals( "play" ) && !c.equals( "stay" ) ) {
			if ( input.hasNextLine() )
				c = input.next().toLowerCase();
		}
		
		return c;
	}
	
	/**
	 * Sets up a new game by creating two players, giving them both
	 * a certain amount of cards then returning the game object.
	 * 
	 * @param d the deck to use for the game.
	 * @param CARDS the amount of cards to give each player.
	 * @return preparedGame the game that has been setup.
	 */
	private static Game prepareGame(Deck deck, int CARDS) {
		Player player = new Player(), house = new Player();
		
		for ( int i = 0; i < CARDS; i++ ) {
			// Removes cards from the top and adds it to a hand.
			player.add( deck.removeTop() );
			house.add( deck.removeTop() );
		}
		
		return new Game( player, house );
	}
	
	/**
	 * Updates the balanced based off the bet and outcome of the
	 * poker match.
	 * 
	 * @param outcome the outcome of the poker match.
	 * @param balance the current balance of the player.
	 * @param bet the bet placed on the current game.
	 * @return newBalance updated balance after processing outcome.
	 */
	private static int handleOutcome(Outcome outcome, int balance, int bet) {
		if ( outcome == Outcome.WIN ) {
			// Gains double the bet because they doubled down.
			balance += bet * 2;
			System.out.println( "\nYou win!" );
		} else if ( outcome == Outcome.LOSE ){
			// Loses double because they doubled down.
			balance -= bet * 2;
			System.out.println( "\nYou lose." );
		} else if ( outcome == Outcome.PUSH) {
			// Balance stays the same.
			System.out.println("\nYou pushed.");
		} else {
			// Loses starting bet.
			balance -= bet;
			System.out.println( "\nYou decided not to play." );
		}
		
		return balance;
	}

}
