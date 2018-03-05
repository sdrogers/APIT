public class File implements FileComponent {
	private String name;
	private Integer size;
	public File(String n,Integer s) {
		name = n;
		size = s;
	}
	public Integer getSize() {
		return size;
	}
	public Integer getCount() {
		return 1;
	}
	public String display(int tab_level) {
		String s = "\n";
		for(int i=0;i<tab_level;i++) {
			s += "\t";
		}
		s += name + " (" + size + ")";
		return s;
	}
}