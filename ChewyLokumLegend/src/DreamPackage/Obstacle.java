package DreamPackage;

import java.awt.Color;

public class Obstacle extends Lokum {

	private static Color obstacleColor = Color.BLACK;

	public Obstacle() {
		super(obstacleColor);
	}

	public String toString() {
		return "O ";
	}

	@Override
	public Lokum createCopy() {
		return new Obstacle();
	}

	@Override
	// Destroying an obstacle? :D
	public int destroy(int x, int y) {
		return 0;
	}

}
