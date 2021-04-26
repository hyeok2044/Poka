package Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	List<Card> deck;
	List<Card> draws;

	public Deck() {
		deck = new ArrayList<Card>();
		draws = new ArrayList<Card>();
	}

	public void init() {
		deck = new ArrayList<Card>();
		// shape
		for (int i = 1; i <= 4; i++) {
			// number
			for (int j = 1; j <= 13; j++) {
				deck.add(new Card(i, j));
			}
		}

		System.out.println("init finished.");
	}

	public void printDeck() {
		System.out.print("[");
		for (int i = 0; i < deck.size(); i++) {
			System.out.print(deck.get(i).toString());
			if (i == deck.size() - 1) {

			} else {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	public void printDeckSimple() {
		System.out.print("[");
		for (int i = 0; i < deck.size(); i++) {
			System.out.print(deck.get(i).toStringSimple());
			if (i == deck.size() - 1) {

			} else {
				System.out.print(", ");
			}
		}
		System.out.println("]");
	}

	public void Shuffle() {
		Collections.shuffle(deck);
		System.out.println("shuffled");
	}

	public Card draw() {
		if (deck.size() == 0) {
			System.out.println("Game Finished! (No cards)");
			return new Card(0, 0);
		} else {
			Card currentDraw = deck.remove(0);
			draws.add(currentDraw);
			return currentDraw;
		}
	}

	public String printDrawed() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < draws.size(); i++) {
			sb.append(i + 1);
			sb.append(": ");
			sb.append(draws.get(i).toStringSimple());
			if (i == draws.size() - 1) {

			} else {
				sb.append(", ");
			}
		}
		sb.append("]");
		System.out.println(sb.toString());
		return sb.toString();

	}
}
