
public class Coordinate {
	public int x;
	public int y;
	public int xBound;
	public int yBound;
	
	public Coordinate(int x,int y,int xBound,int yBound){
		this.x=x;
		this.y=y;
		this.xBound=xBound;
		this.yBound=yBound;
	}
		
	public Coordinate(int x,int y,WindowMain window){
		this.x=x;
		this.y=y;
		xBound=window.column-1;
		yBound=window.row-1;
	}
	
	public Coordinate(WindowMain window){
		xBound=window.column-1;
		yBound=window.row-1;
		x=xBound/2;
		y=yBound/2;
	}

	public boolean outOfBound(){
		if (x<0) return true;
		if (y<0) return true;
		if (x>xBound) return true;
		if (y>yBound) return true;
		return false;
	}
	
	public Coordinate getNext(Snake.Direction dir){
		Coordinate tempC=new Coordinate(x,y,xBound,yBound);
		switch (dir){
		case down:tempC.y++; break;
		case up:tempC.y--; break;
		case left:tempC.x--; break;
		case right:tempC.x++; break;
		}
		if (tempC.outOfBound())
			return null;
		else
			return tempC;
	}
	
	@Override
	public boolean equals(Object obj){
		if ((obj==null)||!(obj instanceof Coordinate)) return false;
		if ((((Coordinate)obj).x==this.x) && (((Coordinate)obj).y==this.y))
			return true;
		else
			return false;
	}
	
	public String toString(){
		return "("+x+","+y+")";
	}
}
