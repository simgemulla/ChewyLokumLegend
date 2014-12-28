import DreamPackage.LokumGame;

;
/**
 * Dream Class is the main class of the game.
 * 
 * @author macbook
 * 
 */
public class DreamClass {

	/**
	 * Main method.
	 */
	public static void main(String[] args) {
		LokumGame theGame = LokumGame.getInstance();
		theGame.getLevels();
		theGame.showMainMenu();
	}

}
