/* Title: Wandering in the woods game
 * File Name: Matrix.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a method to create a Grid for the woods puzzle simulation game.
 */
package wandering.woods;

public class Matrix {
	int row;
	int col;
	int destination;

	public Matrix(int row, int col, int destination) {
		this.row = row;
		this.col = col;
		this.destination = destination;
	}
}
