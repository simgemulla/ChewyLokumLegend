package GUI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DreamPackage.GameState;
import DreamPackage.LokumGame;

@SuppressWarnings("serial")
public class EndGame extends JFrame {

	private static EndGame instance;
	private JLabel messageLabel;
	JButton returnButton;
	JButton nextButton;
	JButton retryButton;

	private EndGame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}

		});

		messageLabel = new JLabel();
		returnButton = new JButton("Main Menu");
		returnButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				GameWindow.getInstance().setEnabled(true);
				LokumGame.getInstance().showMainMenu();
				EndGame.getInstance().dispose();
			}
		});
		nextButton = new JButton("Next Level");
		nextButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				GameWindow.getInstance().setEnabled(true);
				LokumGame.getInstance()
						.selectLevel(
								GameState.getInstance().getSelectedLevel()
										.getLevelID() + 1);
				EndGame.getInstance().dispose();
			}
		});
		retryButton = new JButton("Retry Level");
		retryButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				GameWindow.getInstance().setEnabled(true);
				LokumGame.getInstance()
						.selectLevel(
								GameState.getInstance().getSelectedLevel()
										.getLevelID());
				EndGame.getInstance().dispose();
			}
		});

		setLayout(new GridLayout(4, 1, 0, 4));
		add(messageLabel);
		add(returnButton);
		add(nextButton);
		add(retryButton);
	}

	public static EndGame getInstance() {
		if (instance == null)
			instance = new EndGame();
		return instance;
	}

	/**
	 * Shows the result of the game according to didWin
	 * 
	 * @param didWin
	 *            The result of the game.
	 */
	public void setResult(boolean didWin) {
		if (didWin) {
			messageLabel.setText("YOU ROCK! "
					+ GameState.getInstance().getScore()
					+ " points, unbelievable!");
			returnButton.setVisible(true);
			nextButton.setVisible(true);
			retryButton.setVisible(true);
		} else {
			messageLabel.setText("YOU SUCK! You only got "
					+ GameState.getInstance().getScore() + " points!");
			returnButton.setVisible(true);
			nextButton.setVisible(false);
			retryButton.setVisible(true);
		}
	}

}
