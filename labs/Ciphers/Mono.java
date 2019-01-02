
public class Mono {
	// alp is the cipher array
	private char[] alp = new char[26];
	/*
	 * Constructor makes the cipher array
	 * The method makeAlp can throw the exception
	 * which is then thrown back to whatever made the object
	 * Same in Vignere...
	 */
	public Mono(String keyword) throws BadKeywordException {
		this.makeAlp(keyword);
	}
	// Make the cipher...
	private void makeAlp(String keyword) throws BadKeywordException {
		if(!checkKeyword(keyword)) {
			throw new BadKeywordException();
		}
		// Step 1: copy the keyword into the cipher array
		for(int i=0;i<keyword.length();i++) {
			alp[i] = keyword.charAt(i);
		}
		// Step 2: copy the rest of the alphabet if not in the
		// keyword. I do this by looping over the alphabet, adding characters
		// to the array if they are not in the keyword
		int pos = keyword.length();
		int startVal = (int)('A');
		int endVal = (int)('Z');
		for(int i=startVal;i<=endVal;i++) {
			if(!charInString((char)i,keyword)) {
				alp[pos++] = (char)i;
			}
		}
	}
	/*
	 * Method that returns true if the char passed as a is in String s
	 */
	private boolean charInString(char a,String s) {
		for(int i=0;i<s.length();i++) {
			if(a == s.charAt(i)) {
				return true;
			}
		}
		return false;
	}
	// Also included encode and decode
	// functions that worked with a string
	// They work through the string calling
	// the char versions
	// Useful for debugging
	public String encode(String in) {
		String out = "";
		for(int i=0;i<in.length();i++) {
			out += this.encode(in.charAt(i)); 
		}
		return out;
	}
	public String decode(String in) {
		String out = "";
		for(int i=0;i<in.length();i++) {
			out += this.decode(in.charAt(i)); 
		}
		return out;
	}
	public char encode(char in) {
		int iin = (int)in;
		if(iin < 'A' || iin > 'Z') {
			return in;
		}
		return alp[iin-'A'];
	}
	public char decode(char in) {
		int iin = (int)in;
		if(iin < 'A' || iin > 'Z') {
			return in;
		}
		int pos = 0;
		for(int i=0;i<alp.length;i++) {
			if(alp[i] == in) {
				pos = i;
				break;
			}
		}
		return (char)(pos+'A');
	}
	/*
	 * Method to get the cipher array
	 */
	public String getAlp() {
		String a = "";
		for(int i=0;i<alp.length;i++) {
			a += alp[i];
		}
		return a;
	}
	/*
	 * Method to check the keyword for repeating
	 * characters and make sure it's just A-Z values
	 */
	public boolean checkKeyword(String keyword) {
		// check for repeated characters
		for(int i=0;i<keyword.length()-1;i++) {
			// Check this char is between A and Z
			int thisChar = (int)keyword.charAt(i);
			if(thisChar < 'A' || thisChar > 'Z') {
				return false;
			}
			// Check the char hasn't appeared earlier
			for(int j=i+1;j<keyword.length();j++) {
				if(keyword.charAt(i) == keyword.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
}
