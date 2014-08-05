package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import state.World;

import data.Camera;
import data.Iluminacao;
import data.Ponto;
import data.Vetor;

public class ArquivoIluminacao {

	private Iluminacao iluminacao;

	private Scanner scan;

	public ArquivoIluminacao(String arq, Camera camera) throws FileNotFoundException {
		// arq = "iluminacao.txt"
		this.scan = new Scanner(new File(arq));
		this.iluminacao = this.lerIluminacao();
		World.transformacaoMundoVisao(this.iluminacao.getPontoLuz(), camera);
		
		this.scan.close();
	}

	public Iluminacao getIluminacao() {
		return iluminacao;
	}

	public void setIluminacao(Iluminacao iluminacao) {
		this.iluminacao = iluminacao;
	}

	private Iluminacao lerIluminacao() {

		// Lendo arquivo
		Ponto pontoLuz = lerPonto();
		float ka = Float.parseFloat(this.scan.next());
		Vetor Ia = lerVetor();
		float kd = Float.parseFloat(this.scan.next());
		Vetor Od = lerVetor();
		float ks = Float.parseFloat(this.scan.next());
		Vetor Il = lerVetor();
		float n = Float.parseFloat(this.scan.next());

		// Construindo iluminacao
		Iluminacao ilum = new Iluminacao(pontoLuz, ka, Ia, kd, Od, ks, Il, n);
		return ilum;

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
