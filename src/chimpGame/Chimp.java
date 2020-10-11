
/*
 * Jerry Soong
 * Started: February 11, 2020
 * Ended:
 */


// Statement for what package the class is in, which contains the other object classes.
package chimpGame;


//Import statements, for classes outside of the package. JOptionPane for windows (GUI), and ArrayList
//for holding the Players.
import javax.swing.JOptionPane;
import java.util.ArrayList;


//The Chimp class. Holds the game logic, as well as user interaction for said game.
public class Chimp {

	
//Using the Deck class for a deck object, to draw cards from. Instanced when the game starts.
	private static Deck deck;
	
//Using an ArrayList for containing the multiple players, for ease of adding and removing players. 
	private static ArrayList<Player> players = new ArrayList<Player>();

	
	
//Constants of Strings or String arrays, for JOptionPane use.
//Title constant since the title of the game is repetitively used in each JOptionPane instance.
	private static final String TITLE = "Chimp";

//Array for an OptionDialog's buttons for choosing the amount of players playing the game.
	private static final String[] PLAYER_AMOUNT = {"2", "3", "4", "5"};
	
//Array for an OptionDialog's buttons for playing an Ace card as a 1 or 11 (in amount).
	private static final String[] ONE_ELEVEN = {"1", "11"};
	
//Array for an OptionDialog's buttons for choosing a card to play.
	private static final String[] CARD_CHOICE = {"1", "2", "3"};
	
	
	
//Various class-wide variables for game-logic.
//A boolean for determining whenever the players take turns in forward or reverse order
//(changes by playing a 9).
	private static boolean forward = true;
	
//An integer for determining which player's turn it is. Used in conjunction with a (do) loop.
	private static int index = 0;
	
//An integer as the "total" (number of points) of the card game.
//Making this value go over 99 is considered a round loss.
	private static int total = 0;
	
	
	
	
//The main. What is ran upon running the class.
public static void main(String args[]){
	
	
/*
 * Temporary integer to hold the amount of players will be in the game (decided by user).
 * 
 * Used an OptionDialog (of JOptionPane) to get the amount, for easy input and error avoiding.
 * 
 * In addition, a "closeGame" method can be first seen here. It will be explained in further depth where the
 * method itself is written (at the end). In summary, the method takes an integer (from OptionDialogs) and
 * determines if the user closes (hits X instead of an option) a OptionDialog (meaning that it doesn't occur
 * for normal MessageDialogs, since they don't output anything) and closes the program if they did by use of
 * the "initialValue" parameter of OptionDialogs. Otherwise, the program returns the same integer (used for
 * determining which button the user pressed) that was entered, essentially allowing the OptionDialog to
 * function normally.
 */
	int playernum = closeGame(
			JOptionPane.showOptionDialog(null, 		//Parent component. null for a default frame.
					
					"How many players are there?", 	//The message.
			
					TITLE, 							//The title of the game (using constant).
					
					JOptionPane.DEFAULT_OPTION, 	//Option type. Default for own custom options.
			
					JOptionPane.QUESTION_MESSAGE, 	//Message type. Question for the question icon and it is
													//presenting a question. Additional window icon will
													//draw attention (and for style).
			
					null, 							//Icon. Left as null as no custom icon was needed.
			
					PLAYER_AMOUNT, 					//Object used for the options. The String array is used
													//as the text for the options
			
					-1)								//The "initialValue" parameter. The integer that would
													//returned should the user close the JOptionPane.
													//-1 for the closeGame method. Also end of JOptionPane.
			
			)										//End of closeGame method.
			
			+ 2;									//+2 as the minimum amount of players is 2, but the
													//OptionDialog will output the option's index inside its
													//array, which is two less than our minimum of 2
													//(Option "2" will output 0).
	
	
	
//For loop to continuously add players until the correct amount is reached.
//Integer starts at 1, since we start with "Player 1", until "Player n" for amount of players
//(it will make sense).
	for (int a = 1; a <= playernum; a++){
		
		players.add(new Player(									//Directly add a new Player object into
																//the ArrayList, for efficiency and less
																//unnecessary variables.
				
				JOptionPane.showInputDialog(null, 				//InputDialog for free input of the player's
																//name (will return the String directly into
																//object constructor).
						
						"Input name for Player " + a + ".", 	//Message. Will read "Input name for Player
																//n."
						
						TITLE, 									
						JOptionPane.PLAIN_MESSAGE				
				
						)										//End of InputDialog.
				
				));												//End of Player constructor parameters and
																//ArrayList's "add" method.
		
	}		//End of for loop.
	
	
	
//The reset method is called now to double as setting out the deck and cards initially.
	resetCards();
	
	

//The main chunk of game. In a do loop, since there is an undefined amount of times it should loop.
	do {
		
		
//Initial MessageDialog to show who's turn it is, without spoiling what cards they have.
//Essentially, a pause to allow players to switch who's at the computer (rather inefficient, though the
//only way without network play).
		JOptionPane.showMessageDialog(null,
				"It's " + players.get(index).name + "'s turn!",
				TITLE,
				JOptionPane.INFORMATION_MESSAGE);
		
		
//Calling method playCard to play the card that the current player chooses to play.
//Therefore, get the card of OptionDialog's choice, of the current player(ArrayList @ index)'s hand.
		playCard(players.get(index).hand.get(
				closeGame(
						JOptionPane.showOptionDialog(
								null, 
								
								players.get(index) + "The current total is: "
								+ total + "\n\nWhat card will you play?", 
												/*
												 * The message. Should display:
												 * 
												 * "name"'s hand is:
												 * 1. "card"
												 * 2. "card"
												 * 3. "card"
												 * 
												 * Letters: "status"
												 * 
												 * The current total is: "total"
												 * 
												 * What card will you play?
												 */
						
								TITLE, 
								JOptionPane.DEFAULT_OPTION, 
								JOptionPane.PLAIN_MESSAGE, 
								null, 
								CARD_CHOICE,
								
								-1)			//End of JOptionPane.
						)					//End of closeGame method.
				), 							//End of get method of hand ArrayList of player.
					
				players.get(index));		//End of playCard method, with the current player as another
												//parameter.
		
		
//After the card is played, check to see if the round is over (exceeded 99).
		roundOver(players.get(index));
		
		
//If-else statement for going to the next player's turn, first dependent on if turns are rotating in 
//forward or backward order.
		if (forward){
			
//If forward, use a ternary operator to determine if the index is at its maximum, and subtracting (adding
//negative) by the size of the ArrayList to return to 0. Otherwise, add 1 to move to the next player.
			index += index == (players.size() - 1) ? -1 * (players.size() - 1) : 1;
		
//Otherwise (if backward),
		} else {
			
//Backward is basically opposite of forward - if at 0, add by ArrayList size to return to max index.
//Subtract one to move back a player.
			index += index == 0 ? (players.size() - 1) : -1;
		
		}	//End of if statement.
		
		
		
//Continue looping through players until (therefore, while not) one player remains (a winner exists).
//Note that this also means that starting a new round begins on the next player.
	} while (isWinner() == false);
	

	
}	//End of main.




//The playCard method. Plays the (selected, and inputed as a parameter) card from the player.
public static void playCard(Card aCard, Player p){
	
	
//Prints the card (string) to the console, as a simulation of seeing the pile. Not necessary
//to the game by any means, but is there for error checking.
	System.out.println(aCard);
	
	
//Run the card through the checkMove method, to cause the card's effect.
	checkMove(aCard.rank);

	
//Remove the card from the player's hand. The main reason for the player parameter.
	p.hand.remove(aCard);
	
	
//Get the player to pickup a new card.
	pickUp(p);

	
} //End of playCard method.




//The checkMove method. Integer parameter is for the card's rank, to cause the correct effect per rank.
public static void checkMove(int cRank){


//Switch statement for decision making. Better looking in my opinion (and I don't want to write a million
//comparisons).
	switch (cRank){
	
//For an Ace, ask if they want to play the card as a 1 or 11. If they choose 1 (outputs 0), add 1.
//If not, add 11 (using a ternary operator).
	case 1: total += (
			closeGame(
					JOptionPane.showOptionDialog(null, 
							"Play the Ace as a 1 or 11?", 
							TITLE, 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.QUESTION_MESSAGE, 
							null, 
							ONE_ELEVEN, 
							
							-1)	//End of JOptionPane.
					
					) 			//End of closeGame method.
			== 0 ? 1 : 11); 	//Ternary operator, and end of adding statement.
	break;	
			
//For a 4, add nothing to the total and break, essentially skipping your turn.
	case 4: break;
	
//For a 9, change forward to the opposite of the current (if true, become false and vice versa).
//Additionally, add nothing to skip your turn.
	case 9: forward = forward ? false:true; break;
	
//For a 10, subtract 10.
	case 10: total -=10; break;
	
//For a Jack, add 20.
	case 11: total += 20; break;

//For a Queen, set to 99.
	case 12: total = 99; break;
	
//For a King, add 20 (like Jack).
	case 13: total += 20; break;
	
//For anything else, add the card's number (rank).
	default: total += cRank; break;
	
	} 	//End of switch-case statement.
	
	
}		//End of checkMove method.




//pickUp method. Makes the passed player pickup a card, and removes it from the deck.
public static void pickUp(Player p){
	

//A try-catch, in case the deck contains no cards (NullPointerException).
	try {

//Try to add the card into the player's hand,
		p.hand.add(deck.cards.get(0));
		
//and remove the card from the deck.
		deck.cards.remove(0);	
	
//Should any of these fail (the first line would likely fail first),
	} catch (Exception e){
	
//create a new deck,
		deck = new Deck();
		
//and add an error message in the console notifying of the creation of the new deck.
//Not entirely necessary, but good to know for all players.
		System.err.println("Deck was empty, new deck created");
	
	}	//End of try-catch statement.
	
	
}		//End of pickUp method.




//isWinner method. Checks if one player remains (and is thus the winner). Returns a boolean for the do loop.
public static boolean isWinner(){
	

//If one player remains (size = 1),
	if (players.size() == 1){
		
//Inform the player(s) of the winner,
		JOptionPane.showMessageDialog(null, players.get(0).name + " is the Winner!", TITLE, JOptionPane.INFORMATION_MESSAGE);
		
//and return true (as in, there is a winner) to stop the do loop of the game continuing.
		return true;
	
//Otherwise,
	} else {
		
//return false, and continue on as if nothing happened.
		return false;
	
	}	//End of if statement.
	
	
}		//End of isWinner method.




//roundOver method. Checks if the current round is over (total exceeds 99) and if so, adds a letter
//to the player which causes the total to exceed 99, and resets the cards.
public static void roundOver(Player p){
	
	
	
//If the total exceeds 99,
	if (total > 99){
		
		
//Notify the player(s) of the action, that the player (player who played the card is passed as the parameter
//p) caused the total to exceed 99.
		JOptionPane.showMessageDialog(
				null, 
				p.name + " caused the total to surpass 99!~\n\nThey get a letter!~", 
				TITLE, 
				JOptionPane.WARNING_MESSAGE
				
				);	//End of JOptionPane.
		
//Then, checking the player's status length, add the appropriate letter.
		switch (p.status.length()){
		
//If no letters, add C.
		case 0: p.status += 'C'; break;
		
//If one letter (has C), add H
		case 1: p.status += 'H'; break;
		
//If two letters (has CH), add I.
		case 2: p.status += 'I'; break;
		
//If three letters (has CHI), add M.
		case 3: p.status += 'M'; break;
		
//If four letters (has CHIM), add P.
		case 4: p.status += 'P';
		
//This also means the player has CHIMP now, and is out of the game. Output a message saying so,
		JOptionPane.showMessageDialog(
				null,
				p.name + " has " + p.status + "\n\n" + p.name + " is out!~",
				TITLE,
				JOptionPane.INFORMATION_MESSAGE);
		
//and remove the player from the ArrayList, removing them from the game.
		players.remove(index);
		
		break;
		
//If none of the above, an error has occurred. Output a message saying so.
		default: System.err.println("Invalid length of status..."); break;
		
		}		//End of switch-case statement.
	
		
//After the letters added and players eliminated if needed, reset the cards (hands and deck) and total.
		resetCards();
	
		
	}			//End of if statement.
	
	
}				//End of roundOver method.




//Extra resetCards method. Resets the cards (hand and deck) and total for a "new round" state.
public static void resetCards(){
	

//Set total to 0 for new round.
	total = 0;
	
	
//Create a new deck for the new round (equivalent to gathering all cards and reshuffling).
	deck = new Deck();
	
	
//For statement to cause all players to clear their hands and pickup three new cards.
	for (int i = 0; i < players.size(); i++){
		
//Have each player run the clearHand method, in the Player class (clears their hand of all cards).
		players.get(i).clearHand();
		
//Then, have each player pickup a card, 3 times (in for loop for easy changes, if someone wants to change
//the amount of cards in one hand.
		for (int j = 0; j < 3; j++){
			pickUp(players.get(i));
		
		}	//End of for loop (for pickups).
	
	}		//End of for loop (for players).
}			//End of resetCards method.




//Extra closeGame method. Closes the game, should any decision-making-JOptionPane be closed.
//Passes the integer returned from the JOptionPane as a parameter.
public static int closeGame(int n){
	
//If the passed integer is -1 (set as the initialValue, which is returned if no option is selected),
	if (n == -1){
		
//close the program.
		System.exit(0);
	
	}	//End of if statement.
	

//Otherwise (if an option was selected), return the integer originally outputted by the JOptionPane.
	return n;

}		//End of the closeGame method.


} 		//End of the Chimp class.