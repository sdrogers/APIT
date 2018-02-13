import java.util.Random;

public class LifeWorld {
	private int[][] world;
	private int width;
	private Double probability = 0.2;
	public LifeWorld(int width) {
		this.width = width;
		world = new int[width][width];
	}
	public void randomise() {
		Random r = new Random();
		for(int i=0;i<width;i++) {
			for(int j=0;j<width;j++) {
				if(r.nextDouble()<probability) {
					world[i][j] = 1;
				}else {
					world[i][j] = 0;
				}
			}
		}
	}
	public void setProbability(Double probability) {
		this.probability = probability;
	}
	public int update(int nSteps) {
		int nChanges = 0;
		for(int n=0;n<nSteps;n++) {
			nChanges = 0;
			int[][] newWorld = new int[width][width];
			for(int i=0;i<width;i++) {
				for(int j=0;j<width;j++) {
					newWorld[i][j] = world[i][j];
					int ne = countNeighbours(i,j);
					if(world[i][j]==1 && ne<2) {
						newWorld[i][j] = 0;
						nChanges++;
					}
					// else if(world[i][j]==1 && ne<=3) {
					// 	newWorld[i][j] = 1;
					// }
					else if(world[i][j]==1 && ne>3) {
						newWorld[i][j] = 0;
						nChanges++;
					}
					else if(world[i][j]==0 && ne==3) {
						newWorld[i][j] = 1;
						nChanges++;
					}
				}
			}
			for(int i=0;i<width;i++) {
				for(int j=0;j<width;j++)
					world[i][j] = newWorld[i][j];
			}
		}
		return nChanges;
	}
	private int countNeighbours(int iin,int jin) {
		int count = 0;
		for(int k = -1;k<=1;k++) {
			for(int l = -1;l<=1;l++) {
				int row = iin+k;
				int col = jin+l;
				if(k==0 && l==0) continue;
				if(row==-1) row = width-1;
				if(col==-1) col = width-1;
				if(row==width) row = 0;
				if(col==width) col = 0;
				count += world[row][col];
			}
		}
		return count;
	}
	public int[][] getWorld() {
		return world;
	}
	public int get(int i,int j) {
		return world[i][j];
	}
	public void flip(int i,int j) {
		world[i][j] = 1-world[i][j];
	}
	public void clear() {
		for(int i=0;i<width;i++) {
			for(int j=0;j<width;j++) {
				world[i][j] = 0;
			}
		}
	}
}