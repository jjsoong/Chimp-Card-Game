
/*
 * Jerry Soong
 * Started: February 11, 2020
 * Ended:
 */


//Statement for what package the class is in, which contains the other object classes.
package chimpGame;


//Import statements for classes outside of the package. ArrayList for the cards, Random for shuffling.
import java.util.ArrayList;
import java.util.Random;


//The Deck class. For creating Deck objects, which hold a standard set of 52 playing cards.
public class Deck {

	
//The only instance variable. An ArrayList to hold all cards.
	public ArrayList<Card> cards;
	
	
	
	
//Default (and the only) constructor. Creates a deck of a standard 52 playing cards, shuffled and all.
public Deck(){

	
//First, instance a new ArrayList for Card objects.
	this.cards = new ArrayList<Card>();
	
	
//For loops used to create the 52 playing cards. For loops control the suits and ranks for the cards
//(see p. 136/7 of the Java Textbook).
	for(int i = 0; i <= 3; i++){
		for (int j = 1; j <= 13; j++){
			this.cards.add(new Card(i, j));
		
		}	//End of rank for loop.
	}		//End of suit for loop.
		
	
//Call the shuffle method in the same class, to instantly shuffle the cards. The cards will always be
//shuffled prior to play, so shuffling them upon construction is out of convenience.
	shuffle();
	
	
}			//End of constructor.




//Shuffle method. Shuffles all the cards in the deck.
public void shuffle(){
	
//Temporary variables for the method are:
//An integer to hold the randomly generated integer.
	int x;

//A Random object, to generate a random integer.
	Random rng = new Random();
	
//A Card, to hold a card as two cards exchange spots.
	Card temp;
	
	
//A for loop, to cause all spots of the deck to exchange cards.
	for (int i = 0; i < this.cards.size()-1; i++){
			
		
//A do-while loop. Optional, and not necessary, this is to ensure that the random integer is greater than
//the index of the current card. By doing so, cards are prevented (or at least, extremely unlikely) from
//remaining (being swapped back) to the original position of the card.
		do{
			
			x = rng.nextInt(this.cards.size());	//Save the integer, as the integer is used more than once.
		
		} while (x <= i);
		
		
//Save the current card in the temporary variable.
		temp = this.cards.get(i);
		
//Set the current index as the random card.
		this.cards.set(i, this.cards.get(x));

//Set the random card's index as the current index's card. Voila, the cards are swapped.
		this.cards.set(x, temp);

	
	}		//End of for loop.
	
}			//End of shuffle method.

}			//End of Deck class.
