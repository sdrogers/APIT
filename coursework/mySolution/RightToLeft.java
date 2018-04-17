public class RightToLeft implements MovementModel {
	public GridElement getNext(GridElement current,World g) {
		Coordinate currentCoordinate = g.getCoord(current);
		Coordinate newCoordinate = new Coordinate(currentCoordinate.getRow(),currentCoordinate.getCol()-1);
		return g.getElementByCoordinate(newCoordinate);
	}
}