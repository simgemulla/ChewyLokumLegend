//Test GitHub
import DreamPackage.LokumGame;
import GUI.MainMenu;

;
/**
 * Dream Class is a main class of the game.
 * 
 * @author macbook
 * 
 */
public class DreamClass {

	public DreamClass() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LokumGame theGame = LokumGame.getInstance();
		theGame.getLevels();
		theGame.showMainMenu();
		// GameWindow gw = GameWindow.getInstance();
		// gw.paintBoard();
		// gw.pack();
		// gw.setVisible(true);
	}

}
