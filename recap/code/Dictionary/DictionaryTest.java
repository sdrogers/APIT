public class DictionaryTest {
	public static class Person {
		private String name;
		public Person(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public boolean equals(Object o) {
			Person other = (Person) o;
			if(other.getName().equals(this.name)) {
				return true;
			}else {
				return false;
			}
		}
	}
	public static void main(String[] args) {
		Dictionary<String,Double> d = new Dictionary<String,Double>();
		d.add("apple",3.0);
		d.add("banana",2.5);
		System.out.println(d.getDefinition("banana"));

		// Can also make the reverse!
		Dictionary<Person,String> d2 = new Dictionary<Person,String>();
		Person p = new Person("Simon");
		Person q = new Person("Simon");
		
		d2.add(p,"apple");
		// d2.add(2.5,"banana");
		// Double b = 3.0;
		System.out.println(d2.getDefinition(q));

	}
}