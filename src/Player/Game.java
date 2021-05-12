package Player;

import java.util.HashMap;
import java.util.List;

/**
 * @author hyeok2044
 *
 */
public class Game {

	Player p1, p2;
	Deck d1;
	static HashMap<Integer, String> tierToString;

	public void announceVictory(int n) {
		if (n == 0) {
			System.out.println("Draw");
		} else if (n == 1) {
			System.out.println("player 1 Won");
		} else if (n == 2) {
			System.out.println("player 2 Won");
		} else {
			System.out.println("Error");
		}
	}

	// -1: err, 0: draw. 1: 1, 2: 2
	public int determineVictory(Player p1, Player p2) {
		if (p1.deck.size() == 5 && p2.deck.size() == 5) {
			int[] p1Result = determineTier(p1);
			int[] p2Result = determineTier(p2);
			if (p1Result[0] == p2Result[0]) {
				if (p1Result[1] > p2Result[1]) {
					return 1;
				} else if (p1Result[1] == p2Result[1]) {
					return 0;
				} else {
					return 2;
				}
			} else if (p1Result[0] > p2Result[0]) {
				return 1;
			} else {
				return 2;
			}
		} else {
			System.out.println("size of d1: " + p1.deck.size() + " size of d2: " + p2.deck.size());
			return -1;
		}
	}

	public void tierDebug(Player p) {
		if (p.deck.size() == 5) {
			int[] tierDetermined = determineTier(p);
			System.out.print(tierToString.get(tierDetermined[0]) + " ");
			int cardPart = tierDetermined[1];
			int firstCard = cardPart / 100;
			int firstCardNumber = firstCard / 4;
			int firstCardShape = firstCard % 4;
			int secondCard = cardPart % 100;
			int secondCardNumber = secondCard / 4;
			int secondCardShape = secondCard % 4;
			Card c1 = new Card(firstCardShape + 1, firstCardNumber);
			System.out.print(c1.toStringSimple() + " ");
			if (secondCard == 0) {
				System.out.println();
			} else {
				Card c2 = new Card(secondCardShape + 1, secondCardNumber);
				System.out.println(c2.toStringSimple() + " ");
			}
		} else {
			System.out.println("size of d1: " + p.deck.size());
		}
	}

	/**
	 * @param d Arraylist with card
	 * @return {0, clover, heart, diamond, spade}
	 */
	public int[] shapeDetermination(Player p) {
		List<Card> d = p.deck;
		int[] arr = new int[5];
		for (int i = 0; i < d.size(); i++) {
			arr[d.get(i).shape]++;
		}
		return arr;
	}

	/**
	 * @param arr array from shapeDetermination
	 * @return max val
	 */
	public int shapeDeterminationMax(Player p) {
		int[] arr = shapeDetermination(p);
		int maximum = 0;
		for (int i = 1; i <= 4; i++) {
			maximum = Integer.max(arr[i], maximum);
		}
		return maximum;
	}

	public int[] numberDetermination(Player p) {
		List<Card> d = p.deck;
		int[] arr = new int[13 + 1]; // A~K : 13 cards
		for (Card c : d) {
			arr[c.number]++;
		}
		return arr;
	}

	public int numberDeterminationMax(Player p) {
		int[] arr = numberDetermination(p);
		int maximum = 0;
		for (int i = 1; i <= 13; i++) {
			maximum = Integer.max(arr[i], maximum);
		}
		return maximum;
	}

	public int[] determineTier(Player p) {
		List<Card> d = p.deck;
		p.cardSort();
		String[] tier = new String[2];
		int[] tierReal = new int[2];
		if (d.size() != 5) {
			tier[0] = "null";
			tier[1] = "null";
			tier[0] = "0";
			tier[1] = "0";
			System.out.println("size of d1: " + d.size());
			return tierReal;
		} else {
			if (shapeDeterminationMax(p) == 5) {
				int[] arr = numberDetermination(p);
				boolean straightFlush = false;
				for (int i = 1; i <= 13; i++) {
					boolean check = true;
					for (int j = 0; j < 5; j++) {
						if (arr[(i + j - 1) % 13 + 1] != 1) {
							check = false;
							break;
						}
					}
					if (check) {
						straightFlush = true;
						break;
					}
				}
				if (straightFlush) {
					if (d.get(0).number == 1) {
						tier[0] = "Royal Straight Flush";
						tier[1] = d.get(0).toStringSimple();
						tierReal[0] = 10;
						tierReal[1] = (d.get(0).number * 4 + d.get(0).shape - 1) * 100;
					} else {
						tier[0] = "Straight Flush";
						tier[1] = d.get(4).toStringSimple();
						tierReal[0] = 9;
						tierReal[1] = (d.get(4).number * 4 + d.get(4).shape - 1) * 100;
					}
				} else {
					tier[0] = "Flush";
					tier[1] = d.get(4).toStringSimple();
					tierReal[0] = 6;
					tierReal[1] = (d.get(4).number * 4 + d.get(4).shape - 1) * 100;
				}
			} else {
				if (numberDeterminationMax(p) == 4) {
					int[] arr = numberDetermination(p);
					int temp = 0;
					for (int i = 1; i <= 13; i++) {
						if (arr[i] == 4) {
							temp = i;
						}
					}
					tier[0] = "Four of a Kind";
					tier[1] = temp + "";
					tierReal[0] = 8;
					tierReal[1] = (d.get(4).number * 4 + d.get(4).shape - 1) * 100;
				} else if (numberDeterminationMax(p) == 3) {
					int[] arr = numberDetermination(p);
					int temp1 = 0;
					boolean fullHouse = false;
					for (int i = 1; i <= 13; i++) {
						if (arr[i] == 3) {
							temp1 = i;
						}
						if (arr[i] == 2) {
							fullHouse = true;
						}
					}

					if (fullHouse) {
						tier[0] = "Full House";
						tierReal[0] = 7;
						Card c1 = d.get(0);
						Card c2 = d.get(0);
						for (int i = 1; i <= 4; i++) {
							if (d.get(i).number == temp1) {
								if (c1.shape < d.get(i).shape) {
									c1 = d.get(i);
								}
							} else {
								if (c2.shape < d.get(i).shape) {
									c2 = d.get(i);
								}
							}
						}
						tier[1] = c1.toStringSimple();
						tierReal[1] = (c1.number * 4 + c1.shape - 1) * 100 + (c2.number * 4 + c2.shape - 1);
					} else {
						tier[0] = "Three of a Kind";
						tierReal[0] = 4;
						Card c1 = d.get(0);
						for (int i = 1; i <= 4; i++) {
							if (d.get(i).number == temp1) {
								if (c1.shape < d.get(i).shape) {
									c1 = d.get(i);
								}
							}
						}
						tier[1] = c1.toStringSimple();
						tierReal[1] = (c1.number * 4 + c1.shape - 1) * 100;
					}

				} else if (numberDeterminationMax(p) == 2) {
					int[] arr = numberDetermination(p);
					int temp1 = 0;
					int temp2 = 0;
					int count = 0;

					for (int i = 1; i <= 13; i++) {
						if (arr[i] == 2) {
							count++;
							if (count == 1) {
								temp1 = i;
							} else if (count == 2) {
								temp2 = i;
							}
						}
					}
					if (count == 2) {
						Card c1 = d.get(0);
						Card c2 = d.get(0);
						for (int i = 0; i < 4; i++) {
							if (d.get(i).number == temp1) {
								if (c1.shape < d.get(i).shape) {
									c1 = d.get(i);
								}
							} else if (d.get(i).number == temp2) {
								if (c2.shape < d.get(i).shape) {
									c2 = d.get(i);
								}
							}
						}
						tier[0] = "Two Pairs";
						tier[1] = c1.toStringSimple() + " " + c2.toStringSimple();
						tierReal[1] = (c1.number * 4 + c1.shape - 1) * 100 + c2.number * 4 + c2.shape - 1;
						tierReal[0] = 3;
					} else {
						tier[0] = "One Pair";
						tierReal[0] = 2;
						Card c1 = d.get(0);
						for (int i = 0; i < 4; i++) {
							if (d.get(i).number == temp1) {
								if (c1.shape < d.get(i).shape) {
									c1 = d.get(i);
								}
							}
						}
						tier[1] = c1.toStringSimple();
						tierReal[1] = (c1.number * 4 + c1.shape - 1) * 100;
					}

				} else {
					tier[0] = "High Card";
					tierReal[0] = 1;
					Card c = d.get(0);
					for (int i = 0; i < 5; i++) {
						if (d.get(i).number > d.get(0).number) {
							c = d.get(i);
						}
					}
					tier[1] = c.toStringSimple();
					tierReal[1] = (c.number * 4 + c.shape - 1) * 100;
				}
			}
			return tierReal;
		}
	}

	public Game(int status) {
		tierToString = new HashMap<Integer, String>();
		tierToString.put(10, "Royal Straight Flush");
		tierToString.put(9, "Straight Flush");
		tierToString.put(8, "Four of a Kind");
		tierToString.put(7, "Full House");
		tierToString.put(6, "Flush");
		tierToString.put(5, "Straight");
		tierToString.put(4, "Three of a Kind");
		tierToString.put(3, "Two Pairs");
		tierToString.put(2, "One Pair");
		tierToString.put(1, "High Card");
		tierToString.put(0, "Error");

		if (status == 727) {
			p1 = new Player();
			p1.draw(new Card(1, 11));
			p1.draw(new Card(3, 11));
			p1.draw(new Card(4, 9));
			p1.draw(new Card(3, 10));
			p1.draw(new Card(2, 10));
			p1.lookAtDeck();
//			String[] result = determineTier(p1);
//			System.out.println(result[0] + " " + result[1]);
		} else {
			p1 = new Player();
			p2 = new Player();
			d1 = new Deck();

			// starting phase
			d1.init();
			d1.Shuffle();

			// drawing phase: 3 times per player.

			p1.draw(d1.draw());
			p2.draw(d1.draw());

			p1.draw(d1.draw());
			p2.draw(d1.draw());

			p1.draw(d1.draw());
			p2.draw(d1.draw());
			p1.draw(d1.draw());
			p2.draw(d1.draw());

			p1.draw(d1.draw());
			p2.draw(d1.draw());
			p1.lookAtDeck();
			p2.lookAtDeck();
			int stat = determineVictory(p1, p2);
			tierDebug(p1);
			tierDebug(p2);
			announceVictory(stat);

//			d1.printDeckSimple();

//			System.out.println(p1Result[0] + " " + p1Result[1] + " " + p2Result[0] + " " + p2Result[1]);

		}

	}

	public static void main(String[] args) {
		System.out.println("start");
		for (int i = 0; i < 10; i++) {
			Game g = new Game(0);
		}
	}
}
