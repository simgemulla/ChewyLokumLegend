package DreamPackage;

import java.awt.Color;

public class obstacle extends Lokum {

	private static Color obstacleColor = Color.BLACK;

	public obstacle() {
		super(obstacleColor);
	}

	public String toString() {
		return "O ";
	}

	@Override
	public Lokum createCopy() {
		return new obstacle();
	}

	@Override
	// Destroying an obstacle? :D
	public int destroy(int x, int y) {
		return 0;
	}

}
