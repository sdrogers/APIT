import java.util.ArrayList;

public class Dictionary <A,B> {
	private ArrayList<A> listA;
	private ArrayList<B> listB;
	public Dictionary() {
		listA = new ArrayList<A>();
		listB = new ArrayList<B>();
	}
	public void add(A a, B b) {
		listA.add(a);
		listB.add(b);
	}
	public B get(A a) {
		int index = listA.indexOf(a);
		return listB.get(index);
	}

	public static void main(String[] args) {
		Dictionary<String,String> d = new Dictionary<String,String>();
		d.add("Simon","0778999812");
		d.add("Mary","01413301456");
		System.out.println(d.get("Simon"));

		Dictionary<String,Integer> d2 = new Dictionary<String,Integer>();
		d2.add("Simon",123);
		d2.add("Mary",998);
		System.out.println(d2.get("Mary"));


		// For the second part, we use People as the first type
		// and Contact as the second type
		// Contact is an interface that both Telephone and Address both implement
		// So instances of both can be represented as Contact objects

		People s = new People("Simon");
		People s2 = new People("Mary");
		Telephone t = new Telephone("07788828232");
		Address a = new Address("3","Bank Sreet","Glasgow");

		Dictionary<People,Contact> addressBook = new Dictionary<People,Contact>();
		addressBook.add(s,t);
		addressBook.add(s2,a);
		System.out.println();
		System.out.println(s2 + "\n" + addressBook.get(s2).getDetails());
		System.out.println();
		System.out.println(s + "\n"  + addressBook.get(s).getDetails());
	}
}