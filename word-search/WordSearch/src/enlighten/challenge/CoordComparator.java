package enlighten.challenge;
import java.util.Comparator;

/**
 * Compares two coordinates based on their x and y values.
 * 
 * 2019/05/07
 * 
 * @editor Michael Zheng
 */

public class CoordComparator implements Comparator<Coordinate> {

	public int compare(final Coordinate c1, final Coordinate c2) {
		if (c1.getY() < c2.getY()) {
			return -1;
		} else if (c1.getY() > c2.getY()) {
			return 1;
		} else {
			if (c1.getX() < c2.getX()) {
				return -1;
			} else if (c1.getX() > c2.getX()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
