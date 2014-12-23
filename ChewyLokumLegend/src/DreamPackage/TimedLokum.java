package DreamPackage;

import java.awt.Color;

public class TimedLokum extends Lokum {
	public static final int bonusTime = 5;

	public TimedLokum(Color c) {
		super(c);
	}

	public String toString() {
		if (this.getColor().equals(RED)) {
			return "tR ";

		} else if (this.getColor().equals(GREEN)) {
			return "tG ";

		} else if (this.getColor().equals(YELLOW)) {
			return "tY ";

		} else if (this.getColor().equals(BROWN)) {
			return "tB ";
		}
		return "Unknown Color";
	}

	@Override
	public Lokum createCopy() {
		return new TimedLokum(this.getColor());
	}

	@Override
	public int destroy(int x, int y) {
		((TimedLevel) GameState.getInstance().getSelectedLevel())
				.increaseRemainingTime(bonusTime);
		return 0;
	}

	public int getBonusTime() {
		return bonusTime;
	}

	public boolean isTimedLokum() {
		return true;
	}

}
