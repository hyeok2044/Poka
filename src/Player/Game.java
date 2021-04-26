package Player;

public class Game {

	public static void main(String[] args) {
		Deck d = new Deck();
		d.init();
		d.printDeck();
		d.printDeckSimple();
		d.Shuffle();
		d.draw();
		d.draw();
		d.draw();
		d.draw();
		d.draw();
		d.draw();
		d.draw();
		d.draw();
		d.printDrawed();
	}
}
