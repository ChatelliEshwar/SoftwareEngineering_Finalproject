/* Title: Wandering in the woods game
 * File Name: Tracker.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a method for track the each steps of the players in  the woods puzzle simulation game.
 */
package wandering.woods;

import javax.swing.ImageIcon;

public class Tracker {

	private int steps;
	private String infoTable;
	private ImageIcon activeIcon;

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public String getInfoTable() {
		return infoTable;
	}

	public void setInfoTable(String infoTable) {
		this.infoTable = infoTable;
	}

	public ImageIcon getActiveIcon() {
		return activeIcon;
	}

	public void setActiveIcon(ImageIcon activeIcon) {
		this.activeIcon = activeIcon;
	}
	
	
	
}
