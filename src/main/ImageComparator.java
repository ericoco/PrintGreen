package main;

import java.awt.image.BufferedImage;
import java.util.Comparator;

public class ImageComparator implements Comparator<BufferedImage> {

	@Override
	public int compare(BufferedImage p1, BufferedImage p2) {
		int area1 = p1.getHeight() * p1.getWidth();
		int area2 = p2.getHeight() * p2.getWidth();
		
		if (area1 > area2) {
			return -1;
		} else if (area1 < area2) {
			return 1;
		} else {
			return 0;
		}
	}
	

}
