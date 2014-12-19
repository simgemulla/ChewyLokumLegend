package DreamPackage;

import java.awt.Color;
import java.util.Random;

public class striped extends SpecialLokum {

	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;

	private int orientation;

	/**
	 * Creates a striped Lokum.
	 * 
	 * @param c
	 *            Color of the striped Lokum
	 * @param orientation
	 *            Orientation of the striped Lokum
	 */
	public striped(Color c, int orientation) {
		super(c);
		// Orientation must be 0 or 1 CHECK THIS
		setOrientation(orientation);
		setCreateBonus(120);
		setDeleteBonus(0);
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int a) {
		if (a == HORIZONTAL || a == VERTICAL)
			orientation = a;
		else {
			orientation = HORIZONTAL;
		}
	}

	public striped createCopy() {
		return new striped(this.getColor(), orientation);
	}

	public String toString() {
		if (getColor().equals(Lokum.RED)) {
			return "Rs ";

		} else if (getColor().equals(Lokum.GREEN)) {
			return "Gs ";

		} else if (getColor().equals(Lokum.YELLOW)) {
			return "Ys ";

		} else if (getColor().equals(Lokum.BROWN)) {
			return "Bs ";
		}
		return "Unknown Color";
	}

	// Implement edilecek
	public void specialEffect(int x, int y) {
		Lokum[][] board = GameBoard.getInstance().getLokumList();
		if (orientation == HORIZONTAL) {
			for (int i = 0; i < board.length; i++) {
				GameBoard.getInstance().deleteList.add(new int[] { i, y });
				setDeleteBonus(60 * board.length);
			}
		} else {
			for (int i = 0; i < board[x].length; i++) {
				GameBoard.getInstance().deleteList.add(new int[] { x, i });
				setDeleteBonus(60 * board[x].length);
			}
		}

	}

	public static int getRandomOrientation() {
		int a = (new Random()).nextInt(2);
		if (a == 0)
			return HORIZONTAL;
		return VERTICAL;
	}
}
