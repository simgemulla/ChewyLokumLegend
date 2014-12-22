package DreamPackage;

public class TimedLevel extends Level {

	private int timeLimit;

	public TimedLevel(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked, int timeLimit) {
		super(moveCount, highScore, initialBoard, scoreNeeded, levelID, locked);
		setTimeLimit(timeLimit);
	}

	public void setTimeLimit(int tL) {
		if (tL > 0) {
			timeLimit = tL;
		}
	}

	public int getTimeLimit() {
		return timeLimit;
	}
	
	public boolean isTimedLevel() {
		return true;
	}

}
