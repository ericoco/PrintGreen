package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;

import javax.imageio.ImageIO;

public class FileComparator implements Comparator<File> {

	@Override
	public int compare(File f1, File f2) {
		
		try {
			BufferedImage p1 = ImageIO.read(f1);
			BufferedImage p2 = ImageIO.read(f2);
			int area1 = p1.getHeight() * p1.getWidth();
			int area2 = p2.getHeight() * p2.getWidth();
			
			if (area1 > area2) {
				
				return -1;
			} else if (area1 < area2) {
				return 1;
			} else {
				return 0;
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
