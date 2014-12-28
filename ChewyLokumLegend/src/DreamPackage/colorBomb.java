package DreamPackage;

import java.awt.Color;

public class ColorBomb extends SpecialLokum {
	public static final Color colorBombColor = Color.DARK_GRAY;

	/**
	 * Creates a colorBomb.
	 */
	public ColorBomb() {
		super(colorBombColor);
		setCreateBonus(200);
		setDeleteBonus(0);
	}

	public ColorBomb createCopy() {
		return new ColorBomb();
	}

	public String toString() {
		return "X ";
	}

	public void specialEffect(int x, int y) {
	}
}
