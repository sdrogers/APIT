public class TestCombined {
	public static void main(String[] args) {
		// Make some Standard leaf and composite things
		ShopComponent pencil = new ShopLeaf("Pencil",1.0);
		ShopComponent pencilcase = new ShopLeaf("Pencil case",3.0);
		ShopComponent notebook = new ShopLeaf("Notebook",2.0);
		
		ShopComposite backtoschool = new ShopComposite("Back to school");
		ShopComposite pencilandcase = new ShopComposite("Pencil and case");

		pencilandcase.add(new ShopLeaf("Pencil",1.0));
		pencilandcase.add(new ShopLeaf("Pencil case",2.0));

		backtoschool.add(new ShopLeaf("notebook",3.0));
		backtoschool.add(pencilandcase);

		
		// Do some displaying

		System.out.println(pencil + " costs " + pencil.compPrice());
		System.out.println(notebook + " costs " + notebook.compPrice());
		System.out.println(pencilcase + " costs " + pencilcase.compPrice());
		System.out.println(pencilandcase + " costs " + pencilandcase.compPrice());
		System.out.println(backtoschool + " costs " + backtoschool.compPrice());



		// Try some decorators - we can put either leaves or composites
		// into the decorators
		ShopComponentDecorator discountpencil = new StudentDiscountDecorator(new ShopLeaf("pencil",1.0));
		System.out.println(discountpencil + " costs " + discountpencil.compPrice());
		ShopComponentDecorator discountbacktoschool = new StudentDiscountDecorator(backtoschool);
		System.out.println(discountbacktoschool + " costs " + discountbacktoschool.compPrice());
		ShopComponentDecorator staffdiscountbacktoschool = new StaffDiscountDecorator(backtoschool);
		System.out.println(staffdiscountbacktoschool + " costs " + staffdiscountbacktoschool.compPrice());

		// We can also put decorators inside decorators:
		ShopComponentDecorator doublediscount = new StudentDiscountDecorator(new StudentDiscountDecorator(new ShopLeaf("mug",2.0)));
		System.out.println(doublediscount + " costs " + doublediscount.compPrice());		

		ShopComponentDecorator doublehamster = new StaffDiscountDecorator(new StudentDiscountDecorator(new ShopLeaf("hamster",1.5)));
		System.out.println(doublehamster + " costs " + doublehamster.compPrice());				
	}
}