public class FileTest {
	public static void main(String[] args) {
		File courseworkpdf = new File("courseworkpdf",1);
		File courseworkzip = new File("coursework zip file",2);

		Directory coursework = new Directory("coursework");
		coursework.addChild(courseworkpdf);
		coursework.addChild(courseworkzip);

		Directory unittests = new Directory("unit tests");
		File test1 = new File("unit test 1",56);
		unittests.addChild(test1);

		coursework.addChild(unittests);

		// System.out.println(coursework);
		System.out.println(coursework.nicePrint(""));
		// System.out.println(coursework.getSize());
	}
}