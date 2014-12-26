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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();
		g1.getDeleteList().clear();
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
		// Test for swapping normal lokums

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping normal lokums

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping diagonally and multiple groups of three lokums

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping horizontally striped lokum with a normal lokum and
		// for deleting horizontally striped lokum.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping vertically striped lokum with a normal lokum and
		// for deleting vertically striped lokum.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping colorBomb and normal lokum.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping wrapped and normal lokum.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
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
		// Test for swapping obstacle when there is no specialSwap left.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 0);
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

	public void TestSwap9() {
		// Tests if it is possible to swap an obstacle to create a group of
		// three lokums when specialSwapCount>0.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 1);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 1, 2);

		Assert.assertEquals(g1.getLokumList()[1][0].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.YELLOW);
		Assert.assertTrue(g1.getLokumList()[1][2] instanceof obstacle);
		Assert.assertEquals(g1.getLokumList()[2][0].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.GREEN);
		Assert.assertTrue(GameState.getInstance().getScore() > 0);
		Assert.assertTrue(g1.repOK());

	}

	public void TestSwap10() {
		// Tests if it is possible to swap an obstacle without creating any
		// groups of three lokums when specialSwapCount>0.

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.RED);
		asd[0][1] = new NormalLokum(Lokum.YELLOW);
		asd[0][2] = new NormalLokum(Lokum.BROWN);
		asd[1][0] = new NormalLokum(Lokum.GREEN);
		asd[1][1] = new NormalLokum(Lokum.RED);
		asd[1][2] = new obstacle();
		asd[2][0] = new NormalLokum(Lokum.YELLOW);
		asd[2][1] = new NormalLokum(Lokum.GREEN);
		asd[2][2] = new NormalLokum(Lokum.BROWN);

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 1);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(1, 2, 2, 2);

		Assert.assertEquals(g1.getLokumList()[0][0].getColor(), Lokum.RED);
		Assert.assertEquals(g1.getLokumList()[0][1].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[0][2].getColor(), Lokum.BROWN);
		Assert.assertEquals(g1.getLokumList()[1][0].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[1][1].getColor(), Lokum.RED);
		Assert.assertTrue(g1.getLokumList()[1][2] instanceof obstacle);
		Assert.assertEquals(g1.getLokumList()[2][0].getColor(), Lokum.YELLOW);
		Assert.assertEquals(g1.getLokumList()[2][1].getColor(), Lokum.GREEN);
		Assert.assertEquals(g1.getLokumList()[2][2].getColor(), Lokum.BROWN);
		Assert.assertTrue(GameState.getInstance().getScore() == 0);
		Assert.assertTrue(g1.repOK());
	}

	@Test
	public void TestSwap11() {
		// Test for deleting a group below an obstacle. Obstacle shouldn't move.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 0);
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

	@Test
	public void TestDeletingTimeLokum() {
		// Test for deleting a timedLokum.

		g1 = GameBoard.getInstance();
		Lokum[][] asd = new Lokum[3][3];
		asd[0][0] = new NormalLokum(Lokum.RED);
		asd[0][1] = new NormalLokum(Lokum.RED);
		asd[0][2] = new NormalLokum(Lokum.GREEN);
		asd[1][0] = new NormalLokum(Lokum.BROWN);
		asd[1][1] = new TimedLokum(Lokum.GREEN);
		asd[1][2] = new NormalLokum(Lokum.YELLOW);
		asd[2][0] = new NormalLokum(Lokum.YELLOW);
		asd[2][1] = new NormalLokum(Lokum.BROWN);
		asd[2][2] = new NormalLokum(Lokum.GREEN);

		Level level1 = new TimedLevel(45, 0, asd, 100000000, 0, false, 0, 15);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		int a = ((TimedLevel) GameState.getInstance().getSelectedLevel())
				.getRemainingTime();
		g1.swapLokums(1, 1, 1, 2);

		int b = ((TimedLevel) GameState.getInstance().getSelectedLevel())
				.getRemainingTime();

		((TimedLevel) GameState.getInstance().getSelectedLevel()).quitLevel();

		Assert.assertTrue(b > a);
		Assert.assertTrue(g1.repOK());

	}

	@Test
	public void TestScore() {
		// Test for score calculation when a group of three lokums is deleted.

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

		Level level1 = new NormalLevel(45, 0, asd, 10000000, 0, false, 3);
		GameBoard.getInstance().setBoard(level1);
		GameState.getInstance().setState(level1);
		SelectLevelWindow.getInstance().setVisible(false);
		GameWindow gw = GameWindow.getInstance();
		gw.paintBoard();

		g1.swapLokums(0, 2, 1, 2);

		Assert.assertTrue(GameState.getInstance().getScore() >= 60);

		Assert.assertTrue(g1.repOK());
	}

}
