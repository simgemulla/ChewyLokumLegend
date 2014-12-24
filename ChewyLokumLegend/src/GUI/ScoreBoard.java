package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DreamPackage.GameState;
import DreamPackage.LokumGame;

/**
 * This class basically displays the score and remaining moves.
 * 
 */
@SuppressWarnings("serial")
public class ScoreBoard extends JPanel {

	JLabel scoreLabel;
	JLabel remMovesLabel;
	JLabel scoreNeededLabel;
	JLabel specialSwapLabel;
	JLabel timeLabel;
	JPanel scoreTable;

	public ScoreBoard() {
		setLayout(new GridLayout(5, 0));
		scoreLabel = new JLabel();
		remMovesLabel = new JLabel();
		scoreNeededLabel = new JLabel();
		specialSwapLabel = new JLabel();
		timeLabel = new JLabel();
		scoreTable = new JPanel();
		JButton resButton = new JButton("Restart Level");
		resButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				LokumGame.getInstance()
						.selectLevel(
								GameState.getInstance().getSelectedLevel()
										.getLevelID());
			}
		});

		JButton mainButton = new JButton("Main Menu");
		mainButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				LokumGame.getInstance().showMainMenu();
			}
		});
		scoreTable.setLayout(new BoxLayout(scoreTable, BoxLayout.Y_AXIS));
		scoreTable.add(scoreLabel);
		scoreTable.add(remMovesLabel);
		scoreTable.add(scoreNeededLabel);
		scoreTable.add(specialSwapLabel);
		scoreTable.add(timeLabel);
		add(scoreTable);
		add(resButton);
		add(mainButton);
	}

	public void setScore(int x) {
		scoreLabel.setText(String.valueOf("Score: " + x));
		// revalidate();
		// repaint();
	}

	public void setRemMoves(int x) {
		remMovesLabel.setText(String.valueOf("Remaining Moves: " + x));
		// revalidate();
		// repaint();
	}

	public void setScoreNeeded(int x) {
		scoreNeededLabel.setText(String.valueOf("Score Needed: " + x));
	}

	public void setRemainingTime(int x) {
		timeLabel.setText(String.valueOf("Time Left: " + x));
	}

	public void setSpecialSwapLeft(int x) {
		specialSwapLabel.setText(String.valueOf("Special Swaps: " + x));
	}
}
