public interface ClusterComponent {
	public Integer getSize();
	public Double getVal();
	public Double getDistance(ClusterComponent other);
	public int mergeChildren();
	public String display();
}