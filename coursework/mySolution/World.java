import java.util.ArrayList;

public interface World {
	public GridElement getLeft(GridElement e);
	public GridElement getRight(GridElement e);
	public GridElement getUpper(GridElement e);
	public GridElement getLower(GridElement e);
	public GridElement getElementByCoordinate(Coordinate c);
	public Coordinate getCoord(GridElement e);
	public void draw();
	public int getNCols();
	public int getNRows();
}
