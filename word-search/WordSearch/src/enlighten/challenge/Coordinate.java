package enlighten.challenge;

/**
 * Creates a coordinate object 
 * 
 * 2019/05/07
 * 
 * @editor Michael Zheng
 */

public class Coordinate {
	private int x;
	private int y;

	/**
     * Constructor for coordinate. Keep track of x and y position
     * @param y	y position
     * @param x x position
     */
	public Coordinate(int y, int x) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return x position
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return y position
	 */
	public int getY() {
		return y;
	}
}