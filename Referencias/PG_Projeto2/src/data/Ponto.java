package data;

public class Ponto implements Comparable<Ponto>{
	private float x,y,z;
	private Vetor normal;
	
	public Ponto() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.normal = new Vetor();
	}
	
	public Ponto(float x, float y, float z, Vetor normal) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.normal = normal;
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

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public Vetor getNormal() {
		return normal;
	}

	public void setNormal(Vetor normal) {
		this.normal = normal;
	}

	
	/**
	 * Points are ordered from their y values;
	 */
	@Override
	public int compareTo(Ponto o) {
		// TODO Auto-generated method stub
		if(this.y > o.y){
			return 1;
		} else if(this.y == o.y){
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	
	
}
