/* Title: Wandering in the woods game
 * File Name: Avator.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a method for calculate the statistics of the woods puzzle simulation game.
 */
package wandering.woods;

import javax.swing.Icon;

public class Avator {

	private Icon avator;
	private int steps;
	private int shortestRun;
	private int longestRun;
	private String id;
	
	public Avator( Icon icon, String id) {
		
		this.avator= icon;
		this.id = id;
		this.steps = 0;
		this.longestRun = -1;
		this.shortestRun = Integer.MAX_VALUE;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public int getShortestRun() {
		return shortestRun;
	}

	public void setShortestRun(int shortestRun) {
		this.shortestRun = shortestRun;
	}

	public int getLongestRun() {
		return longestRun;
	}

	public void setLongestRun(int longestRun) {
		this.longestRun = longestRun;
	}

	public Icon getAvator() {
		return avator;
	}

	public String getId() {
		return id;
	}
	

	
}
