
public class RunMe {
	public static void main(String[] args) {
		int nRows = 10;
		int nCols = 20;
		GridWorld world = new GridWorld(nRows,nCols);
		Thread rThread = new Thread(new Reporter(world));
		rThread.start();
		
		MovementModel lr = new LeftToRight();
		MovementModel rl = new RightToLeft();
		MovementModel ud = new UpToDown();
		MovementModel du = new DownToUp();
		MovementModel dr = new DownRight();

		WaitingTimeGenerator uniform = new Uniform(0,20000);
		WaitingTimeGenerator exponential = new Exponential(0.0001);

		SpeedGenerator fastCar = new FastCar();
		SpeedGenerator slowCar = new SlowCar();
		SpeedGenerator madDrunk = new GenericSpeed(100,150);

		for(int i=0;i<nRows/2;i++) {
			Coordinate startPos = new Coordinate(i,0);
			new Thread(new TrafficGenerator(startPos,lr,world,'o',uniform,fastCar)).start();
		}
		for(int i=nRows/2;i<nRows;i++) {
			Coordinate startPos = new Coordinate(i,nCols-1);
			new Thread(new TrafficGenerator(startPos,rl,world,'o',uniform,fastCar)).start();			
		}

		for(int j=0;j<nCols/2;j++) {
			Coordinate startPos = new Coordinate(0,j);
			new Thread(new TrafficGenerator(startPos,ud,world,'-',uniform,slowCar)).start();
			// new Thread(new TrafficGenerator(startPos,dr,world,'t',exponential,fastCar)).start();
		}
		for(int j=nCols/2;j<nCols;j++) {
			Coordinate startPos = new Coordinate(nRows-1,j);
			new Thread(new TrafficGenerator(startPos,du,world,'-',uniform,slowCar)).start();
		}


		// Coordinate topLeft = new Coordinate(0,0);
		// MovementModel knight = new Knight();
		// WaitingTimeGenerator slow_uniform = new Uniform(10000,20000);
		// new Thread(new TrafficGenerator(topLeft,knight,world,'k',slow_uniform)).start();

		// Coordinate middle = new Coordinate(nRows/2,nCols/2);
		// Coordinate everywhere = new RandomCoordinate(nRows,nCols);
		// WaitingTimeGenerator slow_uniform = new Uniform(2000,5000);
		// MovementModel drunk = new Drunk();
		// TrafficGenerator tg = new TrafficGenerator(everywhere,drunk,world,'d',slow_uniform,madDrunk);
		// tg.setNToProduce(20);
		// new Thread(tg).start();
	}
}
