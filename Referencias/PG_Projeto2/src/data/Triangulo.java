package data;

import java.awt.Color;
import java.util.Arrays;

import state.Screen;

import algoritmos.ZBuffer;

public class Triangulo {
	private Ponto[] pontos;
	private Pixel[] pixels;
	private Vetor normalTriangulo;

	private static double FATOR = 1;

	public Triangulo(Ponto p1, Ponto p2, Ponto p3, Pixel px1, Pixel px2,
			Pixel px3) {
		super();
		this.pontos = new Ponto[3];
		this.pontos[0] = p1;
		this.pontos[1] = p2;
		this.pontos[2] = p3;

		this.pixels = new Pixel[3];
		this.pixels[0] = px1;
		this.pixels[1] = px2;
		this.pixels[2] = px3;

		this.normalTriangulo = new Vetor();
		calcularNormal();
	}

	public static double getFATOR() {
		return FATOR;
	}

	public static void setFATOR(double fATOR) {
		FATOR = fATOR;
	}

	public Ponto getP1() {
		return pontos[0];
	}

	public void setP1(Ponto p1) {
		this.pontos[0] = p1;
	}

	public Ponto getP2() {
		return pontos[1];
	}

	public void setP2(Ponto p2) {
		this.pontos[1] = p2;
	}

	public Ponto getP3() {
		return pontos[2];
	}

	public void setP3(Ponto p3) {
		this.pontos[2] = p3;
	}

	public Vetor getNormalTriangulo() {
		return normalTriangulo;
	}

	public void setNormalTriangulo(Vetor normalTriangulo) {
		this.normalTriangulo = normalTriangulo;
	}

	public Pixel[] getPixels() {
		return pixels;
	}

	public void setPixels(Pixel[] pixels) {
		this.pixels = pixels;
	}

	public Ponto[] getPontos() {
		return pontos;
	}

	public void setPontos(Ponto[] pontos) {
		this.pontos = pontos;
	}

	private void calcularNormal() {

		Vetor vetor1;
		Vetor vetor2;

		vetor1 = new Vetor(getP1(), getP2());
		vetor2 = new Vetor(getP1(), getP3());

		float perturbacao = (float) Math.random();
		perturbacao *= Triangulo.FATOR;
		Vetor perturbado1;
		Vetor perturbado2;
		
		if (perturbacao != 0){
			perturbado1 = Vetor.multEscalar(vetor1, perturbacao);
			perturbado2 = Vetor.multEscalar(vetor2, perturbacao);
		} else {
			perturbado1 = vetor1;
			perturbado2 = vetor2;
				
		}
		
		this.normalTriangulo = Vetor.produtoVetorial(perturbado1, perturbado2);

		this.getP1()
				.setNormal(
						Vetor.somaVetor(this.normalTriangulo, this.getP1()
								.getNormal()));
		this.getP2()
				.setNormal(
						Vetor.somaVetor(this.normalTriangulo, this.getP2()
								.getNormal()));
		this.getP3()
				.setNormal(
						Vetor.somaVetor(this.normalTriangulo, this.getP3()
								.getNormal()));
	}

	public float[][] calcularMatrizInversa() {

		float a, b, c, d, determinante;
		float[][] matrizInversa = new float[2][2];

		Pixel px1 = this.getPixels()[0];
		Pixel px2 = this.getPixels()[1];
		Pixel px3 = this.getPixels()[2];

		a = px2.getX() - px1.getX();
		b = px3.getX() - px1.getX();
		c = px2.getY() - px1.getY();
		d = px3.getY() - px1.getY();

		// TODO verificar se denominador eh zero
		determinante = 1 / (a * d - b * c);

		matrizInversa[0][0] = d * determinante;
		matrizInversa[0][1] = -b * determinante;
		matrizInversa[1][0] = -c * determinante;
		matrizInversa[1][1] = a * determinante;

		return matrizInversa;
	}

	public Ponto transformacaoTelaVisao(float x, float y,
			float[][] matrizInversa) {
		Pixel px1 = this.getPixels()[0];

		float alfa, beta, gama;

		float pMenosAX, pMenosAY;

		pMenosAX = x - px1.getX();
		pMenosAY = y - px1.getY();

		beta = matrizInversa[0][0] * pMenosAX + matrizInversa[0][1] * pMenosAY;
		gama = matrizInversa[1][0] * pMenosAX + matrizInversa[1][1] * pMenosAY;
		alfa = 1 - beta - gama;

		// p' = alfa * A' + beta * B' + gama * C'

		Ponto p1 = this.getP1();
		Ponto p2 = this.getP2();
		Ponto p3 = this.getP3();

		Ponto pLinha = new Ponto();
		pLinha.setX(alfa * p1.getX() + beta * p2.getX() + gama * p3.getX());
		pLinha.setY(alfa * p1.getY() + beta * p2.getY() + gama * p3.getY());
		pLinha.setZ(alfa * p1.getZ() + beta * p2.getZ() + gama * p3.getZ());

		Vetor normal = pLinha.getNormal();

		normal.setX(alfa * p1.getNormal().getX() + beta * p2.getNormal().getX()
				+ gama * p3.getNormal().getX());
		normal.setY(alfa * p1.getNormal().getY() + beta * p2.getNormal().getY()
				+ gama * p3.getNormal().getY());
		normal.setZ(alfa * p1.getNormal().getZ() + beta * p2.getNormal().getZ()
				+ gama * p3.getNormal().getZ());

		return pLinha;
	}

	public void scanLine(Screen screen, Iluminacao iluminacao, Camera camera) {
		ZBuffer zBuffer = screen.getzBuffer();
		Pixel[] order = new Pixel[3];
		float ba, ca, cb;

		float[][] matrizInversa = this.calcularMatrizInversa();

		// TODO VER ISSO AQUI:
		order[0] = this.pixels[0];
		
		order[1] = this.pixels[1];

		order[2] = this.pixels[2];


		Arrays.sort(order);
		
		if(order[2].getY() < 0){
			return;
		} else if (order[0].getY() > screen.getResY()){
			return;
		}
		
		Pixel xMenor = order[0];
		
		for (int i = 0; i < order.length; i++) {
			if (order[i].getX() < xMenor.getX()){
				xMenor = order[i];
			}
		}
		
		if (xMenor.getX() > screen.getResX()){
			return;
		}
		
		Pixel xMaior = order[0];
		
		for (int i = 0; i < order.length; i++) {
			if (order[i].getX() > xMaior.getX()){
				xMaior = order[i];
			}
		}
		
		if(xMaior.getX() < 0 ){
			return;
		}
		

		// Encontrando a inclinacao das retas:
		if (order[1].getY() - order[0].getY() != 0) {
			ba = (order[1].getX() - order[0].getX())
					/ (order[1].getY() - order[0].getY());
		} else {
			ba = 0;
		}

		if (order[2].getY() - order[0].getY() != 0) {
			ca = (order[2].getX() - order[0].getX())
					/ (order[2].getY() - order[0].getY());
		} else {
			ca = 0;
		}

		if (order[2].getY() - order[1].getY() != 0) {
			cb = (order[2].getX() - order[1].getX())
					/ (order[2].getY() - order[1].getY());
		} else {
			cb = 0;
		}
		// --------------------------------------------------------

		// Escaneando as linhas:
		float y = order[0].getY();
		float xReta1 = order[0].getX();
		float xReta2 = xReta1;
		float xInicial = 0, xFinal = 0;

		// Caso em que os pontos se encontram na mesma altura

		while ((int) y < (int) order[1].getY()) {

			if (xReta1 < xReta2) {
				xInicial = xReta1;
				xFinal = xReta2;
			} else {
				xInicial = xReta2;
				xFinal = xReta1;
			}
			// Enviando pixels para serem pintados:
			if (xInicial == xFinal) {
				Ponto p = this.transformacaoTelaVisao(xInicial, y,
						matrizInversa);
				// Se diferente de null, pixel deve ser pintado
				if (zBuffer.verificaZBuffer(p, (int) xInicial, (int) y) != null) {
					Color color = iluminacao.calculaCorPhong(p, camera);

					screen.drawPixel(new Pixel(xInicial, y, color));

				}
			} else {
				while ((int) xInicial < (int) xFinal) {
					Ponto p = this.transformacaoTelaVisao(xInicial, y,
							matrizInversa);
					// Se diferente de null, pixel deve ser pintado
					if (zBuffer.verificaZBuffer(p, (int) xInicial, (int) y) != null) {
						Color color = iluminacao.calculaCorPhong(p, camera);
						screen.drawPixel(new Pixel(xInicial, y, color));
					}
					xInicial++;
				}
			}
			xReta1 = xReta1 + ca;
			xReta2 = xReta2 + ba;

			y++;

		}
		xReta2 = order[1].getX();

		while ((int) y < (int) order[2].getY()) {

			if (xReta1 < xReta2) {
				xInicial = xReta1;
				xFinal = xReta2;
			} else {
				xInicial = xReta2;
				xFinal = xReta1;
			}
			// Enviando pixels para serem pintados:
			if (xInicial == xFinal) {
				Ponto p = this.transformacaoTelaVisao(xInicial, y,
						matrizInversa);
				// Se diferente de null, pixel deve ser pintado
				if (zBuffer.verificaZBuffer(p, (int) xInicial, (int) y) != null) {
					Color color = iluminacao.calculaCorPhong(p, camera);
					screen.drawPixel(new Pixel(xInicial, y, color));
				}
			} else {

				while ((int) xInicial < (int) xFinal) {
					Ponto p = this.transformacaoTelaVisao(xInicial, y,
							matrizInversa);
					// Se diferente de null, pixel deve ser pintado
					if (zBuffer.verificaZBuffer(p, (int) xInicial, (int) y) != null) {
						Color color = iluminacao.calculaCorPhong(p, camera);
						screen.drawPixel(new Pixel(xInicial, y, color));
					}
					xInicial++;
				}
			}
			xReta1 = xReta1 + ca;
			xReta2 = xReta2 + cb;

			y++;
		}
	}

}
