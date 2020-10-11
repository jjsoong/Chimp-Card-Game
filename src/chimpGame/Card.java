
/*
 * Jerry Soong
 * Started: February 11, 2020
 * Ended:
 */


//Statement for what package the class is in, which contains the other classes which use this object.
package chimpGame;


//The Card class. For instancing a "card" object, with a suit and rank.
public class Card {

	
//Instance variables of suit and rank. Both are saved as integers for convenience.
	public int suit;
	public int rank;
	
	
//Default constructor. Provides a card with placeholder values (which is an invalid card of hearts).
public Card(){
	this.suit = 0;
	this.rank = 0;
	
}	//End of default constructor.




//Constructor with parameters. Creates a card with the given parameters.
public Card(int suit, int rank){
	this.suit = suit;
	this.rank = rank;

}	//End of constructor.




//toString method. Returns the name of the card in String.
public String toString(){
	
//Temporary arrays for the Strings of the suits and the ranks.
	String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
	String[] ranks = {"null", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
	
//For the name of the card, return the String of the arrays at the corresponding index (of the object's
//instance variable). Should return: "rank" of "suit" (ex: 4 of Spades).
	return (ranks[this.rank] + " of " + suits[this.suit]);
	
	
}	//End of toString method.



}	//End of Card class.
