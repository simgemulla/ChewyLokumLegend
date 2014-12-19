package DreamPackage;


/**
 * ScoringPolicy is a class which checks and assign score according to special rules.
 * @author macbook
 *
 */
public class ScoringPolicy {

	public static final int TRIPLE_HORIZONTAL = 0;
	public static final int TRIPLE_VERTICAL = 1;
	public static final int QUADRUPLE_HORIZONTAL = 2;
	public static final int QUADRUPLE_VERTICAL = 3;
	public static final int QUINTUPLE_HORIZONTAL = 4;
	public static final int QUINTUPLE_VERTICAL = 5;
	public static final int QUINTUPLE_PLUS = 6;
	public static final int QUINTUPLE_T = 7;
	public static final int QUINTUPLE_T_CW = 8;
	public static final int QUINTUPLE_T_CW2 = 9;
	public static final int QUINTUPLE_T_CW3 = 10;

	private static ScoringPolicy instance;
	int round;

	public ScoringPolicy() {
		round = 0;
	}

	public static ScoringPolicy getInstance() {
		if (instance == null) {
			instance = new ScoringPolicy();
		}

		return instance;
	}
	/**
	 * Checking score and modifies the number of movements.
	 * @requires GameBoard.getInstance().LokumList != null
	 * @ensures GameBoard.getInstance().LokumList != null
	 * @modifies GameState.score GameState.remainingMoves
	 */
	public void checkScore() {
	
		/*
		 * Requires: GameBoard.getInstance().LokumList != null
		 * 
		 * Ensures: GameBoard.getInstance().LokumList != null
		 * 
		 * Modifies: GameState.score GameState.remainingMoves
		 */

	}

	public void incRound() {
		round++;
	}

	public void swapOver() {
		round = 0;
	}

}
