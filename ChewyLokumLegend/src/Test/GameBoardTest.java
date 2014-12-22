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
	public static void setUp() {
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

		Level level1 = new Level(45, 0, asd, 1000, 0, false);
		g1.setBoard(level1);
	}

	@Test
	public static void CheckForNormalSwap() {
		// Normal swap
		g1.toString();

		g1.swapLokums(0, 0, 0, 1);

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForNormalSwap, rep not OK", true,
				g1.repOK());
		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalSwap");

	}

	@Test
	public static void CheckForDistantSwap() {
		// Distant swap
		g1.toString();

		g1.swapLokums(0, 0, 2, 2);

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForDistantSwap, rep not OK", true,
				g1.repOK());
		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForDistanceSwap");
	}

	@Test
	public static void CheckForNormalAndColorBombSwap() {
		// Swapping normal and colorBomb
		g1.toString();

		g1.getLokumList()[1][0] = new colorBomb();
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForNormalAndColorBombSwap, rep not OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndColorBombSwap");
	}

	@Test
	public static void CheckForNormalAndStripedSwap() {
		// Swapping normal and striped
		g1.toString();

		g1.getLokumList()[1][0] = new striped(Color.red, striped.VERTICAL);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForNormalAndStripedSwap, rep not OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndStripedSwap");
	}

	@Test
	public static void CheckForNormalAndWrappedSwap() {
		// Swapping normal and wrapped
		g1.toString();

		g1.getLokumList()[1][0] = new wrapped(Color.red);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForNormalAndWrappedSwap, rep not OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForNormalAndWrappedSwap");
	}

	@Test
	public static void CheckForDeleteAndFill() {
		// Test deleteAndFill
		g1.toString();

		g1.getDeleteList().add(new int[] { 0, 0 });
		g1.getDeleteList().add(new int[] { 1, 0 });
		g1.getDeleteList().add(new int[] { 2, 0 });
		g1.deleteAndFill();

		g1.toString();

		Assert.assertEquals(
				"Error in GameBoardTest.CheckForDeleteAndFill, rep not OK",
				true, g1.repOK());

		// if (!g1.repOK())
		// System.out.println("Error in GameBoardTest.CheckForDeleteAndFill");
	}

}
