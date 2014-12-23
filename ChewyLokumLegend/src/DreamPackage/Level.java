package DreamPackage;

abstract public class Level {

	private int moveCount;
	private int highScore;
	private Lokum[][] initialBoard;
	private int scoreNeeded;
	private boolean locked;
	private int levelID;

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
	 */
	public Level(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked) {
		this.moveCount = moveCount;
		this.highScore = highScore;
		this.initialBoard = initialBoard;
		this.scoreNeeded = scoreNeeded;
		this.levelID = levelID;
		this.locked = locked;
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

	public int getLevelID() {
		return levelID;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
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

	abstract public void startLevel();
}
