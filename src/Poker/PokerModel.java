package Poker;



import java.util.ArrayList;

import Poker.PokerPlayer.HandType;






public class PokerModel {
	//Um zu sehen wie viel Player im Spiel sind wenn sich die länge änder kann man mit Property automatisch eine VBOX erstellen
	private final ArrayList<PokerPlayer> players = new ArrayList<>();
	
	
public PokerModel() {
	
}

	

	public HandType getHandType(PokerPlayer player) {
		this.players.add(player);//Player ArrayList einen neuen Player hinzufügen
		
	//Karten Werte werden anhand der Position vom Enum bestimmt CardRank!! Void wird noch zu handtype
		
		//Arrays mit 1,2,3,4,5,6,7,8,9,10, j, q, k ,a
		int [] hearts = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		int [] spades = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		int [] clubs = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		int [] diamonds = {0,0,0,0,0,0,0,0,0,0,0,0,0};
		
	
		
		for(int i =0;i<player.getHand().length;i++) {
			switch(player.getHand()[i].cardRank.ordinal()+1) {
			case 1:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[0]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[0]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[0]++;
						}else {
							diamonds[0]++;
						}
					}
				}
			
			break;
			case 2:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[1]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[1]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[1]++;
						}else {
							diamonds[1]++;
						}
					}
				}
				
				
			break;
			case 3:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[2]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[2]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[2]++;
						}else {
							diamonds[2]++;
						}
					}
				}
				
			break;
			case 4:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[3]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[3]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[3]++;
						}else {
							diamonds[3]++;
						}
					}
				}
				
			break;
			case 5:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[4]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[4]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[4]++;
						}else {
							diamonds[4]++;
						}
					}
				}
				
			break;
			case 6:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[5]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[5]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[5]++;
						}else {
							diamonds[5]++;
						}
					}
				}
				
			break;
			case 7:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[6]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[6]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[6]++;
						}else {
							diamonds[6]++;
						}
					}
				}
				
			break;
			case 8:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[7]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[7]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[7]++;
						}else {
							diamonds[7]++;
						}
					}
				}
				
			break;
			case 9:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[8]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[8]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[8]++;
						}else {
							diamonds[8]++;
						}
					}
				}
				
			break;
			case 10:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[9]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[9]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[9]++;
						}else {
							diamonds[9]++;
						}
					}
				}
				
			break;
			case 11:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[10]++;
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[10]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[10]++;
						}else {
							diamonds[10]++;
						}
					}
				}
				
			break;
			case 12:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[11]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[11]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[11]++;
						}else {
							diamonds[11]++;
						}
					}
				}
			case 13:
				if(player.getHand()[i].cardSuit.toString() == "HEARTS") {//nicht getestet noch testen
					hearts[12]++;
				}else {
					if(player.getHand()[i].cardSuit.toString() == "SPADES") {
						spades[12]++;
					}else {
						if(player.getHand()[i].cardSuit.toString() == "CLUBS") {
							clubs[12]++;
						}else {
							diamonds[12]++;
						}
					}
				}
			
			break;
				
			}
		}
	
	
	
	//Royal Flush
	HandType hand;
	if(
	(HEARTS_ace == 1 && HEARTS_king == 1 && HEARTS_queen == 1 && HEARTS_jack == 1 && HEARTS_ten == 1 )
	|| (Spades_ace == 1 && Spades_king == 1 && Spades_queen == 1 && Spades_jack == 1 && Spades_ten == 1 )
	|| (Clubs_ace == 1 && Clubs_king == 1 && Clubs_queen == 1 && Clubs_jack == 1 && Clubs_ten == 1)
	|| (Diamonds_ace == 1 && Diamonds_king == 1 && Diamonds_queen == 1 && Diamonds_jack == 1 && Diamonds_ten == 1)
	) {
		hand = HandType.ROYALFLUSH;
	}else {
	//Straight Flush
	if(
	(HEARTS_nine == 1 && HEARTS_eight == 1 && HEARTS_seven == 1 && HEARTS_six == 1 && HEARTS_five == 1)
	|| (Spades_nine == 1 && Spades_eight == 1 && Spades_seven == 1 && Spades_six == 1 && Spades_five == 1)
	|| (Clubs_nine == 1 && Clubs_eight == 1 && Clubs_seven == 1 && Clubs_six == 1 && Clubs_five == 1)
	|| (Diamonds_nine == 1 && Diamonds_eight == 1 && Diamonds_seven == 1 && Diamonds_six == 1 && Diamonds_five == 1)
	) {
		hand = HandType.STRAIGHTFLUSH;
	}else {
		//Four of a Kind	
	if((HEARTS_ace == 1 && Spades_ace == 1 && Clubs_ace == 1 && Diamonds_ace == 1)
	||(HEARTS_king == 1 && Spades_king == 1 && Clubs_king == 1 && Diamonds_king == 1)
	||(HEARTS_queen == 1 && Spades_queen == 1 && Clubs_queen == 1 && Diamonds_queen == 1) 
	||(HEARTS_two == 1 && Spades_two == 1 && Clubs_two == 1 && Diamonds_two ==  1)
	||(HEARTS_tree == 1 && Spades_tree == 1 && Clubs_tree == 1 && Diamonds_tree == 1)
	||(HEARTS_four == 1 && Spades_four == 1 && Clubs_four == 1 && Diamonds_four == 1)
	||(HEARTS_five == 1 && Spades_five == 1 && Clubs_five == 1 && Diamonds_five == 1)
	||(HEARTS_six == 1 && Spades_six == 1 && Clubs_six == 1 && Diamonds_six == 1)
	||(HEARTS_seven == 1 && Spades_seven == 1 && Clubs_seven == 1 && Diamonds_seven == 1)
	||(HEARTS_eight == 1 && Spades_eight == 1 && Clubs_eight == 1 && Diamonds_eight == 1)
	||(HEARTS_nine == 1 && Spades_nine == 1 && Clubs_nine == 1 && Diamonds_nine == 1)
	||(HEARTS_ten == 1 && Spades_ten == 1 && Clubs_ten == 1 && Diamonds_ten == 1)
	||(HEARTS_jack == 1 && Spades_jack == 1 && Clubs_jack == 1 && Diamonds_jack == 1)
	) {
		hand = HandType.FOUROFAKIND;
		
	}else {
	//Full house
	
	
		
	
	}
	}
	}
	
	
		
	
		
	
		
	//Flush
		
	//Straight
		
	//Tree of a Kind
		
	//Two Pair
		
	//One Pair
		
	//High Card
		
	
	return null;
		
		
	}
	
	
	

	



	
	



}

