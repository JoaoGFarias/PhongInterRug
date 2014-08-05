package data;

/**
 * @author bruno_000
 *
 */
public class Objeto {
	private Ponto[] arrayPontos;
	private Pixel[] arrayPixels;
	private Triangulo[] arrayTriangulos;
	
	public Objeto(Ponto[] arrayPontos,
			Triangulo[] arrayTriangulos, Pixel[] arrayPixels) {
		super();
		this.arrayPontos = arrayPontos;
		this.arrayTriangulos = arrayTriangulos;
		this.arrayPixels = arrayPixels;
	}

	public Ponto[] getArrayPontos() {
		return arrayPontos;
	}

	public void setArrayPontos(Ponto[] arrayPontos) {
		this.arrayPontos = arrayPontos;
	}
	
	public Pixel[] getArrayPixel() {
		return arrayPixels;
	}

	public void setArrayPixel(Pixel[] arrayPixel) {
		this.arrayPixels = arrayPixel;
	}

	public Triangulo[] getArrayTriangulos() {
		return arrayTriangulos;
	}

	public void setArrayTriangulos(Triangulo[] arrayTriangulos) {
		this.arrayTriangulos = arrayTriangulos;
	}
	
	
}
