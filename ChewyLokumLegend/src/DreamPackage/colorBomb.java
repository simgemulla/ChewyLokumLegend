package DreamPackage;

import java.awt.Color;

public class colorBomb extends SpecialLokum {
	public static final Color colorBombColor = Color.DARK_GRAY;

	/**
	 * Creates a colorBomb.
	 */
	public colorBomb() {
		super(colorBombColor);
		setCreateBonus(200);
		setDeleteBonus(0);
	}

	public colorBomb createCopy() {
		return new colorBomb();
	}

	public String toString() {
		return "X";
	}

	public void specialEffect(int x, int y) {
	}

}
