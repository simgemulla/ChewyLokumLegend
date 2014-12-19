package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DreamPackage.LokumGame;

/**
 * The menu to be displayed when the game starts. It provides two choices: Play
 * and Load
 * 
 */
@SuppressWarnings("serial")
public class MainMenu extends JFrame {

	public static MainMenu instance;

	private MainMenu() {
		super("ChewyLokumLegend by Dream Team");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		JButton playGame = new JButton("Play Game");
		playGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				LokumGame.getInstance().start();

			}

		});
		JButton loadGame = new JButton("Load Game");
		loadGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				LokumGame.getInstance().load();

			}
		});

		setLayout(new GridLayout(2, 0));

		JScrollPane scrollPane = new JScrollPane(playGame);
		JScrollPane scrollPane2 = new JScrollPane(loadGame);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(640, 65));

		contentPane.add(scrollPane, BorderLayout.NORTH);
		contentPane.add(scrollPane2, BorderLayout.SOUTH);
		setContentPane(contentPane);

	}

	public static MainMenu getInstance() {
		if (instance == null)
			instance = new MainMenu();
		return instance;
	}
}
