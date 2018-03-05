import java.util.Random;
public class TestCluster {
	public static void main(String[] args) {
		Random r = new Random();
		int n = 10;
		ClusterComposite blah = new ClusterComposite();
		for(int i=0;i<n;i++) {
			blah.addComponent(new ClusterLeaf(r.nextDouble()));
		}

		while(true) {
			System.out.println(blah.display());
			int a = blah.mergeChildren();
			if(a == -1) {
				break;
			}
		}
		System.out.println(blah.display());
		
	}
}