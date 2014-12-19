package DreamPackage;

import java.awt.Color;

import GUI.GameWindow;
import GUI.MainMenu;
import GUI.SelectLevelWindow;
import GUI.endGame;

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
	 */

	// Level'lari reflection ile cekebiliriz. Bu durumda her Level'in bir
	// class'i olmali -- iPTAL OLDU TAKMAYIN
	public Level[] getLevels() {
		if (levelList != null)
			return levelList;
		Lokum[][] asd = new Lokum[5][5];
		asd[0][0] = new Lokum(Lokum.RED);
		asd[0][1] = new Lokum(Lokum.RED);
		asd[0][2] = new Lokum(Lokum.GREEN);
		asd[0][3] = new Lokum(Lokum.RED);
		asd[0][4] = new Lokum(Lokum.BROWN);
		asd[1][0] = new Lokum(Lokum.YELLOW);
		asd[1][1] = new Lokum(Lokum.YELLOW);
		asd[1][2] = new Lokum(Lokum.RED);
		asd[1][3] = new Lokum(Lokum.GREEN);
		asd[1][4] = new Lokum(Lokum.BROWN);
		asd[2][0] = new Lokum(Lokum.RED);
		asd[2][1] = new Lokum(Lokum.RED);
		asd[2][2] = new Lokum(Lokum.YELLOW);
		asd[2][3] = new Lokum(Lokum.YELLOW);
		asd[2][4] = new Lokum(Lokum.GREEN);
		asd[3][0] = new Lokum(Lokum.YELLOW);
		asd[3][1] = new Lokum(Lokum.YELLOW);
		asd[3][2] = new Lokum(Lokum.GREEN);
		asd[3][3] = new Lokum(Lokum.GREEN);
		asd[3][4] = new Lokum(Lokum.BROWN);
		asd[4][0] = new Lokum(Lokum.RED);
		asd[4][1] = new Lokum(Lokum.RED);
		asd[4][2] = new Lokum(Lokum.YELLOW);
		asd[4][3] = new Lokum(Lokum.YELLOW);
		asd[4][4] = new Lokum(Lokum.GREEN);
		Level level1 = new Level(1, 0, asd, 100000, 1, false);
		levelList = new Level[1];
		levelList[0] = level1;
		// NORMALDE BU OLACAK!!
		levelList = XMLG.getLevelList();
		// NORMALDE BU OLACAK!!
		// System.out.println(GameBoard.getInstance().toString());
		// GameBoard.getInstance().swapLokums(0,2, 1, 2);
		// System.out.println("");
		// System.out.println(GameBoard.getInstance().toString());
		return levelList;
	}

	/**
	 * Starting the game.
	 */
	public void start() {
		MainMenu.getInstance().setVisible(false);
		SelectLevelWindow slw = SelectLevelWindow.getInstance();
		slw.pack();
		slw.setVisible(true);
	}

	public void showMainMenu() {
		endGame.getInstance().setVisible(false);
		GameWindow.getInstance().setVisible(false);
		SelectLevelWindow.getInstance().setVisible(false);
		MainMenu menu = MainMenu.getInstance();
		menu.pack();
		menu.setVisible(true);
	}

	/**
	 * It is for selecting the level.
	 * 
	 * @param l
	 */
	public void selectLevel(int k) {
		Level l = null;
		if (k < levelList.length) {
			l = levelList[k];
		} else {
			l = levelList[levelList.length - 1];
		}
		GameBoard.getInstance().setBoard(l);
		GameState.getInstance().setState(l);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();
		gw.pack();
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
				System.out.println(levelList[1].getLocked());
			}
		} else {

		}
		endGame eg = endGame.getInstance();
		GameWindow.getInstance().setVisible(false);
		eg.setResult(didWin);
		eg.pack();
		eg.setVisible(true);
	}

}
