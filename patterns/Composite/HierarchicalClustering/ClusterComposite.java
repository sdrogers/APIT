import java.util.ArrayList;
public class ClusterComposite implements ClusterComponent {
	private ArrayList<ClusterComponent> children;
	public ClusterComposite() {
		children = new ArrayList<ClusterComponent>();
	}
	public void addComponent(ClusterComponent c) {
		children.add(c);
	}
	public Double getVal() {
		Double temp = 0.0;
		for(ClusterComponent c : children) {
			temp += c.getVal();
		}
		temp /= children.size();
		return temp;
	}
	public int mergeChildren() {
		ClusterComponent cl1 = null;
		ClusterComponent cl2 = null;
		Double smallest_dist = 100.0;
		System.out.println("" + children.size());
		if(children.size() > 2) {
			for(int i=0;i<children.size()-1;i++) {
				for(int j=i+1;j<children.size();j++) {
					Double this_dist = children.get(i).getDistance(children.get(j));
					if(this_dist < smallest_dist) {
						smallest_dist = this_dist;
						cl1 = children.get(i);
						cl2 = children.get(j);
					}
				}
			}
			children.remove(cl1);
			children.remove(cl2);
			ClusterComposite newComp = new ClusterComposite();
			newComp.addComponent(cl1);
			newComp.addComponent(cl2);
			children.add(newComp);
			return 1;
		}else {
			return -1;
		}
	}
	public Double getDistance(ClusterComponent other) {
		return Math.abs(this.getVal() - other.getVal());
	}
	public Integer getSize() {
		Integer s = 0;
		for(ClusterComponent c : children) {
			s += c.getSize();
		}
		return s;
	}
	public String display() {
		String s = "(";
		for(ClusterComponent c : children) {
			s += c.display() + ",";
		}
		if(s.endsWith(",")) {
			s = s.substring(0,s.length() - 1);
		}
		s += ")";
		return s;
	}
}