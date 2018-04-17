public class DownRight implements MovementModel {
	public GridElement getNext(GridElement current,World g) {
		Coordinate currentCoordinate = g.getCoord(current);
		Coordinate newCoordinate = new Coordinate(currentCoordinate.getRow()+1,currentCoordinate.getCol()+1);
		return g.getElementByCoordinate(newCoordinate);
	}
}