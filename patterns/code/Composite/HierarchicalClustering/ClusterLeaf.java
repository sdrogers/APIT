public class ClusterLeaf implements ClusterComponent {
	private Double myVal;
	public ClusterLeaf(Double myVal) {
		this.myVal = myVal;
	}
	public Integer getSize() {
		return 1;
	}
	public int mergeChildren() {
		return -1;
	}
	public Double getDistance(ClusterComponent other) {
		return Math.abs(myVal - other.getVal());
	}
	public Double getVal() {
		return myVal;
	}
	public String display() {
		return "" + myVal;
	}
}