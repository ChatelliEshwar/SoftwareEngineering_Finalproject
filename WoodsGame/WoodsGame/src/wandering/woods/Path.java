/* Title: Wandering in the woods game
 * File Name: Path.java
 * Necessary External Files: ------
 * Created External Files: ----
 * Programmers Name: Eshwar Chatelli, Lokesh Chirumamilla, Sandeep Reddy Sadhu, Sai Manideep Sadhu, Harish Toka
 * Email Address: eshwarchatelli@lewisu.edu, lokeshchirumamilla@lewisu.edu, ssandeepreddy9@gmail.com, saimanideepsadu@lewisu.edu, harishtoka@lewisu.edu
 * course number : CPSC 60500
 * Section number : 005
 * Date: 12-15-2022
 * Usage: This program provides a method to identify the Shortest route to meet all the players for each scenario's in the woods puzzle simulation game.
 */
package wandering.woods;

import java.util.LinkedList;
import java.util.Queue;

public class Path {

	public static int shortestRoute(String[][] gameMatrix, String activeAvator, String nearestAvator) {
		// TODO Auto-generated method stub

		Matrix source = new Matrix(0, 0, 0);

		loopPath: for (int i = 0; i < gameMatrix.length; i++) {
			for (int j = 0; j < gameMatrix[i].length; j++) {

				if (gameMatrix[i][j].equals(activeAvator)) {
					source.row = i;
					source.col = j;
					break loopPath;
				}
			}
		}

		Queue<Matrix> queue = new LinkedList<>();
		queue.add(new Matrix(source.row, source.col, 0));

		boolean[][] met = new boolean[gameMatrix.length][gameMatrix[0].length];
		met[source.row][source.col] = true;

		while (queue.isEmpty() == false) {
			Matrix p = queue.remove();

			if (gameMatrix[p.row][p.col].equals(nearestAvator))
				return p.destination;

			if (walkable(p.row - 1, p.col, gameMatrix, met)) {
				queue.add(new Matrix(p.row - 1, p.col, p.destination + 1));
				met[p.row - 1][p.col] = true;
			}

			if (walkable(p.row + 1, p.col, gameMatrix, met)) {
				queue.add(new Matrix(p.row + 1, p.col, p.destination + 1));
				met[p.row + 1][p.col] = true;
			}

			if (walkable(p.row, p.col - 1, gameMatrix, met)) {
				queue.add(new Matrix(p.row, p.col - 1, p.destination + 1));
				met[p.row][p.col - 1] = true;
			}

			if (walkable(p.row, p.col + 1, gameMatrix, met)) {
				queue.add(new Matrix(p.row, p.col + 1, p.destination + 1));
				met[p.row][p.col + 1] = true;
			}
		}
		return -1;
	}

	private static boolean walkable(int row, int col, String[][] gameMatrix, boolean[][] met) {
		if (row >= 0 && col >= 0 && row < gameMatrix.length && col < gameMatrix[0].length && gameMatrix[row][col] != "0" && met[row][col] == false) {
			return true;
		}
		return false;
	}
}
