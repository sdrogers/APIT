public class Telephone implements Contact {
	private String number;
	public Telephone(String n) {
		this.number = n;
	}
	public String getDetails() {
		return this.number;
	}
}