import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class GridElement {
	private final World world;
	private Mover occupant = null;
	private final ReentrantLock gridLock = new ReentrantLock(); 
	private final Condition lockCondition = gridLock.newCondition();
	public GridElement(World w) {
		world = w;
	}
	public char getChar() {
		if(occupant == null) {
			return ' ';
		}
		else {
			return occupant.getChar();
		}
	}
	
	public void enter(Mover player) {
		gridLock.lock();
		try {
			while(occupant != null) {
				lockCondition.await();						
			}
			occupant = player;
			player.resetDelay();
		}catch(InterruptedException e) {

		}finally {
			gridLock.unlock();
		}
	}
	public void leave() {
		gridLock.lock();
		occupant = null;
		lockCondition.signalAll();
		gridLock.unlock();
	}
}
