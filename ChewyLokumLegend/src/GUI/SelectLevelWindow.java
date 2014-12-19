package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import DreamPackage.Level;

import javax.swing.JButton;
import javax.swing.JFrame;

import DreamPackage.LokumGame;

@SuppressWarnings("serial")
public class SelectLevelWindow extends JFrame {
	private static SelectLevelWindow instance;

	private SelectLevelWindow() {
		super("Levels");

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainMenu.getInstance().setVisible(true);
			}
		});

		super.setSize(300, 300);
		setLayout(new FlowLayout(3, 1, 1));

		ActionListener actList = new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				LokumGame.getInstance()
						.selectLevel(
								Integer.parseInt(((JButton) arg0.getSource())
										.getText()));
				SelectLevelWindow.getInstance().dispose();
			}
		};

		Level[] levels = LokumGame.getInstance().getLevels();

		for (int i = 0; i < levels.length; i++) {
			JButton newButton = new JButton(String.valueOf(i));
			newButton.setSize(120, 120);
			newButton.addActionListener(actList);
			newButton.setEnabled(!levels[i].getLocked());
			add(newButton);
		}
	}

	public static SelectLevelWindow getInstance() {
		if (instance == null)
			instance = new SelectLevelWindow();
		return instance;
	}

	public void refresh() {
		instance = new SelectLevelWindow();
	}

}
