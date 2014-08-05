package data;

public class Camera {
	private Vetor v, n, u;
	private Ponto posicao;
	private float d, hX, hY;

	public Camera(Vetor v, Vetor n, Ponto c, float d, float hX, float hY) {
		this.n = n;
		this.v = Vetor.ortogonalizaVetor(v, n);
		this.u = new Vetor();
		this.n.normalizar();
		this.v.normalizar();
		this.u = Vetor.produtoVetorial(this.n, this.v);
		
		// TODO this.u.normalizar(); VERIFICAR SE SERA NECESSARIO NORMALIZAR
		this.posicao = c;
		this.d = d;
		this.hX = hX;
		this.hY = hY;
	}

	public Vetor getV() {
		return v;
	}

	public void setV(Vetor v) {
		this.v = v;
	}

	public Vetor getN() {
		return n;
	}

	public void setN(Vetor n) {
		this.n = n;
	}

	public Vetor getU() {
		return u;
	}

	public void setU(Vetor u) {
		this.u = u;
	}

	public void setC(Ponto c) {
		this.posicao = c;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public Ponto getPosicao() {
		return posicao;
	}

	public void setPosicao(Ponto posicao) {
		this.posicao = posicao;
	}

	public float gethX() {
		return hX;
	}

	public void sethX(float hX) {
		this.hX = hX;
	}

	public float gethY() {
		return hY;
	}

	public void sethY(float hY) {
		this.hY = hY;
	}

}
