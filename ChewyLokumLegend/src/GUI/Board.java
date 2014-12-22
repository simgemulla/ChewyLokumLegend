package GUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import DreamPackage.GameBoard;
import DreamPackage.Lokum;
import DreamPackage.LokumGame;
import DreamPackage.colorBomb;
import DreamPackage.obstacle;
import DreamPackage.striped;
import DreamPackage.wrapped;

@SuppressWarnings("serial")
/**
 * Board is a GUI class. It paints the entire board and the Lokums.
 * @author macbook
 *
 */
public class Board extends JLabel {

	private int LOKUM_SIZE = 40;
	private int CELL_SIZE = 50;
	BufferedImage image;
	int[] selectedCell = null;
	private Image coco;
	private Image cocoV;
	private Image cocoH;
	private Image cocoW;
	private Image colorbomb;
	private Image hazelnut;
	private Image hazelnutV;
	private Image hazelnutH;
	private Image hazelnutW;
	private Image pistachio;
	private Image pistachioV;
	private Image pistachioH;
	private Image pistachioW;
	private Image redrose;
	private Image redroseV;
	private Image redroseH;
	private Image redroseW;

	public Board() {
		addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
			}

			public void mousePressed(MouseEvent arg0) {
			}

			public void mouseExited(MouseEvent arg0) {
			}

			public void mouseEntered(MouseEvent arg0) {
			}

			public void mouseClicked(MouseEvent e) {

				int x = (int) (e.getLocationOnScreen().x - Board.this
						.getLocationOnScreen().x) / CELL_SIZE;
				int y = (int) (e.getLocationOnScreen().y - Board.this
						.getLocationOnScreen().y) / CELL_SIZE;
				if (selectedCell == null) {
					selectCell(x, y);
					selectedCell = new int[] { x, y };
				} else if (selectedCell[0] < x - 1 || selectedCell[0] > x + 1
						|| selectedCell[1] < y - 1 || selectedCell[1] > y + 1) {
					selectCell(x, y);
					deselectCell(selectedCell[0], selectedCell[1]);
					selectedCell[0] = x;
					selectedCell[1] = y;
				} else {
					if (selectedCell[0] == x && selectedCell[1] == y) {
						deselectCell(x, y);
						selectedCell = null;
					} else {
						LokumGame.getInstance().swap(x, y, selectedCell[0],
								selectedCell[1]);
						deselectCell(selectedCell[0], selectedCell[1]);
						selectedCell = null;
					}
				}

			}
		});

		try {
			coco = ImageIO.read(new File("pic/coco.png"));
			cocoV = ImageIO.read(new File("pic/coco_vstriped.png"));
			cocoH = ImageIO.read(new File("pic/coco_hstriped.png"));
			cocoW = ImageIO.read(new File("pic/coco_wrapped.png"));
			colorbomb = ImageIO.read(new File("pic/colorbomb.png"));
			hazelnut = ImageIO.read(new File("pic/hazelnut.png"));
			hazelnutV = ImageIO.read(new File("pic/hazelnut_vstriped.png"));
			hazelnutH = ImageIO.read(new File("pic/hazelnut_hstriped.png"));
			hazelnutW = ImageIO.read(new File("pic/hazelnut_wrapped.png"));
			pistachio = ImageIO.read(new File("pic/pistachio.png"));
			pistachioV = ImageIO.read(new File("pic/pistachio_vstriped.png"));
			pistachioH = ImageIO.read(new File("pic/pistachio_hstriped.png"));
			pistachioW = ImageIO.read(new File("pic/pistachio_wrapped.png"));
			redrose = ImageIO.read(new File("pic/red_rose.png"));
			redroseV = ImageIO.read(new File("pic/red_rose_vstriped.png"));
			redroseH = ImageIO.read(new File("pic/red_rose_hstriped.png"));
			redroseW = ImageIO.read(new File("pic/red_rose_wrapped.png"));
		} catch (IOException ie) {

		}
	}

	public void paintLokums() {
		Lokum[][] list = GameBoard.getInstance().getLokumList();
		image = new BufferedImage(CELL_SIZE * list.length + 1, CELL_SIZE
				* list[0].length + 1, BufferedImage.TYPE_INT_RGB); // D�KKAT

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				Lokum lok = list[i][j];
				paintLokum(i, j, lok, image);
				for (int k = 0; k < CELL_SIZE; k++) {
					for (int l = 0; l < CELL_SIZE; l++) {
						if (k == 0 || l == 0) {
							image.setRGB(CELL_SIZE * i + k, CELL_SIZE * j + l,
									Color.black.getRGB());
						} else if (!(k >= (CELL_SIZE - LOKUM_SIZE) / 2
								&& k < (CELL_SIZE + LOKUM_SIZE) / 2
								&& l >= (CELL_SIZE - LOKUM_SIZE) / 2 && l < (CELL_SIZE + LOKUM_SIZE) / 2)) {
							image.setRGB(CELL_SIZE * i + k, CELL_SIZE * j + l,
									Color.white.getRGB());
						}

					}
				}
			}
		}
		this.setIcon(new ImageIcon(image));
		// revalidate();
		// repaint();
	}

	public void paintLokum(int i, int j, Lokum lok, BufferedImage image) {
		if (lok instanceof striped) {
			paintStriped(CELL_SIZE * i + (CELL_SIZE - LOKUM_SIZE) / 2,
					CELL_SIZE * j + (CELL_SIZE - LOKUM_SIZE) / 2,
					(striped) lok, image);
		} else if (lok instanceof wrapped) {
			paintWrapped(CELL_SIZE * i + (CELL_SIZE - LOKUM_SIZE) / 2,
					CELL_SIZE * j + (CELL_SIZE - LOKUM_SIZE) / 2,
					(wrapped) lok, image);
		} else if (lok instanceof colorBomb) {
			paintColorBomb(CELL_SIZE * i + (CELL_SIZE - LOKUM_SIZE) / 2,
					CELL_SIZE * j + (CELL_SIZE - LOKUM_SIZE) / 2,
					(colorBomb) lok, image);
		} else if (lok instanceof obstacle) {
			paintObstacle(CELL_SIZE * i + (CELL_SIZE - LOKUM_SIZE) / 2,
					CELL_SIZE * j + (CELL_SIZE - LOKUM_SIZE) / 2,
					(obstacle) lok, image);
		} else {
			paintNormal(CELL_SIZE * i + (CELL_SIZE - LOKUM_SIZE) / 2, CELL_SIZE
					* j + (CELL_SIZE - LOKUM_SIZE) / 2, lok, image);
		}
	}

	public void paintNormal(int i, int j, Lokum lok, BufferedImage image) {

		/*
		 * int color = lok.getColor().getRGB(); for (int l = 0; l < LOKUM_SIZE;
		 * l++) { for (int k = 0; k < LOKUM_SIZE; k++) { image.setRGB(i + k, j +
		 * l, color); } }
		 */

		Graphics2D g2d = image.createGraphics();
		g2d.clipRect(i, j, LOKUM_SIZE, LOKUM_SIZE);

		if (lok.getColor().equals(Lokum.RED))
			g2d.drawImage(redrose, i, j, null);
		if (lok.getColor().equals(Lokum.YELLOW))
			g2d.drawImage(hazelnut, i, j, null);
		if (lok.getColor().equals(Lokum.BROWN))
			g2d.drawImage(coco, i, j, null);
		if (lok.getColor().equals(Lokum.GREEN))
			g2d.drawImage(pistachio, i, j, null);

		g2d.dispose();

	}

	public void paintStriped(int i, int j, striped lok, BufferedImage image) {
		/*
		 * int color = lok.getColor().getRGB(); if (lok.getOrientation() ==
		 * striped.HORIZONTAL) { for (int l = 0; l < LOKUM_SIZE; l++) { for (int
		 * k = 0; k < LOKUM_SIZE; k++) { if (k == 0 || l == 0 || k == LOKUM_SIZE
		 * - 1 || l == LOKUM_SIZE - 1) { image.setRGB(i + k, j + l, color);
		 * image.setRGB(i + k, j + l + 1, color); continue; } if (l % 4 == 0) {
		 * image.setRGB(i + k, j + l, color); image.setRGB(i + k, j + l + 1,
		 * color); } else if (l % 4 > 1) { image.setRGB(i + k, j + l,
		 * Color.white.getRGB()); image.setRGB(i + k, j + l + 1,
		 * Color.white.getRGB()); } }
		 * 
		 * } } else { for (int k = 0; k < LOKUM_SIZE; k++) { for (int l = 0; l <
		 * LOKUM_SIZE; l++) { if (k == 0 || l == 0 || k == LOKUM_SIZE - 1 || l
		 * == LOKUM_SIZE - 1) { image.setRGB(i + k, j + l, color);
		 * image.setRGB(i + k + 1, j + l, color); continue; } if (k % 4 == 0) {
		 * image.setRGB(i + k, j + l, color); image.setRGB(i + k + 1, j + l,
		 * color); } else if (k % 4 > 1) { image.setRGB(i + k, j + l,
		 * Color.white.getRGB()); image.setRGB(i + k + 1, j + l,
		 * Color.white.getRGB()); } }
		 * 
		 * } }
		 */

		Graphics2D g2d = image.createGraphics();
		g2d.clipRect(i, j, LOKUM_SIZE, LOKUM_SIZE);
		if (lok.getOrientation() == HORIZONTAL) {
			if (lok.getColor().equals(Lokum.RED))
				g2d.drawImage(redroseH, i, j, null);
			if (lok.getColor().equals(Lokum.YELLOW))
				g2d.drawImage(hazelnutH, i, j, null);
			if (lok.getColor().equals(Lokum.BROWN))
				g2d.drawImage(cocoH, i, j, null);
			if (lok.getColor().equals(Lokum.GREEN))
				g2d.drawImage(pistachioH, i, j, null);
		} else {
			if (lok.getColor().equals(Lokum.RED))
				g2d.drawImage(redroseV, i, j, null);
			if (lok.getColor().equals(Lokum.YELLOW))
				g2d.drawImage(hazelnutV, i, j, null);
			if (lok.getColor().equals(Lokum.BROWN))
				g2d.drawImage(cocoV, i, j, null);
			if (lok.getColor().equals(Lokum.GREEN))
				g2d.drawImage(pistachioV, i, j, null);
		}
		g2d.dispose();
	}

	public void paintWrapped(int i, int j, wrapped lok, BufferedImage image) {
		/*
		 * int color = lok.getColor().getRGB(); for (int l = 0; l < LOKUM_SIZE;
		 * l++) { for (int k = 0; k < LOKUM_SIZE; k++) { if (l < 5 || k < 5 || l
		 * >= LOKUM_SIZE - 5 || k >= LOKUM_SIZE - 5) { image.setRGB(i + k, j +
		 * l, color); } else { image.setRGB(i + k, j + l, Color.white.getRGB());
		 * } } }
		 */
		int xDiff = (redroseW.getWidth(null) - LOKUM_SIZE) / 2;
		int yDiff = (redroseW.getHeight(null) - LOKUM_SIZE) / 2;
		Graphics2D g2d = image.createGraphics();
		g2d.clipRect(i - xDiff, j - yDiff, LOKUM_SIZE + 2 * xDiff, LOKUM_SIZE
				+ 2 * yDiff);

		if (lok.getColor().equals(Lokum.RED))
			g2d.drawImage(redroseW, i - xDiff, j - yDiff, null);
		if (lok.getColor().equals(Lokum.YELLOW))
			g2d.drawImage(hazelnutW, i - xDiff, j - yDiff, null);
		if (lok.getColor().equals(Lokum.BROWN))
			g2d.drawImage(cocoW, i - xDiff, j - yDiff, null);
		if (lok.getColor().equals(Lokum.GREEN))
			g2d.drawImage(pistachioW, i - xDiff, j - yDiff, null);

		g2d.dispose();
	}

	public void paintColorBomb(int i, int j, colorBomb lok, BufferedImage image) {
		/*
		 * int color = lok.getColor().getRGB(); for (int l = 0; l < LOKUM_SIZE;
		 * l++) { for (int k = 0; k < LOKUM_SIZE; k++) { if (Math.pow(k - 10, 2)
		 * + Math.pow(l - 10, 2) <= 100) { image.setRGB(i + k, j + l, color); }
		 * else { image.setRGB(i + k, j + l, Color.white.getRGB()); } } }
		 */

		Graphics2D g2d = image.createGraphics();
		g2d.clipRect(i, j, LOKUM_SIZE, LOKUM_SIZE);

		g2d.drawImage(colorbomb, i, j, null);

		g2d.dispose();
	}

	public void paintObstacle(int i, int j, obstacle lok, BufferedImage image) {

		int color = lok.getColor().getRGB();
		for (int l = 0; l < LOKUM_SIZE; l++) {
			for (int k = 0; k < LOKUM_SIZE; k++) {
				image.setRGB(i + k, j + l, color);
			}
		}

	}

	public void selectCell(int x, int y) {
		/*
		 * int color = GameBoard.getInstance().getLokumList()[x][y].getColor()
		 * .darker().getRGB(); for (int k = 0; k < CELL_SIZE; k++) { for (int l
		 * = 0; l < CELL_SIZE; l++) { if (k == 0 || l == 0) {
		 * image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y + l,
		 * Color.black.getRGB()); image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y
		 * + l, Color.red.getRGB()); continue; } image.setRGB(CELL_SIZE * x + k,
		 * CELL_SIZE * y + l, color); } }
		 */

		for (int k = 0; k < CELL_SIZE; k++) {
			image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y, Color.red.getRGB());
			image.setRGB(CELL_SIZE * x + k, CELL_SIZE * (y + 1),
					Color.red.getRGB());
			image.setRGB(CELL_SIZE * x, CELL_SIZE * y + k, Color.red.getRGB());
			image.setRGB(CELL_SIZE * (x + 1), CELL_SIZE * y + k,
					Color.red.getRGB());
		}
		this.setIcon(new ImageIcon(image));
		// revalidate();
		// repaint();
	}

	public void deselectCell(int x, int y) {
		// paintLokum(x, y, GameBoard.getInstance().getLokumList()[x][y],
		// image);

		for (int k = 0; k < CELL_SIZE; k++) {
			image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y, Color.black.getRGB());
			image.setRGB(CELL_SIZE * x + k, CELL_SIZE * (y + 1),
					Color.black.getRGB());
			image.setRGB(CELL_SIZE * x, CELL_SIZE * y + k, Color.black.getRGB());
			image.setRGB(CELL_SIZE * (x + 1), CELL_SIZE * y + k,
					Color.black.getRGB());
		}
		this.setIcon(new ImageIcon(image));
	}

	public void markToBeDeleted() {
		for (int[] a : GameBoard.getInstance().getDeleteList()) {
			int x = a[0];
			int y = a[1];
			/*
			 * int color =
			 * GameBoard.getInstance().getLokumList()[x][y].getColor()
			 * .darker().getRGB(); for (int k = 0; k < CELL_SIZE; k++) { for
			 * (int l = 0; l < CELL_SIZE; l++) { if (k == 0 || l == 0) {
			 * image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y + l,
			 * Color.black.getRGB()); continue; } image.setRGB(CELL_SIZE * x +
			 * k, CELL_SIZE * y + l, color); } }
			 */
			for (int k = 0; k < CELL_SIZE; k++) {
				image.setRGB(CELL_SIZE * x + k, CELL_SIZE * y,
						Color.green.getRGB());
				image.setRGB(CELL_SIZE * x + k, CELL_SIZE * (y + 1),
						Color.green.getRGB());
				image.setRGB(CELL_SIZE * x, CELL_SIZE * y + k,
						Color.green.getRGB());
				image.setRGB(CELL_SIZE * (x + 1), CELL_SIZE * y + k,
						Color.green.getRGB());
			}
		}
		this.setIcon(new ImageIcon(image));
	}
}
