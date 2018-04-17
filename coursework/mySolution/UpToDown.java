public class UpToDown implements MovementModel {
	public GridElement getNext(GridElement current,World g) {
		Coordinate currentCoordinate = g.getCoord(current);
		Coordinate newCoordinate = new Coordinate(currentCoordinate.getRow()+1,currentCoordinate.getCol());
		return g.getElementByCoordinate(newCoordinate);
	}

}