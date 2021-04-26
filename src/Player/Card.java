package Player;

class Card {

	int shape;
	int number;

	public Card(int shape, int number) {
		this.shape = shape;
		this.number = number;
	}

	public String returnShape() {
		if (shape == 1) {
			return "Heart";
		} else if (shape == 2) {
			return "Clover";
		} else if (shape == 3) {
			return "Diamond";
		} else if (shape == 4) {
			return "Spade";
		} else {
			return "NULL";
		}

	}

	public String returnNumber() {
		if (number == 1) {
			return "Ace";
		}
		if (number > 1 && number < 11) {
			return number + "";
		} else if (number == 11) {
			return "Jack";
		} else if (number == 12) {
			return "Queen";
		} else if (number == 13) {
			return "King";
		} else {
			return "NULL";
		}

	}

	public String returnShapeSimple() {
		if (shape == 1) {
			return "♥";
		} else if (shape == 2) {
			return "♣";
		} else if (shape == 3) {
			return "◆";
		} else if (shape == 4) {
			return "♠";
		} else {
			return "X";
		}

	}

	public String returnNumberSimple() {
		if (number == 1) {
			return "A";
		}
		if (number > 1 && number < 11) {
			return number + "";
		} else if (number == 11) {
			return "J";
		} else if (number == 12) {
			return "Q";
		} else if (number == 13) {
			return "K";
		} else {
			return "X";
		}

	}

	public String toString() {
		return returnShape() + " " + returnNumber();
	}

	public String toStringSimple() {
		return returnShapeSimple() + " " + returnNumberSimple();
	}
}
