package DreamPackage;

import GUI.GameWindow;

abstract public class Level {

	private int moveCount;
	private int highScore;
	private Lokum[][] initialBoard;
	private int scoreNeeded;
	private boolean locked;
	private int levelID;
	private int specialSwapCount;

	/**
	 * Creates a Level.
	 * 
	 * @param moveCount
	 *            Maximum number of moves allowed to pass the level
	 * @param highScore
	 *            Highest score achieved in this level
	 * @param initialBoard
	 *            Starting board for the level
	 * @param scoreNeeded
	 *            Minimum score needed to pass the level
	 * @param levelID
	 *            ID of the level
	 * @param locked
	 *            Has the scoreNeeded been achieved
	 * @param specialSwapCount
	 *            The number of special swaps allowed in this level
	 */
	public Level(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked, int specialSwapCount) {
		this.moveCount = moveCount;
		this.highScore = highScore;
		this.initialBoard = initialBoard;
		this.scoreNeeded = scoreNeeded;
		this.levelID = levelID;
		this.locked = locked;
		this.setSpecialSwapCount(specialSwapCount);
	}

	/**
	 * @return Maximum number of moves allowed to pass the level
	 */
	public int getMoveCount() {
		return moveCount;
	}

	/**
	 * @return Highest score achieved in this level
	 */
	public int getHighScore() {
		return highScore;
	}

	/**
	 * @return Minimum score needed to pass the level
	 */
	public int getScoreNeeded() {
		return scoreNeeded;
	}

	/**
	 * @return Starting board for the level
	 */
	public Lokum[][] getInitialBoard() {
		return initialBoard;
	}

	/**
	 * @return ID of the level
	 */
	public int getLevelID() {
		return levelID;
	}

	/**
	 * @return Has the scoreNeeded been achieved
	 */
	public boolean getLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            Has the scoreNeeded been achieved
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return The number of special swaps left in this level
	 */
	public int getSpecialSwapCount() {
		return specialSwapCount;
	}

	private void setSpecialSwapCount(int specialSwapCount) {
		this.specialSwapCount = specialSwapCount;
	}

	/**
	 * @param amount
	 *            Decrease amount
	 */
	public void decreaseSpecialSwapCount(int amount) {
		if (specialSwapCount - amount >= 0 && amount > 0)
			specialSwapCount -= amount;
		GameWindow.getInstance().setSpecialSwapLeft(specialSwapCount);
	}

	public boolean repOK() {
		if (moveCount < 1)
			return false;

		if (highScore < 0)
			return false;

		if (scoreNeeded < 0)
			return false;

		if (initialBoard == null)
			return false;

		for (int i = 0; i < initialBoard.length; i++) {
			for (int j = 0; j < initialBoard[0].length; j++) {
				if (initialBoard[i][j] == null)
					return false;
			}
		}

		return true;
	}

	/**
	 * @return A deep-clone of Level.initialBoard
	 */
	public Lokum[][] copyBoard() {
		Lokum[][] board = new Lokum[initialBoard.length][];
		for (int i = 0; i < initialBoard.length; i++) {
			board[i] = new Lokum[initialBoard[i].length];
			for (int j = 0; j < initialBoard[i].length; j++) {
				board[i][j] = initialBoard[i][j].createCopy();
			}
		}
		return board;
	}

	/**
	 * Called when a level is selected
	 */
	abstract public void startLevel();

	/**
	 * Called when a level ends
	 */
	abstract public void quitLevel();

	/**
	 * @return A deep-clone of the level
	 */
	abstract public Level deepClone();
}
