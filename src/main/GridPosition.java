package main;

public class GridPosition {
	
	
	    private int x;
	    private int y;
	    private int pageNumber;
	    
	    
	    public GridPosition(int x, int y, int pageNumber){
	    	this.pageNumber = pageNumber;
	        this.x = x;
	        this.y = y;
	    }

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
		
		public int getPageNumber() {
			return pageNumber;
		}
	    
		public String toString(){
			return "x: " + x + ", y: " + y + " on page: " + pageNumber;
		}

	

}
