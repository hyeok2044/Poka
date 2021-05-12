package Player;

import java.util.ArrayList;
import java.util.List;

public class Player {

	List<Card> deck;

	public Player() {
		deck = new ArrayList<Card>();
	}

	public Card draw(Card c) {
		deck.add(c);
		return c;
	}

	public String printDeck() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < deck.size(); i++) {
			sb.append(deck.get(i).toString());
			if (i == deck.size() - 1) {

			} else {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public String printDeckSimple() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < deck.size(); i++) {
			sb.append(deck.get(i).toStringSimple());
			if (i == deck.size() - 1) {

			} else {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public String lookAtDeck() {
		cardSort();
		System.out.println(printDeckSimple());
		return printDeckSimple();
	}

	public void cardSort() {
		quickSort(deck, 0, deck.size() - 1);
	}

	static void quickSort(List<Card> deck, int l, int r) {
		int left = l;
		int right = r;
		int pivot = deck.get((l + r) / 2).getValue();

		do {
			while (deck.get(left).getValue() < pivot)
				left++;
			while (deck.get(right).getValue() > pivot)
				right--;
			if (left <= right) {
				Card temp = deck.get(left);
				deck.set(left, deck.get(right));
				deck.set(right, temp);
				left++;
				right--;
			}
		} while (left <= right);

		if (l < right)
			quickSort(deck, l, right);
		if (r > left)
			quickSort(deck, left, r);
	}
}
