
public class Mover implements Griddable,Runnable {
	private final int delay;
	private GridElement position;
	private final World world;
	private final char myChar;
	private final MovementModel movementModel;
	private int delay_steps;
	public Mover(int d,GridElement initial,MovementModel movementModel,World world,char m) {
		delay = d;
		position = initial;
		this.world = world;
		myChar = m;
		this.movementModel = movementModel;
		this.delay_steps = 0;
	}
	
	public char getChar() {
		
		return myChar;
	}
	@Override
	public void run() {
		position.enter(this);
		GridElement next;
		while((next = getNext())!=null) {
			try {
				Thread.sleep(this.delay);
				next.enter(this);
				position.leave();
				position = next;
			}catch(InterruptedException e) {
				// Do nothing!
			}
		}
		try {
			Thread.sleep(this.delay);
		}catch(InterruptedException e) {
			
		}
		position.leave();
		
	}
	private GridElement getNext() {
		return this.movementModel.getNext(position,world);
	}
	
}
