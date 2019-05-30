package enlighten.challenge;

import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;

/**
 * The program accepts an ASCII text file containing the word search board and
 * the words that need to be found. The the program would find the words within
 * the grid of characters.
 * 
 * 2019/05/07
 * 
 * @editor Michael Zheng
 */

public class AlphabetSoupMain {

	/**
	 * Takes the name of the ASCII text file containing the word search board
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Prompt user to enter name of file
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the name of the file");
		String filename = keyboard.next();

		// Fill the grid based on the file
		Grid grid = new Grid();
		grid.fill(filename);

		int rows = grid.maxRow();
		int cols = grid.maxCol();

		// Search if any word matches for every position in grid
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				grid.search(x, y);
			}
		}

		HashMap<String, Queue<Coordinate>> foundWords = grid.getWordsList();
		// Iterate and print out all the found words coordinate
		for (String key : foundWords.keySet()) {
			System.out.print(key + " ");
			// Checks if key have a coordinate
			if (!foundWords.get(key).isEmpty()) {
				Queue<Coordinate> queue = foundWords.get(key);
				// Iterate and print out the starting and ending coordinate
				while (!queue.isEmpty()) {
					Coordinate coord = queue.poll();
					String x = Integer.toString(coord.getX());
					String y = Integer.toString(coord.getY());
					System.out.print(x + ":" + y + " ");
				}
				System.out.println();
			} else {
				System.out.println("is not found.");
			}
		}
	}
}