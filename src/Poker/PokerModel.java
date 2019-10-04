package Poker;



import Poker.PokerPlayer.HandType;
import javafx.scene.image.Image;



public class PokerModel {


	public HandType evaluateHand(PokerCard[] hand) {
		//Method which evaluates the actual hand and returns a HandType
		
		
		
		boolean flush = true; //assume there is a flush
		
		for (int x=0; x<6; x++)
		      {
		            if ( hand[x].cardSuit.ordinal()  != hand[x+1].cardSuit.ordinal() )

		                flush = false;
		        }

		
		
		return null;
	}
	/*public Image[] shuffle(Image[] imageArray) {

		

		

		

		return imageArray;

		

		

	}*/



}