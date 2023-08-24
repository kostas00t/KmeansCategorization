/*
 * KONSTANTINOS KIKIDIS (4387) 
 * CHRISTOS KROKIDAS (4399) 
 * KONSTANTINOS TSAMPIRAS (4508)
 */

 
public class Data {
	private double x1;
	private double x2;
	
	public Data(double x1, double x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}
	
	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}
	
	public String toString() {
		return (x1+","+x2);
	}
}
