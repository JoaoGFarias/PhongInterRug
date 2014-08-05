package data;

public class Vetor {

	private float x, y, z;

	public Vetor() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}

	public Vetor(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vetor(Ponto p1, Ponto p2) {
		this.x = p2.getX() - p1.getX();
		this.y = p2.getY() - p1.getY();
		this.z = p2.getZ() - p1.getZ();
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

	public static Vetor produtoVetorial(Vetor v1, Vetor v2) {
		Vetor vetorNormal = new Vetor();
		vetorNormal.setX((v1.getY() * v2.getZ()) - (v2.getY() * v1.getZ()));
		vetorNormal.setY((v2.getX() * v1.getZ()) - (v1.getX() * v2.getZ()));
		vetorNormal.setZ((v1.getX() * v2.getY()) - (v2.getX() * v1.getY()));

		return vetorNormal;
	}

	public static float produtoInterno(Vetor v1, Vetor v2) {
		return (v1.getX() * v2.getX()) + (v1.getY() * v2.getY())
				+ (v1.getZ() * v2.getZ());
	}

	public static Vetor multMatrizVetor(float[][] matriz, Vetor v) {

		float newX = matriz[0][0] * v.getX() + matriz[0][1] * v.getY()
				+ matriz[0][2] * v.getZ();
		float newY = matriz[1][0] * v.getX() + matriz[1][1] * v.getY()
				+ matriz[1][2] * v.getZ();
		float newZ = matriz[2][0] * v.getX() + matriz[2][1] * v.getY()
				+ matriz[2][2] * v.getZ();

		Vetor vRetorno = new Vetor();
		vRetorno.setX(newX);
		vRetorno.setY(newY);
		vRetorno.setZ(newZ);

		return vRetorno;
	}

	public static Vetor ortogonalizaVetor(Vetor v, Vetor n) {
		Vetor vetorOrtogonal = new Vetor();

		float produto = produtoInterno(v, n) / produtoInterno(n, n);

		vetorOrtogonal.setX(v.getX() - produto * n.getX());
		vetorOrtogonal.setY(v.getY() - produto * n.getY());
		vetorOrtogonal.setZ(v.getZ() - produto * n.getZ());

		return vetorOrtogonal;
	}

	public void normalizar() {

		double norma = Math.sqrt(produtoInterno(this, this));

		this.x = (float) (x / norma);
		this.y = (float) (y / norma);
		this.z = (float) (z / norma);
	}

	public static Vetor somaVetor(Vetor v1, Vetor v2) {
		Vetor v = new Vetor();

		v.setX(v1.getX() + v2.getX());
		v.setY(v1.getY() + v2.getY());
		v.setZ(v1.getZ() + v2.getZ());

		return v;
	}

	public static Vetor subtraiVetor(Vetor v1, Vetor v2) {
		Vetor v = new Vetor();

		v.setX(v1.getX() - v2.getX());
		v.setY(v1.getY() - v2.getY());
		v.setZ(v1.getZ() - v2.getZ());

		return v;
	}

	public static Vetor multEscalar(Vetor v, float k) {
		Vetor vetor = new Vetor();

		vetor.setX(v.getX() * k);
		vetor.setY(v.getY() * k);
		vetor.setZ(v.getZ() * k);

		return vetor;
	}

	public static Vetor multVetor(Vetor v1, Vetor v2) {
		Vetor vetor = new Vetor();
		vetor.setX(v1.getX() * v2.getX());
		vetor.setY(v1.getY() * v2.getY());
		vetor.setZ(v1.getZ() * v2.getZ());

		return vetor;
	}

	public static Vetor somaTresVetores(Vetor v1, Vetor v2, Vetor v3) {
		Vetor vetor = new Vetor();
		vetor.setX(v1.getX() + v2.getX() + v3.getX());
		vetor.setY(v1.getY() + v2.getY() + v3.getY());
		vetor.setZ(v1.getZ() + v2.getZ() + v3.getZ());

		return vetor;
	}

	@Override
	public String toString() {
		return "Vetor [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}
