package DreamPackage;

import GUI.GameWindow;

/**
 * GameState is a class which save the current situation of the game.
 * 
 * @author macbook
 * 
 */
public class GameState {

	int score;
	int remainingMoves;
	Level selectedLevel;
	private static GameState instance;

	private GameState() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return The instance of GameState
	 */
	public static GameState getInstance() {
		if (instance == null) {
			instance = new GameState();
		}

		return instance;
	}

	/**
	 * Sets the state according to the Level l.
	 * 
	 * @param l
	 *            The selected level
	 */
	public void setState(Level l) {
		if (selectedLevel != null)
			selectedLevel.quitLevel();
		selectedLevel = l.deepClone();
		remainingMoves = selectedLevel.getMoveCount();
		score = 0;
		GameWindow.getInstance().setScore(score);
		GameWindow.getInstance().setRemainingMoves(remainingMoves);
		GameWindow.getInstance().setScoreNeeded(selectedLevel.getScoreNeeded());
		GameWindow.getInstance().setSpecialSwapLeft(
				selectedLevel.getSpecialSwapCount());
		selectedLevel.startLevel();
	}

	/**
	 * Increases the total score by newScore.
	 * 
	 * @param newScore
	 *            Score to be added to the total score
	 * @requires newScore > 0
	 * @modifies GameState.score
	 */
	public void updateScore(int newScore) {
		score += newScore;
		GameWindow.getInstance().setScore(score);
		if (score >= selectedLevel.getScoreNeeded()) {
			LokumGame.getInstance().gameOver(true);
		}
	}

	/**
	 * Decrements the number of moves left.
	 * 
	 * @requires GameState.remainingMoves >= 0
	 * @modifies GameState.remainingMoves
	 * @ensures GameState.remainingMoves >= 0
	 */
	public void decMoves() {
		if (remainingMoves > 1) {
			remainingMoves--;
			GameWindow.getInstance().setRemainingMoves(remainingMoves);
		} else {

			LokumGame.getInstance().gameOver(false);
		}

	}

	/**
	 * Saves the current state of the game.
	 */
	public void saveState() {
		XMLG.SaveState();
	}

	/**
	 * @return Currently selected level
	 */
	public Level getSelectedLevel() {
		return selectedLevel;
	}

	/**
	 * @return Current total score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @return Currently remaining moves
	 */
	public int getRemainingMoves() {
		return remainingMoves;
	}
}
