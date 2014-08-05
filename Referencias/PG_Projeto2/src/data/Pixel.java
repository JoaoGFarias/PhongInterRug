package data;

import java.awt.Color;

public class Pixel implements Comparable<Pixel>{
	private float x,y;
	private Color cor;
	
	public Pixel(){
		this.x = 0;
		this.y = 0;
		this.cor = null;
	}
	public Pixel(float x, float y, Color c) {
		super();
		this.x = x;
		this.y = y;
		this.cor = c;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Color getCor() {
		return cor;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	@Override
	public int compareTo(Pixel o) {
		if(this.y > o.y){
			return 1;
		} else if(this.y == o.y){
			return 0;
		} else {
			return -1;
		}
	}
	public String toString() {
		return "Pixel [x=" + x + ", y=" + y + "]";
	}
}
