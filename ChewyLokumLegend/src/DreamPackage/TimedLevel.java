package DreamPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import GUI.GameWindow;

public class TimedLevel extends Level {

	private int timeLimit;
	private int remainingTime;
	private Timer timer;
	private ActionListener actList = new ActionListener() {

		public void actionPerformed(ActionEvent arg0) {
			if (((TimedLevel) GameState.getInstance().getSelectedLevel()).getScoreNeeded() > (GameState.getInstance().getScore() )) {
				

				decreaseRemainingTime(1);
			}
			
		}
	};

	public TimedLevel(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked, int specialSwapCount,
			int timeLimit) {
		super(moveCount, highScore, initialBoard, scoreNeeded, levelID, locked,
				specialSwapCount);
		setTimeLimit(timeLimit);
	}

	/**
	 * @param tL
	 *            The time limit
	 */
	public void setTimeLimit(int tL) {
		if (tL > 0) {
			timeLimit = tL;
		}
	}

	/**
	 * @return The time limit
	 */
	public int getTimeLimit() {
		return timeLimit;
	}

	/**
	 * @return Remaining time until the level ends
	 */
	public int getRemainingTime() {
		return remainingTime;
	}

	/**
	 * @param amount
	 *            The increase amount
	 */
	public void increaseRemainingTime(int amount) {
		this.remainingTime += amount;
		GameWindow.getInstance().setRemainingTime(remainingTime);
	}

	/**
	 * @param amount
	 *            The decrease amount
	 */
	public void decreaseRemainingTime(int amount) {
		if (remainingTime - amount > 0) {
			remainingTime -= amount;
		} else {
			timer.stop();
			LokumGame.getInstance().gameOver(false);
		}
		GameWindow.getInstance().setRemainingTime(remainingTime);
	}

	@Override
	public void startLevel() {
		remainingTime = timeLimit;
		GameWindow.getInstance().setRemainingTime(remainingTime);
		timer = new Timer(1000, actList);
		timer.start();
	}

	@Override
	public void quitLevel() {
		timer.stop();
	}

	@Override
	public Level deepClone() {
		return new TimedLevel(this.getMoveCount(), this.getHighScore(),
				this.copyBoard(), this.getScoreNeeded(), this.getLevelID(),
				this.getLocked(), this.getSpecialSwapCount(), timeLimit);
	}

}
