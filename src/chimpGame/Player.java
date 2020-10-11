
/*
 * Jerry Soong
 * Started: February 11, 2020
 * Ended:
 */


//Statement for what package the class is in, which contains the other object classes.
package chimpGame;


//Import statements for classes outside of the package. Only ArrayList is used (for the cards).
import java.util.ArrayList;


//The Player class. Holds a player's cards, name and status, along with one game logic method.
public class Player {

	
//The instance variables: the player's hand (cards), their name, and their status ("CHIMP" letters).
//ArrayList of Cards for hand, for ease of adding and removing cards - no size to worry about.
	public ArrayList<Card> hand;
	public String name;
	public String status;
	
	

	
//The default constructor. Instances a player object, with the default name of "Player".
public Player(){
	this.hand = new ArrayList<Card>();
	this.name = "Player";
	this.status = "";

}	//End of default constructor.




//Constructor with name variable. Instances a player object with the given name.
public Player(String name){
	this.hand = new ArrayList<Card>();
	this.name = name;
	this.status = "";

}	//End of constructor.




//toString method. Prints the player's cards and status, under their name.
public String toString(){
	
	
//Since the String of each card needs to individually added to a base String in order to return it as one
//entire String, a temporary String variable is required. Along with this, the first part of the String
//(the player's name) might as well be added now ("name"'s hand is:).
	String temp = this.name + "'s hand is:\n";
	
	
//Run a for loop using the ArrayList notation to loop through every card.
	for (Card c:this.hand){
		
//Add to the String variable, the index number + 1 of the card (for selection purposes), the card name
//(uses the toString method of the Card class/object), and register a new line.
		temp += (hand.indexOf(c) + 1) + ". " + c + "\n";
		
	}	//End of for loop.
	
	
//Return all of the saved String (so far, name and cards), along with the status tacked onto the end.
	return temp + "\nLetters: " + this.status + "\n\n";
}





//Extra clearHand method. Clears the current hand of the player. Useful for resetting a hand for new cards.
public void clearHand(){
	
	
//Since the ArrayList changes size throughout the loop, a static amount of loops is more consistent
//than one dependent on ArrayList size.
	for (int i = 0; i < 3; i++){
		
//Try-catch statement. For the case of no card(s) being in the player's hand to be removed.
		try {
			
//Attempt to print a line in the console, stating what card is removed (optional really).
			System.out.println("Removed " + hand.get(0));
		
//Then attempt to remove the card.
			hand.remove(0);
			
//Should any of this fail (likely the printing, with a NullPointerException),
		} catch (Exception e){
			
//Notify the console of there being no cards to remove. Mostly extra and unnecessary to players to know.
			System.err.println("No card to remove");
		
		}	//End of try-catch statement.
		
	}		//End of for loop.

}			//End of clearHand method.

}			//End of Player class.