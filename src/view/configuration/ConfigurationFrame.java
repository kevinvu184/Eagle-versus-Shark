package view.configuration;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.InvokeConfigureGameDialogController;
import controller.InvokeLoadGameDialogController;

public class ConfigurationFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454047695594084099L;
	private static final int FRAME_WIDTH = 550;
	private static final int FRAME_HEIGHT = 240;

	private JButton startButton;
	private JButton loadButton;
	private JButton exitButton;

	public ConfigurationFrame() {
		startButton = new JButton("START GAME");
		loadButton = new JButton("LOAD GAME");
		exitButton = new JButton("EXIT");

		startButton.setAlignmentX(CENTER_ALIGNMENT);
		loadButton.setAlignmentX(CENTER_ALIGNMENT);
		exitButton.setAlignmentX(CENTER_ALIGNMENT);

		setTitle("Eagle versus Shark");

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dimension.width / 2 - FRAME_WIDTH / 2, dimension.height / 2 - FRAME_HEIGHT / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setResizable(false);

		JPanel headingPanel = new JPanel();
		headingPanel.add(new JLabel("<html><h1><strong><i>Eagle versus Shark Game</i></strong><hr></h1></html>"));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		buttonPanel.add(startButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonPanel.add(loadButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonPanel.add(exitButton);

		startButton.addActionListener(new InvokeConfigureGameDialogController(this));
		loadButton.addActionListener(new InvokeLoadGameDialogController(this));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		getContentPane().add(buttonPanel, BorderLayout.CENTER);
		getContentPane().add(headingPanel, BorderLayout.NORTH);
	}
}