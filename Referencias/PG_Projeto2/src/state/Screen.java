package state;

import java.awt.Graphics;
import java.util.LinkedList;

import algoritmos.ZBuffer;

import data.Pixel;

public class Screen {

	private int resX;
	private int resY;
	private ZBuffer zBuffer;

	private LinkedList<Pixel> pixelsToBeDrawn;
	private Object pixelsToBeDrawnMonitor;

	public Screen(int resX, int resY) {
		// TODO implement this constructor

		this.pixelsToBeDrawn = new LinkedList<>();
		this.pixelsToBeDrawnMonitor = new Object();

		this.resX = resX;
		this.resY = resY;
		this.zBuffer = new ZBuffer(resX, resY);
	}

	public void drawPixel(Pixel pixel) {
		synchronized (pixelsToBeDrawnMonitor) {
			this.pixelsToBeDrawn.add(pixel);
		}

	}

	public void paintScreen(Graphics g) {
		synchronized (pixelsToBeDrawnMonitor) {
			
			// Painting all pixels that were scheduled to be painted
			for (Pixel aux : pixelsToBeDrawn) {

				g.setColor(aux.getCor());
				g.drawLine((int) aux.getX(), (int) aux.getY(),
						(int) aux.getX(), (int) aux.getY());

			}
			
			

		}
	}
	
	public void clear(){
		this.pixelsToBeDrawn = new LinkedList<Pixel>();
		this.zBuffer.clear();
	}

	public int getResX() {
		return resX;
	}

	public void setResX(int resX) {
		this.resX = resX;
	}

	public int getResY() {
		return resY;
	}

	public void setResY(int resY) {
		this.resY = resY;
	}

	public ZBuffer getzBuffer() {
		return zBuffer;
	}

	public void setzBuffer(ZBuffer zBuffer) {
		this.zBuffer = zBuffer;
	}

}
