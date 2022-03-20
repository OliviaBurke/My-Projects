import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// this class is to identify what can be in a deck of cards

public class Card {

	 private String rank, suit, CardImage;
	 private int value;
	 String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"}; 
	 
	 
	 // the constructor
	 public Card(String rank, String suit) {
		 
		 
		 HashMap<String, Integer> cardRankValue = new HashMap<String, Integer>();
		 for (int i = 0; i < 13; i++) {
			 cardRankValue.put(ranks[i], i);
		 }
		 
		 setRank(rank);
		 setSuit(suit);
		 setCardImage(rank +"of" + suit + ".png");
		 setValue(cardRankValue.get(rank)+1);
	 }
	 
	 
	
	 // getters and setters
	 public String getRank() {
		 return rank;
		 
	 }
	 
	 public void setRank(String rank) {
		 this.rank = rank;
	 }
	 
	 public String getSuit() {
		 return suit;
		
	 }
	 public void setSuit(String suit) {
		 this.suit=suit;
		 
	 }
	 
	 public String getCardImage() {
		 return CardImage;
	 }
	 
	 public void setCardImage(String CardImage) {
		 this.CardImage = CardImage;
	 }
	 
	 public int getValue() {
		 return value;
	 }
	public void setValue(int value) {
		this.value = value;
	}
	 
	 public String toString() {
		return rank + " of " + suit;
		 
	 }
}

