package Test;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import DreamPackage.*;
import GUI.GameWindow;
import GUI.SelectLevelWindow;

public class GameBoardTest {

	public static GameBoard g1;

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
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();
	}

	@Test
	public void CheckForNormalSwap() {
		// Normal swap
		g1.toString();

		g1.swapLokums(0, 0, 0, 1);

		g1.toString();

		Assert.assertEquals("GameBoardTest.CheckForNormalSwap, rep  OK", true,
				g1.repOK());

	}

	@Test
	public void CheckForDistantSwap() {
		// Distant swap
		g1.toString();

		g1.swapLokums(0, 0, 2, 2);

		g1.toString();

		Assert.assertEquals("GameBoardTest.CheckForDistantSwap, rep OK", true,
				g1.repOK());
	}

	@Test
	public void CheckForNormalAndColorBombSwap() {
		// Swapping normal and colorBomb
		g1.toString();

		g1.getLokumList()[1][0] = new colorBomb();
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndColorBombSwap, rep OK", true,
				g1.repOK());

	}

	@Test
	public void CheckForNormalAndStripedSwap() {
		// Swapping normal and striped
		g1.toString();

		g1.getLokumList()[1][0] = new striped(Color.red, striped.VERTICAL);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndStripedSwap, rep OK", true,
				g1.repOK());

	}

	@Test
	public void CheckForNormalAndWrappedSwap() {
		// Swapping normal and wrapped
		g1.toString();

		g1.getLokumList()[1][0] = new wrapped(Color.red);
		g1.swapLokums(0, 0, 1, 0);

		g1.toString();

		Assert.assertEquals(
				"GameBoardTest.CheckForNormalAndWrappedSwap, rep OK", true,
				g1.repOK());

	}

	@Test
	public void CheckForDeleteAndFill() {
		// Test deleteAndFill
		g1.toString();

		g1.getDeleteList().add(new int[] { 0, 0 });
		g1.getDeleteList().add(new int[] { 1, 0 });
		g1.getDeleteList().add(new int[] { 2, 0 });
		g1.deleteAndFill();

		g1.toString();

		Assert.assertEquals("GameBoardTest.CheckForDeleteAndFill, rep OK",
				true, g1.repOK());

	}

	@Test
	public void TestSwap1() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.GREEN);
		asd[0][1] = new NormalLokum(Lokum.GREEN);
		asd[0][2] = new NormalLokum(Lokum.RED);
		asd[1][0] = new NormalLokum(Lokum.RED);
		asd[1][1] = new NormalLokum(Lokum.YELLOW);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.BROWN);
		asd[2][1] = new NormalLokum(Lokum.RED);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 1, 2);

		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[1][0].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][0].getColor(), Lokum.BROWN);

		Assert.assertTrue(g1.repOK());
	}

	@Test
	public void TestSwap2() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.BROWN);
		asd[0][1] = new NormalLokum(Lokum.RED);
		asd[0][2] = new NormalLokum(Lokum.BROWN);
		asd[1][0] = new NormalLokum(Lokum.YELLOW);
		asd[1][1] = new NormalLokum(Lokum.GREEN);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.GREEN);
		asd[2][1] = new NormalLokum(Lokum.BROWN);
		asd[2][2] = new NormalLokum(Lokum.YELLOW);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(1, 0, 2, 0);

		Assert.assertEquals(g1.getLokumList()[0][0].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[0][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[0][2].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][0].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.YELLOW);

		Assert.assertTrue(g1.repOK());
	}

	@Test
	public void TestSwap3() {
		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.GREEN);
		asd[0][1] = new NormalLokum(Lokum.GREEN);
		asd[0][2] = new NormalLokum(Lokum.RED);
		asd[1][0] = new NormalLokum(Lokum.BROWN);
		asd[1][1] = new NormalLokum(Lokum.GREEN);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.RED);
		asd[2][1] = new NormalLokum(Lokum.YELLOW);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 1, 1);

		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.YELLOW);
		Assert.assertTrue(g1.repOK());
	}

	@Test
	public void TestSwap4() {
		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.BROWN);
		asd[0][1] = new NormalLokum(Lokum.RED);
		asd[0][2] = new NormalLokum(Lokum.BROWN);
		asd[1][0] = new NormalLokum(Lokum.YELLOW);
		asd[1][1] = new NormalLokum(Lokum.GREEN);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new striped(Lokum.GREEN, striped.HORIZONTAL);
		asd[2][1] = new NormalLokum(Lokum.BROWN);
		asd[2][2] = new NormalLokum(Lokum.YELLOW);

		Level level1 = new NormalLevel(45, 0, asd, 10000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(2, 0, 1, 0);

		Assert.assertEquals(g1.getLokumList()[0][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[0][2].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.YELLOW);
		Assert.assertTrue(g1.repOK());

	}

	@Test
	public void TestSwap5() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.YELLOW);
		asd[0][1] = new striped(Lokum.RED, striped.VERTICAL);
		asd[0][2] = new NormalLokum(Lokum.BROWN);
		asd[1][0] = new NormalLokum(Lokum.GREEN);
		asd[1][1] = new NormalLokum(Lokum.YELLOW);
		asd[1][2] = new NormalLokum(Lokum.RED);
		asd[2][0] = new NormalLokum(Lokum.RED);
		asd[2][1] = new NormalLokum(Lokum.BROWN);
		asd[2][2] = new NormalLokum(Lokum.RED);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 1, 0, 2);

		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.BROWN);
		Assert.assertTrue(g1.repOK());

	}

	@Test
	public void TestSwap6() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.GREEN);
		asd[0][1] = new NormalLokum(Lokum.GREEN);
		asd[0][2] = new colorBomb();
		asd[1][0] = new NormalLokum(Lokum.RED);
		asd[1][1] = new NormalLokum(Lokum.YELLOW);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.BROWN);
		asd[2][1] = new NormalLokum(Lokum.RED);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 10000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 0, 1);

		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.RED);
		Assert.assertTrue(g1.repOK());

	}

	@Test
	public void TestSwap7() {
		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.GREEN);
		asd[0][1] = new wrapped(Lokum.GREEN);
		asd[0][2] = new NormalLokum(Lokum.RED);
		asd[1][0] = new NormalLokum(Lokum.RED);
		asd[1][1] = new NormalLokum(Lokum.YELLOW);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.BROWN);
		asd[2][1] = new NormalLokum(Lokum.RED);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 10000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 0, 1);
		Assert.assertEquals(g1.getLokumList()[0][1].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[0][2].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.RED);
		Assert.assertTrue(g1.repOK());

	}

	@Test
	public void TestSwap8() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.GREEN);
		asd[0][1] = new NormalLokum(Lokum.GREEN);
		asd[0][2] = new obstacle();
		asd[1][0] = new NormalLokum(Lokum.RED);
		asd[1][1] = new NormalLokum(Lokum.YELLOW);
		asd[1][2] = new NormalLokum(Lokum.GREEN);
		asd[2][0] = new NormalLokum(Lokum.BROWN);
		asd[2][1] = new NormalLokum(Lokum.RED);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 0);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 1, 2);

		// specialSwapCount is zero so we cannot swap obstacle with any lokum.
		// Hence the board remains the same.

		Assert.assertEquals(g1.getLokumList()[0][0].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[0][1].getColor(), Lokum.GREEN);
		Assert.assertTrue(g1.getLokumList()[0][2] instanceof obstacle);
		Assert.assertEquals(g1.getLokumList()[1][0].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[2][0].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.GREEN);
		Assert.assertTrue(g1.repOK());
	}

	@Test
	public void TestSwap9() {

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.RED);
		asd[0][1] = new obstacle();
		asd[0][2] = new NormalLokum(Lokum.GREEN);
		asd[1][0] = new NormalLokum(Lokum.BROWN);
		asd[1][1] = new NormalLokum(Lokum.GREEN);
		asd[1][2] = new NormalLokum(Lokum.YELLOW);
		asd[2][0] = new NormalLokum(Lokum.YELLOW);
		asd[2][1] = new NormalLokum(Lokum.BROWN);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new NormalLevel(45, 0, asd, 1000, 0, false, 0);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(1, 1, 1, 2);

		Assert.assertTrue(g1.getLokumList()[0][1] instanceof obstacle);
		Assert.assertEquals(g1.getLokumList()[0][2].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[1][2].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.BROWN);
		Assert.assertTrue(g1.repOK());
	}

}
