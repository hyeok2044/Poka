package Player;

import java.util.ArrayList;
import java.util.List;

public class Player {

	List<Card> deck;

	public Player() {
		deck = new ArrayList<Card>();
	}

	public Card Draw(Card c) {
		deck.add(c);
		return c;
	}

}
