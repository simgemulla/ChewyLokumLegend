package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import DreamPackage.XMLG;

/**
 * One of the main GUI classes. It contains a Board and a ScoreBoard.
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame {

	Board b;
	ScoreBoard sb;
	public static GameWindow instance;

	private GameWindow() {

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getInstance().setVisible(true);
				GameWindow.getInstance().dispose();
			}
		});

		setLayout(new GridLayout(0, 2));
		b = new Board();
		sb = new ScoreBoard();

		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

		panel.add(b);
		panel2.add(sb);

		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);

		JScrollPane scrollPane = new JScrollPane(panel);
		JScrollPane scrollPane2 = new JScrollPane(panel2);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(640, 675));
		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(scrollPane2, BorderLayout.EAST);
		setContentPane(contentPane);
	}

	public static GameWindow getInstance() {
		if (instance == null)
			instance = new GameWindow();
		return instance;
	}

	public void paintBoard() {
		b.paintLokums();
		// revalidate();
		repaint();
	}

	public void markDeleteList() {
		b.markToBeDeleted();
		repaint();

	}

	public void setScore(int x) {
		sb.setScore(x);
		// revalidate();
		repaint();
	}

	public void setRemainingMoves(int x) {
		sb.setRemMoves(x);
		// revalidate();
		repaint();
	}

	public void setScoreNeeded(int x) {
		sb.setScoreNeeded(x);
	}

	public void setRemainingTime(int x) {
		sb.setRemainingTime(x);
	}

	public static void Save() {
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();

			// buras› bilgilerin kaydedilece€i yer, buras› ayarlanacak xml
			// hocan›n verdi€i bilgilere göre.

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("Dream-game.xml"));

			transformer.transform(source, result);

			System.out.println("Game Saved to Dream-game.xml!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	protected void addButtons(final JToolBar toolBar) {

		JButton button = null;

		button = new JButton("About");
		button.setToolTipText("Information");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JOptionPane
						.showMessageDialog(
								toolBar,
								"ChewyLokumLegend V 1.0.0.0 BETA is a game developed by Dream Team for KU - COMP 302",
								"ChewyLokumLegend - Info",
								JOptionPane.WARNING_MESSAGE);

			}
		});
		toolBar.add(button);

		button = new JButton("Save Current Game");
		button.setToolTipText("Save Game");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLG.SaveState();
			}
		});
		toolBar.add(button);

		button = new JButton("Quit");
		button.setToolTipText("Quit the program");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(button);

	}

}
