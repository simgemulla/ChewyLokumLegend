package DreamPackage;

import java.awt.Color;

public class wrapped extends SpecialLokum {

	/**
	 * Creates a wrapped Lokum.
	 * 
	 * @param c
	 *            Color of the wrapped Lokum
	 */
	public wrapped(Color c) {
		super(c);
		setCreateBonus(200);
		setDeleteBonus(1080);
	}

	public wrapped createCopy() {
		return new wrapped(this.getColor());
	}

	public String toString() {
		if (getColor().equals(Lokum.RED)) {
			return "Rw ";

		} else if (getColor().equals(Lokum.GREEN)) {
			return "Gw ";

		} else if (getColor().equals(Lokum.YELLOW)) {
			return "Yw ";

		} else if (getColor().equals(Lokum.BROWN)) {
			return "Bw ";
		}
		return "Unknown Color";
	}

	public void specialEffect(int x, int y) {
	}

}
