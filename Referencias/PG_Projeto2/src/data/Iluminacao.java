package data;

import java.awt.Color;

public class Iluminacao {

	private Ponto pontoLuz;//posicao do ponto de luz
	private float ka; //constante ambiental
	private Vetor Ia;//vetor cor ambiental
	private float kd;//constante difusa
	private Vetor Od;//vetor difuso
	private float ks;//constante especular
	private Vetor Il;//cor da fonte de luz
	private float n;//constante de rugosidade

	public Iluminacao(Ponto pontoLuz, float ka, Vetor ia, float kd, Vetor od,
			float ks, Vetor il, float n) {
		super();
		this.pontoLuz = pontoLuz;
		this.ka = ka;
		this.Ia = ia;
		this.Ia = Vetor.multEscalar(this.Ia, this.ka);
		this.kd = kd;
		this.Od = od;
		this.ks = ks;
		this.Il = il;
		this.n = n;
	}

	public Ponto getPontoLuz() {
		return pontoLuz;
	}

	public void setPontoLuz(Ponto pontoLuz) {
		this.pontoLuz = pontoLuz;
	}

	public float getKa() {
		return ka;
	}

	public void setKa(float ka) {
		this.ka = ka;
	}

	public Vetor getIa() {
		return Ia;
	}

	public void setIa(Vetor ia) {
		Ia = ia;
	}

	public float getKd() {
		return kd;
	}

	public void setKd(float kd) {
		this.kd = kd;
	}

	public Vetor getOd() {
		return Od;
	}

	public void setOd(Vetor od) {
		Od = od;
	}

	public float getKs() {
		return ks;
	}

	public void setKs(float ks) {
		this.ks = ks;
	}

	public Vetor getIl() {
		return Il;
	}

	public void setIl(Vetor il) {
		Il = il;
	}

	public float getN() {
		return n;
	}

	public void setN(float n) {
		this.n = n;
	}

	public Color calculaCorPhong(Ponto pontoAtual, Camera camera){
		//I = Ia + Ie + Id

		//Ia = Ia * Ka

		//Ie = <V,R>^n * Ks * Il
		//V = origem da camera - ponto atual
		//R = 2 * <L,N> * N - L

		//Id = <L, N> * Kd * Od * Il
		//N = vetor normal do ponto
		//L = ponto da luz - ponto atual

		Vetor V = new Vetor(pontoAtual.getX() * -1, pontoAtual.getY() * -1, pontoAtual.getZ() * -1);
		V.normalizar();

		Vetor L = new Vetor(pontoAtual, this.pontoLuz);
		L.normalizar();

		Vetor N = pontoAtual.getNormal();
		N.normalizar();

		//Se <V,N> < 0, Mudar o sentido da normal
		if(Vetor.produtoInterno(V, N) <= 0.001) {
			N = Vetor.multEscalar(N, -1);
		}

		Vetor R = Vetor.subtraiVetor(Vetor.multEscalar(N, (2 * Vetor.produtoInterno(L, N))), L);
		R.normalizar();


		Vetor Ie, Id;

		/*
		 * Se a luz está atras do triangulo então as componentes difusas e especular devem ser
		 * desconsideras
		 */
		if(Vetor.produtoInterno(L, N) >= 0.001){

			Id = Vetor.multEscalar( Vetor.multVetor(this.Od, this.Il), (Vetor.produtoInterno(L, N) * this.kd) ) ;
			//Se o ângulo entre a camera e o reflexo da luz for maior que 90 graus então
			//devemos desconsiderar a componente especular
			if(Vetor.produtoInterno(V, R) >= 0.001) {
				Ie = Vetor.multEscalar(this.Il, (float) (Math.pow(Vetor.produtoInterno(V, R), this.n) * this.ks));
			} else {
				Ie = new Vetor();
			}

		} else {
			Id = new Vetor();
			Ie = new Vetor();
		}
			
		//Ie = new Vetor();
		//Ia = new Vetor();
		//Id = new Vetor();
		Vetor vetorCor =  Vetor.somaTresVetores(this.Ia, Ie, Id);

		if(vetorCor.getX() > 255){
			vetorCor.setX(255);
		}
		if(vetorCor.getY() > 255){
			vetorCor.setY(255);
		}
		if(vetorCor.getZ() > 255){
			vetorCor.setZ(255);
		}
		
		Color cor = new Color((int)vetorCor.getX(), (int)vetorCor.getY(), (int)vetorCor.getZ());

		return cor;

	}

}
