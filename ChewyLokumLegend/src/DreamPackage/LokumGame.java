package DreamPackage;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.SelectLevelWindow;
import GUI.EndGame;

/**
 * LokumGame is one of the main classes. It has the main methods such as start,
 * swap, selectLevel, save.
 * 
 * @author macbook
 * 
 */
public class LokumGame {

	Level[] levelList;
	public static LokumGame instance;

	private LokumGame() {
		// TODO Auto-generated constructor stub
		levelList = getLevels();
	}

	public static LokumGame getInstance() {
		if (instance == null)
			instance = new LokumGame();
		return instance;
	}

	/**
	 * Gets the all levels containing locked and unlocked levels.
	 * 
	 * @requires levelList != null
	 * @ensures levelList != null <br>
	 *          levelList.size() > 0 <br>
	 *          Locked and unlocked levels will be updated according to the
	 *          highscore and listed.<br>
	 *          levelList.get(i).locked = false or levelList.get(i).locked =
	 *          true
	 * @modifies levelList
	 * @return A list of levels
	 */
	public Level[] getLevels() {
		if (levelList != null)
			return levelList;

		levelList = XMLG.getLevelList();
		return levelList;
	}

	/**
	 * Starting the game.
	 */
	public void start() {
		MainMenu.getInstance().setVisible(false);
		SelectLevelWindow slw = SelectLevelWindow.getInstance();
		slw.pack();
		slw.setLocationRelativeTo(null);
		slw.setVisible(true);
	}

	public void showMainMenu() {
		EndGame.getInstance().setVisible(false);
		GameWindow.getInstance().setVisible(false);
		SelectLevelWindow.getInstance().setVisible(false);
		MainMenu menu = MainMenu.getInstance();
		menu.pack();
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}

	/**
	 * It is for selecting the level.
	 * 
	 * @param k
	 *            The ID of the selected level.
	 */
	public void selectLevel(int k) {
		Level l = null;
		if (k < levelList.length) {
			l = levelList[k].deepClone();
		} else {
			l = levelList[levelList.length - 1].deepClone();
		}
		GameBoard.getInstance().setBoard(l);
		GameState.getInstance().setState(l);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();
		gw.pack();
		gw.setLocationRelativeTo(null);
		gw.setVisible(true);
	}

	/**
	 * @param x1
	 *            The x-coordinate of the first Lokum.
	 * @param y1
	 *            The y-coordinate of the first Lokum.
	 * @param x2
	 *            The x-coordinate of the second Lokum.
	 * @param y2
	 *            The y-coordinate of the second Lokum.
	 */
	public void swap(int x1, int y1, int x2, int y2) {
		GameBoard.getInstance().swapLokums(x1, y1, x2, y2);
	}

	public void save() {
		GameState.getInstance().saveState();
	}

	public void load() {
		Level l = XMLG.getSavedState();
		GameBoard.getInstance().setBoard(l);
		GameState.getInstance().setState(l);
		GameState.getInstance().updateScore(l.getHighScore());
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();
		gw.pack();
		gw.setLocationRelativeTo(null);
		gw.setVisible(true);
		MainMenu.getInstance().setVisible(false);
	}

	public void gameOver(boolean didWin) {
		if (didWin) {
			GameBoard.getInstance().wonTheGame();
			int levelID = GameState.getInstance().getSelectedLevel()
					.getLevelID();
			if (levelID < levelList.length - 1) {
				levelList[levelID + 1].setLocked(false);
				XMLG.updateLocked(levelList[levelID + 1]);
				getLevels();
				SelectLevelWindow.getInstance().refresh();
			}
		} else {

		}
		EndGame eg = EndGame.getInstance();
		GameWindow.getInstance().setEnabled(false);
		eg.setResult(didWin);
		eg.pack();
		eg.setLocationRelativeTo(null);
		eg.setVisible(true);
	}

}
