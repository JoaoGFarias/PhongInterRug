package state;

import java.awt.Color;

import data.Camera;
import data.Iluminacao;
import data.Objeto;
import data.Pixel;
import data.Ponto;
import data.Vetor;

public class World {
	private Objeto object;
	private Camera camera;
	private Iluminacao light;

	public Objeto getObject() {
		return object;
	}

	public void setObject(Objeto object) {
		this.object = object;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public Iluminacao getLight() {
		return light;
	}

	public void setLight(Iluminacao light) {
		this.light = light;
	}

	public static void transformacaoMundoVisao(Ponto p, Camera camera) {

		/*
		 * The user will gives us points in the ijk coordinate system. It's more
		 * useful to us, however, to work with coordinates relatives to the
		 * camera position instead. (Camera being point (0,0,0)) For that
		 * reason, we will calculate the base transformation matrix and use it
		 * to transform all the points we received.
		 * 
		 * 
		 * The transformation matrix is: | <----U----> | | <----V----> | |
		 * <----N----> |
		 */

		Vetor vetorPmenosC = new Vetor(camera.getPosicao(), p);

		p.setX((camera.getU().getX() * vetorPmenosC.getX())
				+ (camera.getU().getY() * vetorPmenosC.getY())
				+ (camera.getU().getZ() * vetorPmenosC.getZ()));
		p.setY((camera.getV().getX() * vetorPmenosC.getX())
				+ (camera.getV().getY() * vetorPmenosC.getY())
				+ (camera.getV().getZ() * vetorPmenosC.getZ()));
		p.setZ((camera.getN().getX() * vetorPmenosC.getX())
				+ (camera.getN().getY() * vetorPmenosC.getY())
				+ (camera.getN().getZ() * vetorPmenosC.getZ()));

	}

	public static Pixel transformacaoVisaoTela(Ponto p, Camera camera,
			int resX, int resY) {

		float xD = (camera.getD() / camera.gethX()) * (p.getX() / p.getZ());
		float yD = (camera.getD() / camera.gethY()) * (p.getY() / p.getZ());

		Pixel coordenadas = new Pixel(xD, yD, new Color(0, 0, 0));
		mapearTela(coordenadas, resX, resY);

		return coordenadas;
	}

	private static void mapearTela(Pixel p, int resX, int resY) {
		p.setX((p.getX() + 1) / 2 * (resX - 1));
		p.setY((1 - p.getY()) / 2 * (resY - 1));
	}

}
