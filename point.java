//Yeng, Ivan, and Stefin
//4.22.23
//This class is to keep track of coordinates
//Coordinates are important to keep track of so that we can see where the graph makes turns

package waterHack;

public class point {
	//x and y represent the x and y on the graph
	double x = 0.0;
	int y = 0;
	//flow is the x value for flowrate which uses int
	int flow = 0;
	//setters and getters
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	//constructor that I used
	public void addPoints(double x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//prints which types of points are being measured
	public void lowString() {
		System.out.println("Low points( x: " + x + ", y: " + y + ")");
	}
	public void highString() {
		System.out.println("High points( x: " + x + ", y: " + y + ")");
	}
	public void string() {
		System.out.println("     points( x: " + x + ", y: " + y + ")");
	}
}
