package DreamPackage;

import java.awt.Color;
import java.util.Random;

abstract public class Lokum {

	private Color color;
	public static final Color RED = new Color(204, 0, 0);
	public static final Color GREEN = new Color(76, 153, 0);
	public static final Color BROWN = new Color(102, 51, 0);
	public static final Color YELLOW = new Color(255, 153, 51);

	/**
	 * Creates a Lokum.
	 * 
	 * @param c
	 *            Color of the Lokum
	 */
	public Lokum(Color c) {
		color = c;
	}

	/**
	 * @return Color of the Lokum
	 */
	public Color getColor() {
		return color;
	}

	public boolean repOK() {
		if (color == null)
			return false;

		return true;
	}

	// Only gives normal Lokums!
	/**
	 * 
	 * @return A Lokum with random color
	 */
	public static Lokum getRandomLokum() {
		int a = (new Random()).nextInt(4);
		switch (a) {
		case 0:
			return new NormalLokum(RED);
		case 1:
			return new NormalLokum(GREEN);
		case 2:
			return new NormalLokum(BROWN);
		default:
			return new NormalLokum(YELLOW);
		}
	}

	/**
	 * @return A Lokum whose properties are the same as this
	 */
	abstract public Lokum createCopy();

	public String toString() {
		if (color.equals(RED)) {
			return "R ";

		} else if (color.equals(GREEN)) {
			return "G ";

		} else if (color.equals(YELLOW)) {
			return "Y ";

		} else if (color.equals(BROWN)) {
			return "B ";
		}
		return "Unknown Color";
	}

	/**
	 * Called before a Lokum gets deleted.
	 */
	abstract public int destroy(int x, int y);
	
	public boolean isTimedLokum() {
		return false;
	}

}
