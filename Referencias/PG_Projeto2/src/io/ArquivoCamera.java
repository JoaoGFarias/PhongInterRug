package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import data.Camera;
import data.Ponto;
import data.Vetor;

public class ArquivoCamera {

	private Camera camera;

	private Scanner scan;

	public ArquivoCamera(String arq) throws FileNotFoundException {
		// arq = "camera.cfg"
		this.scan = new Scanner(new File(arq));
		this.camera = this.lerCamera();
		this.scan.close();

	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	private Camera lerCamera() {

		// Lendo arquivo
		Ponto c = lerPonto();
		Vetor n = lerVetor();
		Vetor v = lerVetor();
		float d = Float.parseFloat(this.scan.next());
		float hX = Float.parseFloat(this.scan.next());
		float hY = Float.parseFloat(this.scan.next());

		// Construindo camera
		Camera camera = new Camera(v, n, c, d, hX, hY);
		return camera;

	}

	private Ponto lerPonto() {

		float x, y, z;
		x = Float.parseFloat(this.scan.next());
		y = Float.parseFloat(this.scan.next());
		z = Float.parseFloat(this.scan.next());
		Ponto p = new Ponto(x, y, z, new Vetor());

		return p;
	}

	private Vetor lerVetor() {

		float x, y, z;
		x = Float.parseFloat(this.scan.next());
		y = Float.parseFloat(this.scan.next());
		z = Float.parseFloat(this.scan.next());
		Vetor v = new Vetor(x, y, z);

		return v;
	}

}
