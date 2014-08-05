package fachada;

import java.io.FileNotFoundException;

import state.Screen;
import state.World;

import data.Camera;
import data.Pixel;
import data.Triangulo;
import io.ArquivoCamera;
import io.ArquivoIluminacao;
import io.ArquivoObjeto;

public class Fachada {
	//Data
	private World world;
	private Screen screen;
	
	//Input file locations
	private String pathIluminacao;
	private String pathObjeto;
	private String pathCamera;
	
	public Fachada(Screen screen){
		this.world = new World();
		this.screen = screen;
		
		this.pathIluminacao = null;
		this.pathObjeto = null;
		this.pathCamera = null;
	}

	public void loadFiles(String pathIluminacao, String pathObjeto, String pathCamera) throws FileNotFoundException{
		updateFileCamera(pathCamera);		

		updateFileIluminacao(pathIluminacao, this.world.getCamera());
		
		updateFileObjeto(pathObjeto);
		
	}

	public void updateFileIluminacao(String pathIluminacao, Camera camera) throws FileNotFoundException{
		ArquivoIluminacao readerIlum = new ArquivoIluminacao(pathIluminacao, camera);
		this.world.setLight(readerIlum.getIluminacao());
		this.pathIluminacao = pathIluminacao;
	}

	public void updateFileCamera(String pathCamera) throws FileNotFoundException{
		//Reading the file and storing the newly (already calibrated)
		ArquivoCamera readerCam = new ArquivoCamera(pathCamera);
		this.world.setCamera(readerCam.getCamera());
		this.pathCamera = pathCamera;
	}

	public void updateFileObjeto(String pathObjeto) throws FileNotFoundException{
		ArquivoObjeto readerObj = new ArquivoObjeto(pathObjeto, this.world.getCamera(), this.screen.getResX(), this.screen.getResY());
		this.world.setObject( readerObj.getObject());
		this.pathObjeto = pathObjeto;
		
	}
	
	public void refreshFileObjeto() throws FileNotFoundException{
		this.updateFileObjeto(this.pathObjeto);
	}
	
	public void refreshFileIluminacao() throws FileNotFoundException{
		this.updateFileIluminacao(this.pathIluminacao, this.world.getCamera());
	}
	
	public void fillAllTriangles(){
		Triangulo[] triangulos = this.world.getObject().getArrayTriangulos();
		//ScanLine:
		//int contador = 0;
		for(Triangulo triangulo : triangulos){
		//	contador++;
		//	System.out.println("Contador = " + contador);
			triangulo.scanLine(this.screen, this.world.getLight(), this.world.getCamera());
			
		}				
	}
	
	public void drawAllVertexes(){
		Pixel[] pixels = this.world.getObject().getArrayPixel();
		for (Pixel pixel : pixels) {
			this.screen.drawPixel(pixel);
		}
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public String getPathIluminacao() {
		return pathIluminacao;
	}

	public void setPathIluminacao(String pathIluminacao) {
		this.pathIluminacao = pathIluminacao;
	}

	public String getPathObjeto() {
		return pathObjeto;
	}

	public void setPathObjeto(String pathObjeto) {
		this.pathObjeto = pathObjeto;
	}

	public String getPathCamera() {
		return pathCamera;
	}

	public void setPathCamera(String pathCamera) {
		this.pathCamera = pathCamera;
	}
	
	
	
	
	

}
