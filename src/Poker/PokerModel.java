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
		HandType hand;
		
	
		
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
	int flash_hearts=0;
	int flash_spades = 0;
	int flash_clubs = 0;
	int flash_diamonds = 0;
	
	for(int i = 8; i<=hearts.length;i++) {
	flash_hearts+=hearts[i];
	flash_spades+=spades[i];
	flash_clubs+=clubs[i];
	flash_diamonds+=diamonds[i];
	}
	if(flash_hearts ==4|| flash_spades==4||flash_clubs==4||flash_diamonds==4) {
		hand = HandType.ROYALFLUSH;
	}else {
		
		
	//Straight Flush
	int straight_hearts = 0;
	int straight_spades = 0;
	int straight_clubs = 0;
	int straight_diamonds = 0;
	for(int b = 3; b<= 7;b++) {
	straight_hearts+=hearts[b];
	straight_spades+=spades[b];
	straight_clubs+=clubs[b];
	straight_diamonds+=diamonds[b];
	}
	if(straight_hearts==4||straight_spades==4||straight_clubs==4||straight_diamonds==4) {
		hand = HandType.STRAIGHTFLUSH;
	}else {
		
		
	
	//Four of a Kind	
	boolean four_of_a_kind = false;
	for(int c = 0; c<hearts.length;c++) {
	if(hearts[c]==1 &&clubs[c]==1&&diamonds[c]==1&&spades[c]==1) {
		
	four_of_a_kind = true;
	}
	}
	if(four_of_a_kind ==true) {
		hand = HandType.FOUROFAKIND;	
	}else {
		
		
	//Full house sehr komplex muss man sich  noch überlegen wie drei gleiche werden bereits verglichen jetzt noch 2 andere die nicht gleich sind wie die vorherigen raus finden
		boolean dreigleiche = false;
		boolean zweigleiche = false;
	for(int d = 0; d<hearts.length;d++) {
		  //3gleiche
	if(((hearts[d]==1&&clubs[d]==1&&diamonds[d]==1)||(hearts[d]==1&&spades[d]==1&&clubs[d]==1)||(hearts[d]==1&&spades[d]==1&&diamonds[d]==1)||(spades[d]==1&&clubs[d]==1&&diamonds[d]==1))) {
		dreigleiche = true;
	}
		//2gleiche
	
	}
	
		
	
	}
	}
	}
	
	
		
	
		
	
		
	//Flush
		
	//Straight
		
	//Tree of a Kind
		
	//Two Pair
		
	//One Pair
		
	//High Card
		
	
	return hand;
		
		
	}
	
	
	

	



	
	



}

