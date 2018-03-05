public class TestFile {
	public static void main(String[] args) {
		Directory root = new Directory("root");
		root.addComponent(new File("Settings",10));
		Directory pictures = new Directory("pictures");
		root.addComponent(pictures);
		pictures.addComponent(new File("portrait",120));
		Directory music = new Directory("music");
		root.addComponent(music);
		Directory jazz = new Directory("jazz");
		music.addComponent(jazz);
		jazz.addComponent(new File("Kind of Blue",201));
		jazz.addComponent(new File("Giant Steps",134));
		Directory classical = new Directory("classical");
		music.addComponent(classical);
		classical.addComponent(new File("Beethoven, Symphony no 6",421));

		System.out.println(root.display(0));	
	}
}