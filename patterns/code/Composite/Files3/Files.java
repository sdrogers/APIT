public class Files {
	public static void main(String[] args) {
		SingleFile aE1Submission = new SingleFile("AE1 Submission",1040);
		System.out.println(aE1Submission.getNumber());
		System.out.println(aE1Submission);

		Folder root = new Folder("root");
		root.add(new SingleFile("cat pic 1",100));
		Folder f = new Folder("f1");
		root.add(f);
		root.add(aE1Submission);
		f.add(new SingleFile("cat pic 2",150));
		Folder f2 = new Folder("f2");
		f.add(f2);
		f2.add(new SingleFile("nickleback",1));
		f2.add(new SingleFile("bach",2));

		System.out.println(root.getNumber());
		System.out.println(root.getSize());

		root.display("");
	}
}