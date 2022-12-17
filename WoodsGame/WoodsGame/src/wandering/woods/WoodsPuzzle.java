/* Title: Wandering in the woods game
 * File Name: WoodsPuzzle.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a GUI for playing  the Wandering in the woods Game.
 */
package wandering.woods;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WoodsPuzzle extends JFrame implements KeyListener {

	JButton buttons[][];
	int[] initPlayerRow, initPlayerCol;
	static String matrixPos[][];
	int rows, col, numOfPlayers;
	Icon tree = loadImg("tree.png");
	static ArrayList<Avator> avators = new ArrayList<Avator>();
	String activeAvator = "1";
	Clip happyMusic;
	Icon avatorsCelebration = loadImg("celebration.gif");
	LinkedList<Integer> metAvators = new LinkedList<Integer>();
	Tracker tracker = new Tracker();

	public WoodsPuzzle(int rows, int col, int numOfPlayers, int[] playerPosRow, int[] playerPosCol, Tracker tracker) {
		this.tracker = tracker;
		this.rows = rows;
		this.col = col;
		this.numOfPlayers = numOfPlayers;
		initPlayerRow = playerPosRow;
		initPlayerCol = playerPosCol;

		matrixPos = new String[rows][col];
		buttons = new JButton[rows][col];

		initializeWoodsmatrixPos();
		graphics();

		addKeyListener(this);
		setFocusable(true);
		
		setTitle("Wandering In Woods");
	}

	private void initializeWoodsmatrixPos() {
		for (String[] row : matrixPos)
			Arrays.fill(row, "#");

		avators = new ArrayList<Avator>();
		for (int i = 0; i < numOfPlayers; i++) {
			matrixPos[initPlayerRow[i]][initPlayerCol[i]] = "" + (i + 1);
			avators.add(new Avator(loadImg("Avator" + (i + 1) + ".png"), "" + (i + 1)));
		}
		metAvators = new LinkedList<Integer>();
		activeAvator = "1";
	}
	
	public void resetGame() {
		// TODO Auto-generated method stub
		initializeWoodsmatrixPos();
		reinitialize();
		
	}

	public void graphics() {

		initialize();

		setLayout(new GridLayout(rows, col));
		setSize(600, 600);
		setVisible(true);
	}

	private void initialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				buttons[i][j] = getBoxGraphics(matrixPos[i][j]);
				add(buttons[i][j]);
			}
		}
	}

	public void reinitialize() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				buttons[i][j].setIcon(getBoxIcon(matrixPos[i][j]));
				buttons[i][j].setText(getIdentifier(matrixPos[i][j]));
			}
		}
	}

	public Icon getBoxIcon(String pos) {

		if (pos != "#") {
			if (avators.stream().filter(p -> p.getId().equals(pos)).findFirst().isPresent()) {
				return avators.stream().filter(p -> p.getId().equals(pos)).findFirst().get().getAvator();
			} else {
				return avatorsCelebration;
			}
		}
		return tree;
	}

	public JButton getBoxGraphics(String pos) {

		if (pos != "#")

			return new JButton("", avators.stream().filter(p -> p.getId().equals(pos)).findFirst().get().getAvator());

		return new JButton("", tree);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (stepIntoPath(e)) {
			return;
		}
		stepPathError(e);

	}

	private boolean stepIntoPath(KeyEvent e) {
		// TODO Auto-generated method stub

		if (avators.size() == metAvators.size()) {
			stopMusic();
			return true;
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < col; j++) {
				if (matrixPos[i][j].equals(activeAvator) || matrixPos[i][j].contains(activeAvator)) {
					return walkByDirection(e, i, j);

				}
			}
		}

		return false;
	}

	private boolean walkByDirection(KeyEvent e, int i, int j) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (col > j + 1) {
				walkToNewBox(i, j, i, j + 1);
				return true;
			}
			return false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (0 <= j - 1) {
				walkToNewBox(i, j, i, j - 1);
				return true;
			}
			return false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (rows > i + 1) {
				walkToNewBox(i, j, i + 1, j);
				return true;
			}
			return false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (0 <= i - 1) {
				walkToNewBox(i, j, i - 1, j);
				return true;
			}
			return false;
		}
		return false;
	}

	private void walkToNewBox(int i, int j, int newRow, int newCol) {
		stopMusic();
		
		if (matrixPos[i][j].contains("-")) {
			matrixPos[i][j] = matrixPos[i][j].replace(activeAvator, "");
			matrixPos[i][j] = matrixPos[i][j].replace("-", "");
		} else {
			matrixPos[i][j] = "#";
		}

		if (avators.stream()
				.filter(p -> matrixPos[newRow][newCol].contains("-") || p.getId().equals(matrixPos[newRow][newCol]))
				.findAny().isPresent()) {
			try {
				try {

					playMusic();
				}

				catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}

				metAvators.add(Integer.parseInt(activeAvator));
				if (!matrixPos[newRow][newCol].contains("-"))
					metAvators.add(Integer.parseInt(matrixPos[newRow][newCol]));

				avators.stream().map(p -> {
					if (p.getId().equals(activeAvator)) {
						p.setSteps(p.getSteps() + 1);
						p.setLongestRun(p.getLongestRun() > p.getSteps() ? p.getLongestRun() : p.getSteps());
						p.setShortestRun(p.getShortestRun() < p.getSteps() ? p.getShortestRun() : p.getSteps());
						p.setSteps(0);
					}
					return p;
				}).collect(Collectors.toList());
				matrixPos[newRow][newCol] = activeAvator + "-" + matrixPos[newRow][newCol];
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {

			avators.stream().map(p -> {
				if (p.getId().equals(activeAvator)) {
					p.setSteps(p.getSteps() + 1);
					p.setLongestRun(p.getLongestRun() > p.getSteps() ? p.getLongestRun() : p.getSteps());
				}
				return p;
			}).collect(Collectors.toList());
			matrixPos[newRow][newCol] = activeAvator;

		}
		changeActiveAvator();

		reinitialize();
		if (metAvators.size() == avators.size()) {
			tracker.setInfoTable(metrics());
		}
	}

	private void stopMusic() {
		if (happyMusic != null)
			happyMusic.stop();
	}

	private void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream sound = AudioSystem.getAudioInputStream(loadFile("celebration.wav"));
		System.out.println("PLay music");
		happyMusic = AudioSystem.getClip();
		happyMusic.open(sound);
		happyMusic.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private void changeActiveAvator() {
		int tempPlayer = Integer.parseInt(activeAvator);
		for (int i = 0; i < avators.size(); i++) {
			tempPlayer = tempPlayer + 1 > avators.size() ? 1 : (tempPlayer + 1);
			if (!metAvators.contains(tempPlayer)) {
				activeAvator = "" + tempPlayer;
				tracker.setActiveIcon(loadImg("Avator"+activeAvator+".png"));
				break;
			}
		}
	}

	private void stepPathError(KeyEvent e) {
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel(
				"Invalid Step for avator " + activeAvator + " cannot move " + getStepDirection(e.getKeyCode()) + "!",
				loadImg("Avator" + activeAvator + ".png"), SwingConstants.LEFT));
		JOptionPane.showMessageDialog(null, jPanel, "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public ImageIcon loadImg(String image) {
		try {
			if (image.contains(".gif")) {
				return new ImageIcon(loadFile(image));
			}
			return new ImageIcon(new ImageIcon(this.getClass().getClassLoader().getResource(image)).getImage()
					.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e);
		}
		return null;

	}

	public String getIdentifier(String id) {

		if (id.equals("#"))
			return "";

		return "A: " + id;

	}

	public String getStepDirection(int key) {
		switch (key) {
		case KeyEvent.VK_LEFT:
			return "LEFT";
		case KeyEvent.VK_RIGHT:
			return "RIGHT";
		case KeyEvent.VK_UP:
			return "UP";
		case KeyEvent.VK_DOWN:
			return "DOWN";
		default:
			return "";

		}

	}

	public URL loadFile(String file) {
		return this.getClass().getClassLoader().getResource(file);
	}

	private String metrics() {

		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= avators.size(); i++) {
			
			if (!String.valueOf(i).equals(activeAvator)) {
				builder.append("Player " + activeAvator + " to " + " Player " + i + " shortest path is " + Path.shortestRoute(matrixPos, activeAvator, String.valueOf(i))+"\n");
			}

		}

		int averageRunAll = 0;
		for (Avator avator : avators) {
			String longestRun = avator.getLongestRun() == -1 ? "No Steps taken"
					: "" + avator.getLongestRun();
			String shortestRun = avator.getShortestRun() == Integer.MAX_VALUE ? "No Steps taken"
					: "" + avator.getShortestRun();
			int longest = longestRun.equals("No Steps taken") ? 0 : Integer.parseInt(longestRun);
			int shortest = shortestRun.equals("No Steps taken") ? 0 : Integer.parseInt(shortestRun);
			int averageRun = ((longest + shortest) / 2);
			averageRunAll = averageRunAll + averageRun;
			builder.append("Player " + avator.getId() + " longest run: " + longestRun + " and shortest run: "
					+ shortestRun + " Average run: " + averageRun+"\n");
		}
		builder.append("Average of all players:  " + (averageRunAll / avators.size())+"\n");

		return builder.toString();
	}

}
