/* Title: Wandering in the woods game
 * File Name: GameConsole.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a GUI for setup the game GUI for the Wandering in the woods game.This is a main program to execute the Wandering in the woods game.
 */
package wandering.woods;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class GameConsole {

	private JFrame frame;
	int nextY = 0;
	int row = 0, col = 0;
	int numOfPlayers = 0;
	ArrayList<JTextField> playersGridRow = new ArrayList<JTextField>();
	ArrayList<JTextField> playersGridCol = new ArrayList<JTextField>();
	Tracker tracker = new Tracker();
	WoodsPuzzle puzzle;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameConsole window = new GameConsole();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameConsole() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel activelabel = new JLabel("Active Player");
		activelabel.setBounds(500, 45, 100, 20);
		frame.getContentPane().add(activelabel);
		
		JLabel title = new JLabel("Wandering in Woods");
		title.setBounds(128, 45, 200, 20);
		frame.getContentPane().add(title);
		
		JLabel lblOccupation = new JLabel("Game Level");
		lblOccupation.setBounds(128, 65, 100, 20);
		frame.getContentPane().add(lblOccupation);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Select Game Level");
		comboBox.addItem("Grade K-2");
		comboBox.addItem("Grade 3-5");
		comboBox.addItem("Grade 6-8");

		comboBox.setBounds(328, 65, 100, 20);
		frame.getContentPane().add(comboBox);

		JLabel rowLabel = new JLabel("Game Row Size:");
		rowLabel.setBounds(128, 115, 100, 14);
		frame.getContentPane().add(rowLabel);

		JTextField rowText = new JTextField();
		rowText.setBounds(328, 115, 46, 14);
		frame.getContentPane().add(rowText);
		rowText.setColumns(10);

		JLabel colLabel = new JLabel("Game Column Size:");
		colLabel.setBounds(128, 150, 150, 14);
		frame.getContentPane().add(colLabel);

		JTextField colText = new JTextField();
		colText.setBounds(328, 150, 46, 14);
		frame.getContentPane().add(colText);
		colText.setColumns(10);

		JLabel numOfPlayersLabel = new JLabel("Number of players:");
		numOfPlayersLabel.setBounds(128, 180, 150, 14);
		frame.getContentPane().add(numOfPlayersLabel);

		JTextField numOfPlayersText = new JTextField();
		numOfPlayersText.setBounds(328, 180, 46, 14);
		frame.getContentPane().add(numOfPlayersText);
		numOfPlayersText.setColumns(10);
		numOfPlayersText.setEnabled(false);
		
		JButton activeAvator = new JButton();
		activeAvator.setBounds(500, 65, 100, 100);
		frame.getContentPane().add(activeAvator);

		numOfPlayersText.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				try {
					
					if (Integer.parseInt(numOfPlayersText.getText()) > 8
							|| Integer.parseInt(numOfPlayersText.getText()) < 2) {
						JOptionPane.showMessageDialog(null, "Invalid players");
						return;
					}

					numOfPlayersText.setEnabled(false);

					for (int i = 0; i < Integer.parseInt(numOfPlayersText.getText()); i++) {
						JLabel numOfPlayersLabel = new JLabel("Player Position row & col:");
						numOfPlayersLabel.setBounds(128, 210 + (30 * i), 150, 14);
						frame.getContentPane().add(numOfPlayersLabel);

						JTextField rowI = new JTextField();
						rowI.setBounds(328, 210 + (30 * i), 46, 14);
						frame.getContentPane().add(rowI);
						rowI.setColumns(10);

						JTextField colI = new JTextField();
						colI.setBounds(400, 210 + (30 * i), 46, 14);
						frame.getContentPane().add(colI);
						colI.setColumns(10);
						
						playersGridRow.add(rowI);
						playersGridCol.add(colI);
					}
					frame.repaint();
				} catch (Exception ex) {
					// TODO: handle exception
				}
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(comboBox.getSelectedItem());
				comboBox.setEnabled(false);
				numOfPlayersText.setEnabled(true);
				if ("Grade K-2".equalsIgnoreCase(comboBox.getSelectedItem().toString())) {
					numOfPlayersText.setText("2");
					numOfPlayersText.setEnabled(false);

				} else if ("Grade 3-5".equalsIgnoreCase(comboBox.getSelectedItem().toString())) {

				} else if ("Grade 6-8".equalsIgnoreCase(comboBox.getSelectedItem().toString())) {

				}

			}
		});

		JButton btnClear = new JButton("Exit");

		btnClear.setBounds(312, 600, 89, 23);
		frame.getContentPane().add(btnClear);

		JButton btnSubmit = new JButton("Play");

		btnSubmit.setBackground(Color.BLUE);
		btnSubmit.setForeground(Color.MAGENTA);
		btnSubmit.setBounds(65, 600, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		
		JButton btnReset = new JButton("Reset Game");

		btnReset.setBackground(Color.BLUE);
		btnReset.setForeground(Color.MAGENTA);
		btnReset.setBounds(165, 600, 120, 23);
		
		
		JButton btnRefresh = new JButton("Refresh Info table");

		btnRefresh.setBackground(Color.BLUE);
		btnRefresh.setForeground(Color.MAGENTA);
		btnRefresh.setBounds(400, 600, 220, 23);
		
		
		
		
		JTextArea  jTextArea = new JTextArea ("Statistics");
		


		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int[] playerRow = new int[Integer.parseInt(numOfPlayersText.getText())];
					int[] playerCol = new int[Integer.parseInt(numOfPlayersText.getText())];

					if ("Grade K-2".equalsIgnoreCase(comboBox.getSelectedItem().toString())) {
						
						playerRow[0] = 0;
						playerCol[0] = 0;
						
						playerRow[1] = Integer.parseInt(rowText.getText())-1;
						playerCol[1] = Integer.parseInt(colText.getText())-1;
						
						if (Integer.parseInt(rowText.getText()) != Integer.parseInt(colText.getText())) {
							JOptionPane.showMessageDialog(null, "Row & Col should be same number for this level");
							return;
						}
					}

					if (Integer.parseInt(rowText.getText()) < 4 || Integer.parseInt(rowText.getText()) > 15) {
						JOptionPane.showMessageDialog(null, "Invalid Row number");
						return;
					}

					if (Integer.parseInt(colText.getText()) < 4 || Integer.parseInt(colText.getText()) > 15) {
						JOptionPane.showMessageDialog(null, "Invalid Col number");
						return;
					}

					HashSet<String> playersPosition = new HashSet<String>();

					for (int i = 0; i < playersGridRow.size(); i++) {
						if (Integer.parseInt(playersGridRow.get(i).getText()) > Integer.parseInt(rowText.getText())
								|| Integer.parseInt(playersGridCol.get(i).getText()) > Integer
										.parseInt(colText.getText())) {
							JOptionPane.showMessageDialog(null, "Invalid player position");
							return;
						} else if (playersPosition
								.contains(playersGridRow.get(i).getText() + "#" + playersGridCol.get(i).getText())) {
							JOptionPane.showMessageDialog(null, "Duplicate player position");
							return;
						} else {
							playersPosition
									.add(playersGridRow.get(i).getText() + "#" + playersGridCol.get(i).getText());
							playerRow[i] = Integer.parseInt(playersGridRow.get(i).getText());
							playerCol[i] = Integer.parseInt(playersGridCol.get(i).getText());
						}
					}

					nextY = (210+(30*(Integer.parseInt(numOfPlayersText.getText())+1)));
					jTextArea.setBounds(500, 265, 600, 300);
					frame.getContentPane().add(jTextArea);
					
					tracker = new Tracker();
					
					
					btnSubmit.setVisible(false);
					frame.getContentPane().add(btnReset);
					frame.getContentPane().add(btnRefresh);
					frame.repaint();
					
					puzzle = new WoodsPuzzle(Integer.parseInt(rowText.getText()),
							Integer.parseInt(colText.getText()), Integer.parseInt(numOfPlayersText.getText()), playerRow, playerCol,
							tracker);
					System.out.println(tracker.getSteps());

				} catch (Exception xe) {
					xe.printStackTrace();
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Invalid input");
					return;
				}

			}
		});

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.exit(0);

			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				puzzle.resetGame();
				tracker.setActiveIcon(null);
				tracker.setInfoTable("");

			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				activeAvator.setIcon(tracker.getActiveIcon());
				jTextArea.setText(tracker.getInfoTable());
			}
		});

	}
}