
public class GridWorld implements World{
	private final int nRows,nCols;
	private final GridElement[][] grid;
	public GridWorld(int nRows,int nCols) {
		this.nRows = nRows;
		this.nCols = nCols;
		grid = new GridElement[this.nRows][this.nCols];
		for(int i=0;i<nRows;i++) {
			for(int j=0;j<nCols;j++) {
				grid[i][j] = new GridElement(this);
			}
		}
	}

	public Coordinate getCoord(GridElement e) {
		Coordinate c = null;
		for(int i=0;i<nRows;i++) {
			for(int j=0;j<nCols;j++) {
				if(e == grid[i][j]) {
					c = new Coordinate(i,j);
					return c;
				}
			}
		}
		return c;
	}
	public GridElement getLeft(GridElement e) {
		Coordinate c = getCoord(e);
		if(c == null) {
			return null;
		}else {
			try {
				return grid[c.getRow()-1][c.getCol()];
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
	}
	@Override
	public GridElement getRight(GridElement e) {
		Coordinate c = getCoord(e);
		if(c == null) {
			return null;
		}else {
			try {
				return grid[c.getRow()+1][c.getCol()];
			}catch(ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
	}
	@Override
	public GridElement getUpper(GridElement e) {
		Coordinate c = getCoord(e);
		if(c == null) {
			return null;
		}else {
			try {
				return grid[c.getRow()][c.getCol()-1];
			}catch(ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
	}
	@Override
	public GridElement getLower(GridElement e) {
		Coordinate c = getCoord(e);
		if(c == null) {
			return null;
		}else {
			try {
				return grid[c.getRow()][c.getCol()+1];
			}catch(ArrayIndexOutOfBoundsException ex) {
				return null;
			}
		}
	}
	@Override
	public void draw() {
		String head = "";
		for(int i=0;i<nCols*2+1;i++) {
			head += "-";
		}
		System.out.println(head);
		for(int i=0;i<nRows;i++) {
			String line = "";
			for(int j=0;j<nCols;j++) {
				line += "|" + grid[i][j].getChar();
			}
			line += "|";
			System.out.println(line);
		}
		System.out.println(head);
		
	}
	public int getNCols() {
		return nCols;
	}
	public int getNRows() {
		return nRows;
	}
	public GridElement getElementByCoordinate(Coordinate c) {
		try {
			return grid[c.getRow()][c.getCol()];
		}catch(ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}
