package enlighten.challenge;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * The grid would be created based on the file. Search and keeps track of words
 * to be founded;
 * 
 * 2019/05/07
 * 
 * @editor Michael Zheng
 */

public class Grid {

	private char[][] grid;
	private int row;
	private int col;
	TreeSet<Coordinate> directions = new TreeSet<Coordinate>(new CoordComparator());
	HashMap<String, Queue<Coordinate>> wordsList;

	/**
	 * Constructor - initialize the grid
	 */
	public Grid() {
		this.row = 0;
		this.col = 0;
		wordsList = new HashMap<String, Queue<Coordinate>>();
		// Add in all eight directions
		directions.add(new Coordinate(0, -1));
		directions.add(new Coordinate(-1, -1));
		directions.add(new Coordinate(-1, 0));
		directions.add(new Coordinate(-1, 1));
		directions.add(new Coordinate(0, 1));
		directions.add(new Coordinate(1, 1));
		directions.add(new Coordinate(1, 0));
		directions.add(new Coordinate(1, -1));

	}

	/**
	 * Takes the file and create the grid and wordsList. The wordsList contain the
	 * the words to be found. Then the method will the grid with the letters.
	 * 
	 * @param filename name of file
	 */
	public void fill(String filename) {
		try {
			// Make the file
			java.net.URL url = getClass().getResource(filename);
			File file = new File(url.getPath());
			Scanner scanner = new Scanner(file);
			// Create the grid
			String gridSizeString = scanner.nextLine();
			String[] gridSize = gridSizeString.strip().split("x");
			this.row = Integer.parseInt(gridSize[0]);
			this.col = Integer.parseInt(gridSize[1]);
			this.grid = new char[this.row][this.col];
			// Fill in the grid with the letters
			for (int i = 0; i < this.row; i++) {
				if (scanner.hasNextLine()) {
					String[] letters = scanner.nextLine().split("\\s+");
					for (int j = 0; j < this.col; j++) {
						grid[i][j] = letters[j].charAt(0);
					}
				}
			}
			// Add words to be found in wordsList
			while (scanner.hasNextLine()) {
				// Remove any whitespace
				String key = scanner.nextLine().replaceAll("\\s", "");
				if (!wordsList.containsKey(key) && !key.isBlank()) {
					wordsList.put(key, new LinkedList<Coordinate>());
				}
			}
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return wordsList
	 */
	public HashMap<String, Queue<Coordinate>> getWordsList() {
		return this.wordsList;
	}

	/**
	 * Determine if the position is in bound
	 * 
	 * @param x position
	 * @param y position
	 * @return true if in bounds
	 */
	public boolean inBound(int x, int y) {
		if (y > row - 1 || y < 0 || x > col - 1 || x < 0) {
			return false;
		}
		return true;
	}

	/**
	 * Return the number of rows
	 * 
	 * @return number of rows
	 */
	public int maxRow() {
		return this.row;
	}

	/**
	 * Return the number of columns
	 * 
	 * @return number of columns
	 */
	public int maxCol() {
		return this.col;
	}

	/**
	 * Finds if any word matches starting at the X and Y coordinates. If a words is
	 * found, adds the coordinates to wordsList.
	 * 
	 * @param x initial x position
	 * @param y initial y position
	 */
	public void search(final int x, final int y) {
		int xPos = x;
		int yPos = y;
		int numMatch = 0;
		Coordinate coord = new Coordinate(y, x);

		// Search if any of the words match in starting position
		for (String key : wordsList.keySet()) {
			// Check if the word is not blank
			if (!key.isBlank()) {
				char[] word = key.toCharArray();
				// Check the initial starting position char match
				if (word[0] == grid[y][x]) {
					// Go through all directions
					for (Coordinate dir : directions) {
						xPos = coord.getX() + dir.getX();
						yPos = coord.getY() + dir.getY();
						numMatch = 1;
						// Iterate through the word's character
						for (int i = 1; i < word.length; i++) {
							// Checks if the the positions are in bound
							if (inBound(xPos, yPos)) {
								if (grid[yPos][xPos] == word[i]) {
									numMatch++;
									// If the length matches numMatches end search
									if (numMatch == word.length) {
										Coordinate start = new Coordinate(x, y);
										Coordinate end = new Coordinate(xPos, yPos);
										wordsList.get(key).add(start);
										wordsList.get(key).add(end);
									}
								} else {
									break;
								}
							} else {
								break;
							}
							xPos += dir.getX();
							yPos += dir.getY();
						}
					}
				}
			}
		}
	}
}