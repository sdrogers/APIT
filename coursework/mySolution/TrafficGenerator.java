import java.util.Random;

public class TrafficGenerator implements Runnable{
	private final World world;
	private final Random r = new Random();
	private final Coordinate entryPoint;
	private final MovementModel movementModel;
	private final char displayChar;
	private final WaitingTimeGenerator waitingTimeGenerator;
	private final SpeedGenerator speedGenerator;
	private int nToProduce = -1;
	public TrafficGenerator(Coordinate entryPoint,MovementModel movementModel,World world,char displayChar,WaitingTimeGenerator waitingTimeGenerator, SpeedGenerator speedGenerator) {
		this.entryPoint = entryPoint;
		this.movementModel = movementModel;
		this.world = world;
		this.displayChar = displayChar;
		this.waitingTimeGenerator = waitingTimeGenerator;
		this.speedGenerator = speedGenerator;
	}
	public void setNToProduce(int n) {
		this.nToProduce = n;
	}
	public void run() {
		int nProduced = 0;
		try {
			while(true) {
				int delay = waitingTimeGenerator.getTime();
				Thread.sleep(delay);
				GridElement e = world.getElementByCoordinate(this.entryPoint);
				new Thread(new Mover(speedGenerator.getSpeed(),e,this.movementModel,world,displayChar)).start();
				nProduced += 1;
				if(nProduced == this.nToProduce) {
					break;
				}
			}
		}catch(InterruptedException e) {
			
		}
	}

}
