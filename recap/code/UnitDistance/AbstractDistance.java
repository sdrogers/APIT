// An alternative solution based upon using
// an abstract class rather than an interface.
// I think this is slightly neater as it allows us to 
// combine the distance atttribute with the conversion
// logic and the comparable in the same place

// Note that the AbstractDistance constructor takes two 
// arguments but the individual ones only take one (and create 
// the necessary string).
// Note 2: the AbstractDistance constructor is defined as protected
// this makes sense as it can only be called by classes that
// inherit from it and nothing else (because it's abstract).
// Note 3: attributes are final...once set in a partiular instance
// they will never change
// Note 4: the compareTo method can be written here as it will be the same
// in all subclasses...ditto the display method

public abstract class AbstractDistance implements Comparable<AbstractDistance> {
	protected final double distance;
	protected final String suffix;
	protected AbstractDistance(double d,String s) {
		this.distance = d;
		this.suffix = s;
	}
	public abstract double getSI();
	public String display() {
		return "" + this.distance + " " + this.suffix;
	}
	public int compareTo(AbstractDistance other) {
		if(this.getSI() <= other.getSI()) {
			return -1;
		}else {
			return 1;
		}
	}
}