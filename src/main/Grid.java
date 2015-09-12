package main;

import java.util.ArrayList;
import java.util.List;

public class Grid {


	private static int COLS;
	private static int ROWS;
	

	private int[][] grid;
	



	public Grid(int c, int r){
		
		ROWS = r;
		COLS = c;
		grid = new int[ROWS][COLS];

		for (int i=0; i<ROWS; i++) { 
			for (int j=0; j<COLS; j++) { 
				grid[i][j] = 0; 
			} 
		}
	}


	public void displayGrid() { 


		for (int i=0; i<ROWS; i++) { 
			for (int j=0; j<COLS; j++) { 
				if (grid[i][j]==0) { 
					System.out.print(" . "); 
				} else { 
					System.out.printf("%3d ",grid[i][j]); 
				}
			} 
			System.out.println(); // new row 
		}
		
		System.out.println("");
	} 


	public void fillSingleRectangle(int startCol, int startRow, int endCol, int endRow){

		if (endCol >= COLS || endRow >= ROWS){
			System.out.println("can't fill, out of bounds");
			return;
		}
		
		for (int i = startRow; i<endRow; i++){
			for (int j = startCol; j<endCol; j++){
				grid[i][j]=1;
			}
		}
		
		
	}

	public boolean isRectangleEmpty(int startCol, int startRow, int endCol, int endRow){
//		Boolean empty = true;
//
//		if (endCol >= COLS || endRow >= ROWS || startCol > endCol || startRow > endRow){
//			empty = false;
//			return empty;
//		}
//
//		for (int i = startRow; i<endRow; i++){
//			for (int j = startCol; j<endCol; j++){
//				if (grid[i][j] != 0)
//					empty = false;
//			}
//		}
//		
//		return empty;
		
		if (endCol >= COLS || endRow >= ROWS || startCol > endCol || startRow > endRow 
				|| grid[startRow][startCol] != 0 || grid[endRow][endCol] != 0){
			return false;
		}
		else{
			return true;
		}
	}


	public GridPosition placeBeside(int pWidth, int pHeight, int pageNumber){
		
		GridPosition startPoint;

		for (int i=0; i<ROWS; i++) { 
			for (int j=0; j<COLS; j++) {		
				if (isRectangleEmpty(j, i, j + pWidth - 1, i + pHeight - 1)){
					System.out.println("beside successful " + i + "  " + j);
					fillSingleRectangle(j, i, j + pWidth - 1, i + pHeight - 1);
					startPoint = new GridPosition(j,i,pageNumber);
					return startPoint;
				}
			}
		}
		
		System.out.println("can't place beside");
		return null;
	}


}
