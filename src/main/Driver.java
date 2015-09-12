package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDCcitt;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.imageio.*;
import javax.swing.*;


public class Driver {
	
	private static final String PICTURES_DIRECTORY = "photos";
	private static final String PHOTO_FILE_TYPE = ".jpg";
	private static final String PROJECT_DIRECTORY_PATH = System.getProperty("user.dir");
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	

	public static void main(String[] args) {
		
		GridManager gridManager = new GridManager();
		PDDocument document=null;
		TreeSet<File> files = new TreeSet<File>(new FileComparator());
		
		try {
			
			ArrayList<String> picnames = new ArrayList<String>();
			
			picnames.add("banana");
			picnames.add("brown 10x60");
			picnames.add("caterpillar 20x10");
			picnames.add("green 10x40");
			picnames.add("kero");
			picnames.add("kirby");
			picnames.add("pikachu");
			picnames.add("puffer");
			picnames.add("rufus");
			
			File file;
			
			for (String name: picnames){
				file = new File(PROJECT_DIRECTORY_PATH
						+ FILE_SEPARATOR + PICTURES_DIRECTORY
						+ FILE_SEPARATOR + name + PHOTO_FILE_TYPE);
				
				files.add(file); //for pdf rendering
				
				BufferedImage img = ImageIO.read(file);
				gridManager.addPhoto(img);
			}		

			
			//simulation
			long startTime = System.currentTimeMillis();
			gridManager.arrange();
			long endTime = System.currentTimeMillis();
			
			System.out.println("That took " + (endTime - startTime)/1000 + " seconds");
			
			ArrayList<GridPosition> gridPositions = gridManager.getGridPositions();
			int numPages = gridManager.getGrids().size();
			
			System.out.println("gridPositions " + gridPositions.size());
			System.out.println("file number: " + files.size());
			System.out.println("numPages: "+ numPages);
			
			
			for (GridPosition p: gridPositions){
				System.out.println(p);
			}
			
			
			//pdf
			document = new PDDocument();

			ArrayList<PDPage> pages = new ArrayList<PDPage>();
			
			System.out.println("making "+ gridManager.getGrids().size() + " pages");

			for (int i = 0; i < gridManager.getGrids().size(); i++){
				PDPage page = new PDPage();
				pages.add(page);
				document.addPage(page);
			}

			

			int numFile = 0;

			for (File f : files){
				
				int whichPage = gridPositions.get(numFile).getPageNumber();
				//System.out.println(whichPage);

				PDPageContentStream contentStream = new PDPageContentStream(document, pages.get(whichPage-1), true, false);		
				InputStream in = new FileInputStream(f);
				PDXObjectImage pdfimage = new PDJpeg(document, in);
		        contentStream.drawImage(pdfimage, gridPositions.get(numFile).getX(), gridPositions.get(numFile).getY());      
		        contentStream.close();

		        numFile++;

			}
			
			
			document.save("/Users/Eric/Desktop/file1.pdf");
			System.out.println("done");
			document.close();
			

			

		} catch (IOException e) {
			e.printStackTrace();
		} catch (COSVisitorException e) {
			e.printStackTrace();
		}

		
		
		

	}
	
	

}




