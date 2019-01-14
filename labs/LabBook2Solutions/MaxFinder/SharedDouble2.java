public  class SharedDouble2 {
    private Double d;
    public Double getD() {
        return d;
    }
    public void setD(Double d) {
        this.d = d;
    }
    public void compare(Double a) {
		if(a > d) {
			try {
				Thread.sleep(1);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			d = a;
		}
	}
}