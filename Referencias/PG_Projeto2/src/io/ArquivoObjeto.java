package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import state.World;

import data.Camera;
import data.Objeto;
import data.Pixel;
import data.Ponto;
import data.Triangulo;
import data.Vetor;

public class ArquivoObjeto {

	private Objeto object;

	// Global variables
	private Scanner scan;
	private Ponto[] arrayPontos;
	private Pixel[] arrayPixels;
	private Triangulo[] arrayTriangulos;

	public ArquivoObjeto(String arq, Camera camera, int resX, int resY)
			throws FileNotFoundException {

		this.scan = new Scanner(new File(arq));
		this.object = this.lerObjeto(camera, resX, resY);
		this.scan.close();
	}

	public Objeto getObject() {
		return object;
	}

	public void setObject(Objeto object) {
		this.object = object;
	}

	private Objeto lerObjeto(Camera camera, int resX, int resY) {

		// Lendo arquivo
		int numPontos = this.scan.nextInt();
		int numTriangulos = this.scan.nextInt();

		this.arrayPontos = new Ponto[numPontos];
		this.arrayTriangulos = new Triangulo[numTriangulos];
		this.arrayPixels = new Pixel[numPontos];

		preencherArrayPontos(camera, resX, resY);


		preencherArrayTriangulos(arrayPixels);

		// Construindo objeto
		Objeto obj = new Objeto(this.arrayPontos, this.arrayTriangulos,
				this.arrayPixels);

		return obj;

	}

	private Ponto lerPonto() {

		float x, y, z;
		x = Float.parseFloat(this.scan.next());
		y = Float.parseFloat(this.scan.next());
		z = Float.parseFloat(this.scan.next());
		Ponto p = new Ponto(x, y, z, new Vetor());

		return p;
	}

	private Triangulo lerTriangulo(Pixel[] pixels) {

		int vertice1, vertice2, vertice3;

		vertice1 = this.scan.nextInt();
		vertice2 = this.scan.nextInt();
		vertice3 = this.scan.nextInt();

		Triangulo tri = new Triangulo(arrayPontos[vertice1 - 1],
				arrayPontos[vertice2 - 1], arrayPontos[vertice3 - 1],
				pixels[vertice1 - 1], pixels[vertice2 - 1],
				pixels[vertice3 - 1]);

		return tri;
	}

	private void preencherArrayPontos(Camera camera, int resX, int resY) {
		for (int i = 0; i < arrayPontos.length; i++) {
			arrayPontos[i] = lerPonto();
			World.transformacaoMundoVisao(arrayPontos[i], camera);
			arrayPixels[i] = World.transformacaoVisaoTela(arrayPontos[i], camera, resX, resY);
			// System.out.println(arrayPontos[i].toString());
		}
	}

	private void preencherArrayTriangulos(Pixel[] pixels) {
		for (int i = 0; i < arrayTriangulos.length; i++) {
			arrayTriangulos[i] = lerTriangulo(pixels);
		}
	}

	
}
