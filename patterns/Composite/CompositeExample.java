public class CompositeExample {
	public static void main(String[] args) {

		// Create some leaf objects
		ShopLeaf pencil = new ShopLeaf(1.0,false,"Pencil");
		ShopLeaf notebook = new ShopLeaf(3.5,true,"Notebook");
		ShopLeaf printerInk = new ShopLeaf(105.0,true,"Ink");


		// Create a composite object
		ShopComposite backToSchool = new ShopComposite("Back To School");
		// Add some leaves to it
		backToSchool.add(pencil);
		backToSchool.add(notebook);

		// Call the compPrice() method
		Double myDiscount = 10.0;
		System.out.println(pencil + " costs " + pencil.compPrice(myDiscount));
		System.out.println(notebook + " costs " + notebook.compPrice(myDiscount));
		System.out.println(backToSchool + " costs " + backToSchool.compPrice(myDiscount));
	}
}