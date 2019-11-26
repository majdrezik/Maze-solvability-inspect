package graph;

public class Place {
	
	private int x;
	private int y;
	public boolean wall;
	private static int serial_num = 0;
	private int serial_ID;
	
	public Place(int x, int y, int bound){
		if(x>= bound || x<0 || y>=bound || y<0)
			throw new IllegalArgumentException();
		else {
			this.x = x;
			this.y = y;
			serial_ID = serial_num++;
		}
	}


	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Place) {
			Place p = (Place)obj;
			if(x==p.x && y==p.y && wall == p.wall)
				return true;
			return false;
		}
		return false;
	}
	
	public int hashCode() {
		return serial_ID;	
	}
}
