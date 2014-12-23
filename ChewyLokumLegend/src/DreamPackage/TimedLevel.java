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
			decreaseRemainingTime(1);
			GameWindow.getInstance().setRemainingTime(remainingTime);
		}
	};

	public TimedLevel(int moveCount, int highScore, Lokum[][] initialBoard,
			int scoreNeeded, int levelID, boolean locked, int timeLimit) {
		super(moveCount, highScore, initialBoard, scoreNeeded, levelID, locked);
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
	}

	/**
	 * @param amount
	 *            The decrease amount
	 */
	public void decreaseRemainingTime(int amount) {
		if (remainingTime - amount > 0) {
			remainingTime -= amount;
		} else {
			LokumGame.getInstance().gameOver(false);
		}
	}

	@Override
	public void startLevel() {
		remainingTime = timeLimit;
		timer = new Timer(1000, actList);
		timer.start();
	}

}
