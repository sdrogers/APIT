public class FactoryTest{
	public static void main(String[] args) {
		// This plays the part of the drawing program
		// Prompt the user to enter a type of shape
		System.out.println("Enter type of shape:");
		String shape = System.console().readLine();
		// Create a new Shape
		Shape newShape = ShapeFactory.makeShape(shape);
		newShape.draw();
	}
}