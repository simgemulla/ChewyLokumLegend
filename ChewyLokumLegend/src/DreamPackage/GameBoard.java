package DreamPackage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import GUI.GameWindow;

/**
 * GameBoard is one of the main classes which has the main methods.
 * 
 * 
 */
public class GameBoard {

	Color obstacleColor = Color.MAGENTA;

	Lokum[][] lokumList;
	ArrayList<int[]> deleteList;
	ArrayList<Object[]> createList;

	private static GameBoard instance;

	private GameBoard() {
		lokumList = new Lokum[1][1];
		deleteList = new ArrayList<int[]>();
		createList = new ArrayList<Object[]>();
	}

	public static GameBoard getInstance() {
		if (instance == null) {
			instance = new GameBoard();
		}

		return instance;
	}

	/**
	 * Sets the board according to Level l.
	 * 
	 * @requires l.repOK() == true
	 * @ensures GameBoard.getInstance().lokumList = l.getInitialBoard() <br>
	 *          this.repOK() == true
	 * @modifies GameBoard.getInstance().lokumList
	 */
	public void setBoard(Level l) {
		lokumList = l.copyBoard();
	}

	/**
	 * Swaps the Lokums at (x1, y1) and (x2, y2).
	 * 
	 * @requires this.repOK() == true <br>
	 *           0 <= x1 < lokumList.length <br>
	 *           0 <= x2 < lokumList.length <br>
	 *           0 <= y1 < lokumList[0].length <br>
	 *           0 <= y2 < lokumList[0].length <br>
	 *           (x1 == x2 && y1 == y2) != true
	 * @ensures this.repOK() == true
	 * @modifies lokumList[x1][y1] <br>
	 *           lokumList[x2][y2]
	 * @param x1
	 *            The x-coordinate of the first Lokum.
	 * @param y1
	 *            The y-coordinate of the first Lokum.
	 * @param x2
	 *            The x-coordinate of the second Lokum.
	 * @param y2
	 *            The y-coordinate of the second Lokum.
	 */
	public void swapLokums(int x1, int y1, int x2, int y2) {
		// THERE ARE FOUR CASES:
		// A) Swapped lokums are both special lokums

		// B) One of the swapped lokums is color bomb

		// C1) The normal stuff (swap and check for score)

		// C2) Swapping lokums cannot generate any groups of three or more
		// lokums (swap back)

		// SWAP EDiLEBiLiR Mi CHECK EDiLECEK!
		if (!(x1 == x2 - 1 || x1 == x2 || x1 == x2 + 1)
				&& !(y1 == y2 - 1 || y1 == y2 || y1 == y2 + 1))
			return;

		// OBSTACLE CHECK
		/*
		 * if (lokumList[x1][y1] instanceof obstacle || lokumList[x2][y2]
		 * instanceof obstacle) return;
		 */
		// OBSTACLE CHECK
		int scoreEarned = 0;

		Lokum lokum1 = lokumList[x1][y1].createCopy();
		lokumList[x1][y1] = lokumList[x2][y2].createCopy();
		lokumList[x2][y2] = lokum1.createCopy();
		lokum1 = lokumList[x1][y1];
		Lokum lokum2 = lokumList[x2][y2];
		/*
		 * int temp = x1; x1 = x2; x2 = temp; temp = y1; y1 = y2; y2 = temp;
		 */
		if (lokum1 instanceof SpecialLokum && lokum2 instanceof SpecialLokum) {
			//
			// HAS TO CHECK FOR:
			// 1. striped + striped
			// 2. striped + wrapped
			// 3. striped + colorBomb
			// 4. wrapped + wrapped
			// 5. wrapped + colorBomb
			// 6. colorBomb + colorBomb
			//

			// ------ 1
			if (lokum1 instanceof striped && lokum2 instanceof striped) {
				striped l1 = (striped) lokum1;
				striped l2 = (striped) lokum2;
				if (l1.getOrientation() == l2.getOrientation()) {
					l1.setOrientation(striped.HORIZONTAL);
					l2.setOrientation(striped.VERTICAL);
				}
				lokumList[x1][y1] = l1;
				lokumList[x2][y2] = l2;
				deleteList.add(new int[] { x1, y1 });
				deleteList.add(new int[] { x2, y2 });
			}
			// ------ 2
			if (lokum1 instanceof striped && lokum2 instanceof wrapped) {
				// System deletes 3 horizontal and 3 vertical lines including
				// swapped special lokums rows and columns. Merkezi wrapped'in
				// swap sonrasi yeri alalim
				for (int i = x2 - 1; i <= x2 + 1; i++) {
					if (i < 0 || i >= lokumList.length)
						continue;
					for (int j = 0; j < lokumList[i].length; j++) {
						deleteList.add(new int[] { i, j });
					}
				}

				for (int i = 0; i < lokumList.length; i++) {
					for (int j = y2 - 1; j <= y2 + 1; j++) {
						if (j < 0 || j >= lokumList[i].length)
							continue;
						deleteList.add(new int[] { i, j });
					}
				}
			}
			if (lokum1 instanceof wrapped && lokum2 instanceof striped) {
				for (int i = x1 - 1; i <= x1 + 1; i++) {
					if (i < 0 || i >= lokumList.length)
						continue;
					for (int j = 0; j < lokumList[i].length; j++) {
						deleteList.add(new int[] { i, j });
					}
				}

				for (int i = 0; i < lokumList.length; i++) {
					for (int j = y1 - 1; j <= y1 + 1; j++) {
						if (j < 0 || j >= lokumList[i].length)
							continue;
						deleteList.add(new int[] { i, j });
					}
				}
			}
			// ------ 3
			if (lokum1 instanceof striped && lokum2 instanceof colorBomb) {
				// System changes all lokums with the same color of the striped
				// lokum to striped lokums (random orientation) and deletes them
				// (DONT FORGET THE SPEC. EFFECT!).
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum1.getColor())) {
							lokumList[i][j] = new striped(lokum1.getColor(),
									striped.getRandomOrientation());
							deleteList.add(new int[] { i, j });
						}
					}
				}
				deleteList.add(new int[] { x2, y2 });
			}

			if (lokum1 instanceof colorBomb && lokum2 instanceof striped) {
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum2.getColor())) {
							lokumList[i][j] = new striped(lokum2.getColor(),
									striped.getRandomOrientation());
							deleteList.add(new int[] { i, j });
						}
					}
				}
				deleteList.add(new int[] { x1, y1 });
			}
			// ------ 4
			if (lokum1 instanceof wrapped && lokum2 instanceof wrapped) {
				// System increases the score by 3600 points.
				scoreEarned += 3600;
				deleteList.add(new int[] { x1, y1 });
				deleteList.add(new int[] { x2, y2 });
			}
			// ------ 5
			if (lokum1 instanceof wrapped && lokum2 instanceof colorBomb) {
				// System deletes all lokums with the same color as the wrapped
				// lokum and another random color.
				Color c = Lokum.getRandomLokum().getColor();
				while (c.equals(lokum1.getColor())) {
					c = Lokum.getRandomLokum().getColor();
				}
				int count = 0;
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum1.getColor())
								|| lokumList[i][j].getColor().equals(c)) {
							count++;
							deleteList.add(new int[] { i, j });
						}
					}
				}
				scoreEarned += count * count * 60;
				deleteList.add(new int[] { x2, y2 });
			}

			if (lokum1 instanceof colorBomb && lokum2 instanceof wrapped) {
				Color c = Lokum.getRandomLokum().getColor();
				while (c.equals(lokum1.getColor())) {
					c = Lokum.getRandomLokum().getColor();
				}
				int count = 0;
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum1.getColor())
								|| lokumList[i][j].getColor().equals(c)) {
							count++;
							deleteList.add(new int[] { i, j });
						}
					}
				}
				scoreEarned += count * count * 60;
				deleteList.add(new int[] { x1, y1 });
			}
			// ------ 6
			if (lokum1 instanceof colorBomb && lokum2 instanceof colorBomb) {
				// System increases the score by n^2 x 100 points where ??n is
				// the number of lokums on the entire board. Deletes all lokums
				// on board!
				scoreEarned += lokumList.length * lokumList[0].length;
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						deleteList.add(new int[] { i, j });
					}
				}
			}

		} else {
			//
			// HAS TO CHECK FOR:
			// 1. normal + striped (DOES IT?)
			// 2. normal + wrapped (DOES IT?)
			// 3. normal + colorBomb
			// 4. normal + normal

			// ------ 1
			if (lokum1 instanceof striped) {
				// No idea what to do!
			}
			if (lokum2 instanceof striped) {
				// No idea what to do!
			}

			// ------ 2
			if (lokum1 instanceof wrapped) {
				// No idea what to do!
			}
			if (lokum2 instanceof wrapped) {
				// No idea what to do!
			}

			// ------ 3
			if (lokum1 instanceof colorBomb) {
				// 1. All lokums with the same color as the colored swapped
				// lokum are deleted and score is increased by n^2 x 60 points,
				// where is the number of lokums which are deleted.
				int count = 0;
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum2.getColor())) {
							count++;
							deleteList.add(new int[] { i, j });
						}
					}
				}
				deleteList.add(new int[] { x1, y1 });
				scoreEarned += count * count * 60;
			}
			if (lokum2 instanceof colorBomb) {
				// 1. All lokums with the same color as the colored swapped
				// lokum are deleted and score is increased by n^2 x 60 points,
				// where is the number of lokums which are deleted.
				int count = 0;
				for (int i = 0; i < lokumList.length; i++) {
					for (int j = 0; j < lokumList[i].length; j++) {
						if (lokumList[i][j].getColor()
								.equals(lokum1.getColor())) {
							count++;
							deleteList.add(new int[] { i, j });
						}
					}
				}
				deleteList.add(new int[] { x2, y2 });
				scoreEarned += count * count * 60;
			}

			// ------ 4
			if (!(lokum1 instanceof SpecialLokum)
					&& !(lokum2 instanceof SpecialLokum)) {
				// Group of three or more lokums of the same color are deleted
				// and score is updated. SpecialLokums are created if necessary
				// checkGroups();
			}

		}

		System.out.println("--- New Swap ---");
		int count = checkGroups();

		if (lokumList[x1][y1] instanceof obstacle
				|| lokumList[x2][y2] instanceof obstacle) {

			if (GameState.getInstance().getSelectedLevel()
					.getSpecialSwapCount() > 0) {

				GameState.getInstance().getSelectedLevel()
						.decreaseSpecialSwapCount(1);
				System.out.println("Special swap is allowed.");

				if ((count == 0 && deleteList.size() == 0)) {
					System.out.println(toString());
					GameWindow.getInstance().paintBoard();
					return;
				}

			} else {
				Lokum a = lokumList[x1][y1].createCopy();
				lokumList[x1][y1] = lokumList[x2][y2].createCopy();
				lokumList[x2][y2] = a.createCopy();
				return;
			}
		} else {
			if ((count == 0 && deleteList.size() == 0)) {
				if (GameState.getInstance().getSelectedLevel()
						.getSpecialSwapCount() > 0) {
					GameState.getInstance().getSelectedLevel()
							.decreaseSpecialSwapCount(1);
					System.out.println("Special swap is allowed.");
					System.out.println(toString());
					GameWindow.getInstance().paintBoard();
				} else {
					Lokum a = lokumList[x1][y1].createCopy();
					lokumList[x1][y1] = lokumList[x2][y2].createCopy();
					lokumList[x2][y2] = a.createCopy();
				}
				return;
			}
		}

		count = 1; // To enter while
		int k = 1;
		while (count != 0) {
			int a = deleteAndFill();
			scoreEarned += k * a;
			k++;
			count = checkGroups();
		}

		GameState.getInstance().updateScore(scoreEarned);
		GameState.getInstance().decMoves();
	}

	public int checkGroups() {
		int quins = checkQuins();
		int quads = checkQuads();
		int triples = checkTriples();
		String s = "5li :" + quins + ", 4lu: " + quads + ", 3lu: " + triples;
		System.out.println(s);
		return quins + quads + triples;
	}

	/**
	 * Deletes groups of 3 or more Lokums, and fills the places of deleted
	 * Lokums.
	 * 
	 * @requires this.repOK() == true <br>
	 *           deleteList!=null<br>
	 *           createList!=null
	 * @ensures this.repOK() == true <br>
	 *          deleteList != null <br>
	 *          createList!=null<br>
	 *          deleteList.size() == 0<br>
	 *          createList.size()==0
	 * @modifies lokumList <br>
	 *           deleteList<br>
	 *           createList
	 * @return Points earned by deleting and filling
	 */
	public int deleteAndFill() {
		System.out.println(" . Del&Fill . BEFORE DELETE, lokums to delete: "
				+ deleteList.size());
		System.out.println(toString());
		GameWindow.getInstance().markDeleteList();

		// Burada bir süre bekletebilmemiz gerekiyor.

		int newScore = 0;
		Lokum[][] newList = new Lokum[lokumList.length][lokumList[0].length];
		for (int i = 0; i < lokumList.length; i++) {
			for (int j = 0; j < lokumList[i].length; j++) {
				newList[i][j] = lokumList[i][j].createCopy();
			}
		}

		for (int i = deleteList.size() - 1; i >= 0; i = deleteList.size() - 1) {
			int[] a = deleteList.get(i);
			int x = a[0];
			int y = a[1];
			Lokum l = newList[x][y];
			if (l == null || l instanceof obstacle) {
				deleteList.remove(i);
				continue;
			}

			newScore += l.destroy(x, y);

			newList[x][y] = null;

			deleteList.remove(i);
		}

		for (int i = createList.size() - 1; i >= 0; i = createList.size() - 1) {
			Object[] a = createList.get(i);
			int x = (Integer) a[0];
			int y = (Integer) a[1];
			SpecialLokum l = (SpecialLokum) a[2];
			if (l == null) {
				newScore += 60;
			} else {
				newScore += l.getCreateBonus();
			}
			newList[x][y] = l;
			createList.remove(i);
		}

		// Moving down the lokums
		// OBSTACLE CASE MUST BE IMPLEMENTED!!!
		for (int i = 0; i < newList.length; i++) {
			for (int j = newList[0].length - 1; j > 0; j--) {
				if (newList[i][j] == null) {
					for (int x = j - 1; x >= 0; x--) {
						if (newList[i][x] != null) {
							if (newList[i][x] instanceof obstacle) {
								if (x == 0) {
									if (newList[i][x + 1] == null)
										newList[i][x + 1] = Lokum
												.getRandomLokum();
								} else {
									if (newList[i][x - 1] != null) {
										newList[i][x + 1] = newList[i][x - 1]
												.createCopy();
										newList[i][x - 1] = null;
									}
								}
							} else {
								newList[i][x + 1] = newList[i][x].createCopy();
								newList[i][x] = null;
							}
						} else {
							if (x == 0) {
								newList[i][x] = Lokum.getRandomLokum();
							}
						}
					}
					j++;
				}
			}
		}

		for (int i = 0; i < newList.length; i++) {
			if (newList[i][0] == null)
				newList[i][0] = Lokum.getRandomLokum();
		}

		// Filling empty spaces
		// GEREK KALMAMIs OLMASI LAZIM
		/*
		 * for (int j = 0; j < newList[0].length; j++) { for (int i = 0; i <
		 * newList.length; i++) { if (newList[i][j] == null) { newList[i][j] =
		 * Lokum.getRandomLokum(); } } }
		 */

		lokumList = newList;
		GameWindow.getInstance().paintBoard();

		System.out.println(" . Del&Fill . END");
		System.out.println(toString());

		return newScore;
	}

	public void wonTheGame() {
		int movesLeft = GameState.getInstance().getRemainingMoves();
		Random rand = new Random();
		for (int i = 0; i < movesLeft; i++) {
			int x = rand.nextInt(lokumList.length);
			int y = rand.nextInt(lokumList[x].length);
			lokumList[x][y] = new striped(lokumList[x][y].getColor(),
					striped.getRandomOrientation());
			deleteList.add(new int[] { x, y });
		}
		int scoreEarned = 0;
		int count = 1; // To enter while
		int k = 1;
		while (count != 0) {
			int a = deleteAndFill();
			scoreEarned += k * a;
			k++;
			count = checkGroups();
		}
		GameState.getInstance().score += scoreEarned;
		GameWindow.getInstance().setScore(GameState.getInstance().score);
	}

	public Lokum[][] getLokumList() {
		return lokumList;
	}

	public ArrayList<int[]> getDeleteList() {
		return deleteList;
	}

	/**
	 * Checks for triples. Then adds those Lokums to deleteList, and adds the
	 * specialLokum to be created to createList.
	 * 
	 * @return Number of triples found.
	 */
	public int checkTriples() {
		int count = createList.size();
		int yMax = lokumList[0].length;
		int xMax = lokumList.length;
		for (int k = 0; k < xMax; k++) {
			for (int l = 0; l < yMax; l++) {

				Color c = lokumList[k][l].getColor();

				if (k > 0
						&& k < xMax - 1
						&& c.equals(lokumList[k - 1][l].getColor())
						&& c.equals(lokumList[k + 1][l].getColor())
						&& !(k > 1 && c.equals(lokumList[k - 2][l].getColor()))
						&& !(k < xMax - 2 && c.equals(lokumList[k + 2][l]
								.getColor()))
						&& !((l > 0 && c.equals(lokumList[k][l - 1].getColor())) && (l < yMax - 1 && c
								.equals(lokumList[k][l + 1].getColor()))) // Plus-Shape
																			// olmasin
																			// diye
						&& !(l < yMax - 2
								&& c.equals(lokumList[k][l + 1].getColor()) && c
									.equals(lokumList[k][l + 2].getColor())) // T
						// olmasin
						// diye
						&& !(l > 1 && c.equals(lokumList[k][l - 1].getColor()) && c
								.equals(lokumList[k][l - 2].getColor()))// T_CW2
						// olmasin
						// diye
						&& !((l > 0 && k < xMax - 1 && c
								.equals(lokumList[k + 1][l - 1].getColor())) && (l < yMax - 1
								&& k < xMax - 1 && c
									.equals(lokumList[k + 1][l + 1].getColor()))) // T_CW
																					// olmasin
																					// diye
						&& !((l > 0 && k > 0 && c
								.equals(lokumList[k - 1][l - 1].getColor())) && (l < yMax - 1
								&& k > 0 && c.equals(lokumList[k - 1][l + 1]
								.getColor()))) // T_CW3
												// olmasin
												// diye

				) {
					deleteList.add(new int[] { k - 1, l });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k + 1, l });
					createList.add(new Object[] { k, l, null });

				}
				if (l > 0
						&& l < yMax - 1
						&& c.equals(lokumList[k][l - 1].getColor())
						&& c.equals(lokumList[k][l + 1].getColor())
						&& !(l > 1 && c.equals(lokumList[k][l - 2].getColor()))
						&& !(l < yMax - 2 && c.equals(lokumList[k][l + 2]
								.getColor()))
						&& !((k > 0 && c.equals(lokumList[k - 1][l].getColor())) && (k < xMax - 1 && c
								.equals(lokumList[k + 1][l].getColor()))) // Plus-Shape
																			// olmasin
																			// diye
						&& !(k < xMax - 2 && c.equals(lokumList) && c
								.equals(lokumList[k + 2][l].getColor())) // T_CW3
						// olmasin
						// diye
						&& !(k > 1 && c.equals(lokumList[k - 1][l].getColor()) && c
								.equals(lokumList[k - 2][l].getColor()))// T_CW
						// olmasin
						// diye
						&& !((k > 0 && l < yMax - 1 && c
								.equals(lokumList[k - 1][l + 1].getColor())) && (k < xMax - 1
								&& l < yMax - 1 && c
									.equals(lokumList[k + 1][l + 1].getColor()))) // T_CW2
																					// olmasin
																					// diye
						&& !((l > 0 && k > 0 && c
								.equals(lokumList[k - 1][l - 1].getColor())) && (k < xMax - 1
								&& l > 0 && c.equals(lokumList[k + 1][l - 1]
								.getColor()))) // T
												// olmasin
												// diye
				) {
					deleteList.add(new int[] { k, l - 1 });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k, l + 1 });
					createList.add(new Object[] { k, l, null });
				}
			}
		}
		return createList.size() - count;
	}

	/**
	 * Checks for quadruples. Then adds those Lokums to deleteList, and adds the
	 * specialLokum to be created to createList.
	 * 
	 * @return Number of quadruples found.
	 */
	public int checkQuads() {
		int count = createList.size();
		int yMax = lokumList[0].length;
		int xMax = lokumList.length;

		for (int k = 0; k < xMax; k++) {
			for (int l = 0; l < yMax; l++) {

				Color c = lokumList[k][l].getColor();

				if (k < xMax - 3
						&& c.equals(lokumList[k + 1][l].getColor())
						&& c.equals(lokumList[k + 2][l].getColor())
						&& c.equals(lokumList[k + 3][l].getColor())
						&& !(k > 0 && c.equals(lokumList[k - 1][l].getColor()))
						&& !(k < xMax - 4 && c.equals(lokumList[k + 4][l]
								.getColor()))) {
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k + 1, l });
					deleteList.add(new int[] { k + 2, l });
					deleteList.add(new int[] { k + 3, l });
					createList.add(new Object[] { k + 1, l,
							new striped(c, striped.HORIZONTAL) }); // SIKINTILI
				}
				if (l < yMax - 3
						&& c.equals(lokumList[k][l + 1].getColor())
						&& c.equals(lokumList[k][l + 2].getColor())
						&& c.equals(lokumList[k][l + 3].getColor())
						&& !(l > 0 && c.equals(lokumList[k][l - 1].getColor()))
						&& !(l < yMax - 4 && c.equals(lokumList[k][l + 4]
								.getColor()))) {
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k, l + 1 });
					deleteList.add(new int[] { k, l + 2 });
					deleteList.add(new int[] { k, l + 3 });
					createList.add(new Object[] { k, l + 1,
							new striped(c, striped.VERTICAL) }); // SIKINTILI
				}
			}
		}
		return createList.size() - count;
	}

	/**
	 * Checks for quintuples. Then adds those Lokums to deleteList, and adds the
	 * specialLokum to be created to createList.
	 * 
	 * @return Number of quintuples found.
	 */
	public int checkQuins() {
		int count = createList.size();
		int yMax = lokumList[0].length;
		int xMax = lokumList.length;

		for (int k = 0; k < xMax; k++) {
			for (int l = 0; l < yMax; l++) {

				Color c = lokumList[k][l].getColor();
				// Check for Plus-Shape
				if (k > 0 && k < xMax - 1 && l > 0 && l < yMax - 1
						&& c.equals(lokumList[k + 1][l].getColor())
						&& c.equals(lokumList[k - 1][l].getColor())
						&& c.equals(lokumList[k][l + 1].getColor())
						&& c.equals(lokumList[k][l - 1].getColor())) {
					deleteList.add(new int[] { k + 1, l });
					deleteList.add(new int[] { k - 1, l });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k, l + 1 });
					deleteList.add(new int[] { k, l - 1 });
					createList.add(new Object[] { k, l, new wrapped(c) });
				}
				// Check for T
				if (k > 0 && k < xMax - 1 && l > 0 && l < yMax - 1
						&& c.equals(lokumList[k][l - 1].getColor())
						&& c.equals(lokumList[k][l + 1].getColor())
						&& c.equals(lokumList[k - 1][l - 1].getColor())
						&& c.equals(lokumList[k + 1][l - 1].getColor())) {
					deleteList.add(new int[] { k, l - 1 });
					deleteList.add(new int[] { k, l + 1 });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k - 1, l - 1 });
					deleteList.add(new int[] { k + 1, l - 1 });
					createList.add(new Object[] { k, l - 1, new wrapped(c) });
				}
				// Check for T_CW
				if (k > 0 && k < xMax - 1 && l > 0 && l < yMax - 1
						&& c.equals(lokumList[k - 1][l].getColor())
						&& c.equals(lokumList[k + 1][l].getColor())
						&& c.equals(lokumList[k + 1][l - 1].getColor())
						&& c.equals(lokumList[k + 1][l + 1].getColor())) {
					deleteList.add(new int[] { k - 1, l });
					deleteList.add(new int[] { k + 1, l });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k + 1, l - 1 });
					deleteList.add(new int[] { k + 1, l + 1 });
					createList.add(new Object[] { k + 1, l, new wrapped(c) });
				}
				// Check for T_CW2
				if (k > 0 && k < xMax - 1 && l > 0 && l < yMax - 1
						&& c.equals(lokumList[k][l - 1].getColor())
						&& c.equals(lokumList[k][l + 1].getColor())
						&& c.equals(lokumList[k - 1][l + 1].getColor())
						&& c.equals(lokumList[k + 1][l + 1].getColor())) {
					deleteList.add(new int[] { k, l - 1 });
					deleteList.add(new int[] { k, l + 1 });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k - 1, l + 1 });
					deleteList.add(new int[] { k + 1, l + 1 });
					createList.add(new Object[] { k, l + 1, new wrapped(c) });
				}
				// Check for T_CW3
				if (k > 0 && k < xMax - 1 && l > 0 && l < yMax - 1
						&& c.equals(lokumList[k - 1][l].getColor())
						&& c.equals(lokumList[k + 1][l].getColor())
						&& c.equals(lokumList[k - 1][l - 1].getColor())
						&& c.equals(lokumList[k - 1][l + 1].getColor())) {
					deleteList.add(new int[] { k - 1, l });
					deleteList.add(new int[] { k + 1, l });
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k - 1, l - 1 });
					deleteList.add(new int[] { k - 1, l + 1 });
					createList.add(new Object[] { k - 1, l, new wrapped(c) });
				}
				// Check for Horizontal
				if (k < xMax - 4 && c.equals(lokumList[k + 1][l].getColor())
						&& c.equals(lokumList[k + 2][l].getColor())
						&& c.equals(lokumList[k + 3][l].getColor())
						&& c.equals(lokumList[k + 4][l].getColor())) {
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k + 1, l });
					deleteList.add(new int[] { k + 2, l });
					deleteList.add(new int[] { k + 3, l });
					deleteList.add(new int[] { k + 4, l });
					createList.add(new Object[] { k + 2, l, new colorBomb() });
				}
				// Check for Vertical
				if (l < yMax - 4 && c.equals(lokumList[k][l + 1].getColor())
						&& c.equals(lokumList[k][l + 2].getColor())
						&& c.equals(lokumList[k][l + 3].getColor())
						&& c.equals(lokumList[k][l + 4].getColor())) {
					deleteList.add(new int[] { k, l });
					deleteList.add(new int[] { k, l + 1 });
					deleteList.add(new int[] { k, l + 2 });
					deleteList.add(new int[] { k, l + 3 });
					deleteList.add(new int[] { k, l + 4 });
					createList.add(new Object[] { k, l + 2, new colorBomb() });
				}
			}
		}
		return createList.size() - count;
	}

	public Lokum[][] copyLokumList() {
		Lokum[][] newList = new Lokum[lokumList.length][];
		for (int i = 0; i < lokumList.length; i++) {
			newList[i] = new Lokum[lokumList[i].length];
			for (int j = 0; j < lokumList[i].length; j++) {
				newList[i][j] = lokumList[i][j].createCopy();
			}
		}
		return newList;
	}

	public String toString() {
		String text = "";
		for (int j = 0; j < lokumList[0].length; j++) {
			for (int i = 0; i < lokumList.length; i++) {
				if (lokumList[i][j] == null) {
					text += "E";
				} else {
					text += lokumList[i][j].toString();
				}
			}
			text += "\n";
		}
		return text;
	}

	public boolean repOK() {
		if (lokumList == null)
			return false;

		for (int i = 0; i < lokumList.length; i++) {
			for (int j = 0; j < lokumList[0].length; j++) {
				if (lokumList[i][j] == null)
					return false;
			}
		}

		// ALSO HAS TO VERIFY THAT THERE ARE NO GROUPS OF THREE OR MORE

		return true;
	}
}
