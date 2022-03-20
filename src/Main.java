import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

// card images from wikipedia: https://en.wikipedia.org/wiki/Standard_52-card_deck
// HOW TO PLAY "WAR" :
// A deck of playing cards is split in half among two players
// With the cards face down in front of the players, each player
// will turn the top card face up in front of them.
// The player with the higher card will take each of the cards in front of them.
// If the two cards are of the same rank, is is "war".
// Each player will place another card on top and the player with the
// higher rank will take all 4 cards from the center pile.
// This continues until one player has the whole deck of cards.
// And that person is the winner.
// In this program you are playing against the computer. 
// Instructions from: https://bicyclecards.com/how-to-play/war/
// My Edit to the Game: If 150 turns are played before there is a winner, the game ends.

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	
	private ImageIcon playerCard; 
	private ImageIcon cpuCard;
	private ImageIcon backOfCard;
	private JLabel playerCardLabel;
	private JLabel cpuCardLabel;
	private JLabel cpuBackOfCardLabel;
	private JButton playerBackOfCardButton;
	private JLabel cpuCardCounter;
	private JLabel playerCardCounter;
	private JLabel ifTie; 
	private JLabel turnsLeft;
	private int counter = 150; // counter for the number of turns left in a round
	JPanel button = new JPanel();
	
	// array that creates the number of buttons in the grid
	JButton[] buttons = new JButton[4];
	JPanel[] panel = new JPanel[3];
	
	//Generate and shuffle a deck of cards
	static ArrayList<Card> deck = new ArrayList<Card>();
    // Empty array for players cards
	static ArrayList<Card> playerDeck = new ArrayList<Card>();
	static ArrayList<Card> cpuDeck = new ArrayList<Card>();
	// Empty arraylist for tied cards to go into, then come out on the next turn
    static ArrayList<Card> tieDeck = new ArrayList<Card>();
			
			
	public static void main(String[] args) throws IOException {
		
		// instructions at the start of the game
		JOptionPane.showMessageDialog(null, "HOW TO PLAY \"WAR\" :\n"
				+ "// A deck of playing cards is split in half among two players\n"
				+ "// With the cards face down in front of the players, each player\n"
				+ "// will turn the top card face up in front of them.\n"
				+ "// The player with the higher card will take each of the cards in front of them.\n"
				+ "// If the two cards are of the same rank, it is \"war\".\n"
				+ "// Each player will place another card on top and the player with the\n"
				+ "// higher rank will take all 4 cards from the center pile.\n"
				+ "// This continues until one player has the whole deck of cards.\n"
				+ "// And that person is the winner.\n"
				+ "// In this program you are playing against the computer. \n"
				+ "// Instructions from: https://bicyclecards.com/how-to-play/war/\n"
				+ "// My Edit to the Game: If 150 turns are played before there is a winner, the game ends.\n"
				+ "", "Instructions", JOptionPane.INFORMATION_MESSAGE);
		
		
		String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
		String[] ranks = {"Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
		for(String suit: suits) {
		  for (String rank: ranks) {
			 Card card = new Card(rank,suit);
			 deck.add(card);
		  }
		}
		
		//Shuffle the deck and get the deck size
		Collections.shuffle(deck);
		int size = deck.size();
		//Give the player the first half of the deck and the cpu the second
		for(int i = 0; i < size/2; i++) {
			playerDeck.add(deck.get(i));
		}
		for (int i = size/2; i < size; i++) {
			cpuDeck.add(deck.get(i));
		}
		
		//Generate the gui and pass in the deck
		Main gui = new Main(playerDeck, cpuDeck);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setVisible(true);
		gui.pack();
	}
	
	//The GUI 
	Main(ArrayList<Card> playerDeck, ArrayList<Card> cpuDeck) {
		
		setLayout(new GridLayout(8,8));
		Font labelFont = new Font("Verdana", Font.PLAIN, 18);
		
		cpuCardCounter = new JLabel();
		playerCardCounter = new JLabel();
		cpuCardCounter.setFont(labelFont);
		cpuCardCounter.setHorizontalAlignment(JLabel.CENTER);
		cpuCardCounter.setText(String.valueOf(cpuDeck.size()));
		
		ifTie = new JLabel();
		ifTie.setFont(labelFont);
		ifTie.setHorizontalAlignment(JLabel.CENTER);
		
		playerCardCounter = new JLabel();		
		playerCard = new ImageIcon(getClass().getResource(playerDeck.get(0).getCardImage()));
		cpuCard = new ImageIcon(getClass().getResource(cpuDeck.get(0).getCardImage()));
		backOfCard = new ImageIcon(getClass().getResource("backOfCard.png"));
		playerCardLabel = new JLabel(playerCard);
		cpuCardLabel = new JLabel(cpuCard);
		playerBackOfCardButton = new JButton(backOfCard);
		playerBackOfCardButton.addActionListener(this);
		cpuBackOfCardLabel = new JLabel(backOfCard);
		
		playerCardCounter.setFont(labelFont);
		playerCardCounter.setHorizontalAlignment(JLabel.CENTER);
		playerCardCounter.setText(String.valueOf(playerDeck.size()));
		
		turnsLeft = new JLabel();
		turnsLeft.setFont(labelFont);
		turnsLeft.setHorizontalAlignment(JLabel.CENTER);
		turnsLeft.setText("Turns Remaining: " + counter);
	
	    add(turnsLeft);
		add(cpuCardCounter);
		add(cpuBackOfCardLabel);
		add(cpuCardLabel);
		add(ifTie);
		add(playerCardLabel);
		add(playerBackOfCardButton);	 
		add(playerCardCounter);	
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		counter--; // decrements after each turn
		turnsLeft.setText("Turns Remaining: " + counter);
		
		if (counter == 0) {
			// display popup message when the max number of turns is reached
			JOptionPane.showMessageDialog(null, "Game Over: Max turns reached. ");
		} 
		else if(cpuDeck.size() == 0) {
			// popup message when player wins
			JOptionPane.showMessageDialog(null, "You Won!!!");
		}
		else if (playerDeck.size() == 0) {
			// popup message when player losses/ cpu wins
			JOptionPane.showMessageDialog(null, "You Lost :(");
		} else {
		
		String winner = CompareCards(cpuDeck.get(0).getValue(), playerDeck.get(0).getValue());
		ifTie.setText("");
		
		// the scenarios in which a player or cpu wins or a tie
		if(winner == "player") {
			// if the user gets a higher card, both cards shown go in to playerDeck arraylist
			playerDeck.add(cpuDeck.get(0));
			cpuDeck.remove(0);
			playerDeck.add(playerDeck.get(0));
			playerDeck.remove(0);
			playerDeck.addAll(tieDeck);
			tieDeck.removeAll(tieDeck);
			
		}else if(winner == "cpu") {
			// if the cpu gets a higher card, both cards shown go in to the cpuDeck arraylist
			cpuDeck.add(playerDeck.get(0));
			playerDeck.remove(0);
			cpuDeck.add(cpuDeck.get(0));
			cpuDeck.remove(0);
			cpuDeck.addAll(tieDeck);
			tieDeck.removeAll(tieDeck);
			
		} else { // else there is a tie
			  
			// if there is a tie, put both players current card in the tieDeck array list,
			// then the on the next play, the player with the higher card takes cards that are shown and cards in tieDeck
			tieDeck.add(playerDeck.get(0));
			tieDeck.add(cpuDeck.get(0));
			playerDeck.remove(0); 
			cpuDeck.remove(0);
	   }
				
		// Updating the card images through the deck of cards
      
		playerCard = new ImageIcon(getClass().getResource(playerDeck.get(0).getCardImage()));
		playerCardLabel.setIcon(playerCard);
		
		cpuCard = new ImageIcon(getClass().getResource(cpuDeck.get(0).getCardImage()));
		cpuCardLabel.setIcon(cpuCard);
        
		if(playerDeck.get(0).getValue() == cpuDeck.get(0).getValue()) {
			ifTie.setText("TIE");
		}
		//Update the player and cpu deck counter
		cpuCardCounter.setText(String.valueOf(cpuDeck.size()-1));
		playerCardCounter.setText(String.valueOf(playerDeck.size()-1));
        
		
		
		}
		
	}
	
	
	
	// game scenarios
	public String CompareCards(int cpuCardValue, int playerCardValue) {
		
		if(cpuCardValue == playerCardValue)   {
			return "tie";
		} else if (cpuCardValue > playerCardValue) {
			return "cpu";
		} else if (cpuCardValue < playerCardValue) {
			return "player";
		}
		
		return "none";
			
	
	
		
	}
	
	

}
