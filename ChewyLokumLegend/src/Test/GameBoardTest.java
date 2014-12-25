package Test;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DreamPackage.*;

public class GameBoardTest {

	public static GameBoard g1;

	// @BeforeClass
	@Before
	public void setUp() {
		// Initializes g1, fills g1
		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.RED);
		asd[0][1] = new NormalLokum(Lokum.GREEN);
		asd[0][2] = new NormalLokum(Lokum.BROWN);
		asd[1][0] = new NormalLokum(Lokum.RED);
		asd[1][1] = new NormalLokum(Lokum.RED);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.BROWN);
		asd[2][1] = new NormalLokum(Lokum.RED);
		asd[2][2] = new NormalLokum(Lokum.RED);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 3);
		g1.setBoard(level1);
	}

	@Test(expected=NullPointerException.class)
	public  void CheckForNormalSwap() {
		// Normal swap
		g1.toString();

		g1.swapLokums(0, 0, 0, 1);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalSwap, rep  OK", true,
				g1.repOK());
		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalSwap");

	}

	@Test
	public  void CheckForDistantSwap() {
		// Distant swap
		g1.toString();

		g1.swapLokums(0, 0, 2, 2);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForDistantSwap, rep OK", true,
				g1.repOK());
		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForDistanceSwap");
	}

	@Test(expected=NullPointerException.class)
	public  void CheckForNormalAndColorBombSwap() {
		// Swapping normal and colorBomb
		g1.toString();

		g1.getLokumList()[1][0] = new colorBomb();
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndColorBombSwap, rep OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndColorBombSwap");
	}

	@Test(expected=NullPointerException.class)
	public  void CheckForNormalAndStripedSwap() {
		// Swapping normal and striped
		g1.toString();

		g1.getLokumList()[1][0] = new striped(Color.red, striped.VERTICAL);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndStripedSwap, rep OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndStripedSwap");
	}

	@Test(expected=NullPointerException.class)
	public  void CheckForNormalAndWrappedSwap() {
		// Swapping normal and wrapped
		g1.toString();

		g1.getLokumList()[1][0] = new wrapped(Color.red);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndWrappedSwap, rep OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndWrappedSwap");
	}

	@Test(expected=NullPointerException.class)
	public  void CheckForDeleteAndFill() {
		// Test deleteAndFill
		g1.toString();

		g1.getDeleteList().add(new int[] { 0, 0 });
		g1.getDeleteList().add(new int[] { 1, 0 });
		g1.getDeleteList().add(new int[] { 2, 0 });
		g1.deleteAndFill();

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForDeleteAndFill, rep OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForDeleteAndFill");
	}

}
