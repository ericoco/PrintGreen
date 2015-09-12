package main;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;



public class GridManager {

	private ArrayList<Grid> grids;
	private TreeSet<BufferedImage> photos;
	private ArrayList<GridPosition> gridPositions;

	private final int pageWidth = 612;
	private final int pageHeight = 792;


	public GridManager(){
		gridPositions = new ArrayList<GridPosition>();

		grids = new ArrayList<Grid>();
		grids.add(new Grid(pageWidth,pageHeight));
		photos = new TreeSet<BufferedImage>(new ImageComparator());
	}

	public void addPhoto(BufferedImage p){


		if (p.getHeight() <= pageHeight && p.getWidth() <= pageWidth){
			photos.add(p);
			System.out.println("added photo");
		}
		else System.out.println("photo too big to add");
	}

	public TreeSet<BufferedImage> getPhotos(){
		return photos;
	}

	public void addGrid(){
		grids.add(new Grid(pageWidth,pageHeight));
	}

	public ArrayList<GridPosition> getGridPositions(){
		return gridPositions;
	}

	public ArrayList<Grid> getGrids(){
		return grids;
	}


	public void arrange(){

		int gridNumber;
		GridPosition gridPos;


		for (BufferedImage p : photos){
			gridNumber = 1;


			for (Grid g : grids){


				gridPos = g.placeBeside(p.getWidth(), p.getHeight(), gridNumber);

				if (gridPos != null){
					System.out.println("page number: " + gridNumber);
					gridPositions.add(gridPos);

					break;
				}


				if ((gridPos == null) && (grids.size() == gridNumber)){  //if can't add onto grid and last, add new grid, place it on

					System.out.println("page number: " + (gridNumber+1));
					System.out.println("needs new page");
					addGrid();
					Grid temp = grids.get(grids.size()-1); //get the one you just created
					GridPosition tempGridPos = temp.placeBeside(p.getWidth(), p.getHeight(), gridNumber+1);
					gridPositions.add(tempGridPos);
					break;
				}

				gridNumber++;
			}
		}
	}

}
