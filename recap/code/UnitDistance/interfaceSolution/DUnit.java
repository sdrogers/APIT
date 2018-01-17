// Interface for a unit
// All unit objects need to do is 
// convert a value to a standard unit (e.g. metres)
// and provide a display string
public interface DUnit {
	public double convertToSI(double value);
	public String displayString();
}