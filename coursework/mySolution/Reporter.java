
public class Reporter implements Runnable{
	private int it;
	private final World world;
	public Reporter(World world) {
		this.world = world;
		it = 0;
	}
	public void run() {
		try {
			while(true) {
				Thread.sleep(20);
				System.out.println(it++);
				world.draw();
			}
		}catch(InterruptedException e) {
			
		}
	}
}
