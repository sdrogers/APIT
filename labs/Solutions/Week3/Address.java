public class Address implements Contact {
	private String houseNumber;
	private String street;
	private String city;
	public Address(String h, String s, String c) {
		this.houseNumber = h;
		this.street = s;
		this.city = c;
	}
	public String getDetails() {
		String rString = "";
		rString += this.houseNumber + " " + this.street + "\n" + this.city;
		return rString;
	}
}