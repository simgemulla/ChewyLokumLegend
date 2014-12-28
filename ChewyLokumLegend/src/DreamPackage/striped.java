package DreamPackage;

import java.awt.Color;
import java.util.Random;

public class Striped extends SpecialLokum {

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
	public Striped(Color c, int orientation) {
		super(c);
		// Orientation must be 0 or 1 CHECK THIS
		setOrientation(orientation);
		setCreateBonus(120);
		setDeleteBonus(0);
	}

	/**
	 * @return The orientation of the striped lokum
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @param a
	 *            The orientation of the striped lokum
	 */
	public void setOrientation(int a) {
		if (a == HORIZONTAL || a == VERTICAL)
			orientation = a;
		else {
			orientation = HORIZONTAL;
		}
	}

	public Striped createCopy() {
		return new Striped(this.getColor(), orientation);
	}

	public String toString() {
		if (getColor().equals(Lokum.RED)) {
			if (orientation == HORIZONTAL) {
				return "RsH ";
			} else {
				return "RsV ";
			}

		} else if (getColor().equals(Lokum.GREEN)) {
			if (orientation == HORIZONTAL) {
				return "GsH ";
			} else {
				return "GsV ";
			}

		} else if (getColor().equals(Lokum.YELLOW)) {
			if (orientation == HORIZONTAL) {
				return "YsH ";
			} else {
				return "YsV ";
			}

		} else if (getColor().equals(Lokum.BROWN)) {
			if (orientation == HORIZONTAL) {
				return "BsH ";
			} else {
				return "BsV ";
			}
		}
		return "Unknown Color";
	}

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

	/**
	 * @return striped.HORIZONTAL or striped.VERTICAL
	 */
	public static int getRandomOrientation() {
		int a = (new Random()).nextInt(2);
		if (a == 0)
			return HORIZONTAL;
		return VERTICAL;
	}
}
